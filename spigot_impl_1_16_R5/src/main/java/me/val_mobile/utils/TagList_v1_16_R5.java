/*
    Copyright (C) 2023  Val_Mobile

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

import org.bukkit.Material;
import org.bukkit.Tag;

public enum TagList_v1_16_R5 {

    ACACIA_LOGS(Tag.ACACIA_LOGS),
    ANVIL(Tag.ANVIL),
    BAMBOO_PLANTABLE_ON(Tag.BAMBOO_PLANTABLE_ON),
    BANNERS(Tag.BANNERS),
    BEACON_BASE_BLOCKS(Tag.BEACON_BASE_BLOCKS),
    BEDS(Tag.BEDS),
    BEE_GROWABLES(Tag.BEE_GROWABLES),
    BEEHIVES(Tag.BEEHIVES),
    BIRCH_LOGS(Tag.BIRCH_LOGS),
    BUTTONS(Tag.BUTTONS),
    CAMPFIRES(Tag.CAMPFIRES),
    CARPETS(Tag.CARPETS),
    CLIMBABLE(Tag.CLIMBABLE),
    CORAL_BLOCKS(Tag.CORAL_BLOCKS),
    CORAL_PLANTS(Tag.CORAL_PLANTS),
    CORALS(Tag.CORALS),
    CRIMSON_STEMS(Tag.CRIMSON_STEMS),
    CROPS(Tag.CROPS),
    DARK_OAK_LOGS(Tag.DARK_OAK_LOGS),
    DOORS(Tag.DOORS),
    DRAGON_IMMUNE(Tag.DRAGON_IMMUNE),
    ENDERMAN_HOLDABLE(Tag.ENDERMAN_HOLDABLE),
    FENCE_GATES(Tag.FENCE_GATES),
    FENCES(Tag.FENCES),
    FIRE(Tag.FIRE),
    FLOWER_POTS(Tag.FLOWER_POTS),
    FLOWERS(Tag.FLOWERS),
    GOLD_ORES(Tag.GOLD_ORES),
    GUARDED_BY_PIGLINS(Tag.GUARDED_BY_PIGLINS),
    HOGLIN_REPELLENTS(Tag.HOGLIN_REPELLENTS),
    ICE(Tag.ICE),
    IMPERMEABLE(Tag.IMPERMEABLE),
    INFINIBURN_END(Tag.INFINIBURN_END),
    INFINIBURN_NETHER(Tag.INFINIBURN_NETHER),
    INFINIBURN_OVERWORLD(Tag.INFINIBURN_OVERWORLD),
    ITEMS_ARROWS(Tag.ITEMS_ARROWS),
    ITEMS_BANNERS(Tag.ITEMS_BANNERS),
    ITEMS_BEACON_PAYMENT_ITEMS(Tag.ITEMS_BEACON_PAYMENT_ITEMS),
    ITEMS_BOATS(Tag.ITEMS_BOATS),
    ITEMS_COALS(Tag.ITEMS_COALS),
    ITEMS_CREEPER_DROP_MUSIC_DISCS(Tag.ITEMS_CREEPER_DROP_MUSIC_DISCS),
    ITEMS_FISHES(Tag.ITEMS_FISHES),
    ITEMS_FURNACE_MATERIALS(Tag.ITEMS_FURNACE_MATERIALS),
    ITEMS_LECTURM_BOOKS(Tag.ITEMS_LECTERN_BOOKS),
    ITEMS_MUSIC_DISCS(Tag.ITEMS_MUSIC_DISCS),
    ITEMS_PIGLIN_LOVED(Tag.ITEMS_PIGLIN_LOVED),
    ITEMS_STONE_TOOL_MATERIALS(Tag.ITEMS_STONE_TOOL_MATERIALS),
    JUNGLE_LOGS(Tag.JUNGLE_LOGS),
    LEAVES(Tag.LEAVES),
    LOGS(Tag.LOGS),
    LOGS_THAT_BURN(Tag.LOGS_THAT_BURN),
    NON_FLAMMABLE_WOOD(Tag.NON_FLAMMABLE_WOOD),
    NYLIUM(Tag.NYLIUM),
    OAK_LOGS(Tag.OAK_LOGS),
    PIGLIN_REPELLENTS(Tag.PIGLIN_REPELLENTS),
    PLANKS(Tag.PLANKS),
    PORTALS(Tag.PORTALS),
    PRESSURE_PLATES(Tag.PRESSURE_PLATES),
    PREVENT_MOB_SPAWNING_INSIDE(Tag.PREVENT_MOB_SPAWNING_INSIDE),
    RAILS(Tag.RAILS),
    SAND(Tag.SAND),
    SAPLINGS(Tag.SAPLINGS),
    SHULKER_BOXES(Tag.SHULKER_BOXES),
    SIGNS(Tag.SIGNS),
    SLABS(Tag.SLABS),
    SMALL_FLOWERS(Tag.SMALL_FLOWERS),
    SOUL_FIRE_BASE_BLOCKS(Tag.SOUL_FIRE_BASE_BLOCKS),
    SOUL_SPEED_BLOCKS(Tag.SOUL_SPEED_BLOCKS),
    SPRUCE_LOGS(Tag.SPRUCE_LOGS),
    STAIRS(Tag.STAIRS),
    STANDING_SIGNS(Tag.STANDING_SIGNS),
    STONE_BRICKS(Tag.STONE_BRICKS),
    STONE_PRESSURE_PLATES(Tag.STONE_PRESSURE_PLATES),
    STRIDER_WARM_BLOCKS(Tag.STRIDER_WARM_BLOCKS),
    TALL_FLOWERS(Tag.TALL_FLOWERS),
    TRAPDOORS(Tag.TRAPDOORS),
    UNDERWATER_BONEMEALS(Tag.UNDERWATER_BONEMEALS),
    UNSTABLE_BOTTOM_CENTER(Tag.UNSTABLE_BOTTOM_CENTER),
    VALID_SPAWN(Tag.VALID_SPAWN),
    WALL_CORALS(Tag.WALL_CORALS),
    WALL_POST_OVERRIDE(Tag.WALL_POST_OVERRIDE),
    WALL_SIGNS(Tag.WALL_SIGNS),
    WALLS(Tag.WALLS),
    WARPED_STEMS(Tag.WARPED_STEMS),
    WART_BLOCKS(Tag.WART_BLOCKS),
    WITHER_IMMUNE(Tag.WITHER_IMMUNE),
    WITHER_SUMMON_BASE_BLOCKS(Tag.WITHER_SUMMON_BASE_BLOCKS),
    WOODEN_BUTTONS(Tag.WOODEN_BUTTONS),
    WOODEN_DOORS(Tag.WOODEN_DOORS),
    WOODEN_FENCES(Tag.WOODEN_FENCES),
    WOODEN_PRESSURE_PLATES(Tag.WOODEN_PRESSURE_PLATES),
    WOODEN_SLABS(Tag.WOODEN_SLABS),
    WOODEN_STAIRS(Tag.STAIRS),
    WOODEN_TRAPDOORS(Tag.WOODEN_TRAPDOORS),
    WOOL(Tag.WOOL);

    private final Tag<Material> tag;

    TagList_v1_16_R5(Tag<Material> tag) {
        this.tag = tag;
    }

    public Tag<Material> getTag() {
        return tag;
    }
}
