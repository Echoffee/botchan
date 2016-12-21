package botchan;

import java.io.*;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.Map;

/**
 * Created by echo on 21/12/2016.
 */
public class SynonymsDictionary {
    public Map<String, String[]> dictionary;

    public SynonymsDictionary(String path)
    {
        try
        {
        BufferedReader br = new BufferedReader(new FileReader(path));
        String item = "";
        while ((item = br.readLine()) != "")
        {
            String[] split = item.split(" : ");
            String meaning = split[0];
            String[] synonyms = split[1].split(", ");
            dictionary.put(meaning, synonyms);
        }

        }catch(FileNotFoundException e)
        {
            System.out.print("Dictionary file not found");
            e.printStackTrace();
        }catch (Exception e)
        {
            System.out.print("Error while loading dictionary");
            e.printStackTrace();
        }
    }

    public void SaveDictionary(String path)
    {
        try{
            BufferedWriter bw = new BufferedWriter(new FileWriter(path));
            for (String key : dictionary.keySet())
            {
                String[] syns = dictionary.get(key);
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
            System.out.print("Errir while saving dictionary");
            e.printStackTrace();
        }
    }
}
