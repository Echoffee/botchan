package botchan;

import java.lang.reflect.Array;
import java.text.Normalizer;
import java.util.*;

/**
 * Created by echo on 21/12/2016.
 */
public class SentenceBuilder {
public SynonymsDictionary dictionary;
public Dictionary2 dictionary2;


    public SentenceBuilder()
    {
        this.dictionary = new SynonymsDictionary("dictionary.txt");
        this.dictionary2 = new Dictionary2("dictionary.txt");
    }

    public String CreateSentence(String s)
    {

        String result = "";

        return result;
    }

    /*public String TranslateSentence(String s)
    {
        String result = s;
        for (String w : dictionary.GetKnownWords()) {
            result = result.replaceAll(w, dictionary.GetMeaningOf(w));
        }
        result = result.replaceAll("(\\s[A-Za-z0-9 ]+(?=_))|(([A-Za-z0-9]+\\s(?=_)|\\s[A-Za-z0-9]+)(?=_))", " ");
        return result;
    }*/
    public Sentence TranslateMessage(String s)
    {
        String result = s;
        /*for (String w : dictionary.GetKnownWords()) {
            String b = (w.charAt(w.length() - 1) == '\''?"":"\\b");
            result = result.replaceAll("\\b" + w + b, " " + dictionary.GetMeaningOf(w) + " ");
        }*/
        String pattern = result.replaceAll("\\s+", " ");
        String[] split = pattern.split(" ");
        for (int i = 0; i < split.length; i++) {
            split[i] = dictionary2.GetMeaningOf(split[i]);

        }

        Sentence sentence = new Sentence(this);
        for(String w : split)
        {
            if (w != null && !w.equals("")) {
                Word e = new Word();
                e.word = w;
                sentence.sentence.add(e);
            }
        }
        return sentence;
    }

    public String NoSymbols(String s)
    {
        String result = s;
        result = Normalizer.normalize(result, Normalizer.Form.NFD);
        result = result.replaceAll("[^\\p{ASCII}]", "");
        result = result.replaceAll("[-,]", " ");
        return result;
    }
}
