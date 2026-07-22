package recipe;

import org.jspecify.annotations.Nullable;

public record TGenerationOption(
    String key,
    String label,
    String type,
    @Nullable String placeholder,
    @Nullable String defaultValue
) {}
