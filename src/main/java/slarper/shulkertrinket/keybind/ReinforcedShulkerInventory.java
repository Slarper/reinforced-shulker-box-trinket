package slarper.shulkertrinket.keybind;

import atonkish.reinfcore.screen.ReinforcedStorageScreenHandler;
import atonkish.reinfcore.util.ReinforcingMaterial;
import atonkish.reinfshulker.block.ReinforcedShulkerBoxBlock;
import net.minecraft.block.Block;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.util.collection.DefaultedList;
import org.jetbrains.annotations.Nullable;

public class ReinforcedShulkerInventory extends ShulkerTrinketInventory {

    private final ReinforcingMaterial cachedMaterial;

    public ReinforcedShulkerInventory(ItemStack stack) {
        super(stack);
        Block block = ((BlockItem) stack.getItem()).getBlock();
        this.cachedMaterial = ((ReinforcedShulkerBoxBlock) block).getMaterial();
        this.items = DefaultedList.ofSize(this.cachedMaterial.getSize(), ItemStack.EMPTY);
    }
    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory inv, PlayerEntity player) {
        return ReinforcedStorageScreenHandler.createShulkerBoxScreen(this.cachedMaterial, syncId, inv, this);
    }


}
