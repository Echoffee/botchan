package botchan;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by echo on 21/12/2016.
 */
public class Sentence {
    public ArrayList<Word> sentence;

    public SentenceBuilder builder;

    public Map<String, String> variables;

    public Sentence(SentenceBuilder builder)
    {
        this.sentence = new ArrayList<>();
        this.builder = builder;
        this.variables = new HashMap<>();
    }

    public ArrayList<Word> DuplicateSentence()
    {
        ArrayList<Word> sentenceClone = new ArrayList<>();
        for (Word w : sentence)
            if (w.word != null)
                sentenceClone.add(w.Duplicate());
        return sentenceClone;
    }

    public boolean Matches(String pattern)
    {
        pattern = pattern.replaceAll("\\s+", " ");
        String[] split = pattern.split(" ");
        int goal = split.length;
        for (int i = 0; i < split.length; i++)
            if (split[i].equals("..") || split[i].equals("..."))
                goal--;
        int decal = 0;
        boolean varScope = false;
        boolean doubleScope = false;
        boolean tripleScope = false;
        boolean varOrScope = false;
        ArrayList<Word> sentence2 = DuplicateSentence();
        int i = 0;
        for (i = 0; i < Math.min(split.length, sentence.size()) && i + decal < sentence.size(); i++)
        {
            /*
            Syntaxe :
            plain, __synonyme__, $$variable$$
            .   : .
            ..  : séquence de mots sans .
            ... : séquence de mots avec . possible
            valA(+)valB : OR
             */

            Word word = sentence2.get(i + decal);
            String patt = split[i];
            boolean pass = false;

            if (word.word.equals(split[i]))
            {
                //plain, synonym
                pass = true;
            }

            if (patt.charAt(0) == '$' || varScope)
            {
                //variable

                word.value = patt;
                word.type = WordType.variable;
                decal++;
                varScope = true;
                pass = true;
                if (i + 1 < split.length)
                    if (split[i + 1].equals(word.word))
                    {
                        decal--;
                        varScope = false;
                    }
            }

            if (patt.equals(".."))
            {
                //double .

                //word.value = patt;
                word.type = WordType.plain;
                decal++;
                doubleScope = true;
                pass = true;
                if (i + 1 < split.length)
                    if (split[i + 1].equals(word.word) || word.word.charAt(word.word.length() - 1) == '.')
                    {
                        decal--;
                        if (decal != 0)
                            decal--;
                        doubleScope = false;
                    }
            }

            if (varScope || doubleScope || tripleScope)
                i--;

            if (!pass)
                return false;
        }
        this.sentence = sentence2;
        if (i >= goal)
            return true;
        return false;
    }

    public void ComputeVariables()
    {
        for(Word w : sentence)
        {
            if (w.type == WordType.variable)
            {
                if (!variables.containsKey(w.value))
                    variables.put(w.value, "");
                String space = (variables.get(w.value).equals("")?"":" ");
                variables.replace(w.value, variables.get(w.value) + space + w.word);
            }
        }
    }

}
