package datastructures.external_sorting;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Comparator;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.logging.Logger;

public class ExternalTwoPathMerge {
    public static void merge(String[] inputFiles, String outputFile) {
        if (inputFiles.length == 1) {
            File input = new File(inputFiles[0]);
            File output = new File(outputFile);
            try {
                Files.copy(input.toPath(), output.toPath(), StandardCopyOption.REPLACE_EXISTING);
            } catch (Exception e) {
                Logger.getLogger(ExternalTwoPathMerge.class.getName()).severe(e.getMessage());
            }
        }
        PriorityQueue<File> pq = new PriorityQueue<>(Comparator.comparingLong(File::length));
        for (String file : inputFiles) {
            pq.add(new File(file));
        }
        while (pq.size() > 1) {
            File file1 = pq.poll();
            File file2 = pq.poll();
            File outputFile1 = new File(outputFile + "tempForMerge");
            try {
                assert file2 != null;
                mergeFiles(file1, file2, outputFile1);
                pq.add(outputFile1);
            } catch (IOException e) {
                Logger.getLogger(ExternalTwoPathMerge.class.getName()).severe(e.getMessage());
            }
        }
        try {
            Files.move(Objects.requireNonNull(pq.poll()).toPath(), new File(outputFile).toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            Logger.getLogger(ExternalTwoPathMerge.class.getName()).severe(e.getMessage());
        }
    }

    public static void mergeFiles(File file1, File file2, File outputFile) throws IOException {
        BufferedReader reader1 = new BufferedReader(new FileReader(file1.getPath()));
        BufferedReader reader2 = new BufferedReader(new FileReader(file2.getPath()));
        BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile.getPath()));
        String line1 = reader1.readLine();
        String line2 = reader2.readLine();
        while (line1 != null && line2 != null) {
            if (Integer.parseInt(line1) < Integer.parseInt(line2)) {
                writer.write(line1);
                line1 = reader1.readLine();
            } else {
                writer.write(line2);
                line2 = reader2.readLine();
            }
            writer.newLine();
        }
        while (line1 != null) {
            writer.write(line1);
            writer.newLine();
            line1 = reader1.readLine();
        }
        while (line2 != null) {
            writer.write(line2);
            writer.newLine();
            line2 = reader2.readLine();
        }
        reader1.close();
        reader2.close();
        writer.close();
    }
}
