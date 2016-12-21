package botchan;

import java.util.ArrayList;

/**
 * Created by echo on 21/12/2016.
 */
public class Sentence {
    public ArrayList<Word> sentence;

    public SentenceBuilder builder;

    public Sentence(SentenceBuilder builder)
    {
        this.sentence = new ArrayList<>();
        this.builder = builder;
    }

    public ArrayList<Word> DuplicateSentence()
    {
        ArrayList<Word> sentenceClone = new ArrayList<>();
        for (Word w : sentence)
            sentenceClone.add(w.Duplicate());
        return sentenceClone;
    }

    public boolean Matches(String pattern)
    {
        pattern = pattern.replaceAll("\\s+", " ");
        String[] split = pattern.split(" ");
        int decal = 0;
        boolean varScope = false;
        boolean doubleScope = false;
        boolean tripleScope = false;
        boolean varOrScope = false;
        ArrayList<Word> sentence2 = DuplicateSentence();
        for (int i = 0; i < Math.min(split.length, sentence.size()); i++)
        {
            /*
            Syntaxe :
            plain, __synonyme__, $$variable$$
            .   : .
            ..  : séquence de mots sans .
            ... : séquence de mots avec . possible
            valA(+)valB : OR
             */
            if (varScope || doubleScope || tripleScope)
                i--;
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
                if (i + 1 < split.length)
                    if (!split[i + 1].equals(word))
                    {
                        word.value = patt;
                        word.type = WordType.variable;
                        varScope = true;
                        decal++;
                    }else{
                        decal--;
                        varScope = false;
                    }

                pass = true;
            }

            if (!pass)
                return false;
        }
        this.sentence = sentence2;
        return true;
    }


}
