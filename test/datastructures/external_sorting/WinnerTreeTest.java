package datastructures.external_sorting;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

class WinnerTreeTest {

    @AfterEach
    void tearDown() {
        for (int i = 1; i <= 2; i++) {
            String fileName = "player" + i + ".txt";
            File file = new File(fileName);
            file.delete();
        }
    }

    @Test
    void winnerTree_withSinglePlayer_shouldReturnPlayerAsWinner() throws IOException {
        ArrayList<String> players = new ArrayList<>();
        players.add("player1.txt");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("player1.txt"))) {
            writer.write("1\n2\n3\n");
        }

        WinnerTree winnerTree = new WinnerTree(players);
        assertEquals(0, winnerTree.getWinner());
    }

    @Test
    void winnerTree_withMultiplePlayers_shouldReturnCorrectWinner() throws IOException {
        ArrayList<String> players = new ArrayList<>();
        players.add("player1.txt");
        players.add("player2.txt");

        try (BufferedWriter writer1 = new BufferedWriter(new FileWriter("player1.txt"))) {
            writer1.write("1\n3\n5\n");
        }

        try (BufferedWriter writer2 = new BufferedWriter(new FileWriter("player2.txt"))) {
            writer2.write("2\n4\n6\n");
        }

        WinnerTree winnerTree = new WinnerTree(players);
        assertEquals(0, winnerTree.getWinner());
    }

    @Test
    void winnerTree_withEmptyPlayerFile_shouldThrowIOException() {
        ArrayList<String> players = new ArrayList<>();
        players.add("emptyPlayer.txt");

        assertThrows(IOException.class, () -> new WinnerTree(players));
    }

    @Test
    void winnerTree_withNonNumericContent_shouldThrowNumberFormatException() throws IOException {
        ArrayList<String> players = new ArrayList<>();
        players.add("player1.txt");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("player1.txt"))) {
            writer.write("a\nb\nc\n");
        }

        assertThrows(NumberFormatException.class, () -> new WinnerTree(players));
    }

    @Test
    void next_withValidData_shouldGetWinnerCorrectly() throws IOException {
        ArrayList<String> players = new ArrayList<>();
        players.add("player1.txt");
        players.add("player2.txt");

        try (BufferedWriter writer1 = new BufferedWriter(new FileWriter("player1.txt"))) {
            writer1.write("1\n3\n5\n");
        }

        try (BufferedWriter writer2 = new BufferedWriter(new FileWriter("player2.txt"))) {
            writer2.write("2\n4\n6\n");
        }

        WinnerTree winnerTree = new WinnerTree(players);
        assertEquals(1, winnerTree.getWinnerValue());
    }

    @Test
    void next_withValidData_shouldUpdateWinnerCorrectly() throws IOException {
        ArrayList<String> players = new ArrayList<>();
        players.add("player1.txt");
        players.add("player2.txt");

        try (BufferedWriter writer1 = new BufferedWriter(new FileWriter("player1.txt"))) {
            writer1.write("1\n3\n5\n");
        }

        try (BufferedWriter writer2 = new BufferedWriter(new FileWriter("player2.txt"))) {
            writer2.write("2\n4\n6\n");
        }

        WinnerTree winnerTree = new WinnerTree(players);
        winnerTree.next();
        assertEquals(2, winnerTree.getWinnerValue());
    }
}
