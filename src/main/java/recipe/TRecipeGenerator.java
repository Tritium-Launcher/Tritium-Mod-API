package recipe;

import org.jspecify.annotations.Nullable;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Generates recipe definitions from user-provided slot fills in the recipe
 * builder.
 * <p>
 * Each {@link TRecipeTypeDescriptor} may provide a generator that knows the
 * schema of its own recipe type. For example, the crafting generator maps
 * 9 input slots to a 3×3 shaped or shapeless pattern.
 * </p>
 *
 * @see TRecipeTypeDescriptor#getRecipeGenerator()
 */
public interface TRecipeGenerator {

    /**
     * Lists the output formats this generator supports.
     *
     * @return supported formats (never {@code null})
     */
    List<TGenerationFormat> getSupportedFormats();

    /**
     * Returns the list of user-configurable options this generator supports.
     * <p>
     * The launcher renders these as widgets in the recipe builder toolbar.
     * Collected values are passed via the {@code options} map to
     * {@link #generate(Map, String, Map)}.
     *
     * @return generation options (never {@code null})
     */
    default List<TGenerationOption> getGenerationOptions() {
        return Collections.emptyList();
    }

    /**
     * Returns launcher-side generation templates for this generator.
     *
     * @return generation templates, or {@code null} to rely on in-game generation
     */
    default @Nullable GenerationTemplates getGenerationTemplates() {
        return null;
    }

    /**
     * Generates a recipe definition string for the given slot fills in
     * the requested format.
     *
     * @param fills    map of component ID → slot fill data
     * @param formatId the target format identifier (one of
     *                 {@link #getSupportedFormats()})
     * @param options  optional hints for generation
     *                 (e.g. {@code "mode" → "shaped"/"shapeless"}).
     *                 May be empty.
     * @return the generated recipe as a string (JSON, script snippet, etc.)
     * @throws IllegalArgumentException if {@code formatId} is not supported
     */
    String generate(Map<String, SlotFill> fills, String formatId, Map<String, String> options);

    /**
     * Convenience overload without extra options.
     */
    default String generate(Map<String, SlotFill> fills, String formatId) {
        return generate(fills, formatId, Collections.emptyMap());
    }
}
