package datastructures.external_sorting;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class WinnerTree {
    private final int playerCnt;
    private final ArrayList<Integer> tree;
    private final ArrayList<BufferedReader> players;
    private final ArrayList<Integer> currentValueOfPlayer;
    private int lastWinner = -1;

    public WinnerTree(ArrayList<String> players) throws IOException {
        this.players = new ArrayList<>();
        for (String player : players) {
            this.players.add(new BufferedReader(new FileReader(player)));
        }
        this.playerCnt = players.size();
        this.tree = new ArrayList<>();
        for (int i = 0; i < 2 * playerCnt; i++) {
            tree.add(0);
        }
        // 设置叶子节点为玩家的索引
        for (int i = 0; i < playerCnt; i++) {
            tree.set(playerCnt + i, i);
        }
        // 将内部节点设置为左子结点的值
        for (int i = playerCnt - 1; i > 0; i--) {
            tree.set(i, tree.get(2 * i));
        }
        currentValueOfPlayer = new ArrayList<>();
        for (var player : this.players) {
            String line = player.readLine();
            if (line == null) {
                throw new IOException("Player file is empty");
            }
            currentValueOfPlayer.add(Integer.parseInt(line));
        }
        play();
    }

    private void play() {
        for (int i = playerCnt - 1; i > 0; i--) {
            if (currentValueOfPlayer.get(tree.get(2 * i)) < currentValueOfPlayer.get(tree.get(2 * i + 1))) {
                tree.set(i, tree.get(2 * i));
            } else {
                tree.set(i, tree.get(2 * i + 1));
            }
        }
        lastWinner = tree.get(1);
    }

    private void reFetchData(int playerIndex) throws IOException {
        String line = players.get(playerIndex).readLine();
        if (line == null) {
            currentValueOfPlayer.set(playerIndex, Integer.MAX_VALUE);
        } else {
            currentValueOfPlayer.set(playerIndex, Integer.parseInt(line));
        }
    }

    private void rePlay(int playerIndex) {
        int i = (playerIndex + playerCnt) / 2;
        while (i > 0) {
            if (currentValueOfPlayer.get(tree.get(2 * i)) < currentValueOfPlayer.get(tree.get(2 * i + 1))) {
                tree.set(i, tree.get(2 * i));
            } else {
                tree.set(i, tree.get(2 * i + 1));
            }
            i /= 2;
        }
        lastWinner = tree.get(1);
    }

    public int getWinner() {
        return lastWinner;
    }

    public Integer getWinnerValue() {
        return currentValueOfPlayer.get(lastWinner);
    }

    public void next() throws IOException {
        reFetchData(lastWinner);
        rePlay(lastWinner);
    }
}
