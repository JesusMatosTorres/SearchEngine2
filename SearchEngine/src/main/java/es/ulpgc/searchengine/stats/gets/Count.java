package es.ulpgc.searchengine.stats.gets;

import java.util.List;
import java.util.Map;

public class Count {
    public static void countAuthors(Map<String, List<String>> authorsmap, String author, String doc) {
        if (authorsmap.containsKey(author)){
            List<String> documents = authorsmap.get(author);
            documents.add(doc);
            authorsmap.replace(author,documents);
        }
        else{
            List<String> documents = null;
            documents.add(doc);
            authorsmap.put(author,documents);
        }
    }
}
