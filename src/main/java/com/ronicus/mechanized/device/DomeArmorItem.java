package com.ronicus.mechanized.device;

import com.ronicus.mechanized.Mechanized;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Equipable;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.level.Level;

public class DomeArmorItem extends Item implements Equipable {
    protected final ArmorItem.Type type;

    public DomeArmorItem(ArmorItem.Type type, Properties properties) {
        super(properties.attributes(createAttributes(type)));
        this.type = type;
    }

    private static ItemAttributeModifiers createAttributes(ArmorItem.Type type) {
        EquipmentSlotGroup slotGroup = EquipmentSlotGroup.bySlot(type.getSlot());
        return ItemAttributeModifiers.builder()
                .add(
                        Attributes.MOVEMENT_SPEED,
                        new AttributeModifier(
                                ResourceLocation.fromNamespaceAndPath(Mechanized.MODID, "armor_speed"),
                                0.5,
                                AttributeModifier.Operation.ADD_VALUE
                        ),
                        slotGroup
                )
                .add(
                        Attributes.JUMP_STRENGTH,
                        new AttributeModifier(
                                ResourceLocation.fromNamespaceAndPath(Mechanized.MODID, "armor_jump"),
                                1.5,
                                AttributeModifier.Operation.ADD_VALUE
                        ),
                        slotGroup
                )
                .build();
    }

    public ArmorItem.Type getType() {
        return this.type;
    }

    @Override
    public EquipmentSlot getEquipmentSlot() {
        return this.type.getSlot();
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        return this.swapWithEquipmentSlot(this, level, player, hand);
    }
}
