package recipe;

/**
 * Describes a target output format for recipe generation.
 * <p>
 * Each format corresponds to a concrete syntax a recipe can be emitted in,
 * such as a vanilla JSON data-pack file or a KubeJS script snippet.
 * </p>
 *
 * @param id          Unique format identifier (e.g. {@code "json"}, {@code "kubejs"}).
 * @param displayName Name shown in the generation dialog
 *                    (e.g. {@code "JSON"}, {@code "KubeJS"}).
 */
public record TGenerationFormat(
        String id,
        String displayName
) {
}
