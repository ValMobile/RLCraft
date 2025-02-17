/*
    Copyright (C) 2025  Val_Mobile

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package me.val_mobile.utils;

import me.val_mobile.data.RSVModule;
import me.val_mobile.data.RSVPlayer;
import me.val_mobile.iceandfire.IceFireModule;
import me.val_mobile.integrations.CompatiblePlugin;
import me.val_mobile.integrations.RealisticSeasons;
import me.val_mobile.rsv.RSVPlugin;
import me.val_mobile.tan.TanModule;
import me.val_mobile.tan.TempManager;
import me.val_mobile.tan.ThirstManager;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class DisplayTask extends BukkitRunnable implements RSVTask {

    private static final Map<UUID, DisplayTask> tasks = new HashMap<>();
    private final UUID id;
    private final FileConfiguration tanConfig;
    private final FileConfiguration ifConfig;
    private final RSVPlugin plugin;
    private final RSVPlayer player;
    private final CharacterValues characterValues;
    private boolean underSirenEffect = false;
    private boolean parasitesActive = false;
    private final TanModule tanModule;
    private final IceFireModule ifModule;
    private final RealisticSeasons rs;

    public DisplayTask(RSVPlugin plugin, RSVPlayer player) {
        this.plugin = plugin;

        this.tanModule = (TanModule) RSVModule.getModule(TanModule.NAME);
        this.ifModule = (IceFireModule) RSVModule.getModule(IceFireModule.NAME);

        this.tanConfig = tanModule.isGloballyEnabled() ? tanModule.getUserConfig().getConfig() : null;
        this.ifConfig = ifModule.isGloballyEnabled() ? ifModule.getUserConfig().getConfig() : null;
        this.player = player;
        this.characterValues = new CharacterValues();
        this.id = player.getPlayer().getUniqueId();
        this.rs = (RealisticSeasons) CompatiblePlugin.getPlugin(RealisticSeasons.NAME);
        tasks.put(id, this);
    }

    @Override
    public void run() {
        Player player = this.player.getPlayer();

        if (globalConditionsMet(player)) {
            String actionbarText = "";
            String titleText = "";

            if (ifConfig != null && ifModule.getAllowedWorlds().contains(player.getWorld().getName())) {
                if (!player.hasPermission("realisticsurvival.iceandfire.resistance.sirenvisual")) {
                    if (underSirenEffect) {
                        if (ifConfig.getBoolean("Siren.ChangeScreen.Enabled")) {
                            titleText += characterValues.getSirenView(player);
                        }
                    }
                }
            }

            if (tanConfig != null) {
                TempManager tempManager = tanModule.getTempManager();
                ThirstManager thirstManager = tanModule.getThirstManager();
                double temperature = tempManager.getTemperature(player);
                double thirst = thirstManager.getThirst(player);

                boolean isUnderwater = player.getRemainingAir() < 300 || player.getEyeLocation().getBlock().getType() == Material.WATER;

                if (tempManager.isTempEnabled(player) && thirstManager.isThirstEnabled(player)) {
                    actionbarText += characterValues.getTemperatureThirstActionbar(player, (int) Math.round(temperature), (int) Math.round(thirst), isUnderwater, parasitesActive);
                }
                else {
                    // only temperature is enabled
                    if (tempManager.isTempEnabled(player)) {
                        actionbarText += characterValues.getTemperatureOnlyActionbar(player, (int) Math.round(temperature));
                    }
                    // only thirst is enabled
                    else {
                        actionbarText += characterValues.getThirstOnlyActionbar(player, (int) Math.round(thirst), isUnderwater, parasitesActive);
                    }
                }

                if (temperature < 6) {
                    if (tanConfig.getBoolean("Temperature.Hypothermia.ScreenTinting.Enabled") && !rs.disableHypothermiaTinting()) {
                        if (!player.hasPermission("realisticsurvival.toughasnails.resistance.cold.visual")) {
                            if (tanConfig.getBoolean("Temperature.Hypothermia.ScreenTinting.UseVanillaFreezeEffect")) {
                                Utils.setFreezingView(player, tanConfig.getInt("VisualTickPeriod") + 5);
                            }
                            else {
                                titleText += characterValues.getIceVignette(player, (int) Math.round(temperature));
                            }
                        }
                    }
                }
                if (temperature > 19) {
                    if (tanConfig.getBoolean("Temperature.Hyperthermia.ScreenTinting") && !rs.disableHyperthermiaTinting()) {
                        if (!player.hasPermission("realisticsurvival.toughasnails.resistance.hot.visual")) {
                            titleText += characterValues.getFireVignette(player, (int) Math.round(temperature));
                        }
                    }
                }

                if (thirst < 5) {
                    if (tanConfig.getBoolean("Thirst.Dehydration.ScreenTinting")) {
                        if (!player.hasPermission("realisticsurvival.toughasnails.resistance.thirst.visual")) {
                            titleText += characterValues.getThirstVignette(player, (int) Math.round(thirst));
                        }
                    }
                }
            }

            if (!actionbarText.isEmpty()) {
                player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(Utils.translateMsg(actionbarText, player, null)));
            }

            if (!titleText.isEmpty()) {
                player.sendTitle(Utils.translateMsg(titleText, player, null), "", 0, 70, 0);
            }
        }
        else {
            stop();
        }
    }

    @Override
    public boolean conditionsMet(@Nullable Player player) {
        return globalConditionsMet(player);
    }

    @Override
    public void start() {
        int tickPeriod = tanConfig.getInt("VisualTickPeriod"); // get the tick period
        this.runTaskTimer(plugin, 0L, tickPeriod);
    }

    @Override
    public void stop() {
        tasks.remove(id);
        cancel();
    }

    public void setUnderSirenEffect(boolean underSirenEffect) {
        this.underSirenEffect = underSirenEffect;
    }

    public void setParasitesActive(boolean parasitesActive) {
        this.parasitesActive = parasitesActive;
    }

    public static boolean hasTask(UUID id) {
        return tasks.containsKey(id) && tasks.get(id) != null;
    }

    public static Map<UUID, DisplayTask> getTasks() {
        return tasks;
    }
}
