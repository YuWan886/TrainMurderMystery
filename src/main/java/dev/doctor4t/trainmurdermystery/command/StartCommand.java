package dev.doctor4t.trainmurdermystery.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import dev.doctor4t.trainmurdermystery.TMM;
import dev.doctor4t.trainmurdermystery.cca.GameWorldComponent;
import dev.doctor4t.trainmurdermystery.command.argument.TMMGameModeArgumentType;
import dev.doctor4t.trainmurdermystery.game.GameConstants;
import dev.doctor4t.trainmurdermystery.game.GameFunctions;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;

public class StartCommand {
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(
                CommandManager.literal("tmm:start")
                        .requires(source -> source.hasPermissionLevel(2))
                        .then(CommandManager.argument("gameMode", TMMGameModeArgumentType.gamemode())
                                .then(CommandManager.argument("startTimeInMinutes", IntegerArgumentType.integer(1))
                                        .executes(context -> execute(context.getSource(), TMMGameModeArgumentType.getGamemode(context, "gameMode"), IntegerArgumentType.getInteger(context, "startTimeInMinutes")))
                                )
                                .executes(context -> {
                                            GameWorldComponent.GameMode gameMode = TMMGameModeArgumentType.getGamemode(context, "gameMode");
                                            return execute(context.getSource(), gameMode, gameMode.startTime);
                                        }
                                )
                        )
        );
    }

    private static int execute(ServerCommandSource source, GameWorldComponent.GameMode gameMode, int minutes) {
        if (GameWorldComponent.KEY.get(source.getWorld()).isRunning()) {
            source.sendError(Text.translatable("game.start_error.game_running"));
            return -1;
        }

        return TMM.executeSupporterCommand(source,
                () -> {
                    GameFunctions.startGame(source.getWorld(), gameMode, GameConstants.getInTicks(minutes, 0));
                    source.sendFeedback(
                        () -> Text.translatable("commands.tmm.start", gameMode.toString(), minutes)
                            .styled(style -> style.withColor(0x00FF00)),
                        true
                    );
                }
        );
    }
}
