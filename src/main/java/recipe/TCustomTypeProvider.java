package recipe;

import org.jspecify.annotations.Nullable;
import java.util.Collection;

/**
 * Supplies dumped value instances for a Tritium custom value type.
 * <p>
 * Register implementations via {@link TCustomTypeRegistry#register(TCustomTypeProvider)}
 * during mod initialization.
 * </p>
 *
 * @see TCustomTypeDescriptor
 * @see TCustomTypeEntry
 * @see TCustomTypeRegistry
 */
public interface TCustomTypeProvider
{
    /**
     * Returns the descriptor that identifies this custom type.
     *
     * @return the type descriptor (never {@code null})
     */
    TCustomTypeDescriptor getDescriptor();

    /**
     * Dumps all value instances of this custom type from the server.
     * <p>
     * Called during the launcher registry dump phase. The returned entries are
     * serialized into the dump snapshot for the launcher to consume.
     * </p>
     *
     * @param server the current server or integrated server instance;
     *               may be {@code null} if the game is not fully started
     * @return all known value instances for this type (never {@code null})
     */
    Collection<TCustomTypeEntry> dumpValues(@Nullable Object server);
}
