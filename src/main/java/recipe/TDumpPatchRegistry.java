package recipe;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Global registry for {@link TDumpPatch} instances.
 * Patches are invoked after all dump tasks finish and before manifest finalization.
 */
@SuppressWarnings("unused")
public final class TDumpPatchRegistry
{
    private static final List<TDumpPatch> patches = new CopyOnWriteArrayList<>();

    private TDumpPatchRegistry() {}

    /**
     * Registers a dump patch.
     * <p>
     * Patches are executed after all registry dumps complete,
     * in registration order.
     * </p>
     *
     * @param patch the patch to register (never {@code null})
     */
    public static void register(TDumpPatch patch)
    {
        patches.add(patch);
    }

    /**
     * Returns all registered dump patches.
     *
     * @return a snapshot of registered patches (never {@code null})
     */
    public static List<TDumpPatch> getPatches()
    {
        return List.copyOf(patches);
    }
}
