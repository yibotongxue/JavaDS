package datastructures.external_sorting;

import java.io.*;

public class ReplacementSelectionSort {

    public static void sort(String inputFilePath, String outputFileDir, int capacity) throws IOException {
        var heap = new MinHeap<Integer>(capacity);
        BufferedReader reader = new BufferedReader(new FileReader(inputFilePath));
        int counter = 1;
        heap.clear();
        while (!heap.isFull()) {
            String line = reader.readLine();
            if (line == null) {
                break;
            }
            heap.add(Integer.valueOf(line));
        }
        while (!heap.isEmpty()) {
            String outputFilePath = outputFileDir + "/" + counter++ + ".txt";
            System.out.println(outputFilePath);
            BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath));
            while (!heap.isEmpty()) {
                String line = reader.readLine();
                if (line == null) {
                    writer.write(heap.poll().toString());
                    writer.newLine();
                } else {
                    writer.write(heap.replace(Integer.valueOf(line)).toString());
                    writer.newLine();
                }
            }
            writer.close();
            heap.reBuild();
        }
        reader.close();
    }
}
