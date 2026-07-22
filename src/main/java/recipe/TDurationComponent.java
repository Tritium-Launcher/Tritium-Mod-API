package recipe;

import java.util.HashMap;
import java.util.Map;

/**
 * Component representing recipe duration.
 */
public interface TDurationComponent extends TRecipeComponent
{

    int duration();

    @Override
    default String category() {
        return "DURATION";
    }

    @Override
    default String id() {
        return "duration";
    }

    @Override
    default Map<String, Object> data() {
        Map<String, Object> data = new HashMap<>();
        data.put("duration", duration());
        return data;
    }
}
