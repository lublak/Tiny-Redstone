package com.dannyandson.tinyredstone.items;

import com.dannyandson.tinyredstone.setup.Registration;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.IItemRenderProperties;

import java.util.function.Consumer;

public class TinyBlockItem extends PanelCellItem {

    @Override
    public Component getName(ItemStack stack) {
        String thisName = super.getName(stack).getString();
        String fromBlockName = null;
        if (stack.hasTag()) {
            CompoundTag itemNBT = stack.getTag();
            CompoundTag madeFromTag = itemNBT.getCompound("made_from");
            if (madeFromTag.contains("namespace")) {
                fromBlockName = (new TranslatableComponent("block." + madeFromTag.getString("namespace") + "." + madeFromTag.getString("path"))).getString();
            }
        }
        if (fromBlockName==null){
            if (stack.getItem()== Registration.TINY_SOLID_BLOCK.get()){
                    fromBlockName = new TranslatableComponent("block.minecraft.white_wool").getString();
            } else if (stack.getItem()== Registration.TINY_TRANSPARENT_BLOCK.get()){
                fromBlockName = new TranslatableComponent("block.minecraft.glass").getString();
            }
        }
        return Component.nullToEmpty(thisName + " (" + fromBlockName + ")");
    }

    @Override
    public void initializeClient(Consumer<IItemRenderProperties> consumer) {
        consumer.accept(new IItemRenderProperties() {
                            @Override
                            public BlockEntityWithoutLevelRenderer getItemStackRenderer() {
                                return new TinyBlockItemRenderer(Minecraft.getInstance().getBlockEntityRenderDispatcher(), Minecraft.getInstance().getEntityModels());
                            }
                        }
        );
    }

}
