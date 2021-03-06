package slarper.shulkertrinket.keybind;

import atonkish.reinfshulker.block.ReinforcedShulkerBoxBlock;
import dev.emi.trinkets.api.TrinketsApi;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.block.ShulkerBoxBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;

public class NetworkingReceiver implements ModInitializer {
    @Override
    public void onInitialize() {
        ServerPlayNetworking.registerGlobalReceiver(
                ShulkerTrinketPackets.OPEN_SHULKER_BOX,
                (server, player, handler, buf, responseSender)-> server.execute(() -> openShulkerBox(player)
                )
        );
    }

    public static void openShulkerBox(ServerPlayerEntity player){
        if(TrinketsApi.getTrinketComponent(player).isPresent()) {
            ItemStack stack = TrinketsApi.getTrinketComponent(player).get().getInventory().get("chest").get("back").getStack(0);
            if(isShulkerBox(stack)) {
                if (isReinforcedShulkerBox(stack)){
                    player.openHandledScreen(new ReinforcedShulkerInventory(stack));
                } else {
                    player.openHandledScreen(new ShulkerTrinketInventory(stack));
                }
            }
        }
    }

    public static boolean isShulkerBox(ItemStack stack){
        if (stack.getItem() instanceof BlockItem){
            return ((BlockItem) stack.getItem()).getBlock() instanceof ShulkerBoxBlock;
        }
        return false;
    }

    public static boolean isReinforcedShulkerBox(ItemStack stack){
        if (stack.getItem() instanceof BlockItem){
            return ((BlockItem) stack.getItem()).getBlock() instanceof ReinforcedShulkerBoxBlock;
        }
        return false;
    }
}
