package com.ronicus.mechanized.client;

import com.ronicus.mechanized.device.BasicDomeItem;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class BasicDomeItemRenderer extends GeoItemRenderer<BasicDomeItem> {

    public BasicDomeItemRenderer() {
        super(new BasicDomeItemModel());
    }
}
