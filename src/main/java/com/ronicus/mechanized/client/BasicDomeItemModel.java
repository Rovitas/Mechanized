package com.ronicus.mechanized.client;

import com.ronicus.mechanized.Mechanized;
import com.ronicus.mechanized.device.BasicDomeItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class BasicDomeItemModel extends GeoModel<BasicDomeItem> {
    @Override
    public ResourceLocation getModelResource(BasicDomeItem animatable) {
        return ResourceLocation.fromNamespaceAndPath(Mechanized.MODID, "geo/basic_dome.geo.json");
    }

    public ResourceLocation getTextureResource(BasicDomeItem animatable) {
        return ResourceLocation.fromNamespaceAndPath(Mechanized.MODID, "textures/item/basic_dome.png");
    }

    public ResourceLocation getAnimationResource(BasicDomeItem animatable) {
        return ResourceLocation.fromNamespaceAndPath(Mechanized.MODID, "animations/basic_dome.animation.json");
    }
}
