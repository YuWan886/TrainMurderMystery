package dev.doctor4t.trainmurdermystery.command;

import com.mojang.brigadier.CommandDispatcher;
import dev.doctor4t.trainmurdermystery.game.GameFunctions;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;
import org.jetbrains.annotations.NotNull;

public class StopCommand {
    public static void register(@NotNull CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(CommandManager.literal("tmm:stop")
                .requires(source -> source.hasPermissionLevel(2))
                .then(CommandManager.literal("force").executes(context -> {
                            GameFunctions.finalizeGame(context.getSource().getWorld());
                            return 1;
                        }
                ))
                .executes(context -> {
                    GameFunctions.stopGame(context.getSource().getWorld());
                    context.getSource().sendFeedback(
                            () -> Text.translatable("commands.tmm.stop")
                                    .styled(style -> style.withColor(0x00FF00)),
                            true
                    );
                    return 1;
                })
        );
    }
}