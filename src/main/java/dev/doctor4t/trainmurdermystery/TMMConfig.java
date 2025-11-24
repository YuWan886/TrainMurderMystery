package dev.doctor4t.trainmurdermystery;

import dev.doctor4t.trainmurdermystery.game.GameConstants;
import eu.midnightdust.lib.config.MidnightConfig;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class TMMConfig extends MidnightConfig {
    // 存储默认配置值 - 在静态初始化块中设置
    private static final Map<String, Object> DEFAULT_VALUES = new HashMap<>();
    
    static {
        // 客户端配置默认值
        DEFAULT_VALUES.put("ultraPerfMode", false);
        DEFAULT_VALUES.put("disableScreenShake", false);
        
        // 商店物品价格默认值
        DEFAULT_VALUES.put("knifePrice", 100);
        DEFAULT_VALUES.put("revolverPrice", 300);
        DEFAULT_VALUES.put("grenadePrice", 350);
        DEFAULT_VALUES.put("psychoModePrice", 300);
        DEFAULT_VALUES.put("poisonVialPrice", 100);
        DEFAULT_VALUES.put("scorpionPrice", 50);
        DEFAULT_VALUES.put("firecrackerPrice", 10);
        DEFAULT_VALUES.put("lockpickPrice", 50);
        DEFAULT_VALUES.put("crowbarPrice", 25);
        DEFAULT_VALUES.put("bodyBagPrice", 200);
        DEFAULT_VALUES.put("blackoutPrice", 200);
        DEFAULT_VALUES.put("notePrice", 10);
        
        // 物品冷却时间默认值（秒）
        DEFAULT_VALUES.put("knifeCooldown", 60);
        DEFAULT_VALUES.put("revolverCooldown", 10);
        DEFAULT_VALUES.put("derringerCooldown", 1);
        DEFAULT_VALUES.put("grenadeCooldown", 300);
        DEFAULT_VALUES.put("lockpickCooldown", 180);
        DEFAULT_VALUES.put("crowbarCooldown", 10);
        DEFAULT_VALUES.put("bodyBagCooldown", 300);
        DEFAULT_VALUES.put("psychoModeCooldown", 300);
        DEFAULT_VALUES.put("blackoutCooldown", 180);
        
        // 游戏配置默认值
        DEFAULT_VALUES.put("startingMoney", 100);
        DEFAULT_VALUES.put("passiveMoneyAmount", 5);
        DEFAULT_VALUES.put("passiveMoneyInterval", 10);
        DEFAULT_VALUES.put("moneyPerKill", 100);
        DEFAULT_VALUES.put("psychoModeArmor", 1);
        DEFAULT_VALUES.put("psychoModeDuration", 30);
        DEFAULT_VALUES.put("firecrackerDuration", 15);
        DEFAULT_VALUES.put("blackoutMinDuration", 15);
        DEFAULT_VALUES.put("blackoutMaxDuration", 20);
    }
    
    // 客户端专用配置 - 仅在客户端环境生效
    @Environment(EnvType.CLIENT)
    @Entry
    public static boolean ultraPerfMode = false;
    
    @Environment(EnvType.CLIENT)
    @Entry
    public static boolean disableScreenShake = false;

    // 商店物品价格配置 - 服务端只读
    @Comment(category = "shop", centered = true)
    public static Comment shopPricesComment;
    
    @Entry(category = "shop", min = 0, max = 1000, isSlider = true)
    public static int knifePrice = 100;
    @Entry(category = "shop", min = 0, max = 1000, isSlider = true)
    public static int revolverPrice = 300;
    @Entry(category = "shop", min = 0, max = 1000, isSlider = true)
    public static int grenadePrice = 350;
    @Entry(category = "shop", min = 0, max = 1000, isSlider = true)
    public static int psychoModePrice = 300;
    @Entry(category = "shop", min = 0, max = 1000, isSlider = true)
    public static int poisonVialPrice = 100;
    @Entry(category = "shop", min = 0, max = 1000, isSlider = true)
    public static int scorpionPrice = 50;
    @Entry(category = "shop", min = 0, max = 1000, isSlider = true)
    public static int firecrackerPrice = 10;
    @Entry(category = "shop", min = 0, max = 1000, isSlider = true)
    public static int lockpickPrice = 50;
    @Entry(category = "shop", min = 0, max = 1000, isSlider = true)
    public static int crowbarPrice = 25;
    @Entry(category = "shop", min = 0, max = 1000, isSlider = true)
    public static int bodyBagPrice = 200;
    @Entry(category = "shop", min = 0, max = 1000, isSlider = true)
    public static int blackoutPrice = 200;
    @Entry(category = "shop", min = 0, max = 1000, isSlider = true)
    public static int notePrice = 10;

    // 物品冷却时间配置（秒）- 服务端只读
    @Comment(category = "cooldowns", centered = true)
    public static Comment cooldownsComment;
    
    @Entry(category = "cooldowns", min = 0, max = 300, isSlider = true)
    public static int knifeCooldown = 60;
    @Entry(category = "cooldowns", min = 0, max = 300, isSlider = true)
    public static int revolverCooldown = 10;
    @Entry(category = "cooldowns", min = 0, max = 300, isSlider = true)
    public static int derringerCooldown = 1;
    @Entry(category = "cooldowns", min = 0, max = 300, isSlider = true)
    public static int grenadeCooldown = 300;
    @Entry(category = "cooldowns", min = 0, max = 300, isSlider = true)
    public static int lockpickCooldown = 180;
    @Entry(category = "cooldowns", min = 0, max = 300, isSlider = true)
    public static int crowbarCooldown = 10;
    @Entry(category = "cooldowns", min = 0, max = 300, isSlider = true)
    public static int bodyBagCooldown = 300;
    @Entry(category = "cooldowns", min = 0, max = 300, isSlider = true)
    public static int psychoModeCooldown = 300;
    @Entry(category = "cooldowns", min = 0, max = 300, isSlider = true)
    public static int blackoutCooldown = 180;

    // 游戏配置 - 服务端只读
    @Comment(category = "game", centered = true)
    public static Comment gameConfigComment;
    
    @Entry(category = "game", min = 0, max = 1000, isSlider = true)
    public static int startingMoney = 100;
    @Entry(category = "game", min = 0, max = 100, isSlider = true)
    public static int passiveMoneyAmount = 5;
    @Entry(category = "game", min = 1, max = 1200, isSlider = true)
    public static int passiveMoneyInterval = 10;
    @Entry(category = "game", min = 0, max = 1000, isSlider = true)
    public static int moneyPerKill = 100;
    @Entry(category = "game", min = 0, max = 30, isSlider = true)
    public static int psychoModeArmor = 1;
    @Entry(category = "game", min = 0, max = 300, isSlider = true)
    public static int psychoModeDuration = 30;
    @Entry(category = "game", min = 0, max = 300, isSlider = true)
    public static int firecrackerDuration = 15;
    @Entry(category = "game", min = 0, max = 300, isSlider = true)
    public static int blackoutMinDuration = 15;
    @Entry(category = "game", min = 0, max = 300, isSlider = true)
    public static int blackoutMaxDuration = 20;

    /**
     * 初始化配置系统
     * 必须在mod初始化时调用以生成配置文件
     */
    public static void init() {
        // 只在客户端初始化UI，服务端只读取配置文件
        if (net.fabricmc.loader.api.FabricLoader.getInstance().getEnvironmentType() == EnvType.CLIENT) {
            MidnightConfig.init(TMM.MOD_ID, TMMConfig.class);
        } else {
            // 服务端直接从文件读取
            try {
                java.nio.file.Path configPath = net.fabricmc.loader.api.FabricLoader.getInstance()
                    .getConfigDir()
                    .resolve(TMM.MOD_ID + ".json");
                
                if (java.nio.file.Files.exists(configPath)) {
                    // 读取配置文件
                    String content = java.nio.file.Files.readString(configPath);
                    // 解析JSON并设置字段值
                    parseConfig(content);
                    TMM.LOGGER.info("服务端配置已从文件加载");
                } else {
                    // 创建默认配置文件
                    MidnightConfig.init(TMM.MOD_ID, TMMConfig.class);
                    TMM.LOGGER.info("服务端配置文件已创建");
                }
            } catch (Exception e) {
                TMM.LOGGER.error("服务端加载配置失败", e);
                // 降级到默认初始化
                MidnightConfig.init(TMM.MOD_ID, TMMConfig.class);
            }
        }
    }

    /**
     * 重新加载配置文件
     * 服务端：只从文件读取，不修改
     * 客户端：可以通过UI修改
     */
    public static void reload() {
        try {
            java.nio.file.Path configPath = net.fabricmc.loader.api.FabricLoader.getInstance()
                .getConfigDir()
                .resolve(TMM.MOD_ID + ".json");
            
            if (java.nio.file.Files.exists(configPath)) {
                String content = java.nio.file.Files.readString(configPath);
                parseConfig(content);
                
                // 重载冷却时间到GameConstants
                GameConstants.reloadItemCooldowns();
                
                TMM.LOGGER.info("配置已从文件重新加载");
            }
        } catch (Exception e) {
            TMM.LOGGER.error("重新加载配置失败", e);
        }
    }
    
    /**
     * 解析配置JSON字符串并设置字段值
     */
    private static void parseConfig(String jsonContent) {
        // 使用Gson解析JSON
        com.google.gson.JsonObject json = com.google.gson.JsonParser.parseString(jsonContent).getAsJsonObject();
        
        // 读取shop配置
        if (json.has("knifePrice")) knifePrice = json.get("knifePrice").getAsInt();
        if (json.has("revolverPrice")) revolverPrice = json.get("revolverPrice").getAsInt();
        if (json.has("grenadePrice")) grenadePrice = json.get("grenadePrice").getAsInt();
        if (json.has("psychoModePrice")) psychoModePrice = json.get("psychoModePrice").getAsInt();
        if (json.has("poisonVialPrice")) poisonVialPrice = json.get("poisonVialPrice").getAsInt();
        if (json.has("scorpionPrice")) scorpionPrice = json.get("scorpionPrice").getAsInt();
        if (json.has("firecrackerPrice")) firecrackerPrice = json.get("firecrackerPrice").getAsInt();
        if (json.has("lockpickPrice")) lockpickPrice = json.get("lockpickPrice").getAsInt();
        if (json.has("crowbarPrice")) crowbarPrice = json.get("crowbarPrice").getAsInt();
        if (json.has("bodyBagPrice")) bodyBagPrice = json.get("bodyBagPrice").getAsInt();
        if (json.has("blackoutPrice")) blackoutPrice = json.get("blackoutPrice").getAsInt();
        if (json.has("notePrice")) notePrice = json.get("notePrice").getAsInt();
        
        // 读取cooldowns配置
        if (json.has("knifeCooldown")) knifeCooldown = json.get("knifeCooldown").getAsInt();
        if (json.has("revolverCooldown")) revolverCooldown = json.get("revolverCooldown").getAsInt();
        if (json.has("derringerCooldown")) derringerCooldown = json.get("derringerCooldown").getAsInt();
        if (json.has("grenadeCooldown")) grenadeCooldown = json.get("grenadeCooldown").getAsInt();
        if (json.has("lockpickCooldown")) lockpickCooldown = json.get("lockpickCooldown").getAsInt();
        if (json.has("crowbarCooldown")) crowbarCooldown = json.get("crowbarCooldown").getAsInt();
        if (json.has("bodyBagCooldown")) bodyBagCooldown = json.get("bodyBagCooldown").getAsInt();
        if (json.has("psychoModeCooldown")) psychoModeCooldown = json.get("psychoModeCooldown").getAsInt();
        if (json.has("blackoutCooldown")) blackoutCooldown = json.get("blackoutCooldown").getAsInt();
        
        // 读取game配置
        if (json.has("startingMoney")) startingMoney = json.get("startingMoney").getAsInt();
        if (json.has("passiveMoneyAmount")) passiveMoneyAmount = json.get("passiveMoneyAmount").getAsInt();
        if (json.has("passiveMoneyInterval")) passiveMoneyInterval = json.get("passiveMoneyInterval").getAsInt();
        if (json.has("moneyPerKill")) moneyPerKill = json.get("moneyPerKill").getAsInt();
        if (json.has("psychoModeArmor")) psychoModeArmor = json.get("psychoModeArmor").getAsInt();
        if (json.has("psychoModeDuration")) psychoModeDuration = json.get("psychoModeDuration").getAsInt();
        if (json.has("firecrackerDuration")) firecrackerDuration = json.get("firecrackerDuration").getAsInt();
        if (json.has("blackoutMinDuration")) blackoutMinDuration = json.get("blackoutMinDuration").getAsInt();
        if (json.has("blackoutMaxDuration")) blackoutMaxDuration = json.get("blackoutMaxDuration").getAsInt();
        
        // 读取客户端配置
        if (json.has("ultraPerfMode")) ultraPerfMode = json.get("ultraPerfMode").getAsBoolean();
        if (json.has("disableScreenShake")) disableScreenShake = json.get("disableScreenShake").getAsBoolean();
    }
    
    /**
     * 重置配置为默认值
     * 通过精确修改配置文件内容来实现，不删除文件
     */
    public static void reset() {
        try {
            // 将所有字段重置为默认值
            for (Field field : TMMConfig.class.getDeclaredFields()) {
                if (field.isAnnotationPresent(Entry.class) &&
                    java.lang.reflect.Modifier.isStatic(field.getModifiers()) &&
                    !field.getType().equals(Comment.class)) {
                    
                    String fieldName = field.getName();
                    if (DEFAULT_VALUES.containsKey(fieldName)) {
                        field.setAccessible(true);
                        Object defaultValue = DEFAULT_VALUES.get(fieldName);
                        field.set(null, defaultValue);
                        TMM.LOGGER.debug("重置字段 {} 为默认值: {}", fieldName, defaultValue);
                    }
                }
            }
            
            // 强制写入配置文件
            MidnightConfig.write(TMM.MOD_ID);
            
            // 重新加载配置到内存和游戏常量
            reload();
            
            TMM.LOGGER.info("配置已重置为默认值");
        } catch (Exception e) {
            TMM.LOGGER.error("重置配置失败", e);
            throw new RuntimeException("重置配置失败", e);
        }
    }

    @Override
    public void writeChanges(String modid) {
        // 调用父类方法来保存配置
        super.writeChanges(modid);
        
        // 每次保存后重新加载，确保冷却时间等配置立即生效
        reload();
        
        TMM.LOGGER.debug("配置已保存并重新加载");

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