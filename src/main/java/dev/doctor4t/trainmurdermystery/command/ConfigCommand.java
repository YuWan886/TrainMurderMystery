package dev.doctor4t.trainmurdermystery.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import dev.doctor4t.trainmurdermystery.TMM;
import dev.doctor4t.trainmurdermystery.TMMConfig;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;

public class ConfigCommand {
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(CommandManager.literal("tmm:config")
                .requires(source -> source.hasPermissionLevel(2))
                .executes(ConfigCommand::showConfig)
                .then(CommandManager.literal("reload")
                        .executes(ConfigCommand::reloadConfig))
                .then(CommandManager.literal("reset")
                        .executes(ConfigCommand::resetConfig)));
    }
    
    private static int reloadConfig(CommandContext<ServerCommandSource> context) {
        ServerCommandSource source = context.getSource();
        
        try {
            TMMConfig.reload();
            source.sendFeedback(
                () -> Text.translatable("commands.tmm.config.reload")
                    .styled(style -> style.withColor(0x00FF00)),
                true
            );
            TMM.LOGGER.info("配置文件已由 {} 重载", source.getName());
            return 1;
        } catch (Exception e) {
            source.sendError(Text.translatable("commands.tmm.config.reload.fail", e.getMessage()));
            TMM.LOGGER.error("配置重载失败", e);
            return 0;
        }
    }
    
    private static int resetConfig(CommandContext<ServerCommandSource> context) {
        ServerCommandSource source = context.getSource();
        
        try {
            TMMConfig.reset();
            source.sendFeedback(
                () -> Text.translatable("commands.tmm.config.reset")
                    .styled(style -> style.withColor(0x00FF00)),
                true
            );
            TMM.LOGGER.info("配置文件已由 {} 重置为默认值", source.getName());
            return 1;
        } catch (Exception e) {
            source.sendError(Text.translatable("commands.tmm.config.reset.fail", e.getMessage()));
            TMM.LOGGER.error("配置重置失败", e);
            return 0;
        }
    }

    private static int showConfig(CommandContext<ServerCommandSource> context) {
        ServerCommandSource source = context.getSource();
        
        source.sendFeedback(() -> Text.translatable("commands.tmm.config.show.header"), false);
        
        // 商店价格
        source.sendFeedback(() -> Text.translatable("commands.tmm.config.show.shop_prices.header"), false);
        source.sendFeedback(() -> Text.translatable("commands.tmm.config.show.shop_prices.knife", TMMConfig.knifePrice), false);
        source.sendFeedback(() -> Text.translatable("commands.tmm.config.show.shop_prices.revolver", TMMConfig.revolverPrice), false);
        source.sendFeedback(() -> Text.translatable("commands.tmm.config.show.shop_prices.grenade", TMMConfig.grenadePrice), false);
        source.sendFeedback(() -> Text.translatable("commands.tmm.config.show.shop_prices.psycho_mode", TMMConfig.psychoModePrice), false);
        source.sendFeedback(() -> Text.translatable("commands.tmm.config.show.shop_prices.poison_vial", TMMConfig.poisonVialPrice), false);
        source.sendFeedback(() -> Text.translatable("commands.tmm.config.show.shop_prices.scorpion", TMMConfig.scorpionPrice), false);
        source.sendFeedback(() -> Text.translatable("commands.tmm.config.show.shop_prices.firecracker", TMMConfig.firecrackerPrice), false);
        source.sendFeedback(() -> Text.translatable("commands.tmm.config.show.shop_prices.lockpick", TMMConfig.lockpickPrice), false);
        source.sendFeedback(() -> Text.translatable("commands.tmm.config.show.shop_prices.crowbar", TMMConfig.crowbarPrice), false);
        source.sendFeedback(() -> Text.translatable("commands.tmm.config.show.shop_prices.body_bag", TMMConfig.bodyBagPrice), false);
        source.sendFeedback(() -> Text.translatable("commands.tmm.config.show.shop_prices.blackout", TMMConfig.blackoutPrice), false);
        source.sendFeedback(() -> Text.translatable("commands.tmm.config.show.shop_prices.note", TMMConfig.notePrice), false);
        
        // 物品冷却时间
        source.sendFeedback(() -> Text.translatable("commands.tmm.config.show.cooldowns.header"), false);
        source.sendFeedback(() -> Text.translatable("commands.tmm.config.show.cooldowns.knife", TMMConfig.knifeCooldown), false);
        source.sendFeedback(() -> Text.translatable("commands.tmm.config.show.cooldowns.revolver", TMMConfig.revolverCooldown), false);
        source.sendFeedback(() -> Text.translatable("commands.tmm.config.show.cooldowns.derringer", TMMConfig.derringerCooldown), false);
        source.sendFeedback(() -> Text.translatable("commands.tmm.config.show.cooldowns.grenade", TMMConfig.grenadeCooldown), false);
        source.sendFeedback(() -> Text.translatable("commands.tmm.config.show.cooldowns.lockpick", TMMConfig.lockpickCooldown), false);
        source.sendFeedback(() -> Text.translatable("commands.tmm.config.show.cooldowns.crowbar", TMMConfig.crowbarCooldown), false);
        source.sendFeedback(() -> Text.translatable("commands.tmm.config.show.cooldowns.body_bag", TMMConfig.bodyBagCooldown), false);
        source.sendFeedback(() -> Text.translatable("commands.tmm.config.show.cooldowns.psycho_mode", TMMConfig.psychoModeCooldown), false);
        source.sendFeedback(() -> Text.translatable("commands.tmm.config.show.cooldowns.blackout", TMMConfig.blackoutCooldown), false);
        
        // 游戏设置
        source.sendFeedback(() -> Text.translatable("commands.tmm.config.show.game_settings.header"), false);
        source.sendFeedback(() -> Text.translatable("commands.tmm.config.show.game_settings.starting_money", TMMConfig.startingMoney), false);
        source.sendFeedback(() -> Text.translatable("commands.tmm.config.show.game_settings.passive_money_amount", TMMConfig.passiveMoneyAmount), false);
        source.sendFeedback(() -> Text.translatable("commands.tmm.config.show.game_settings.passive_money_interval", TMMConfig.passiveMoneyInterval), false);
        source.sendFeedback(() -> Text.translatable("commands.tmm.config.show.game_settings.money_per_kill", TMMConfig.moneyPerKill), false);
        source.sendFeedback(() -> Text.translatable("commands.tmm.config.show.game_settings.psycho_mode_armor", TMMConfig.psychoModeArmor), false);
        source.sendFeedback(() -> Text.translatable("commands.tmm.config.show.game_settings.psycho_mode_duration", TMMConfig.psychoModeDuration), false);
        source.sendFeedback(() -> Text.translatable("commands.tmm.config.show.game_settings.firecracker_duration", TMMConfig.firecrackerDuration), false);
        source.sendFeedback(() -> Text.translatable("commands.tmm.config.show.game_settings.blackout_min_duration", TMMConfig.blackoutMinDuration), false);
        source.sendFeedback(() -> Text.translatable("commands.tmm.config.show.game_settings.blackout_max_duration", TMMConfig.blackoutMaxDuration), false);
        
        source.sendFeedback(() -> Text.translatable("commands.tmm.config.show.footer"), false);
        source.sendFeedback(() -> Text.translatable("commands.tmm.config.show.hint"), false);
        
        return 1;
    }
}