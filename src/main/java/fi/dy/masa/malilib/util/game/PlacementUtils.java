package fi.dy.masa.malilib.util.game;

import net.minecraft.block.BlockState;
import net.minecraft.item.ItemPlacementContext;

public class PlacementUtils
{
    /**
     * The checkMaterial flag is provided to deal with the vanilla inconsistency of checking the replaceability
     * of the block versus that of the material. ItemBlock offsets the position based on the replaceability
     * of the block, and then later checks if the block can be placed in the new offset position
     * based on the replaceability of the material instead. If <b>checkMaterial</b> is true, then the
     * replaceability of the material can override the non-replaceability of the block for the return value.
     */
    public static boolean isReplaceable(ItemPlacementContext ctx, boolean checkMaterial)
    {
        BlockState state = ctx.getWorld().getBlockState(ctx.getBlockPos());

        return state.canReplace(ctx) ||
               (checkMaterial && state.getMaterial().isReplaceable());
    }

    /**
     * Returns the block placement position for the provided initial click position.
     * This means that if the initial click position (such as from a ray trace against an existing block)
     * is not replaceable, then the placement position is the position adjacent to it, on the targeted side.
     * The checkMaterial flag is provided to deal with the vanilla inconsistency of checking the replaceability
     * of the block versus that of the material. ItemBlock offsets the position based on the replaceability
     * of the block, and then later checks if the block can be placed in the new offset position
     * based on the replaceability of the material instead. If <b>checkMaterial</b> is true, then the position
     * is only offset if both of them say they are not replaceable. If <b>checkMaterial</b> is false, then only
     * the block's replaceability is checked, and it's enough for the position to be offset.
     * @param checkMaterial whether or not to check the replaceability of the material too, or only the block
     */
    /* TODO 1.13+ port
    public static HitPosition getPlacementPositionForClickPosition(ItemPlacementContext ctx, HitPosition originalPos, boolean checkMaterial)
    {
        BlockPos origBlockPos = originalPos.getBlockPos();

        if (isReplaceable(ctx, origBlockPos, checkMaterial) == false)
        {
            BlockPos offsetBlockPos = origBlockPos.offset(originalPos.getSide());
            Vec3d origExactPos = originalPos.getExactPos();
            Vec3d offsetExactPos = new Vec3d(
                    origExactPos.x - origBlockPos.getX() + offsetBlockPos.getX(),
                    origExactPos.y - origBlockPos.getY() + offsetBlockPos.getY(),
                    origExactPos.z - origBlockPos.getZ() + offsetBlockPos.getZ());

            return HitPosition.of(offsetBlockPos, offsetExactPos, originalPos.getSide());
        }

        return originalPos;
    }
    */
}