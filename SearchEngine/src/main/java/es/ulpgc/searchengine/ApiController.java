package es.ulpgc.searchengine;

import es.ulpgc.searchengine.finders.MetadataFinder;
import es.ulpgc.searchengine.finders.WordsFinder;
import es.ulpgc.searchengine.stats.gets.Better;

import java.io.IOException;
import java.util.*;

import static spark.Spark.get;

public class ApiController {

    public static void start() {
        Map<String, String> queryParams = new HashMap<>();
        port(8080);
        get("/documents/:words", (req, res) -> {
            Set<String> allParams = req.queryParams();
            if (allParams.contains("from")) queryParams.put("from", req.queryParams("from").toLowerCase());
            if (allParams.contains("to")) queryParams.put("to", req.queryParams("to").toLowerCase());
            if (allParams.contains("author")) queryParams.put("author", req.queryParams("author").toLowerCase().replace("+"," "));

            List<String> requestParams = Arrays.asList(req.params("words").toLowerCase().split("\\+"));

            return solveApiConsult(queryParams, requestParams);
        });

    }

    private static String solveApiConsult(Map<String, String> queryParams, List<String> requestParams) throws IOException {
        List<String> metadataCoincidences = new MetadataFinder(queryParams).findCoincidences();

        requestParams = new StopwordsDelete().delete(requestParams);

        List<String> wordsCoincidences;
        if (requestParams.isEmpty()) wordsCoincidences = new MetadataFinder(queryParams).findCoincidences();
        else{
            wordsCoincidences = new WordsFinder(requestParams).findCoincidences();
        }

        List<String> output = CoincidencesGetter.getCoincidences(wordsCoincidences, metadataCoincidences);
        Collections.sort(output);

        if (JoinJson.Join(output).isEmpty()) return "No Matches";
        return JoinJson.Join(output);
    }




//    private static void getstats() {
//        Map<String,Integer> result = new HashMap<>();
//        get("/stats/:type", (req, res) -> {
//            String type = req.params("type");
//            ConnectionStatistics.Stats stats = new ConnectionStatistics.Stats();
//            if (type.equalsIgnoreCase("authorwithmorebooks")){
//                  Map<String, List<String>> authorslist = mostAuthorGetter();
//                  Map<String, String> mostOnes = new HashMap<>();
//                  for (int i = 0; i < 3; i++) {
//                      better = Better.getBetters(authorslist, mostOnes);
//                      mostOnes.put(better, JoinJson.Join(authorslist.get(better)));
//                      authorslist.remove(better);
//                  }
//                  return mostOnes;
//            }
//
//            if (type.equalsIgnoreCase("oldersbooks")){
//                return stats.getOlderBook(new File("C:\\Users\\jaorg\\IdeaProjects\\CRAWLER-main\\Crawler\\src\\main\\resources\\MetaData"));
//            }
//            if (type.equalsIgnoreCase("mostcommonword")){
//                return stats.getMostCommonWord(new File("C:\\Users\\jaorg\\IdeaProjects\\CRAWLER-main\\InvertedIndex\\src\\main\\resources\\Inv_Index.json"));
//            }
//            return null;
//        });
//    }
}
