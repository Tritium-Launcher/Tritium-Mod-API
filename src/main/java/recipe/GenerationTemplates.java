package recipe;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Describes generation templates for a recipe type.
 * <p>
 * Each recipe generator may provide launcher-side generation templates
 * that the launcher renders using its template engine.
 * </p>
 *
 * @param variantOption   name of the option that selects which variant template
 *                        to use (e.g. {@code "mode"}). May be {@code null} for
 *                        single-template formats.
 * @param autoValue       the option value that triggers auto-detection
 *                        (e.g. {@code "auto"}). May be {@code null}.
 * @param expectsGrid     if {@code true} the launcher runs its grid-compactness
 *                        heuristic when the variant option equals {@code autoValue}.
 * @param gridSlots       slot range for grid auto-detection
 *                        (e.g. {@code "input_0..input_8"}).
 * @param gridCols        number of columns in the grid (e.g. {@code 3}).
 * @param formats         map of format ID → variant templates.
 *                        Each format maps variant names to template strings.
 *                        For single-variant formats the map contains a single
 *                        entry keyed {@code "_"}.
 * @param variantDefaults per-variant default values for generation options.
 *                        Maps variant key → option key → default value.
 *                        The launcher applies these when the variant changes.
 */
public record GenerationTemplates(
    String variantOption,
    String autoValue,
    boolean expectsGrid,
    String gridSlots,
    int gridCols,
    Map<String, Map<String, String>> formats,
    Map<String, Map<String, String>> variantDefaults
) {

    public GenerationTemplates {
        if (formats == null) {
            formats = Collections.emptyMap();
        } else {
            Map<String, Map<String, String>> copy = new LinkedHashMap<>();
            for (var entry : formats.entrySet()) {
                String fmtId = entry.getKey();
                Map<String, String> variants = entry.getValue();
                if (variants == null || variants.isEmpty()) continue;
                copy.put(fmtId, Collections.unmodifiableMap(new LinkedHashMap<>(variants)));
            }
            formats = Collections.unmodifiableMap(copy);
        }
        if (variantDefaults == null) {
            variantDefaults = Collections.emptyMap();
        }
    }

    /**
     * Creates templates with a single variant per format (no mode selector).
     */
    public static GenerationTemplates single(Map<String, String> formatTemplates) {
        Map<String, Map<String, String>> formats = new LinkedHashMap<>();
        for (var entry : formatTemplates.entrySet()) {
            formats.put(entry.getKey(), Map.of("_", entry.getValue()));
        }
        return new GenerationTemplates(null, null, false, null, 3, formats, Collections.emptyMap());
    }

    /**
     * Creates templates with a variant option for grid-based recipe types.
     */
    public static GenerationTemplates withVariants(
        String variantOption,
        String autoValue,
        String gridSlots,
        int gridCols,
        Map<String, Map<String, String>> formatVariants
    ) {
        return new GenerationTemplates(
            variantOption, autoValue, true, gridSlots, gridCols, formatVariants, Collections.emptyMap()
        );
    }
}
