package recipe;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Central registry for recipe generation output formats.
 * <p>
 * Built-in formats ({@code json}, {@code kubejs}) are registered automatically
 * when this class is loaded. Mods may register additional formats during their
 * initialisation phase.
 * </p>
 */
public final class TGenerationTypeRegistry
{
    private static final Map<String, TGenerationFormat> FORMATS = new LinkedHashMap<>();

    static {
        register("json", "JSON");
        register("kubejs", "KubeJS");
    }

    private TGenerationTypeRegistry() {}

    /**
     * Registers a generation format.
     * @param id          unique format identifier (e.g. {@code "crafttweaker"})
     * @param displayName human-readable name (e.g. {@code "CraftTweaker"})
     * @throws IllegalStateException if a format with the same id is already registered
     */
    public static void register(String id, String displayName) {
        if (FORMATS.containsKey(id)) {
            throw new IllegalStateException("Generation format already registered: " + id);
        }
        FORMATS.put(id, new TGenerationFormat(id, displayName));
    }

    /**
     * Looks up a format by its identifier.
     */
    public static Optional<TGenerationFormat> getFormat(String id) {
        return Optional.ofNullable(FORMATS.get(id));
    }

    /**
     * Returns all registered formats.
     */
    public static Collection<TGenerationFormat> getAll() {
        return FORMATS.values();
    }

    /**
     * Removes all registered formats (primarily for testing).
     */
    public static void clear() {
        FORMATS.clear();
    }
}
