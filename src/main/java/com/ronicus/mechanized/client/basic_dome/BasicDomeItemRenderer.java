package com.ronicus.mechanized.client.basic_dome;

import com.ronicus.mechanized.device.BasicDomeItem;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class BasicDomeItemRenderer extends GeoItemRenderer<BasicDomeItem> {
    public BasicDomeItemRenderer() {
        super(new BasicDomeItemModel());
    }

    @Override
    public long getInstanceId(BasicDomeItem animatable) {
        if (this.currentItemStack != null) {
            long id = GeoItem.getId(this.currentItemStack);
            if (id != Long.MAX_VALUE) {
                return id;
            }
            String state = BasicDomeItem.getDomeState(this.currentItemStack);
            return ((long) this.currentItemStack.getItem().hashCode() << 32) ^ state.hashCode();
        }
        return super.getInstanceId(animatable);
    }
}
