package datastructures.external_sorting;

import java.io.BufferedReader;
import java.io.IOException;

public class LoserTree {
    private final int[] tree;
    private final BufferedReader[] readers;
    private final int[] currentValues;

    public LoserTree(BufferedReader[] readers) throws IOException {
        this.readers = readers;
        this.currentValues = new int[readers.length];
        this.tree = new int[2 * readers.length];
        for (int i = 0; i < readers.length; i++) {
            String line = readers[i].readLine();
            currentValues[i] = line == null ? Integer.MAX_VALUE : Integer.parseInt(line);
        }
        buildLoserTree();
    }

    private void buildLoserTree() {
        int n = currentValues.length;
        int[] winners = new int[2 * n];
        for (int i = 0; i < n; i++) {
            tree[n + i] = currentValues[i];
            winners[n + i] = i;
        }
        for (int i = n - 1; i > 0; i--) {
            int left = i << 1;
            int right = left + 1;
            if (currentValues[winners[left]] < currentValues[winners[right]]) {
                tree[i] = winners[right];
                winners[i] = winners[left];
            } else {
                tree[i] = winners[left];
                winners[i] = winners[right];
            }
        }
        tree[0] = winners[1];
    }

    public int getWinner() {
        return currentValues[tree[0]];
    }

    public void rePlay() throws IOException {
        reFetchData(tree[0]);
    }

    private void reFetchData(int index) throws IOException {
        String line = readers[index].readLine();
        currentValues[index] = line == null ? Integer.MAX_VALUE : Integer.parseInt(line);
        int n = currentValues.length;
        int i = n + index;
        int winner = index;
        while (i > 1) {
            i >>= 1;
            if (currentValues[winner] > currentValues[tree[i]]) {
                int temp = winner;
                winner = tree[i];
                tree[i] = temp;
            }
        }
        tree[0] = winner;
    }
}
