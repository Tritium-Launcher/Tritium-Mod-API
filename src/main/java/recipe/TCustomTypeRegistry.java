package recipe;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Central registry for custom value type providers.
 */
public final class TCustomTypeRegistry
{
    private static final Map<String, TCustomTypeProvider> PROVIDERS = new LinkedHashMap<>();

    private TCustomTypeRegistry() {}

    /**
     * Registers a custom type provider.
     *
     * @param provider the provider to register (never {@code null})
     * @throws IllegalStateException if a provider with the same type ID is already registered
     */
    public static void register(TCustomTypeProvider provider) {
        String id = provider.getDescriptor().getTypeId();
        if (PROVIDERS.containsKey(id)) {
            throw new IllegalStateException("Custom type provider already registered: " + id);
        }
        PROVIDERS.put(id, provider);
    }

    /**
     * Retrieves a registered provider by its type identifier.
     *
     * @param typeId the custom type identifier
     * @return an {@link Optional} containing the provider, or empty if not registered
     */
    public static Optional<TCustomTypeProvider> getProvider(String typeId) {
        return Optional.ofNullable(PROVIDERS.get(typeId));
    }

    /**
     * Returns all registered custom type providers.
     *
     * @return a live view of all registered providers (never {@code null})
     */
    public static Collection<TCustomTypeProvider> getProviders() {
        return PROVIDERS.values();
    }

    /**
     * Removes all registered providers.
     */
    public static void clear() {
        PROVIDERS.clear();
    }
}
