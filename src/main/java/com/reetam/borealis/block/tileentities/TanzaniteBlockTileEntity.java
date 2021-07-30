package com.reetam.borealis.block.tileentities;

import com.reetam.borealis.block.TanzaniteBlock;
import com.reetam.borealis.registry.BorealisTileEntities;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.tileentity.BeaconTileEntity;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.AxisAlignedBB;

import java.util.List;

public class TanzaniteBlockTileEntity extends TileEntity implements ITickableTileEntity {

    private BeaconTileEntity beaconTile;

    public TanzaniteBlockTileEntity() {
        super(BorealisTileEntities.TANZANITE_BLOCK.get());
    }

    @Override
    public void tick() {
        if (this.getBlockState().getValue(TanzaniteBlock.ACTIVATED)) {

            boolean foundBeacon = true;
            if (this.getLevel().getGameTime() % 160L == 0L) {
                foundBeacon = false;
                for (int y = 0; y < this.worldPosition.getY(); y++) {
                    BlockState block = this.getLevel().getBlockState(this.worldPosition.below(y));
                    if (block.getBlock() == Blocks.BEACON) {
                        beaconTile = (BeaconTileEntity) this.getLevel().getBlockEntity(this.worldPosition.below(y));
                        foundBeacon = true;
                        break;
                    }
                }
            }

            if (foundBeacon) {
                if (beaconTile != null) {
                    AxisAlignedBB axisalignedbb = (new AxisAlignedBB(this.worldPosition)).inflate(beaconTile.getLevels() * 20 + 20).expandTowards(0.0D, 255, 0.0D);
                    List<PlayerEntity> players = this.getLevel().getEntitiesOfClass(PlayerEntity.class, axisalignedbb);

                    int i = 0;
                    if (beaconTile.getLevels() >= 4 && beaconTile.primaryPower == beaconTile.secondaryPower) {
                        i = 1;
                    }
                    int j = (9 + beaconTile.getLevels() * 2) * 20;

                    if (beaconTile.primaryPower != null && !this.level.isClientSide && !beaconTile.getBeamSections().isEmpty()) {
                        for (PlayerEntity playerentity : players) {
                            playerentity.addEffect(new EffectInstance(beaconTile.primaryPower, j, i, true, true));
                            playerentity.addEffect(new EffectInstance(Effects.NIGHT_VISION, j, i, true, true));
                        }

                        if (beaconTile.getLevels() >= 4 && beaconTile.primaryPower != beaconTile.secondaryPower && beaconTile.secondaryPower != null) {
                            for (PlayerEntity playerentity1 : players) {
                                playerentity1.addEffect(new EffectInstance(beaconTile.secondaryPower, j, 0, true, true));
                            }
                        }
                    } else if (beaconTile.getBeamSections().isEmpty()) {
                        this.getLevel().setBlock(this.worldPosition, this.getBlockState().setValue(TanzaniteBlock.ACTIVATED, false), 1);
                    }
                }
            } else {
                this.getLevel().setBlock(this.worldPosition, this.getBlockState().setValue(TanzaniteBlock.ACTIVATED, false), 1);
            }
        } else {
            if (this.getLevel().getGameTime() % 80L == 0L) {
                for (int y = 0; y < this.worldPosition.getY(); y++) {
                    BlockState block = this.getLevel().getBlockState(this.worldPosition.below(y));
                    if (block.getBlock() == Blocks.BEACON) {
                        this.getLevel().setBlock(this.worldPosition, this.getBlockState().setValue(TanzaniteBlock.ACTIVATED, true), 1);
                        beaconTile = (BeaconTileEntity) this.getLevel().getBlockEntity(this.worldPosition.below(y));
                        break;
                    }
                }
            }
        }
    }
}
