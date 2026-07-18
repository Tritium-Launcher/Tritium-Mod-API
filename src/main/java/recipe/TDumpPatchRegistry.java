package recipe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Global registry for {@link TDumpPatch} instances.
 * Patches are invoked after all dump tasks finish and before manifest finalization.
 */
public final class TDumpPatchRegistry
{
    private static final List<TDumpPatch> patches = new ArrayList<>();

    private TDumpPatchRegistry() {}

    public static void register(TDumpPatch patch)
    {
        patches.add(patch);
    }

    public static List<TDumpPatch> getPatches()
    {
        return Collections.unmodifiableList(patches);
    }
}
