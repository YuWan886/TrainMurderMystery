package dev.doctor4t.trainmurdermystery.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import dev.doctor4t.trainmurdermystery.TMM;
import dev.doctor4t.trainmurdermystery.cca.TrainWorldComponent;
import dev.doctor4t.trainmurdermystery.command.argument.TimeOfDayArgumentType;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;

import java.util.function.BiConsumer;

public class SetVisualCommand {
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(CommandManager.literal("tmm:setVisual")
                .requires(source -> source.hasPermissionLevel(2))
                .then(CommandManager.literal("snow")
                        .then(CommandManager.argument("enabled", BoolArgumentType.bool())
                                .executes(context -> executeSnow(context.getSource(), BoolArgumentType.getBool(context, "enabled")))))
                .then(CommandManager.literal("fog")
                        .then(CommandManager.argument("enabled", BoolArgumentType.bool())
                                .executes(context -> executeFog(context.getSource(), BoolArgumentType.getBool(context, "enabled")))))
                .then(CommandManager.literal("hud")
                        .then(CommandManager.argument("enabled", BoolArgumentType.bool())
                                .executes(context -> executeHud(context.getSource(), BoolArgumentType.getBool(context, "enabled")))))
                .then(CommandManager.literal("trainSpeed")
                        .then(CommandManager.argument("speed", IntegerArgumentType.integer(0))
                                .executes(context -> executeSpeed(context.getSource(), IntegerArgumentType.getInteger(context, "speed")))))
                .then(CommandManager.literal("time")
                        .then(CommandManager.argument("timeOfDay", TimeOfDayArgumentType.timeofday())
                                .executes(context -> executeTimeOfDay(context.getSource(), TimeOfDayArgumentType.getTimeofday(context, "timeOfDay")))))
                .then(CommandManager.literal("reset")
                        .executes(context -> reset(context.getSource())))
        );
    }

    private static int reset(ServerCommandSource source) {
        TrainWorldComponent trainWorldComponent = TrainWorldComponent.KEY.get(source.getWorld());
        trainWorldComponent.reset();
        source.sendFeedback(
            () -> Text.translatable("commands.tmm.setvisual.reset")
                .styled(style -> style.withColor(0x00FF00)),
            true
        );
        return 1;
    }

    private static int executeSnow(ServerCommandSource source, boolean enabled) {
        return TMM.executeSupporterCommand(source,
                () -> {
                    TrainWorldComponent.KEY.get(source.getWorld()).setSnow(enabled);
                    source.sendFeedback(
                        () -> Text.translatable("commands.tmm.setvisual.snow", enabled)
                            .styled(style -> style.withColor(0x00FF00)),
                        true
                    );
                }
        );
    }
    
    private static int executeFog(ServerCommandSource source, boolean enabled) {
        return TMM.executeSupporterCommand(source,
                () -> {
                    TrainWorldComponent.KEY.get(source.getWorld()).setFog(enabled);
                    source.sendFeedback(
                        () -> Text.translatable("commands.tmm.setvisual.fog", enabled)
                            .styled(style -> style.withColor(0x00FF00)),
                        true
                    );
                }
        );
    }
    
    private static int executeHud(ServerCommandSource source, boolean enabled) {
        return TMM.executeSupporterCommand(source,
                () -> {
                    TrainWorldComponent.KEY.get(source.getWorld()).setHud(enabled);
                    source.sendFeedback(
                        () -> Text.translatable("commands.tmm.setvisual.hud", enabled)
                            .styled(style -> style.withColor(0x00FF00)),
                        true
                    );
                }
        );
    }
    
    private static int executeSpeed(ServerCommandSource source, int speed) {
        return TMM.executeSupporterCommand(source,
                () -> {
                    TrainWorldComponent.KEY.get(source.getWorld()).setSpeed(speed);
                    source.sendFeedback(
                        () -> Text.translatable("commands.tmm.setvisual.trainspeed", speed)
                            .styled(style -> style.withColor(0x00FF00)),
                        true
                    );
                }
        );
    }
    
    private static int executeTimeOfDay(ServerCommandSource source, TrainWorldComponent.TimeOfDay timeOfDay) {
        return TMM.executeSupporterCommand(source,
                () -> {
                    TrainWorldComponent.KEY.get(source.getWorld()).setTimeOfDay(timeOfDay);
                    source.sendFeedback(
                        () -> Text.translatable("commands.tmm.setvisual.time", timeOfDay)
                            .styled(style -> style.withColor(0x00FF00)),
                        true
                    );
                }
        );
    }
}
