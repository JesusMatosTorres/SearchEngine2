package es.ulpgc.searchengine.stats.gets;

import es.ulpgc.searchengine.JoinJson;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class Better {
    public static String getBetters(Map<String, List<String>> authorsmap, Map<String, String> mostOnes) throws IOException {
        int times = 0;
        String better = null;
        for (String author : authorsmap.keySet()) {
            if (authorsmap.get(author).size() > times) {
                times = authorsmap.get(author).size();
                better = author;
            }
        }
        return better;
    }
}
