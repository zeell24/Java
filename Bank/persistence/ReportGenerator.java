package clg.Bank.persistence;

import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class ReportGenerator
{
    public static void generate(Path logsFolder, Path reportFile)
    {
        long totalDeposits = 0;
        long totalWithdrawals = 0;

        try(BufferedWriter writer = Files.newBufferedWriter(reportFile);
            DirectoryStream<Path> files = Files.newDirectoryStream(logsFolder))
        {
            for(Path file : files)
            {
                if(!Files.isRegularFile(file))
                    continue;

                try(BufferedReader reader = Files.newBufferedReader(file))
                {
                    String line;

                    while((line = reader.readLine()) != null)
                    {
                        String[] parts = line.split(" ");

                        if(parts.length >= 3)
                        {
                            long amount = Long.parseLong(parts[2]);

                            if(parts[0].equals("DEPOSIT"))
                                totalDeposits += amount;
                            else if(parts[0].equals("WITHDRAW"))
                                totalWithdrawals += amount;
                        }
                    }
                }

                BasicFileAttributes attr = Files.readAttributes(file, BasicFileAttributes.class);

                writer.write("File: " + file.getFileName());
                writer.newLine();
                writer.write("Size: " + attr.size() + " bytes");
                writer.newLine();
                writer.write("Last modified: " + attr.lastModifiedTime());
                writer.newLine();
                writer.newLine();
            }

            writer.write("Total deposits: " + totalDeposits);
            writer.newLine();
            writer.write("Total withdrawals: " + totalWithdrawals);
            writer.newLine();

            System.out.println("Report generated successfully.");
        }
        catch(IOException | NumberFormatException e)
        {
            e.printStackTrace();
        }
    }
}