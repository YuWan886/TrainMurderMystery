package dev.doctor4t.trainmurdermystery.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.BoolArgumentType;
import dev.doctor4t.trainmurdermystery.cca.TrainWorldComponent;
import dev.doctor4t.trainmurdermystery.command.argument.TimeOfDayArgumentType;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;

public class SetVisualCommand {
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(CommandManager.literal("tmm:setVisual")
                        .requires(source -> source.hasPermissionLevel(2))
                .then(CommandManager.literal("snow")
                        .then(CommandManager.argument("enabled", BoolArgumentType.bool())
                                .executes(context -> executeSnow(context.getSource(), BoolArgumentType.getBool(context, "enabled")))))
                .then(CommandManager.literal("screenshake")
                        .then(CommandManager.argument("enabled", BoolArgumentType.bool())
                                .executes(context -> executeScreenshake(context.getSource(), BoolArgumentType.getBool(context, "enabled")))))
                .then(CommandManager.literal("time")
                        .then(CommandManager.argument("timeOfDay", TimeOfDayArgumentType.timeofday())
                                .executes(context -> executeTimeOfDay(context.getSource(), TimeOfDayArgumentType.getTimeofday(context, "timeOfDay")))))
        );
    }

    private static int executeSnow(ServerCommandSource source, boolean enabled) {
        TrainWorldComponent trainWorldComponent = TrainWorldComponent.KEY.get(source.getWorld());
        trainWorldComponent.setSnow(enabled);
        return 1;
    }

    private static int executeScreenshake(ServerCommandSource source, boolean enabled) {
        TrainWorldComponent trainWorldComponent = TrainWorldComponent.KEY.get(source.getWorld());
        trainWorldComponent.setScreenshake(enabled);
        return 1;
    }

    private static int executeTimeOfDay(ServerCommandSource source, TrainWorldComponent.TimeOfDay timeOfDay) {
        TrainWorldComponent trainWorldComponent = TrainWorldComponent.KEY.get(source.getWorld());
        trainWorldComponent.setTimeOfDay(timeOfDay);
        return 1;
    }

}
