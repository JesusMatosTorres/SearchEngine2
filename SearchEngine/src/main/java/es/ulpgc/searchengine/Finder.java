package es.ulpgc.searchengine;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface Finder {
   List<String> findCoincidences() throws IOException;
}
