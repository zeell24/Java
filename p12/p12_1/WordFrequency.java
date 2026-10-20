package clg.p12.p12_1;
import java.util.*;

public class WordFrequency
{
    public static void main(String[] args)
    {
        String paragraph = "lets learn java and word arrangement using java";
        String[] words = paragraph.split("\\s+");

        HashMap<String, Integer> map = new HashMap<>();

        for(String word : words)
        {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }

        List<Map.Entry<String, Integer>> list =
            new ArrayList<>(map.entrySet());

        list.sort((a, b) -> b.getValue().compareTo(a.getValue()));

        for(Map.Entry<String, Integer> entry : list)
        {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }
}
