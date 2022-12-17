package es.ulpgc.searchengine.stats.gets;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class AuthorGetter {

    public static String docAuthorGetter(String docPath) throws IOException {
        File fileObj = new File(docPath);
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> docMetadata = mapper.readValue(fileObj,
                new TypeReference<Map<String, Object>>() {});
        if (docMetadata.containsKey("Author")) return docMetadata.get("Author").toString().toLowerCase();
        else return "";
    }
}
