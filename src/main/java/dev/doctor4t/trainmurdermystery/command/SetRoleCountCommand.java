package dev.doctor4t.trainmurdermystery.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.context.CommandContext;
import dev.doctor4t.trainmurdermystery.TMM;
import dev.doctor4t.trainmurdermystery.TMMConfig;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;

public class SetRoleCountCommand {
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(
                CommandManager.literal("tmm:setrolecount")
                        .requires(source -> source.hasPermissionLevel(2))
                        .then(CommandManager.literal("killer")
                                .then(CommandManager.argument("count", IntegerArgumentType.integer(1))
                                        .executes(SetRoleCountCommand::setKillerCount)))
                        .then(CommandManager.literal("vigilante")
                                .then(CommandManager.argument("count", IntegerArgumentType.integer(1))
                                        .executes(SetRoleCountCommand::setVigilanteCount)))
        );
    }

    private static int setKillerCount(CommandContext<ServerCommandSource> context) {
        return TMM.executeSupporterCommand(context.getSource(), () -> {
            int count = IntegerArgumentType.getInteger(context, "count");
            int playerCount = context.getSource().getServer().getCurrentPlayerCount();
            
            if (count > playerCount) {
                context.getSource().sendError(
                        Text.literal(String.format("错误: 杀手人数(%d)不能超过当前在线玩家人数(%d)", count, playerCount))
                );
                return;
            }
            
            TMMConfig.killerCount = count;
            TMMConfig.write("trainmurdermystery");
            
            context.getSource().sendFeedback(
                    () -> Text.literal(String.format("杀手人数已设置为: %d", count)),
                    true
            );
        });
    }

    private static int setVigilanteCount(CommandContext<ServerCommandSource> context) {
        return TMM.executeSupporterCommand(context.getSource(), () -> {
            int count = IntegerArgumentType.getInteger(context, "count");
            int playerCount = context.getSource().getServer().getCurrentPlayerCount();
            
            if (count > playerCount) {
                context.getSource().sendError(
                        Text.literal(String.format("错误: 警卫人数(%d)不能超过当前在线玩家人数(%d)", count, playerCount))
                );
                return;
            }
            
            TMMConfig.vigilanteCount = count;
            TMMConfig.write("trainmurdermystery");
            
            context.getSource().sendFeedback(
                    () -> Text.literal(String.format("警卫人数已设置为: %d", count)),
                    true
            );
        });
    }
}