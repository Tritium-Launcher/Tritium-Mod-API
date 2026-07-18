package recipe;

import util.TJsonAdapter;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Descriptions for custom recipe types.
 */
public interface TRecipeTypeDescriptor
{
    /**
     * @return The recipe type ID
     */
    String getRecipeTypeId();

    /**
     * @return User-facing display name for this recipe type.
     */
    default String getDisplayName() {
        return getRecipeTypeId();
    }

    /**
     * Gets all components that define this recipe type.
     * @return List of all ingredient slots/tanks
     * @see TRecipeComponent
     */
    List<TRecipeComponent> getComponents();

    /**
     * @return The texture path
     */
    String getUITexture();

    /**
     * @return The UI layout
     * @see TUILayout
     */
    TUILayout getUILayout();

    /**
     * Returns a structured description of the recipe background sprite and its
     * interactive slot regions.
     * <p>
     * The default implementation derives the sprite from {@link #getUITexture()},
     * {@link #getUILayout()} and {@link #getComponents()}. Override this method
     * when you need to provide slot regions that differ from the raw component
     * list (e.g. to exclude energy/duration indicators or to supply richer
     * labelling).
     * </p>
     *
     * @return The recipe sprite descriptor
     * @see TRecipeSprite
     */
    default TRecipeSprite getSprite() {
        TUILayout layout = getUILayout();
        List<TSpriteRegion> regions = new java.util.ArrayList<>();
        for (TRecipeComponent comp : getComponents()) {
            String role = switch (comp.getCategory()) {
                case "SLOT" -> {
                    boolean isInput = comp instanceof TSlotComponent && ((TSlotComponent) comp).isInput();
                    yield isInput ? "INPUT" : "OUTPUT";
                }
                case "ENERGY" -> "ENERGY";
                case "DURATION" -> "DURATION";
                default -> "CUSTOM";
            };
            String slotType = comp instanceof TSlotComponent
                    ? ((TSlotComponent) comp).getSlotType()
                    : comp.getCategory();
            boolean displayOnly = comp instanceof TSlotComponent && ((TSlotComponent) comp).isDisplayOnly();
            regions.add(new TSpriteRegion(
                    comp.getId(),
                    comp instanceof TSlotComponent ? ((TSlotComponent) comp).getDisplayName() : comp.getId(),
                    role,
                    slotType,
                    comp.x(),
                    comp.y(),
                    comp.width(),
                    comp.height(),
                    displayOnly
            ));
        }
        return new TRecipeSprite(getUITexture(), layout.getWidth(), layout.getHeight(), regions, getSlotTextures());
    }

    /**
     * Returns the recipe generator for this recipe type.
     * <p>
     * The generator is used by the recipe builder's "Generate Code" feature
     * to turn filled slots into concrete recipe definitions (JSON, KubeJS, etc.).
     * Return {@link Optional#empty()} if this recipe type does not support
     * code generation.
     * </p>
     *
     * @return an Optional containing the generator, or empty
     * @see TRecipeGenerator
     */
    default Optional<TRecipeGenerator> getRecipeGenerator() { return Optional.empty(); }

    /**
     * Returns a map of slot type identifiers to custom background sprite paths.
     * <p>
     * When the recipe builder renders a slot whose {@code slotType} matches a key
     * in this map, it will use the corresponding sprite as the slot background
     * instead of the default coloured overlay. Texture paths use resource location
     * format.
     * </p>
     *
     * @return slot type → texture path map (empty by default)
     */
    default Map<String, String> getSlotTextures() {
        return Collections.emptyMap();
    }

    /**
     * @return An Optional of the JSON adapter or empty
     * @see TJsonAdapter
     */
    default Optional<TJsonAdapter> getRecipeJsonAdapter() { return Optional.empty(); }

    /**
     * @return An Optional adapter that converts runtime recipes into Tritium's display format.
     */
    default Optional<TRecipeDumpAdapter> getRecipeDumpAdapter() { return Optional.empty(); }

    /**
     * Gets additional metadata for this recipe type.
     * @return Map of metadata
     */
    default Map<String, Object> getMetadata() {
        return Collections.emptyMap();
    }

    /**
     * Gets the catalyst items for this recipe type.
     * @return List of item IDs that act as catalysts for this recipe type
     */
    default List<String> getCatalysts() {
        return Collections.emptyList();
    }

    /**
     * Returns additional recipe type IDs that this descriptor covers.
     * <p>
     * The descriptor is used as the fallback for these types when no dedicated
     * descriptor is registered. This allows a single descriptor to handle
     * multiple vanilla recipe types that share the same UI layout (e.g.
     * smelting, smoking, blasting).
     * </p>
     *
     * @return additional recipe type IDs (never {@code null})
     */
    default List<String> getAdditionalRecipeTypeIds() {
        return Collections.emptyList();
    }
}
