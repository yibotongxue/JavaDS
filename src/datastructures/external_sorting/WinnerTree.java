package datastructures.external_sorting;

import java.io.BufferedReader;
import java.io.IOException;

public class WinnerTree {
    private final int[] tree;
    private final BufferedReader[] readers;
    private final int[] currentValues;

    public WinnerTree(BufferedReader[] readers) throws IOException {
        int n = readers.length;
        this.readers = new BufferedReader[n];
        System.arraycopy(readers, 0, this.readers, 0, n);
        this.tree = new int[2 * n];
        for (int i = 0; i < 2 * n; i++) {
            this.tree[i] = 0;
        }
        // 设置叶子节点为玩家的索引
        for (int i = 0; i < n; i++) {
            tree[n + i] = i;
        }
        // 将内部节点设置为左子结点的值
        for (int i = n - 1; i > 0; i--) {
            tree[i] = tree[2 * i];
        }
        currentValues = new int[n];
        for (int i = 0; i < n; i++) {
            String line = this.readers[i].readLine();
            if (line == null) {
                currentValues[i] = Integer.MAX_VALUE;
            } else {
                currentValues[i] = Integer.parseInt(line);
            }
        }
        play();
    }

    private void play() {
        int n = currentValues.length;
        for (int i = n - 1; i > 0; i--) {
            if (currentValues[tree[2 * i]]< currentValues[tree[2 * i + 1]]) {
                tree[i] = tree[2 * i];
            } else {
                tree[i] = tree[2 * i + 1];
            }
        }
    }

    public void rePlay() throws IOException {
        int playerIndex = tree[1];
        String line = readers[playerIndex].readLine();
        if (line == null) {
            currentValues[playerIndex] = Integer.MAX_VALUE;
        } else {
            currentValues[playerIndex] = Integer.parseInt(line);
        }
        int i = (playerIndex + currentValues.length) / 2;
        while (i > 0) {
            if (currentValues[tree[2 * i]] < currentValues[tree[2 * i + 1]]) {
                tree[i] = tree[2 * i];
            } else {
                tree[i] = tree[2 * i + 1];
            }
            i /= 2;
        }
    }

    public Integer getWinner() {
        return currentValues[tree[1]];
    }
}
