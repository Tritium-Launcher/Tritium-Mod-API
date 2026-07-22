package recipe;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Central registry for custom value type providers.
 */
@SuppressWarnings("unused")
public final class TCustomTypeRegistry
{
    private static final Map<String, TCustomTypeProvider> PROVIDERS = new ConcurrentHashMap<>();

    private TCustomTypeRegistry() {}

    /**
     * Registers a custom type provider.
     *
     * @param provider the provider to register (never {@code null})
     * @throws IllegalStateException if a provider with the same type ID is already registered
     */
    public static void register(TCustomTypeProvider provider) {
        String id = provider.getDescriptor().getTypeId();
        TCustomTypeProvider existing = PROVIDERS.computeIfAbsent(id, k -> provider);
        if (existing != provider) {
            throw new IllegalStateException("Custom type provider already registered: " + id);
        }
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
     * @return a snapshot of all registered providers (never {@code null})
     */
    public static List<TCustomTypeProvider> getProviders() {
        return List.copyOf(PROVIDERS.values());
    }

    /**
     * Removes all registered providers.
     */
    public static void clear() {
        PROVIDERS.clear();
    }
}
