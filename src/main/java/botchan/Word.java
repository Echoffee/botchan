package botchan;

/**
 * Created by echo on 21/12/2016.
 */
public class Word {
    public String word;

    public WordType type;

    public String value;

    public Word Duplicate()
    {
        Word result = new Word();
        result.word = word;
        result.type = type;
        result.value = value;
        return result;
    }
}

