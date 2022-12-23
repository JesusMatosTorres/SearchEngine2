package es.ulpgc.searchengine;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class JoinJson {


    public static String Join(List<String> docs) throws IOException {
        StringBuilder result = new StringBuilder();
        for (String doc: docs){
            File fileObj = FileSearcher.searchFile(new File("./Datamart/MetaData/"),doc+".json");
            ObjectMapper mapper = new ObjectMapper();
            Map<String, Object> docMetadata = mapper.readValue(fileObj,
                    new TypeReference<Map<String, Object>>() {});
            result.append("{").append(doc).append(" = ").append(docMetadata).append("}").append("\n");
        }
        return result.toString();

    }
    
}
