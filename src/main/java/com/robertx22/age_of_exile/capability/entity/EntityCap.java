package com.robertx22.age_of_exile.capability.entity;

import com.robertx22.age_of_exile.areas.AreaData;
import com.robertx22.age_of_exile.capability.bases.EntityGears;
import com.robertx22.age_of_exile.capability.bases.ICommonPlayerCap;
import com.robertx22.age_of_exile.capability.bases.INeededForClient;
import com.robertx22.age_of_exile.config.forge.ModConfig;
import com.robertx22.age_of_exile.database.base.Rarities;
import com.robertx22.age_of_exile.database.data.EntityConfig;
import com.robertx22.age_of_exile.database.data.gear_types.bases.BaseGearType;
import com.robertx22.age_of_exile.database.data.rarities.MobRarity;
import com.robertx22.age_of_exile.database.data.rarities.mobs.BossMob;
import com.robertx22.age_of_exile.database.data.stats.types.generated.WeaponDamage;
import com.robertx22.age_of_exile.database.data.tiers.base.Tier;
import com.robertx22.age_of_exile.database.registry.SlashRegistry;
import com.robertx22.age_of_exile.event_hooks.entity.damage.DamageEventData;
import com.robertx22.age_of_exile.event_hooks.player.OnLogin;
import com.robertx22.age_of_exile.mmorpg.ModRegistry;
import com.robertx22.age_of_exile.mmorpg.Ref;
import com.robertx22.age_of_exile.saveclasses.CustomExactStatsData;
import com.robertx22.age_of_exile.saveclasses.item_classes.GearItemData;
import com.robertx22.age_of_exile.saveclasses.unit.ResourcesData;
import com.robertx22.age_of_exile.saveclasses.unit.Unit;
import com.robertx22.age_of_exile.uncommon.datasaving.CustomExactStats;
import com.robertx22.age_of_exile.uncommon.datasaving.Gear;
import com.robertx22.age_of_exile.uncommon.datasaving.UnitNbt;
import com.robertx22.age_of_exile.uncommon.effectdatas.DamageEffect;
import com.robertx22.age_of_exile.uncommon.effectdatas.EffectData;
import com.robertx22.age_of_exile.uncommon.effectdatas.interfaces.WeaponTypes;
import com.robertx22.age_of_exile.uncommon.enumclasses.Elements;
import com.robertx22.age_of_exile.uncommon.localization.Chats;
import com.robertx22.age_of_exile.uncommon.utilityclasses.EntityTypeUtils;
import com.robertx22.age_of_exile.uncommon.utilityclasses.LevelUtils;
import com.robertx22.age_of_exile.uncommon.utilityclasses.OnScreenMessageUtils;
import com.robertx22.age_of_exile.uncommon.utilityclasses.PlayerUtils;
import com.robertx22.age_of_exile.vanilla_mc.packets.sync_cap.PlayerCaps;
import com.robertx22.age_of_exile.vanilla_mc.potion_effects.bases.EntityStatusEffectsData;
import com.robertx22.library_of_exile.main.Packets;
import com.robertx22.library_of_exile.utils.LoadSave;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.text.LiteralText;
import net.minecraft.text.MutableText;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.registry.Registry;

import java.util.Random;
import java.util.UUID;

public class EntityCap {

    public static final Identifier RESOURCE = new Identifier(Ref.MODID, "entitydata");

    private static final String RARITY = "rarity";
    private static final String LEVEL = "level";
    private static final String EXP = "exp";
    private static final String UUID = "uuid";
    private static final String MOB_SAVED_ONCE = "mob_saved_once";
    private static final String SET_MOB_STATS = "set_mob_stats";
    private static final String NEWBIE_STATUS = "is_a_newbie";
    private static final String EQUIPS_CHANGED = "EQUIPS_CHANGED";
    private static final String TIER = "TIER";
    private static final String PREVENT_LOOT = "PREVENT_LOOT";
    private static final String SHOULD_SYNC = "SHOULD_SYNC";
    private static final String ENTITY_TYPE = "ENTITY_TYPE";
    private static final String RESOURCES_LOC = "RESOURCES_LOC";

    public interface UnitData extends ICommonPlayerCap, INeededForClient {

        void onDamagedBy(LivingEntity entity, float dmg, LivingEntity self);

        EntityStatusEffectsData getStatusEffectsData();

        void modifyResource(ResourcesData.Context ctx);

        void onDeath(LivingEntity en);

        void setType(LivingEntity en);

        EntityTypeUtils.EntityClassification getType();

        void trySync(LivingEntity entity);

        void onAttackEntity(LivingEntity attacker, LivingEntity victim);

        GearItemData setupWeaponData(LivingEntity entity);

        void setEquipsChanged(boolean bool);

        boolean isNewbie();

        void setNewbieStatus(boolean bool);

        boolean needsToBeGivenStats();

        boolean increaseRarity(LivingEntity entity);

        Unit getUnit();

        void setUnit(Unit unit, LivingEntity entity);

        void setRarity(int rarity);

        int getRarity();

        String getUUID();

        void setUUID(UUID id);

        MutableText getName(LivingEntity entity);

        void tryRecalculateStats(LivingEntity entity);

        void forceRecalculateStats(LivingEntity entity, DamageEventData data);

        void forceRecalculateStats(LivingEntity entity);

        void forceSetUnit(Unit unit);

        boolean tryUseWeapon(GearItemData gear, LivingEntity entity);

        boolean tryUseWeapon(GearItemData gear, LivingEntity entity, float multi);

        void onLogin(PlayerEntity player);

        boolean decreaseRarity(LivingEntity entity);

        boolean isWeapon(GearItemData gear);

        void setTier(int tier);

        int getTier();

        Tier getMapTier();

        CustomExactStatsData getCustomExactStats();

        ResourcesData getResources();

        float getCurrentMana();

        void mobStatsAreSet();

        void attackWithWeapon(DamageEventData data);

        void unarmedAttack(DamageEventData data);

        void mobBasicAttack(DamageEventData data);

        int getLevel();

        void setLevel(int lvl, LivingEntity entity);

        boolean CheckIfCanLevelUp();

        boolean LevelUp(PlayerEntity player);

        boolean CheckLevelCap();

        void SetMobLevelAtSpawn(LivingEntity entity, PlayerEntity player);

        int getExp();

        void setExp(int exp);

        int GiveExp(PlayerEntity player, int i);

        int getExpRequiredForLevelUp();

        EntityGears getCurrentGears();

        AreaData getArea();

        void setArea(AreaData area);
    }

    public static class DefaultImpl implements UnitData {

        LivingEntity entity;

        //dont save this
        EntityGears gears = new EntityGears();
        // dont

        // sync these for mobs
        Unit unit = new Unit();
        int rarity = 0;
        int level = 1;
        int exp = 0;

        String areaID = "";

        EntityTypeUtils.EntityClassification type = EntityTypeUtils.EntityClassification.PLAYER;
        // sync these for mobs

        boolean setMobStats = false;
        String uuid = "";
        boolean isNewbie = true;
        boolean equipsChanged = true;
        int tier = 0;
        boolean shouldSync = false;

        ResourcesData resources = new ResourcesData();
        CustomExactStatsData customExactStats = new CustomExactStatsData();

        public DefaultImpl(LivingEntity entity) {
            this.entity = entity;
        }

        @Override
        public void addClientNBT(CompoundTag nbt) {

            nbt.putInt(LEVEL, level);
            nbt.putInt(RARITY, rarity);
            nbt.putString(ENTITY_TYPE, this.type.toString());
            nbt.putString("area", areaID);

            if (unit != null) {
                // System.out.println(unit.getStats().size()); for testing if mobs get all stats or only ones they need

                UnitNbt.Save(nbt, unit);
            }

        }

        @Override
        public void loadFromClientNBT(CompoundTag nbt) {

            this.rarity = nbt.getInt(RARITY);
            this.level = nbt.getInt(LEVEL);
            this.areaID = nbt.getString("area");

            try {
                String typestring = nbt.getString(ENTITY_TYPE);
                this.type = EntityTypeUtils.EntityClassification.valueOf(typestring);
            } catch (Exception e) {
                this.type = EntityTypeUtils.EntityClassification.OTHER;
                //if no nbt, set to default. Then at spawn, set correctly
            }

            this.unit = UnitNbt.Load(nbt);
            if (this.unit == null) {
                this.unit = new Unit();
            }
        }

        @Override
        public CompoundTag toTag(CompoundTag nbt) {

            addClientNBT(nbt);

            nbt.putInt(EXP, exp);
            nbt.putInt(TIER, tier);
            nbt.putString(UUID, uuid);
            nbt.putBoolean(MOB_SAVED_ONCE, true);
            nbt.putBoolean(SET_MOB_STATS, setMobStats);
            nbt.putBoolean(NEWBIE_STATUS, this.isNewbie);
            nbt.putBoolean(EQUIPS_CHANGED, equipsChanged);
            nbt.putBoolean(SHOULD_SYNC, shouldSync);

            if (customExactStats != null) {
                CustomExactStats.Save(nbt, customExactStats);
            }

            if (resources != null) {
                LoadSave.Save(resources, nbt, RESOURCES_LOC);
            }

            return nbt;

        }

        @Override
        public void fromTag(CompoundTag nbt) {

            loadFromClientNBT(nbt);

            this.exp = nbt.getInt(EXP);
            this.tier = nbt.getInt(TIER);
            this.uuid = nbt.getString(UUID);
            this.setMobStats = nbt.getBoolean(SET_MOB_STATS);
            this.isNewbie = nbt.getBoolean(NEWBIE_STATUS);
            this.equipsChanged = nbt.getBoolean(EQUIPS_CHANGED);
            this.shouldSync = nbt.getBoolean(SHOULD_SYNC);

            try {
                this.resources = LoadSave.Load(ResourcesData.class, new ResourcesData(), nbt, RESOURCES_LOC);
                if (resources == null) {
                    resources = new ResourcesData();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            this.customExactStats = CustomExactStats.Load(nbt);
            if (this.customExactStats == null) {
                this.customExactStats = new CustomExactStatsData();
            }

        }

        @Override
        public void setEquipsChanged(boolean bool) {
            this.equipsChanged = bool;
        }

        @Override
        public void onDamagedBy(LivingEntity entity, float dmg, LivingEntity self) {
            try {

                if (entity == null && self != null) {
                    float msDamage = this.getUnit()
                        .magicShieldData()
                        .getAverageValue() * (dmg / self.getMaxHealth());
                    ResourcesData.Context ms = new ResourcesData.Context(this, self,
                        ResourcesData.Type.MAGIC_SHIELD,
                        msDamage,
                        ResourcesData.Use.SPEND
                    );
                    getResources()
                        .modify(ms);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        @Override
        public void onAttackEntity(LivingEntity attacker, LivingEntity victim) {

        }

        @Override
        public EntityStatusEffectsData getStatusEffectsData() {
            return this.getUnit().statusEffects;
        }

        @Override
        public void modifyResource(ResourcesData.Context ctx) {
            this.resources.modify(ctx);
        }

        @Override
        public void onDeath(LivingEntity en) {

            int expLoss = (int) (exp * ModConfig.get().Server.EXP_LOSS_ON_DEATH);

            if (expLoss > 0) {
                this.exp = MathHelper.clamp(exp - expLoss, 0, Integer.MAX_VALUE);
            }
        }

        @Override
        public void setType(LivingEntity en) {
            this.type = EntityTypeUtils.getType(en);
        }

        @Override
        public EntityTypeUtils.EntityClassification getType() {
            return this.type;
        }

        @Override
        public void trySync(LivingEntity entity) {
            if (this.shouldSync) {
                this.shouldSync = false;

                if (!Unit.shouldSendUpdatePackets((LivingEntity) entity)) {
                    return;
                }
                Packets.sendToTracking(Unit.getUpdatePacketFor(entity, this), entity);
            }

        }

        @Override
        public PlayerCaps getCapType() {
            return PlayerCaps.ENTITY_DATA;
        }

        @Override
        public Unit getUnit() {
            return unit;
        }

        @Override
        public void setUnit(Unit unit, LivingEntity entity) {
            this.unit = unit;
        }

        @Override
        public void setRarity(int rarity) {
            this.rarity = MathHelper.clamp(rarity, Rarities.Mobs.lowest()
                .Rank(), Rarities.Mobs.highest()
                .Rank());
            this.equipsChanged = true;
            this.shouldSync = true;
        }

        @Override
        public int getRarity() {
            return MathHelper.clamp(rarity, Rarities.Mobs.lowest()
                .Rank(), Rarities.Mobs.highest()
                .Rank());
        }

        @Override
        public String getUUID() {
            return uuid;
        }

        @Override
        public void setUUID(UUID id) {
            uuid = id.toString();
        }

        @Override
        public MutableText getName(LivingEntity entity) {

            if (entity instanceof PlayerEntity) {
                return new LiteralText("")
                    .append(entity.getDisplayName());

            } else {

                MobRarity rarity = Rarities.Mobs.get(getRarity());

                Formatting rarformat = rarity.textFormatting();

                MutableText name = new LiteralText("").append(entity.getDisplayName())
                    .formatted(rarformat);

                if (rarity.Rank() == BossMob.getInstance()
                    .Rank()) {
                    name = new LiteralText("[" + BossMob.SKULL + "] ").formatted(Formatting.YELLOW)
                        .append(name);
                }

                MutableText finalName =
                    name;

                MutableText part = new LiteralText("")
                    .append(finalName)
                    .formatted(rarformat);

                MutableText tx = (part);

                return tx;

            }
        }

        @Override
        public void tryRecalculateStats(LivingEntity entity) {

            if (unit == null) {
                unit = new Unit();
            }

            if (needsToRecalcStats()) {
                unit.recalculateStats(entity, this, null);
            }

        }

        @Override
        public void forceRecalculateStats(LivingEntity entity, DamageEventData data) {
            unit.recalculateStats(entity, this, data);
        }

        @Override
        public void forceRecalculateStats(LivingEntity entity) {

            if (unit == null) {
                unit = new Unit();
            }
            unit.recalculateStats(entity, this, null);
        }

        // This reduces stat calculation by about 4 TIMES!
        private boolean needsToRecalcStats() {

            return this.equipsChanged;
        }

        @Override
        public void forceSetUnit(Unit unit) {
            this.unit = unit;
        }

        @Override
        public GearItemData setupWeaponData(LivingEntity entity) {
            return Gear.Load(entity.getMainHandStack());
        }

        @Override
        public boolean tryUseWeapon(GearItemData weaponData, LivingEntity source) {
            return tryUseWeapon(weaponData, source, 1);
        }

        @Override
        public boolean tryUseWeapon(GearItemData weaponData, LivingEntity source, float multi) {

            try {

                if (weaponData != null) {

                    return true;

                }
            } catch (Exception e) {

                e.printStackTrace();
            }
            return false;
        }

        @Override
        public void onLogin(PlayerEntity player) {

            try {

                if (unit == null) {
                    unit = new Unit();
                }
                unit.removeUnregisteredStats();

                // check if newbie
                if (isNewbie()) {
                    setNewbieStatus(false);

                    // guide book
                    ItemStack book = new ItemStack(Registry.ITEM.get(new Identifier("patchouli", "guide_book")));
                    CompoundTag tag = new CompoundTag();
                    tag.putString("patchouli:book", "mmorpg:age_of_exile_guide");
                    book.setTag(tag);
                    PlayerUtils.giveItem(book, player);

                    if (ModConfig.get().Server.GET_STARTER_ITEMS) {
                        OnLogin.GiveStarterItems(player);
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public boolean increaseRarity(LivingEntity entity) {

            if (rarity >= Rarities.Mobs.highest()
                .Rank()) {
                return false;
            } else {
                rarity = rarity + 1;
                this.equipsChanged = true;
                this.shouldSync = true;
                return true;

            }
        }

        @Override
        public boolean decreaseRarity(LivingEntity entity) {

            if (rarity - 1 < 0) {
                return false;
            } else {
                rarity = rarity - 1;
                this.shouldSync = true;
                return true;

            }
        }

        @Override
        public boolean isWeapon(GearItemData gear) {
            try {

                if (gear == null) {
                    return false;
                }
                if (gear.GetBaseGearType()
                    .family()
                    .equals(BaseGearType.SlotFamily.Weapon)) {
                    return true;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return false;
        }

        @Override
        public void setTier(int tier) {
            this.tier = tier;
        }

        @Override
        public int getTier() {
            return tier;
        }

        @Override
        public Tier getMapTier() {
            return SlashRegistry.Tiers()
                .get(this.tier + "");
        }

        @Override
        public CustomExactStatsData getCustomExactStats() {
            return this.customExactStats;
        }

        @Override
        public ResourcesData getResources() {
            return this.resources;
        }

        @Override
        public float getCurrentMana() {
            return this.resources.getMana();
        }

        @Override
        public void mobStatsAreSet() {
            this.setMobStats = true;
        }

        @Override
        public void attackWithWeapon(DamageEventData data) {
            if (data.weaponData.GetBaseGearType()
                .getWeaponMechanic() != null) {

                if (data.weapon != null) {
                    data.weapon.damage(1, new Random(), null);
                }

                data.weaponData.GetBaseGearType()
                    .getWeaponMechanic()
                    .attack(data);

            }
        }

        @Override
        public void unarmedAttack(DamageEventData data) {

        }

        @Override
        public void mobBasicAttack(DamageEventData data) {
            MobRarity rar = Rarities.Mobs.get(data.sourceData.getRarity());

            float vanilla = data.getEventDamage() * (float) ModConfig.get().Server.VANILLA_MOB_DMG_AS_EXILE_DMG;

            float num = vanilla * rar.DamageMultiplier() * getMapTier().mob_damage_multi;

            num *= SlashRegistry.getEntityConfig(data.source, data.sourceData).dmg_multi;

            num = new WeaponDamage(Elements.Physical).scale(num, getLevel());

            DamageEffect dmg = new DamageEffect(
                data, (int) num, EffectData.EffectTypes.BASIC_ATTACK, WeaponTypes.None
            );

            dmg.Activate();

        }

        @Override
        public boolean isNewbie() {
            return isNewbie;
        }

        @Override
        public void setNewbieStatus(boolean bool) {
            isNewbie = bool;
        }

        @Override
        public boolean needsToBeGivenStats() {
            return this.setMobStats == false;
        }

        @Override
        public int getExpRequiredForLevelUp() {
            return LevelUtils.getExpRequiredForLevel(this.getLevel() + 1);
        }

        @Override
        public EntityGears getCurrentGears() {
            return gears;
        }

        @Override
        public AreaData getArea() {

            if (areaID.isEmpty()) {
                System.out.println("Area id of mob is empty!!!");
                return AreaData.EMPTY;
            }

            return ModRegistry.COMPONENTS.WORLD_AREAS.get(entity.world)
                .getAreaById(areaID);
        }

        @Override
        public void setArea(AreaData area) {
            this.areaID = area.uuid;

            area.getAreaModifier().effectsOnMobSpawn.forEach(x -> {
                this.entity.addStatusEffect(x);
            });

        }

        @Override
        public void SetMobLevelAtSpawn(LivingEntity entity,
                                       PlayerEntity nearestPlayer) {
            this.setMobStats = true;

            setMobLvlNormally(entity, nearestPlayer);

        }

        private void setMobLvlNormally(LivingEntity entity, PlayerEntity nearestPlayer) {
            EntityConfig entityConfig = SlashRegistry.getEntityConfig(entity, this);

            int lvl = LevelUtils.determineLevel(entity.world, entity.getBlockPos(),
                nearestPlayer
            );

            setLevel(MathHelper.clamp(lvl, entityConfig.min_lvl, entityConfig.max_lvl), entity);
        }

        @Override
        public int GiveExp(PlayerEntity player, int i) {

            setExp(exp + i);

            if (exp > this.getExpRequiredForLevelUp()) {

                if (this.CheckIfCanLevelUp() && this.CheckLevelCap()) {
                    this.LevelUp(player);
                }

                return i;
            }
            return i;
        }

        @Override
        public boolean CheckIfCanLevelUp() {

            return getExp() >= getExpRequiredForLevelUp();

        }

        public int getRemainingExp() {
            int num = getExp() - getExpRequiredForLevelUp();

            if (num < 0) {
                num = 0;
            }
            return num;
        }

        @Override
        public boolean CheckLevelCap() {
            return getLevel() + 1 <= ModConfig.get().Server.MAX_LEVEL;
        }

        @Override
        public boolean LevelUp(PlayerEntity player) {

            if (!CheckIfCanLevelUp()) {
                player.sendMessage(Chats.Not_enough_experience.locName(), false);
            } else if (!CheckLevelCap()) {
                player.sendMessage(Chats.Can_not_go_over_maximum_level.locName(), false);
            }

            if (CheckIfCanLevelUp() && CheckLevelCap()) {

                this.setLevel(level + 1, player);
                setExp(getRemainingExp());

                OnScreenMessageUtils.sendLevelUpMessage(player, new LiteralText("Player"), level - 1, level);

                return true;
            }
            return false;
        }

        @Override
        public int getLevel() {

            return level;

        }

        @Override
        public void setLevel(int lvl, LivingEntity entity) {

            level = MathHelper.clamp(lvl, 1, ModConfig.get().Server.MAX_LEVEL);

            this.equipsChanged = true;
            this.shouldSync = true;

        }

        @Override
        public int getExp() {
            return exp;
        }

        @Override
        public void setExp(int exp) {
            this.exp = exp;
        }

        /*
        @Override
        public Entity getEntity() {
            return entity;
        }

         */
    }

}
