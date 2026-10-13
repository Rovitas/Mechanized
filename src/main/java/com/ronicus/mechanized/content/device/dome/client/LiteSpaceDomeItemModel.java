package com.ronicus.mechanized.content.device.dome.client;

import com.ronicus.mechanized.Mechanized;
import com.ronicus.mechanized.content.device.dome.LiteSpaceDomeItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.DefaultedItemGeoModel;

public class LiteSpaceDomeItemModel extends DefaultedItemGeoModel<LiteSpaceDomeItem> {
    public LiteSpaceDomeItemModel() {
        super(ResourceLocation.fromNamespaceAndPath(Mechanized.MODID, "lite_space_dome"));
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
