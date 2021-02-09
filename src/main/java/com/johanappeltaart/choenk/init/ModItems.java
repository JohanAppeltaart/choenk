package com.johanappeltaart.choenk.init;

import com.johanappeltaart.choenk.Choenk;
import com.johanappeltaart.choenk.items.ModChoenkAxeItem;
import com.johanappeltaart.choenk.items.ModVoidChoenkAxeItem;
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
            ()-> new ModChoenkAxeItem(ModItemTier.WOOD,1,-2.4f, new Item.Properties().group(Choenk.TAB)));

    public static final RegistryObject<ModVoidChoenkAxeItem> VOID_CHOENKAXE = ITEMS.register("void_choenkaxe",
            ()-> new ModVoidChoenkAxeItem(ModItemTier.WOOD,1,-2.4f,new Item.Properties().group(Choenk.TAB)));

}
