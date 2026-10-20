package clg.p11.p11_3;
import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.Instant;

public class FolderReport
{
    public static void main(String[] args)
    {
        Path folder = Paths.get("files");
        Path report = Paths.get("report.txt");

        try(BufferedWriter writer = Files.newBufferedWriter(report);
            var paths = Files.walk(folder))
        {
            for(Path path : paths.filter(Files::isRegularFile).toList())
            {
                BasicFileAttributes attr = Files.readAttributes(path, BasicFileAttributes.class);

                String name = path.getFileName().toString();
                long size = attr.size();
                Instant modified = attr.lastModifiedTime().toInstant();

                String line = "Name: " + name
                        + ", Size: " + size + " bytes"
                        + ", Modified: " + modified;

                writer.write(line);
                writer.newLine();

                System.out.println(line);
            }

            System.out.println("Report saved successfully.");
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }
}