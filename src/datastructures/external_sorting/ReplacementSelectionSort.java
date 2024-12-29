package datastructures.external_sorting;

import java.io.*;

public class ReplacementSelectionSort {
    private final MinHeap<Integer> heap;
    private final String inputFilePath;
    private final String outputFileDir;

    public ReplacementSelectionSort(String inputFilePath, String outputFileDir, int capacity) {
        heap = new MinHeap<>(capacity);
        this.inputFilePath = inputFilePath;
        this.outputFileDir = outputFileDir;
    }

    public void sort() throws IOException {
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
