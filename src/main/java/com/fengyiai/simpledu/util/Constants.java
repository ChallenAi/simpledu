package com.fengyiai.simpledu.util;

import java.util.HashMap;

public class Constants {
    final private static HashMap<String, Long> paramsMap = new HashMap<String, Long>() {{
        // resources
        put("wiki", 1L);
        put("explain", 2L);
        put("question", 3L);
        put("answer", 4L);
        put("gist", 5L);
        put("article", 6L);
        put("review", 7L);

        // user
        put("user", 8L);

        // action
        put("action_up", 9L);
        put("action_down", 10L);
        put("action_collect", 11L);
        put("action_review", 12L);
    }};

    public static Long mapParamsToId(String type) {
        return paramsMap.get(type);
    }
}
