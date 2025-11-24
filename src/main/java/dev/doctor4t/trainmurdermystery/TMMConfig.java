package dev.doctor4t.trainmurdermystery;

import dev.doctor4t.trainmurdermystery.game.GameConstants;
import eu.midnightdust.lib.config.MidnightConfig;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

public class TMMConfig extends MidnightConfig {
    // 添加初始化标志
    private static boolean configInitialized = false;

    // 客户端专用配置 - 仅在客户端环境生效
    @Environment(EnvType.CLIENT)
    @Entry
    public static boolean ultraPerfMode = false;
    
    @Environment(EnvType.CLIENT)
    @Entry
    public static boolean disableScreenShake = false;

    // 商店物品价格配置
    @Comment(category = "shop", centered = true)
    public static Comment shopPricesComment;
    
    @Entry(category = "shop", min = 0, max = 1000)
    public static int knifePrice = 100;
    @Entry(category = "shop", min = 0, max = 1000)
    public static int revolverPrice = 300;
    @Entry(category = "shop", min = 0, max = 1000)
    public static int grenadePrice = 350;
    @Entry(category = "shop", min = 0, max = 1000)
    public static int psychoModePrice = 300;
    @Entry(category = "shop", min = 0, max = 1000)
    public static int poisonVialPrice = 100;
    @Entry(category = "shop", min = 0, max = 1000)
    public static int scorpionPrice = 50;
    @Entry(category = "shop", min = 0, max = 1000)
    public static int firecrackerPrice = 10;
    @Entry(category = "shop", min = 0, max = 1000)
    public static int lockpickPrice = 50;
    @Entry(category = "shop", min = 0, max = 1000)
    public static int crowbarPrice = 25;
    @Entry(category = "shop", min = 0, max = 1000)
    public static int bodyBagPrice = 200;
    @Entry(category = "shop", min = 0, max = 1000)
    public static int blackoutPrice = 200;
    @Entry(category = "shop", min = 0, max = 1000)
    public static int notePrice = 10;

    // 物品冷却时间配置（秒）
    @Comment(category = "cooldowns", centered = true)
    public static Comment cooldownsComment;
    
    @Entry(category = "cooldowns", min = 0, max = 300)
    public static int knifeCooldown = 60;
    @Entry(category = "cooldowns", min = 0, max = 300)
    public static int revolverCooldown = 10;
    @Entry(category = "cooldowns", min = 0, max = 300)
    public static int derringerCooldown = 1;
    @Entry(category = "cooldowns", min = 0, max = 300)
    public static int grenadeCooldown = 300;
    @Entry(category = "cooldowns", min = 0, max = 300)
    public static int lockpickCooldown = 180;
    @Entry(category = "cooldowns", min = 0, max = 300)
    public static int crowbarCooldown = 10;
    @Entry(category = "cooldowns", min = 0, max = 300)
    public static int bodyBagCooldown = 300;
    @Entry(category = "cooldowns", min = 0, max = 300)
    public static int psychoModeCooldown = 300;
    @Entry(category = "cooldowns", min = 0, max = 300)
    public static int blackoutCooldown = 180;

    // 游戏配置
    @Comment(category = "game", centered = true)
    public static Comment gameConfigComment;
    
    @Entry(category = "game", min = 0, max = 1000)
    public static int startingMoney = 100;
    @Entry(category = "game", min = 0, max = 100)
    public static int passiveMoneyAmount = 5;
    @Entry(category = "game", min = 1, max = 1200)
    public static int passiveMoneyInterval = 10;
    @Entry(category = "game", min = 0, max = 1000)
    public static int moneyPerKill = 100;
    @Entry(category = "game", min = 0, max = 30)
    public static int psychoModeArmor = 1;
    @Entry(category = "game", min = 0, max = 300)
    public static int psychoModeDuration = 30;
    @Entry(category = "game", min = 0, max = 300)
    public static int firecrackerDuration = 15;
    @Entry(category = "game", min = 0, max = 300)
    public static int blackoutMinDuration = 15;
    @Entry(category = "game", min = 0, max = 300)
    public static int blackoutMaxDuration = 20;

    /**
     * 初始化配置系统
     * 必须在mod初始化时调用以生成配置文件
     * 使用标志防止重复初始化
     */
    public static void init() {
        if (!configInitialized) {
            MidnightConfig.init(TMM.MOD_ID, TMMConfig.class);
            configInitialized = true;
            TMM.LOGGER.info("配置系统初始化完成");
        }
    }

    /**
     * 重新加载配置文件
     * 用于运行时重载配置，无需重启服务器
     * 不重置初始化标志，避免重复注册
     */
    public static void reload() {
        // 只读取配置，不重新初始化
        MidnightConfig.init(TMM.MOD_ID, TMMConfig.class);
        // 重载冷却时间
        GameConstants.reloadItemCooldowns();
        TMM.LOGGER.info("配置已重新加载");
    }
    
    /**
     * 重置配置为默认值
     * 通过删除配置文件并重新初始化来实现
     */
    public static void reset() {
        try {
            java.nio.file.Path configPath = net.fabricmc.loader.api.FabricLoader.getInstance()
                .getConfigDir()
                .resolve(TMM.MOD_ID + ".json");
            
            // 删除现有配置文件
            if (java.nio.file.Files.exists(configPath)) {
                java.nio.file.Files.delete(configPath);
                TMM.LOGGER.info("已删除配置文件: {}", configPath);
            }
            
            // 重新初始化配置，这会创建新的默认配置文件
            MidnightConfig.init(TMM.MOD_ID, TMMConfig.class);
            
            // 确保配置被写入到文件
            MidnightConfig.write(TMM.MOD_ID);
            TMM.LOGGER.info("配置已重置为默认值");
        } catch (java.io.IOException e) {
            TMM.LOGGER.error("重置配置文件失败", e);
            throw new RuntimeException("重置配置文件失败", e);
        }
    }

    @Override
    public void writeChanges(String modid) {
        // 调用父类方法来保存配置
        super.writeChanges(modid);
        
        // 重载游戏常量（包括冷却时间）
        GameConstants.reloadItemCooldowns();
        TMM.LOGGER.debug("配置已保存并应用到游戏常量");

        // 仅在客户端环境应用客户端专用配置
        if (net.fabricmc.loader.api.FabricLoader.getInstance().getEnvironmentType() == EnvType.CLIENT) {
            applyClientConfig();
        }
    }
    
    /**
     * 应用客户端专用配置
     * 仅在客户端环境调用
     */
    @Environment(EnvType.CLIENT)
    private static void applyClientConfig() {
        // 注释掉的代码保留供将来使用
        // int lockedRenderDistance = TMMClient.getLockedRenderDistance(ultraPerfMode);
        // OptionLocker.overrideOption("renderDistance", lockedRenderDistance);
        // MinecraftClient.getInstance().options.viewDistance.setValue(lockedRenderDistance);
    }
}