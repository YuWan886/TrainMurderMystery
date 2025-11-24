package dev.doctor4t.trainmurdermystery.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.context.CommandContext;
import dev.doctor4t.trainmurdermystery.TMM;
import dev.doctor4t.trainmurdermystery.game.GameConstants;
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
                        Text.translatable("commands.tmm.setrolecount.error.too_many_killers", count, playerCount)
                );
                return;
            }
            
            GameConstants.RoleConfig.killerCount = count;
            
            context.getSource().sendFeedback(
                    () -> Text.translatable("commands.tmm.setrolecount.killer", count)
                        .styled(style -> style.withColor(0x00FF00)),
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
                        Text.translatable("commands.tmm.setrolecount.too_many_vigilantes", count, playerCount)
                );
                return;
            }
            
            GameConstants.RoleConfig.vigilanteCount = count;
            
            context.getSource().sendFeedback(
                    () -> Text.translatable("commands.tmm.setrolecount.vigilante", count)
                        .styled(style -> style.withColor(0x00FF00)),
                    true
            );
        });
    }
}