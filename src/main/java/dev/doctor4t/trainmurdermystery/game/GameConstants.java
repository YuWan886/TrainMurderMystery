package dev.doctor4t.trainmurdermystery.game;

import dev.doctor4t.trainmurdermystery.TMM;
import dev.doctor4t.trainmurdermystery.TMMConfig;
import dev.doctor4t.trainmurdermystery.cca.PlayerShopComponent;
import dev.doctor4t.trainmurdermystery.index.TMMItems;
import dev.doctor4t.trainmurdermystery.util.ShopEntry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Util;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;

public interface GameConstants {
    // Logistics
    int FADE_TIME = 40;
    int FADE_PAUSE = 20;
    int MIN_PLAYER_COUNT = 6;
    
    // Role Configuration (Server-side, mutable via command)
    class RoleConfig {
        public static int killerCount = 1;
        public static int vigilanteCount = 1;
    }

    // Blocks
    int DOOR_AUTOCLOSE_TIME = getInTicks(0, 5);

    // Items
    Map<Item, Integer> ITEM_COOLDOWNS = new HashMap<>();

    /**
     * 初始化游戏常量
     * 在mod初始化时调用
     */
    static void init() {
        reloadItemCooldowns();
    }
    
    /**
     * 重新加载物品冷却时间
     * 可以在运行时调用以应用配置更改
     */
    static void reloadItemCooldowns() {
        ITEM_COOLDOWNS.clear();
        ITEM_COOLDOWNS.put(TMMItems.KNIFE, TMMConfig.knifeCooldown * 20);
        ITEM_COOLDOWNS.put(TMMItems.REVOLVER, TMMConfig.revolverCooldown * 20);
        ITEM_COOLDOWNS.put(TMMItems.DERRINGER, TMMConfig.derringerCooldown * 20);
        ITEM_COOLDOWNS.put(TMMItems.GRENADE, TMMConfig.grenadeCooldown * 20);
        ITEM_COOLDOWNS.put(TMMItems.LOCKPICK, TMMConfig.lockpickCooldown * 20);
        ITEM_COOLDOWNS.put(TMMItems.CROWBAR, TMMConfig.crowbarCooldown * 20);
        ITEM_COOLDOWNS.put(TMMItems.BODY_BAG, TMMConfig.bodyBagCooldown * 20);
        ITEM_COOLDOWNS.put(TMMItems.PSYCHO_MODE, TMMConfig.psychoModeCooldown * 20);
        ITEM_COOLDOWNS.put(TMMItems.BLACKOUT, TMMConfig.blackoutCooldown * 20);
        
        TMM.LOGGER.debug("物品冷却时间已重载: 小刀={}秒, 左轮={}秒", 
            TMMConfig.knifeCooldown, TMMConfig.revolverCooldown);
    }

    int JAMMED_DOOR_TIME = getInTicks(1, 0);

    // Sprint
    int MAX_SPRINTING_TICKS = getInTicks(0, 10);

    // Corpses
    int TIME_TO_DECOMPOSITION = getInTicks(1, 0);
    int DECOMPOSING_TIME = getInTicks(4, 0);

    // Game areas
    Vec3d SPAWN_POS = new Vec3d(-872.5, 0, -323);
    Box READY_AREA = new Box(-1017, -1, -363.5f, -813, 3, -357.5f);
    Vec3d PLAY_OFFSET = new Vec3d(963, 121, -175);
    Consumer<ServerPlayerEntity> SPECTATOR_TP = serverPlayerEntity -> serverPlayerEntity.teleport(serverPlayerEntity.getServerWorld(), -68, 133, -535.5, -90, 15);
    Box PLAY_AREA = new Box(-140, 118, -535.5f - 15, 230, 200, -535.5f + 15);
    Box BACKUP_TRAIN_LOCATION = new Box(-57, 64, -531, 177, 74, -541);
    Box TRAIN_LOCATION = BACKUP_TRAIN_LOCATION.offset(0, 55, 0);

    // Task Variables
    float MOOD_GAIN = 0.5f;
    float MOOD_DRAIN = 1f / getInTicks(4, 0);
    int TIME_TO_FIRST_TASK = getInTicks(0, 30);
    int MIN_TASK_COOLDOWN = getInTicks(0, 30);
    int MAX_TASK_COOLDOWN = getInTicks(1, 0);
    int SLEEP_TASK_DURATION = getInTicks(0, 8);
    int OUTSIDE_TASK_DURATION = getInTicks(0, 8);
    float MID_MOOD_THRESHOLD = 0.55f;
    float DEPRESSIVE_MOOD_THRESHOLD = 0.2f;
    float ITEM_PSYCHOSIS_CHANCE = .5f; // in percent
    int ITEM_PSYCHOSIS_REROLL_TIME = 200;

    // Shop Variables
    static List<ShopEntry> getShopEntries() {
        List<ShopEntry> entries = new ArrayList<>();
        entries.add(new ShopEntry(TMMItems.KNIFE.getDefaultStack(), TMMConfig.knifePrice, ShopEntry.Type.WEAPON));
        entries.add(new ShopEntry(TMMItems.REVOLVER.getDefaultStack(), TMMConfig.revolverPrice, ShopEntry.Type.WEAPON));
        entries.add(new ShopEntry(TMMItems.GRENADE.getDefaultStack(), TMMConfig.grenadePrice, ShopEntry.Type.WEAPON));
        entries.add(new ShopEntry(TMMItems.PSYCHO_MODE.getDefaultStack(), TMMConfig.psychoModePrice, ShopEntry.Type.WEAPON) {
            @Override
            public boolean onBuy(@NotNull PlayerEntity player) {
                return PlayerShopComponent.usePsychoMode(player);
            }
        });
        entries.add(new ShopEntry(TMMItems.POISON_VIAL.getDefaultStack(), TMMConfig.poisonVialPrice, ShopEntry.Type.POISON));
        entries.add(new ShopEntry(TMMItems.SCORPION.getDefaultStack(), TMMConfig.scorpionPrice, ShopEntry.Type.POISON));
        entries.add(new ShopEntry(TMMItems.FIRECRACKER.getDefaultStack(), TMMConfig.firecrackerPrice, ShopEntry.Type.TOOL));
        entries.add(new ShopEntry(TMMItems.LOCKPICK.getDefaultStack(), TMMConfig.lockpickPrice, ShopEntry.Type.TOOL));
        entries.add(new ShopEntry(TMMItems.CROWBAR.getDefaultStack(), TMMConfig.crowbarPrice, ShopEntry.Type.TOOL));
        entries.add(new ShopEntry(TMMItems.BODY_BAG.getDefaultStack(), TMMConfig.bodyBagPrice, ShopEntry.Type.TOOL));
        entries.add(new ShopEntry(TMMItems.BLACKOUT.getDefaultStack(), TMMConfig.blackoutPrice, ShopEntry.Type.TOOL) {
            @Override
            public boolean onBuy(@NotNull PlayerEntity player) {
                return PlayerShopComponent.useBlackout(player);
            }
        });
        entries.add(new ShopEntry(new ItemStack(TMMItems.NOTE, 4), TMMConfig.notePrice, ShopEntry.Type.TOOL));
        return entries;
    }
    
    static int getMoneyStart() {
        return TMMConfig.startingMoney;
    }
    
    static Function<Long, Integer> getPassiveMoneyTicker() {
        return time -> {
            if (time % (TMMConfig.passiveMoneyInterval * 20) == 0) {
                return TMMConfig.passiveMoneyAmount;
            }
            return 0;
        };
    }
    
    static int getMoneyPerKill() {
        return TMMConfig.moneyPerKill;
    }
    
    static int getPsychoModeArmour() {
        return TMMConfig.psychoModeArmor;
    }

    // Timers
    static int getPsychoTimer() {
        return TMMConfig.psychoModeDuration * 20;
    }
    
    static int getFirecrackerTimer() {
        return TMMConfig.firecrackerDuration * 20;
    }
    
    static int getBlackoutMinDuration() {
        return TMMConfig.blackoutMinDuration * 20;
    }
    
    static int getBlackoutMaxDuration() {
        return TMMConfig.blackoutMaxDuration * 20;
    }
    int TIME_ON_CIVILIAN_KILL = getInTicks(1, 0);

    static int getInTicks(int minutes, int seconds) {
        return (minutes * 60 + seconds) * 20;
    }
}