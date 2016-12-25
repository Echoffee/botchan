package botchan;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by echo on 21/12/2016.
 */
public class SynonymsDictionary {
    public Map<String, String[]> synDictionary;
    public Map<String, String> meaDictionary;

    public SynonymsDictionary(String path)
    {
        synDictionary = new HashMap<>();
        meaDictionary = new HashMap<>();
        try
        {
        BufferedReader br = new BufferedReader(new FileReader(path));
        String item = "";
        while ((item = br.readLine()) != null)
        {
            String[] split = item.split(" : ");
            String meaning = split[0];
            String[] synonyms = split[1].split(", ");
            synDictionary.put(meaning, synonyms);
            for(String w : synonyms)
                meaDictionary.put(w, meaning);
        }

        }catch(FileNotFoundException e)
        {
            System.out.print("Dictionary file not found");
            e.printStackTrace();
        }catch (Exception e)
        {
            System.out.print("Error while loading synDictionary");
            e.printStackTrace();
        }
    }

    public SynonymsDictionary(String path, char letter)
    {
        synDictionary = new HashMap<>();
        meaDictionary = new HashMap<>();
        try
        {
            BufferedReader br = new BufferedReader(new FileReader(path));
            String item = "";
            while ((item = br.readLine()) != null) {
                String[] split = item.split(" : ");
                String meaning = split[0];
                String[] synonyms = split[1].split(", ");
                synDictionary.put(meaning, synonyms);
                for (String w : synonyms) {
                    if (w.charAt(0) == letter)
                    meaDictionary.put(w, meaning);
                }
            }

        }catch(FileNotFoundException e)
        {
            System.out.print("Dictionary file not found");
            e.printStackTrace();
        }catch (Exception e)
        {
            System.out.print("Error while loading synDictionary");
            e.printStackTrace();
        }
    }

    public void SaveDictionary(String path)
    {
        try{
            BufferedWriter bw = new BufferedWriter(new FileWriter(path));
            for (String key : synDictionary.keySet())
            {
                String[] syns = synDictionary.get(key);
                StringBuilder sb = new StringBuilder();
                sb.append(key);
                sb.append(" : ");
                for (int i = 0; i < syns.length; i++)
                {
                    if (i != 0)
                        sb.append(", ");
                    sb.append(syns[i]);
                }
                sb.append("\n");
                bw.write(sb.toString());
            }

        }catch(FileNotFoundException e)
        {
            System.out.print("Dictionary file not found");
            e.printStackTrace();
        }catch (Exception e)
        {
            System.out.print("Errir while saving synDictionary");
            e.printStackTrace();
        }
    }

    public String GetMeaningOf(String word)
    {
        if (meaDictionary.containsKey(word))
            return meaDictionary.get(word);
        else
            return null;
    }

    public Set<String> GetKnownWords()
    {
        return meaDictionary.keySet();
    }
}
