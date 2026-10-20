package clg.p11.p11_2;
import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class LogAnalyzer
{
    public static void main(String[] args)
    {
        Path folder = Paths.get("logs");
        String keyword = "ERROR";
        int totalLines = 0;
        int keywordLines = 0;

        try(DirectoryStream<Path> files = Files.newDirectoryStream(folder, "*.txt"))
        {
            for(Path file : files)
            {
                int fileLines = 0;
                int fileKeywordLines = 0;

                try(BufferedReader br = Files.newBufferedReader(file))
                {
                    String line;

                    while((line = br.readLine()) != null)
                    {
                        totalLines++;
                        fileLines++;

                        if(line.contains(keyword))
                        {
                            keywordLines++;
                            fileKeywordLines++;
                        }
                    }
                }

                BasicFileAttributes attr = Files.readAttributes(file, BasicFileAttributes.class);

                System.out.println("File: " + file.getFileName());
                System.out.println("Size: " + attr.size() + " bytes");
                System.out.println("Lines: " + fileLines);
                System.out.println("Lines containing " + keyword + ": " + fileKeywordLines);
                System.out.println();
            }
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }

        System.out.println("Total lines: " + totalLines);
        System.out.println("Total lines containing " + keyword + ": " + keywordLines);
    }
}