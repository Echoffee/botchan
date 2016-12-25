package botchan;

import java.util.HashMap;

/**
 * Created by echo on 25/12/2016.
 */
public class Dictionary2 {
    public HashMap<String, SynonymsDictionary> Sections;
    public String alphabet = "abcdefghijklmnopqrstuvwxyz";

    public Dictionary2(String path)
    {
        Sections = new HashMap<>();
        for (int i = 0; i < 26; i++)
        {
            SynonymsDictionary d = new SynonymsDictionary(path, alphabet.charAt(i));
            Sections.put("" + alphabet.charAt(i), d);
        }
    }

    public String GetMeaningOf(String w)
    {
        String l = w.toLowerCase();
        SynonymsDictionary d = Sections.get("" + l.charAt(0));
        if (d == null)
            return w;
        String result = d.GetMeaningOf(l);
        if (result != null)
            return result;
        else
            return w;
    }
}
