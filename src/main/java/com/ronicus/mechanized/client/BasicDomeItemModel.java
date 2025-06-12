package com.ronicus.mechanized.client;

import com.ronicus.mechanized.Mechanized;
import com.ronicus.mechanized.device.BasicDomeItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class BasicDomeItemModel extends GeoModel<BasicDomeItem> {
    @Override
    public ResourceLocation getModelResource(BasicDomeItem basicDomeItem) {
        return new ResourceLocation(Mechanized.MODID,"geo/basic_dome.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(BasicDomeItem basicDomeItem) {
        return new ResourceLocation(Mechanized.MODID,"textures/item/basic_dome.png");
    }

    @Override
    public ResourceLocation getAnimationResource(BasicDomeItem basicDomeItem) {
        return new ResourceLocation(Mechanized.MODID,"animations/basic_dome.animation.json");
    }
}
