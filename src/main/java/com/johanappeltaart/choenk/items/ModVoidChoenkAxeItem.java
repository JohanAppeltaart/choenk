package com.johanappeltaart.choenk.items;

import com.google.common.collect.ImmutableSet;
import com.johanappeltaart.choenk.Choenk;
import com.johanappeltaart.choenk.util.WorldUtils;
import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolItem;
import net.minecraft.stats.Stats;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.ForgeHooks;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class ModVoidChoenkAxeItem extends ToolItem{
    private static final Set<Block> EFFECTIVE_ON = ImmutableSet.of(Blocks.ACTIVATOR_RAIL, Blocks.COAL_ORE, Blocks.COBBLESTONE, Blocks.DETECTOR_RAIL, Blocks.DIAMOND_BLOCK, Blocks.DIAMOND_ORE, Blocks.POWERED_RAIL, Blocks.GOLD_BLOCK, Blocks.GOLD_ORE, Blocks.NETHER_GOLD_ORE, Blocks.ICE, Blocks.IRON_BLOCK, Blocks.IRON_ORE, Blocks.LAPIS_BLOCK, Blocks.LAPIS_ORE, Blocks.MOSSY_COBBLESTONE, Blocks.NETHERRACK, Blocks.PACKED_ICE, Blocks.BLUE_ICE, Blocks.RAIL, Blocks.REDSTONE_ORE, Blocks.SANDSTONE, Blocks.CHISELED_SANDSTONE, Blocks.CUT_SANDSTONE, Blocks.CHISELED_RED_SANDSTONE, Blocks.CUT_RED_SANDSTONE, Blocks.RED_SANDSTONE, Blocks.STONE, Blocks.GRANITE, Blocks.POLISHED_GRANITE, Blocks.DIORITE, Blocks.POLISHED_DIORITE, Blocks.ANDESITE, Blocks.POLISHED_ANDESITE, Blocks.STONE_SLAB, Blocks.SMOOTH_STONE_SLAB, Blocks.SANDSTONE_SLAB, Blocks.PETRIFIED_OAK_SLAB, Blocks.COBBLESTONE_SLAB, Blocks.BRICK_SLAB, Blocks.STONE_BRICK_SLAB, Blocks.NETHER_BRICK_SLAB, Blocks.QUARTZ_SLAB, Blocks.RED_SANDSTONE_SLAB, Blocks.PURPUR_SLAB, Blocks.SMOOTH_QUARTZ, Blocks.SMOOTH_RED_SANDSTONE, Blocks.SMOOTH_SANDSTONE, Blocks.SMOOTH_STONE, Blocks.STONE_BUTTON, Blocks.STONE_PRESSURE_PLATE, Blocks.POLISHED_GRANITE_SLAB, Blocks.SMOOTH_RED_SANDSTONE_SLAB, Blocks.MOSSY_STONE_BRICK_SLAB, Blocks.POLISHED_DIORITE_SLAB, Blocks.MOSSY_COBBLESTONE_SLAB, Blocks.END_STONE_BRICK_SLAB, Blocks.SMOOTH_SANDSTONE_SLAB, Blocks.SMOOTH_QUARTZ_SLAB, Blocks.GRANITE_SLAB, Blocks.ANDESITE_SLAB, Blocks.RED_NETHER_BRICK_SLAB, Blocks.POLISHED_ANDESITE_SLAB, Blocks.DIORITE_SLAB, Blocks.SHULKER_BOX, Blocks.BLACK_SHULKER_BOX, Blocks.BLUE_SHULKER_BOX, Blocks.BROWN_SHULKER_BOX, Blocks.CYAN_SHULKER_BOX, Blocks.GRAY_SHULKER_BOX, Blocks.GREEN_SHULKER_BOX, Blocks.LIGHT_BLUE_SHULKER_BOX, Blocks.LIGHT_GRAY_SHULKER_BOX, Blocks.LIME_SHULKER_BOX, Blocks.MAGENTA_SHULKER_BOX, Blocks.ORANGE_SHULKER_BOX, Blocks.PINK_SHULKER_BOX, Blocks.PURPLE_SHULKER_BOX, Blocks.RED_SHULKER_BOX, Blocks.WHITE_SHULKER_BOX, Blocks.YELLOW_SHULKER_BOX, Blocks.PISTON, Blocks.STICKY_PISTON, Blocks.PISTON_HEAD);

    public ModVoidChoenkAxeItem(IItemTier tier, int attackDamageIn, float attackSpeedIn, Properties builder) {
        super((float)attackDamageIn, attackSpeedIn, tier, EFFECTIVE_ON, builder.addToolType(net.minecraftforge.common.ToolType.PICKAXE, tier.getHarvestLevel()));
    }

    /**
     * Check whether this Item can harvest the given Block
     */
    public boolean canHarvestBlock(BlockState blockIn) {
        int i = this.getTier().getHarvestLevel();
        if (blockIn.getHarvestTool() == net.minecraftforge.common.ToolType.PICKAXE) {
            return i >= blockIn.getHarvestLevel();
        }else{
            return true;
        }
//        Material material = blockIn.getMaterial();
//        return material == Material.ROCK || material == Material.IRON || material == Material.ANVIL;
    }

    public float getDestroySpeed(ItemStack stack, BlockState state) {
        Material material = state.getMaterial();
        return material != Material.IRON && material != Material.ANVIL && material != Material.ROCK ? super.getDestroySpeed(stack, state) : this.efficiency;
    }

    @Override
    public boolean onBlockStartBreak(ItemStack stack, BlockPos StartBlockpos, PlayerEntity player) {
        World world = player.world;
        if (!world.isRemote
                && !player.isCreative()
                &&canHarvestBlock(world.getBlockState(StartBlockpos))
        ) {
//            IEnergyContainer energyContainer = StorageUtils.getEnergyContainer(stack, 0);
//            if (energyContainer == null) {
//                //If something went wrong and we don't have an energy container, just go to super
//                return super.onBlockStartBreak(stack, StartBlockpos, player);
//            }
//            DisassemblerMode mode = getMode(stack);
//            boolean extended = mode == DisassemblerMode.EXTENDED_VEIN;
//            if (extended || mode == DisassemblerMode.VEIN) {
//            if(true){
                BlockState state = world.getBlockState(StartBlockpos);
//                if (state.getBlock() instanceof BlockBounding) {
//                    //Even though we now handle breaking bounding blocks properly, don't allow vein mining
//                    // them as an added safety measure
//                    return super.onBlockStartBreak(stack, StartBlockpos, player);
//                }
                //If it is extended or should be treated as an ore
//                if (true || EFFECTIVE_ON.contains(state)) {
                    ServerPlayerEntity serverPlayerEntity = (ServerPlayerEntity) player;
                    List<BlockPos> found = findPositions(state, StartBlockpos, world, -1);
                    for (BlockPos foundPos : found) {
                        if (StartBlockpos.equals(foundPos)) {
                            continue;
                        }
                        BlockState foundState = world.getBlockState(foundPos);
//                        FloatingLong destroyEnergy = getDestroyEnergy(stack, foundState.getBlockHardness(world, foundPos));
//                        if (energyContainer.extract(destroyEnergy, Action.SIMULATE, AutomationType.MANUAL).smallerThan(destroyEnergy)) {
//                        if(false){
                            //If we don't have energy to break the block continue
                            //Note: We do not break as given the energy scales with hardness, so it is possible we still have energy to break another block
                            // Given we validate the blocks are the same but their block states may be different thus making them have different
                            // block hardness values in a modded context
//                            continue;
//                        }
                        int exp = ForgeHooks.onBlockBreakEvent(world, serverPlayerEntity.interactionManager.getGameType(), serverPlayerEntity, foundPos);
                        if (exp == -1) {
                            //If we can't actually break the block continue (this allows mods to stop us from vein mining into protected land)
//                            Choenk.LOGGER.info("protected land");
                            continue;
                        }
                        //Otherwise break the block
                        Block block = foundState.getBlock();
                        //Get the tile now so that we have it for when we try to harvest the block
                        TileEntity tileEntity = WorldUtils.getTileEntity(world, foundPos);
                        //Remove the block
                        boolean removed = foundState.removedByPlayer(world, foundPos, player, true, foundState.getFluidState());
                        if (removed) {
                            block.onPlayerDestroy(world, foundPos, foundState);
                            //Harvest the block allowing it to handle block drops, incrementing block mined count, and adding exhaustion
//                            block.harvestBlock(world, player, foundPos, foundState, tileEntity, stack);
                            player.addStat(Stats.ITEM_USED.get(this));
                            if (exp > 0) {
                                //If we have xp drop it
                                block.dropXpOnBlockBreak((ServerWorld) world, foundPos, exp);
                            }

                            //Use energy
//                            energyContainer.extract(destroyEnergy, Action.EXECUTE, AutomationType.MANUAL);
                        }
                    }
//                }
            }
//        }
        return super.onBlockStartBreak(stack, StartBlockpos, player);
    }
    //use 1 durability per chunk.
//    @Override
//    public boolean onBlockDestroyed(ItemStack stack, World worldIn, BlockState state, BlockPos pos, LivingEntity entityLiving) {
//        stack.damageItem(1, entityLiving, (entity) -> {
//            entity.sendBreakAnimation(EquipmentSlotType.MAINHAND);
//        });
//        return super.onBlockDestroyed(stack, worldIn, state, pos, entityLiving);
//    }

    private List<BlockPos> findPositions(BlockState state, BlockPos location, World world, int maxRange) {
        Choenk.LOGGER.info("FINDS INBNANANA BANANA");
        List<BlockPos> found = new ArrayList<>();
        Set<BlockPos> checked = new ObjectOpenHashSet<>();
        found.add(location);
        Block startBlock = state.getBlock();
        ChunkPos startChunk = world.getChunkAt(location).getPos();
//        int maxCount = MekanismConfig.gear.disassemblerMiningCount.get() - 1;
        int maxCount = 65280;
//        for (int i = 0; i < found.size(); i++) {// runonce
//            BlockPos blockPos = found.get(i);//runonce
            BlockPos blockPos = found.get(0);
            checked.add(blockPos);
//            for (BlockPos pos : BlockPos.getAllInBoxMutable(blockPos.add(-1, -1, -1), blockPos.add(1, 1, 1))) {
            for (BlockPos pos : BlockPos.getAllInBoxMutable(blockPos.add(-16, -255, -16), blockPos.add(16, 255, 16))) {
                //We can check contains as mutable
                if (!checked.contains(pos)) {
//                    if (maxRange == -1 || WorldUtils.distanceBetween(location, pos) <= maxRange) {//true
                        Optional<BlockState> blockState = WorldUtils.getBlockState(world, pos);
//                        if (blockState.isPresent() && startBlock == blockState.get().getBlock()) {
                        //29013888888120348549023902349049023849023849023849023===================================================
                        if (blockState.isPresent()
                                && canHarvestBlock(blockState.get())
                                &&startChunk==world.getChunkAt(pos).getPos()
                                &&blockState.get().getBlockHardness(world,blockPos)!=-1
                        ) {
                            //===========
                            //Make sure to add it as immutable
                            found.add(pos.toImmutable());
                            //Note: We do this for all blocks we find/attempt to mine, not just ones we do mine, as it is a bit simpler
                            // and also represents those blocks getting checked by the vein mining for potentially being able to be mined
//                            Mekanism.packetHandler.sendToAllTracking(new PacketLightningRender(LightningPreset.TOOL_AOE, Objects.hash(blockPos, pos),
//                                    Vector3d.copyCentered(blockPos), Vector3d.copyCentered(pos), 10), world, blockPos);
                            if (found.size() > maxCount) {
                                Choenk.LOGGER.info("more than maxcount count /size");
                                Choenk.LOGGER.info(maxCount);
                                Choenk.LOGGER.info(found.size());
                                //limit is 10
//                                Choenk.LOGGER.info(found.toString());
                                return found;
                            }
                        }
//                    }
                }
            }
//        }//runonce
        return found;
    }

}
