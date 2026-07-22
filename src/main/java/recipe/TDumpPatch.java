package recipe;

import org.jspecify.annotations.Nullable;
import java.nio.file.Path;

/**
 * A patch that runs after all registry dumps complete but before the manifest is finalized.
 * Implementations can modify, add, or delete any file in the snapshot directory.
 * <p>
 * Register via {@link TDumpPatchRegistry#register(TDumpPatch)}.
 */
public interface TDumpPatch
{
    /**
     * Apply this patch to the snapshot.
     *
     * @param snapshotDir root of the temporary snapshot directory
     * @param gameContext opaque game context (e.g. a Minecraft client or server instance);
     *                    may be {@code null} if not available
     */
    void apply(Path snapshotDir, @Nullable Object gameContext);
}
