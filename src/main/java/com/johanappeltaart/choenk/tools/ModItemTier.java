package com.johanappeltaart.choenk.tools;

import java.util.function.Supplier;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.tags.ItemTags;

public enum ModItemTier implements IItemTier {

    QUICK(5, 30, 80.0f, 10.0f, 15, () -> {
            return Ingredient.fromItems(Items.OAK_WOOD);
//            return Ingredient.fromItems(ModItems.BANANA_IRON.get());
    }),
    WOOD(0, 59, 2.0F, 0.0F, 15, () -> {
        return Ingredient.fromTag(ItemTags.PLANKS);
    });

        private final int harvestLevel;
        private final int maxUses;
        private final float efficiency;
        private final float attackDamage;
        private final int enchantability;
        private final Supplier<Ingredient> repairMaterial;

        ModItemTier(int harvestLevel, int maxUses, float efficiency, float attackDamage, int enchantability, Supplier<Ingredient> repairMaterial) {
            this.harvestLevel = harvestLevel;
            this.maxUses = maxUses;
            this.efficiency = efficiency;
            this.attackDamage = attackDamage;
            this.enchantability = enchantability;
            this.repairMaterial = repairMaterial;
        }

        @Override
        public int getMaxUses() {
            // TODO Auto-generated method stub
            return maxUses;
        }

        @Override
        public float getEfficiency() {
            // TODO Auto-generated method stub
            return efficiency;
        }

        @Override
        public float getAttackDamage() {
            // TODO Auto-generated method stub
            return attackDamage;
        }

        @Override
        public int getHarvestLevel() {
            // TODO Auto-generated method stub
            return harvestLevel;
        }

        @Override
        public int getEnchantability() {
            // TODO Auto-generated method stub
            return enchantability;
        }

        @Override
        public Ingredient getRepairMaterial() {
            // TODO Auto-generated method stub
            return repairMaterial.get();
        }

}
