package dev.doctor4t.trainmurdermystery.item;

import dev.doctor4t.trainmurdermystery.block.SmallDoorBlock;
import dev.doctor4t.trainmurdermystery.block_entity.SmallDoorBlockEntity;
import dev.doctor4t.trainmurdermystery.index.TrainMurderMysterySounds;
import net.minecraft.block.BlockState;
import net.minecraft.block.enums.DoubleBlockHalf;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.LoreComponent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.sound.SoundCategory;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.List;

public class KeyItem extends Item {
    public KeyItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        PlayerEntity player = context.getPlayer();
        World world = context.getWorld();
        BlockPos pos = context.getBlockPos();
        BlockState state = world.getBlockState(pos);

        if (state.getBlock() instanceof SmallDoorBlock) {
            BlockPos lowerPos = state.get(SmallDoorBlock.HALF) == DoubleBlockHalf.LOWER ? pos : pos.down();
            if (world.getBlockEntity(lowerPos) instanceof SmallDoorBlockEntity entity) {
                // Sneaking creative player with key sets the door to require a key with the same name
                ItemStack mainHandStack = player.getMainHandStack();
                LoreComponent loreComponent = mainHandStack.get(DataComponentTypes.LORE);
                if (loreComponent != null) {
                    String roomName = loreComponent.lines().getFirst().getString();
                    if (player.isCreative() && player.isSneaking()) {
                        entity.setKeyName(roomName);
                        return ActionResult.SUCCESS;
                    } else {
                        if (roomName.equals(entity.getKeyName()) || entity.getKeyName().equals("")) {
                            SmallDoorBlock.toggleDoor(state, world, entity, lowerPos);
                            if (!world.isClient)
                                world.playSound(null, lowerPos.getX() + .5f, lowerPos.getY() + 1, lowerPos.getZ() + .5f, TrainMurderMysterySounds.ITEM_KEY_DOOR, SoundCategory.BLOCKS, 1f, 1f);
                            return ActionResult.SUCCESS;
                        } else {
                            if (!world.isClient) {
                                world.playSound(null, lowerPos.getX() + .5f, lowerPos.getY() + 1, lowerPos.getZ() + .5f, TrainMurderMysterySounds.BLOCK_DOOR_LOCKED, SoundCategory.BLOCKS, 1f, 1f);
                            } else {
                                player.sendMessage(Text.translatable("tip.door.requires_different_key"), true);
                            }
                            return ActionResult.FAIL;
                        }
                    }
                }
            }

            return ActionResult.PASS;
        }

        return super.useOnBlock(context);
    }
}
