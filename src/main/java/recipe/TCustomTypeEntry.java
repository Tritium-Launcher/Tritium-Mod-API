package recipe;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * A dumped value instance for a custom type.
 * <p>
 * Represents a single value of a custom type that was dumped from the game. The launcher renders these
 * as browseable entries in its registry browser.
 * </p>
 *
 * @param id           unique identifier for this value
 * @param displayName  name shown in the launcher UI
 * @param texturePath  path to the texture sprite in the mod's resources
 * @param rawData      additional key-value metadata defensively copied and made unmodifiable
 * @see TCustomTypeProvider
 * @see TCustomTypeDescriptor
 */
public record TCustomTypeEntry(
        String id,
        String displayName,
        String texturePath,
        Map<String, Object> rawData
)
{
    /**
     * Compact canonical constructor.
     * Defensively copies and protects the {@code rawData} map.
     */
    public TCustomTypeEntry {
        rawData = Collections.unmodifiableMap(new LinkedHashMap<>(rawData));
    }
}
