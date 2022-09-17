package me.val_mobile.utils;

import com.mojang.datafixers.DataFixUtils;
import com.mojang.datafixers.types.Type;
import net.minecraft.server.v1_16_R1.*;
import org.bukkit.entity.EntityType;

import java.lang.reflect.Field;
import java.util.Map;

public enum CustomEntities {

    ENDERMAN_ALLY("enderman_ally", EntityType.ENDERMAN.getTypeId(), EntityTypes.ENDERMAN, EntityEnderman.class, EndermanAlly.class),
    FIRE_DRAGON("fire_dragon", EntityType.ENDER_DRAGON.getTypeId(), EntityTypes.ENDER_DRAGON, EntityEnderDragon.class, FireDragon.class),
    ICE_DRAGON("ice_dragon", EntityType.ENDER_DRAGON.getTypeId(), EntityTypes.ENDER_DRAGON, EntityEnderDragon.class, IceDragon.class),
    LIGHTNING_DRAGON("lightning_dragon", EntityType.ENDER_DRAGON.getTypeId(), EntityTypes.ENDER_DRAGON, EntityEnderDragon.class, LightningDragon.class),
    SEA_SERPENT("sea_serpent", EntityType.ELDER_GUARDIAN.getTypeId(), EntityTypes.ELDER_GUARDIAN, EntityGuardianElder.class, SeaSerpent.class),
    SIREN("siren", EntityType.GUARDIAN.getTypeId(), EntityTypes.GUARDIAN, EntityGuardian.class, Siren.class);

    private String name;
    private int id;
    private EntityTypes<?> entityType;
    private MinecraftKey minecraftKey;
    private Class<? extends EntityInsentient> nmsClass;
    private Class<? extends net.minecraft.server.v1_16_R1.Entity> customClass;

    CustomEntities(String name, int id, EntityTypes entityType, Class<? extends EntityInsentient> nmsClass,
                   Class<? extends net.minecraft.server.v1_16_R1.Entity> customClass) {
        this.name = name;
        this.id = id;
        this.entityType = entityType;
        this.minecraftKey = new MinecraftKey(name);
        this.nmsClass = nmsClass;
        this.customClass = customClass;
    }

    public static void registerEntities() {
        Map<String, Type<?>> types = (Map<String, Type<?>>) DataConverterRegistry.a().getSchema(DataFixUtils.makeKey(SharedConstants.getGameVersion().getWorldVersion())).findChoiceType(DataConverterTypes.ENTITY).types();
//        unfreezeRegistry();
        registerEntity("fire_dragon", FireDragon::new, types);
        registerEntity("ice_dragon", IceDragon::new, types);
        registerEntity("lightning_dragon", LightningDragon::new, types);
        registerEntity("enderman_ally", EndermanAlly::new, types);
        registerEntity("sea_serpent", SeaSerpent::new, types);
        registerEntity("siren", Siren::new, types);
//        Registry.ENTITY_TYPE.freeze();
    }

    private static void registerEntity(String type, EntityTypes.b customMob, Map<String, Type<?>> types) {
        if (!IRegistry.ENTITY_TYPE.getOptional(new MinecraftKey(type)).isPresent()) {
            String customName = "minecraft:realisticsurvival_" + type;
            types.put(customName, types.get("minecraft:" + type));
            EntityTypes.Builder<net.minecraft.server.v1_16_R1.Entity> a = EntityTypes.Builder.a(customMob, EnumCreatureType.MONSTER);
            IRegistry.a(IRegistry.ENTITY_TYPE, customName, a.a(customName));
        }
    }

//    private static void unfreezeRegistry() {
//        Class<MappedRegistry> registryClass = MappedRegistry.class;
//        try {
//            Field intrusiveHolderCache = registryClass.getDeclaredField(ObfuscatedFields.INTRUSIVE_HOLDER_CACHE);
//            intrusiveHolderCache.setAccessible(true);
//            intrusiveHolderCache.set(Registry.ENTITY_TYPE, new IdentityHashMap<EntityType<?>, Holder.Reference<EntityType<?>>>());
//            Field frozen = registryClass.getDeclaredField(ObfuscatedFields.FROZEN);
//            frozen.setAccessible(true);
//            frozen.set(Registry.ENTITY_TYPE, false);
//        } catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
//            e.printStackTrace();
//        }
//    }

    public static void unregisterEntities() {}

    public static Object getPrivateField(Class<?> clazz, Object handle, String fieldName) throws Exception {
        Field field = clazz.getDeclaredField(fieldName);
        field.setAccessible(true);
        return field.get(handle);
    }

    public String getName() {
        return name;
    }

    public int getID() {
        return id;
    }

    public EntityTypes getEntityTypes() {
        return entityType;
    }

    public MinecraftKey getMinecraftKey() {
        return minecraftKey;
    }

    public Class<? extends EntityInsentient> getNMSClass() {
        return nmsClass;
    }

    public Class<? extends Entity> getCustomClass() {
        return customClass;
    }

}
