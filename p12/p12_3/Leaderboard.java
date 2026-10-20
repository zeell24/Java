package clg.p12.p12_3;
import java.util.*;

public class Leaderboard
{
    public static void main(String[] args)
    {
        Map<String, Integer> scores = new HashMap<>();

        scores.put("Zeel", 95);
        scores.put("keya", 88);
        scores.put("sweta", 95);
        scores.put("maya", 76);

        List<Map.Entry<String, Integer>> list =
            new ArrayList<>(scores.entrySet());

        list.sort((a, b) ->
        {
            int result = b.getValue().compareTo(a.getValue());

            if(result == 0)
                return a.getKey().compareTo(b.getKey());

            return result;
        });

        int n = 3;

        System.out.println("---LeaderBoard---");
        for(int i = 0; i < Math.min(n, list.size()); i++)
        {
            System.out.println(
                list.get(i).getKey() + " : " +
                list.get(i).getValue()
            );
        }
    }
}
