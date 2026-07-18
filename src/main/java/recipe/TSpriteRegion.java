package recipe;

/**
 * A named interactive region on a recipe type's sprite/background texture.
 * <p>
 * Each region corresponds to a slot, tank, energy indicator, or other
 * interactive element that the recipe builder should render as a drop target.
 * </p>
 *
 * @param id          Unique identifier for this region (matches the component ID).
 * @param label       Display name (e.g. "Input 1", "Result").
 * @param role        The functional role: {@code "INPUT"}, {@code "OUTPUT"},
 *                    {@code "FUEL"}, {@code "ENERGY"}, {@code "DURATION"}, or {@code "CUSTOM"}.
 * @param slotType    The type of value this region accepts: {@code "ITEM"},
 *                    {@code "FLUID"}, or a custom type identifier.
 * @param x           X position of the region on the sprite (pixels).
 * @param y           Y position of the region on the sprite (pixels).
 * @param width       Width of the region (pixels).
 * @param height      Height of the region (pixels).
 * @param displayOnly If {@code true} this region is display-only (no drops or interactions).
 */
public record TSpriteRegion(
        String id,
        String label,
        String role,
        String slotType,
        int x,
        int y,
        int width,
        int height,
        boolean displayOnly
) {
}
