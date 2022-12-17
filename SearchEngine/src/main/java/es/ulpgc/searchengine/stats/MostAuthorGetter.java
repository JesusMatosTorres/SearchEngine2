package es.ulpgc.searchengine.stats;

import es.ulpgc.searchengine.DocumentsGetter;
import es.ulpgc.searchengine.JoinJson;
import es.ulpgc.searchengine.stats.gets.AuthorGetter;
import es.ulpgc.searchengine.stats.gets.Better;
import es.ulpgc.searchengine.stats.gets.Count;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MostAuthorGetter {

        public static Map<String, List<String>> mostAuthorGetter() throws IOException {
            String path = ".Datamart/MetaData";
            String[] folders = DocumentsGetter.getFolders(path);
            Map<String, List<String>> authorsmap = new HashMap<>();

            for (String folder:folders){
                Set<String> Documents = DocumentsGetter.getDocuments(path+"/"+folder);
                for (String doc:Documents){
                    String docPath = path+"/"+folder+"/"+doc;
                    String author = AuthorGetter.docAuthorGetter(docPath);
                    if (author.isEmpty()) continue;
                    Count.countAuthors(authorsmap, author, doc);
                }
            }
            return authorsmap;

        }


}
