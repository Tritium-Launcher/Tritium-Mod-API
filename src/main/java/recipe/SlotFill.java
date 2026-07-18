package recipe;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * A filled slot's data passed to a {@link TRecipeGenerator} for code generation.
 * <p>
 * Carries the item or value that the user placed into a component slot,
 * along with optional NBT / Data Component key-value pairs.
 * </p>
 *
 * @param itemId  The item or value ID (e.g. {@code "minecraft:stone"}).
 * @param quantity The stack size / amount.
 * @param data    Optional NBT / Data Component key-value pairs
 *                (e.g. {@code {"minecraft:custom_name": "\"My Sword\""}}).
 */
public record SlotFill(
        String itemId,
        long quantity,
        Map<String, Object> data
) {
    public SlotFill(String itemId, long quantity) {
        this(itemId, quantity, Collections.emptyMap());
    }

    public SlotFill {
        data = data == null
                ? Collections.emptyMap()
                : Collections.unmodifiableMap(new LinkedHashMap<>(data));
    }
}
