package botchan;

import java.lang.reflect.Array;
import java.text.Normalizer;
import java.util.*;

/**
 * Created by echo on 21/12/2016.
 */
public class SentenceBuilder {
public SynonymsDictionary dictionary;


    public SentenceBuilder()
    {
        this.dictionary = new SynonymsDictionary("dictionary.txt");
    }

    public String CreateSentence(String s)
    {

        String result = "";

        return result;
    }

    public String TranslateSentence(String s)
    {
        String result = s;
        for (String w : dictionary.GetKnownWords()) {
            result = result.replaceAll(w, dictionary.GetMeaningOf(w));
        }
        result = result.replaceAll("(?=\\s([a-zA-Z0-9 ]+))\\s|([a-zA-Z0-9]+\\s)", " ");
        return result;
    }

    public String NoSymbols(String s)
    {
        String result = s;
        result = Normalizer.normalize(result, Normalizer.Form.NFD);
        result = result.replaceAll("[^\\p{ASCII}]", "");
        return result;
    }
}
