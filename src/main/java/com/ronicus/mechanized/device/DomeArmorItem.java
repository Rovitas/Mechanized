package com.ronicus.mechanized.device;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Equipable;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.UUID;

public class DomeArmorItem extends Item implements Equipable {
    protected final ArmorItem.Type type;
    private final Multimap<Attribute, AttributeModifier> defaultModifiers;
    public DomeArmorItem(ArmorItem.Type type, Properties properties) {
        super(properties);
        this.type = type;
        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
        UUID uuid = UUID.fromString("845DB27C-C624-495F-8C9F-6020A9A58B6B");
        builder.put(Attributes.MOVEMENT_SPEED, new AttributeModifier(uuid,"Armor speed",(double)0.5, AttributeModifier.Operation.ADDITION));
        builder.put(Attributes.JUMP_STRENGTH, new AttributeModifier(uuid,"Jump",(double)1.5, AttributeModifier.Operation.ADDITION));
        this.defaultModifiers = builder.build();
    }

    public ArmorItem.Type getType() {
        return this.type;
    }

    public EquipmentSlot getEquipmentSlot() {
        return this.type.getSlot();
    }

    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        return this.swapWithEquipmentSlot(this, level, player, hand);
    }

    public Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(EquipmentSlot slot) {
        return slot == this.type.getSlot() ? this.defaultModifiers : super.getDefaultAttributeModifiers(slot);
    }
}
