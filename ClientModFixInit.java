package com.novaventure.survivalessentials;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;

import static com.novaventure.survivalessentials.SurvivalEssentials.*;

public class ClientModInitFix implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        System.out.println("if you're reading this client mod initialization loaded fine™️");

        //apple leaves texture fix
        BlockRenderLayerMap.INSTANCE.putBlock(APPLE_LEAVES, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(APPLE_LEAVES, RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(APPLE_SAPLING, RenderLayer.getCutout());
    }
}
