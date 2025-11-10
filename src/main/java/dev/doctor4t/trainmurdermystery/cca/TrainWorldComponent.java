package dev.doctor4t.trainmurdermystery.cca;

import dev.doctor4t.trainmurdermystery.TMM;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.StringIdentifiable;
import net.minecraft.world.World;
import org.ladysnake.cca.api.v3.component.ComponentKey;
import org.ladysnake.cca.api.v3.component.ComponentRegistry;
import org.ladysnake.cca.api.v3.component.sync.AutoSyncedComponent;
import org.ladysnake.cca.api.v3.component.tick.ClientTickingComponent;
import org.ladysnake.cca.api.v3.component.tick.ServerTickingComponent;

public class TrainWorldComponent implements AutoSyncedComponent, ServerTickingComponent, ClientTickingComponent {
    public static final ComponentKey<TrainWorldComponent> KEY = ComponentRegistry.getOrCreate(TMM.id("train"), TrainWorldComponent.class);

    private final World world;
    private float trainSpeed = 0; // im km/h
    private int time = 0;
    private boolean snow = true;
    private boolean isScreenshake = true;
    private TimeOfDay timeOfDay = TimeOfDay.NIGHT;

    public TrainWorldComponent(World world) {
        this.world = world;
    }

    private void sync() {
        TrainWorldComponent.KEY.sync(this.world);
    }

    public void setTrainSpeed(float trainSpeed) {
        this.trainSpeed = trainSpeed;
        this.sync();
    }

    public float getTrainSpeed() {
        return trainSpeed;
    }

    public float getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
        this.sync();
    }

    public boolean isSnowing() {
        return snow;
    }

    public void setSnow(boolean snow) {
        this.snow = snow;
        this.sync();
    }

    public boolean isScreenshake() {
        return isScreenshake;
    }

    public void setScreenshake(boolean isScreenshake) {
        this.isScreenshake = isScreenshake;
        this.sync();
    }

    public TimeOfDay getTimeOfDay() {
        return timeOfDay;
    }

    public void setTimeOfDay(TimeOfDay timeOfDay) {
        this.timeOfDay = timeOfDay;
        this.sync();
    }

    @Override
    public void readFromNbt(NbtCompound nbtCompound, RegistryWrapper.WrapperLookup wrapperLookup) {
        this.trainSpeed = nbtCompound.getFloat("Speed");
        this.setTime(nbtCompound.getInt("Time"));
        this.setSnow(nbtCompound.getBoolean("Snow"));
        this.setTimeOfDay(TimeOfDay.valueOf(nbtCompound.getString("TimeOfDay")));
    }

    @Override
    public void writeToNbt(NbtCompound nbtCompound, RegistryWrapper.WrapperLookup wrapperLookup) {
        nbtCompound.putFloat("Speed", trainSpeed);
        nbtCompound.putInt("Time", time);
        nbtCompound.putBoolean("Snow", snow);
        nbtCompound.putString("TimeOfDay", timeOfDay.name());
    }

    @Override
    public void clientTick() {
        tickTime();
    }

    private void tickTime() {
        if (trainSpeed > 0) {
            time++;
        } else {
            time = 0;
        }
    }

    @Override
    public void serverTick() {
        tickTime();

        ServerWorld serverWorld = (ServerWorld) world;
        serverWorld.setTimeOfDay(timeOfDay.time);
    }

    public enum TimeOfDay implements StringIdentifiable {
        DAY(6000),
        NIGHT(18000),
        DUSK(12800);

        final int time;

        TimeOfDay(int time) {
            this.time = time;
        }

        @Override
        public String asString() {
            return this.name();
        }
    }

}
