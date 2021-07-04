package me.val_mobile.rlcraft;

import de.tr7zw.nbtapi.NBTItem;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class ItemAbilities {

    private final RLCraft plugin;
    private final ItemRunnables itemRunnables;
    private final Utils util;
    private final CustomConfig customConfig;
    public ItemAbilities(RLCraft instance) {
        plugin = instance;
        util = new Utils(instance);
        customConfig = new CustomConfig(instance);
        itemRunnables = new ItemRunnables(instance);
    }

    public void FireDragonBoneAbility(LivingEntity entity) {

        int fireTicks = customConfig.getIceFireGearConfig().getInt("Abilities.FlamedDragonBone.FireTicks");

        if (entity.getFireTicks() < fireTicks) {
            entity.setFireTicks(fireTicks);
        }

    }

    public void FireDragonsteelAbility(LivingEntity entity) {

        int fireTicks = customConfig.getIceFireGearConfig().getInt("Abilities.FireDragonsteel.FireTicks");

        if (entity.getFireTicks() < fireTicks) {
            entity.setFireTicks(fireTicks);
        }

    }

    public void IceDragonBoneAbility(LivingEntity entity) {

        int amplifier = customConfig.getIceFireGearConfig().getInt("Abilities.IcedDragonBone.Slowness.Amplifier");
        int duration = customConfig.getIceFireGearConfig().getInt("Abilities.IcedDragonBone.Slowness.Duration");
        PotionEffect slowness = new PotionEffect(PotionEffectType.SLOW, duration, amplifier);
        util.addOrStackPotionEffect(entity, slowness);

        itemRunnables.freezeEntity(entity).runTaskLater(plugin, 1);

        Location loc = entity.getLocation();
        FallingBlock block = entity.getWorld().spawnFallingBlock(loc, Material.ICE.createBlockData());
        FallingBlock block2;
        FallingBlock block3;

        block.setDropItem(false);
        block.setHurtEntities(false);
        block.setGravity(false);
        block.setCustomName("Ice Dragon Bone Block");

        itemRunnables.iceDragonBoneBlocks.add(block);

        if (entity.getHeight() >= 1.5) {
            if (entity.getHeight() >= 2.5) {
                loc.setY(loc.getY() + 1.0D);
                block2 = entity.getWorld().spawnFallingBlock(loc, Material.ICE.createBlockData());
                loc.setY(loc.getY() + 1.0D);
                block3 = entity.getWorld().spawnFallingBlock(loc, Material.ICE.createBlockData());

                block2.setDropItem(false);
                block2.setHurtEntities(false);
                block2.setGravity(false);
                block2.setCustomName("Ice Dragon Bone Block");

                block3.setDropItem(false);
                block3.setHurtEntities(false);
                block3.setGravity(false);
                block3.setCustomName("Ice Dragon Bone Block");

                itemRunnables.iceDragonBoneBlocks.add(block2);
                itemRunnables.iceDragonBoneBlocks.add(block3);
            }
            else {
                loc.setY(loc.getY() + 1.0D);
                block2 = entity.getWorld().spawnFallingBlock(loc, Material.ICE.createBlockData());

                block2.setDropItem(false);
                block2.setHurtEntities(false);
                block2.setGravity(false);
                block2.setCustomName("Ice Dragon Bone Block");

                itemRunnables.iceDragonBoneBlocks.add(block2);
            }
        }

        entity.getWorld().playSound(loc, Sound.BLOCK_GLASS_BREAK, 1, 1);
        itemRunnables.removeIceDragonBoneBlocks().runTaskLater(plugin, customConfig.getIceFireGearConfig().getInt("Abilities.IcedDragonBone.FrozenDuration"));

    }

    public void IceDragonsteelAbility(LivingEntity entity) {

        int amplifier = customConfig.getIceFireGearConfig().getInt("Abilities.IceDragonsteel.Slowness.Amplifier");
        int duration = customConfig.getIceFireGearConfig().getInt("Abilities.IceDragonsteel.Slowness.Duration");

        PotionEffect slowness = new PotionEffect(PotionEffectType.SLOW, duration, amplifier);
        util.addOrStackPotionEffect(entity, slowness);

        itemRunnables.freezeEntity(entity).runTaskLater(plugin, 1);

        Location loc = entity.getLocation();
        FallingBlock block = entity.getWorld().spawnFallingBlock(loc, Material.BLUE_ICE.createBlockData());
        FallingBlock block2;
        FallingBlock block3;

        block.setDropItem(false);
        block.setHurtEntities(false);
        block.setGravity(false);
        block.setCustomName("Ice Dragonsteel Block");

        itemRunnables.iceDragonsteelBlocks.add(block);

        if (entity.getHeight() >= 1.5) {
            if (entity.getHeight() >= 2.5) {
                loc.setY(loc.getY() + 1.0D);
                block2 = entity.getWorld().spawnFallingBlock(loc, Material.BLUE_ICE.createBlockData());
                loc.setY(loc.getY() + 1.0D);
                block3 = entity.getWorld().spawnFallingBlock(loc, Material.BLUE_ICE.createBlockData());

                block2.setDropItem(false);
                block2.setHurtEntities(false);
                block2.setGravity(false);
                block2.setCustomName("Ice Dragonsteel Block");

                block3.setDropItem(false);
                block3.setHurtEntities(false);
                block3.setGravity(false);
                block3.setCustomName("Ice Dragonsteel Block");

                itemRunnables.iceDragonsteelBlocks.add(block2);
                itemRunnables.iceDragonsteelBlocks.add(block3);
            }
            else {
                loc.setY(loc.getY() + 1.0D);
                block2 = entity.getWorld().spawnFallingBlock(loc, Material.BLUE_ICE.createBlockData());

                block2.setDropItem(false);
                block2.setHurtEntities(false);
                block2.setGravity(false);
                block2.setCustomName("Ice Dragonsteel Block");

                itemRunnables.iceDragonsteelBlocks.add(block2);
            }
        }
        entity.getWorld().playSound(loc, Sound.BLOCK_GLASS_BREAK, 1, 1);
        itemRunnables.removeIceDragonsteelBlocks().runTaskLater(plugin, customConfig.getIceFireGearConfig().getInt("Abilities.IceDragonsteel.FrozenDuration"));

    }

    public void LightningDragonBoneAbility(LivingEntity entity, LivingEntity source) {
        entity.getWorld().strikeLightning(entity.getLocation());
        itemRunnables.shockEntity(entity, source).runTaskTimer(plugin, 0L, 1);
    }

    public void LightningDragonsteelAbility(LivingEntity entity, LivingEntity source) {
        entity.getWorld().strikeLightning(entity.getLocation());
        itemRunnables.electrocuteEntity(entity, source).runTaskTimer(plugin, 0L, 1);
    }

    public boolean hasArmor(LivingEntity entity) {
        ItemStack[] armor = entity.getEquipment().getArmorContents();
        for (ItemStack item : armor) {
            if (! (item == null || item.getType() == Material.AIR) ) {
                return true;
            }
        }
        return false;
    }

    public boolean hasChestplate(LivingEntity entity) {
        ItemStack chestplate = entity.getEquipment().getChestplate();
        if (! (chestplate == null || chestplate.getType() == Material.AIR)) {
            return true;
        }
        return false;
    }

    public boolean isHoldingTwoHandedWeapon(LivingEntity entity) {
        ItemStack item = entity.getEquipment().getItemInMainHand();
        if (! (item == null || item.getType() == Material.AIR)) {
            NBTItem nbti = new NBTItem(item);
            if (nbti.hasKey("Spartan's Weapon")) {
                switch (nbti.getString("Spartan's Weapon")) {
                    case "Katana":
                    case "Glaive":
                    case "Quarterstaff":
                    case "Battleaxe":
                    case "Warhammer":
                    case "Halberd":
                    case "Pike":
                    case "Longsword":
                    case "Greatsword":
                        return true;
                }
                return false;
            }
            return false;

        }
        return false;
    }

    public boolean checkTwoHandedDebuff(LivingEntity entity) {
        if (isHoldingTwoHandedWeapon(entity)) {
            ItemStack item = entity.getEquipment().getItemInOffHand();
            if (! (item == null || item.getType() == Material.AIR)) {
                return true;
            }
            return false;
        }
        return false;
    }

    public void applyTwoHandedDebuff(LivingEntity entity) {
        PotionEffect effect = new PotionEffect(PotionEffectType.SLOW_DIGGING, 30, 0);
    }
}
