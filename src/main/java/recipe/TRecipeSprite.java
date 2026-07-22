package recipe;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Describes a mod-provided sprite for a recipe type,
 * including the pixel regions where items, fluids, or other values can be
 * placed by the recipe builder.
 *
 * @param imagePath Path to the PNG image in the mod's resources.
 * @param width     Pixel width of the sprite.
 * @param height    Pixel height of the sprite.
 * @param regions   The interactive slot regions on this sprite.
 * @param slotTextures Maps slot type identifiers to custom background sprite paths
 *                    used when rendering that slot type on this recipe UI.
 * @see TSpriteRegion
 */
@SuppressWarnings("unused")
public record TRecipeSprite(
        String imagePath,
        int width,
        int height,
        List<TSpriteRegion> regions,
        Map<String, String> slotTextures
) {
    /**
     * Creates a sprite with no custom slot textures.
     * The launcher will use default colored overlays for all slot types.
     *
     * @param imagePath path to the sprite in the mod's resources
     * @param width     pixel width of the sprite
     * @param height    pixel height of the sprite
     * @param regions   the interactive slot regions on this sprite
     */
    public TRecipeSprite(String imagePath, int width, int height, List<TSpriteRegion> regions) {
        this(imagePath, width, height, regions, Collections.emptyMap());
    }
}
