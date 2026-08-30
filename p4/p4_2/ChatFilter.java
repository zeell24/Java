package clg.p4.p4_2;

public class ChatFilter {
    
    public static String filterLogs(String[] logs , String key)
    {
        int n = logs.length;
        int count = 0;
        StringBuilder ref = new StringBuilder();

        for(int i=0 ; i < n ; i++)
        {
            String[] parts = logs[i].split(" " , 3);

            if(parts.length < 3)
            {
                continue;
            }
            
            if(parts[2].toLowerCase().contains(key.toLowerCase()))
            {
                count++;

                ref.append(parts[0]).append(" ")
                    .append(parts[1]).append(": ")
                    .append(parts[2]).append("\n");
            }
        }

        return "count = " + count + "\n" + ref;
        
    }
}
