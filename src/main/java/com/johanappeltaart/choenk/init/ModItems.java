package com.johanappeltaart.choenk.init;

import com.johanappeltaart.choenk.Choenk;
import com.johanappeltaart.choenk.items.ModChoenkAxeItem;
import com.johanappeltaart.choenk.tools.ModItemTier;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Choenk.MOD_ID);

    public static final RegistryObject<ModChoenkAxeItem> WOODEN_CHOENKAXE = ITEMS.register("wooden_choenkaxe",
            ()-> new ModChoenkAxeItem(ModItemTier.WOOD,1,-2.4f,new Item.Properties().group(Choenk.TAB).food(new Food.Builder().hunger(9).saturation(3.0f).effect(()->new EffectInstance(Effects.GLOWING,200),0.8f).effect(()->new EffectInstance(Effects.POISON,60),0.5f).build())));

}
