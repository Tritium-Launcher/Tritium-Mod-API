package recipe;

import org.jspecify.annotations.Nullable;
import java.util.*;

/**
 * Normalized recipe display data emitted into Tritium dump files.
 */
@SuppressWarnings("unused")
public record TNormalizedRecipeView(
        String mode,
        @Nullable String descriptorId,
        List<TRecipeBinding> bindings,
        List<TRenderedValue> inputs,
        List<TRenderedValue> outputs,
        Map<String, Object> properties
)
{
    public TNormalizedRecipeView {
        bindings = List.copyOf(bindings);
        inputs = List.copyOf(inputs);
        outputs = List.copyOf(outputs);
        properties = Collections.unmodifiableMap(new LinkedHashMap<>(properties));
    }

    public static Builder descriptor(String descriptorId) {
        return new Builder("descriptor", descriptorId);
    }

    public static Builder fallback() {
        return new Builder("fallback", null);
    }

    /**
     * Builder for constructing {@link TNormalizedRecipeView} instances.
     * <p>
     * Start with one of the factory methods ({@link #descriptor(String)} or
     * {@link #fallback()}), then add bindings, inputs, outputs, and
     * properties before calling {@link #build()}.
     * </p>
     */
    public static final class Builder {
        private final String mode;
        @Nullable
        private final String descriptorId;
        private final List<TRecipeBinding> bindings = new ArrayList<>();
        private final List<TRenderedValue> inputs = new ArrayList<>();
        private final List<TRenderedValue> outputs = new ArrayList<>();
        private final Map<String, Object> properties = new LinkedHashMap<>();

        private Builder(String mode, @Nullable String descriptorId) {
            this.mode = mode;
            this.descriptorId = descriptorId;
        }

        public Builder bind(String componentId, List<TRenderedValue> entries) {
            bindings.add(TRecipeBinding.of(componentId, entries));
            return this;
        }

        public Builder input(TRenderedValue value) {
            inputs.add(value);
            return this;
        }

        public Builder output(TRenderedValue value) {
            outputs.add(value);
            return this;
        }

        public Builder property(String key, Object value) {
            properties.put(key, value);
            return this;
        }

        public TNormalizedRecipeView build() {
            return new TNormalizedRecipeView(mode, descriptorId, bindings, inputs, outputs, properties);
        }
    }
}
