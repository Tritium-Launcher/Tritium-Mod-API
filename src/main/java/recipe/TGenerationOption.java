package recipe;

import org.jspecify.annotations.Nullable;

/**
 * A user-configurable option for recipe code generation.
 * <p>
 * Each {@link TRecipeGenerator} may expose a list of these options.
 * The launcher renders them as input widgets (text fields, checkboxes,
 * dropdowns, etc.) in the recipe builder toolbar. Collected values are
 * passed back to the generator via the {@code options} map.
 * </p>
 *
 * @param key          unique option identifier within the generator
 * @param label        label shown in the UI
 * @param type         widget type hint (e.g. {@code "text"}, {@code "number"},
 *                     {@code "checkbox"})
 * @param placeholder  optional placeholder text; may be {@code null}
 * @param defaultValue optional default value; may be {@code null}
 * @see TRecipeGenerator#getGenerationOptions()
 */
public record TGenerationOption(
    String key,
    String label,
    String type,
    @Nullable String placeholder,
    @Nullable String defaultValue
) {}
