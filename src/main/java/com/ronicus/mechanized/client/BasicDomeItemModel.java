package com.ronicus.mechanized.client;

import com.ronicus.mechanized.Mechanized;
import com.ronicus.mechanized.device.BasicDomeItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.DefaultedItemGeoModel;

public class BasicDomeItemModel extends DefaultedItemGeoModel<BasicDomeItem> {
    public BasicDomeItemModel() {
        super(ResourceLocation.fromNamespaceAndPath(Mechanized.MODID, "basic_dome"));
    }

    @Override
    public ResourceLocation buildFormattedModelPath(ResourceLocation basePath) {
        return ResourceLocation.fromNamespaceAndPath(basePath.getNamespace(), "geo/" + basePath.getPath() + ".geo.json");
    }

    @Override
    public ResourceLocation buildFormattedAnimationPath(ResourceLocation basePath) {
        return ResourceLocation.fromNamespaceAndPath(basePath.getNamespace(), "animations/" + basePath.getPath() + ".animation.json");
    }
}
