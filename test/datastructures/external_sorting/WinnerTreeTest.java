package datastructures.external_sorting;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

import static org.junit.jupiter.api.Assertions.*;

class WinnerTreeTest {

    private WinnerTree loserTree;

    @BeforeEach
    void setUp() throws IOException {
        // 模拟从多个流读取数据
        BufferedReader[] readers = new BufferedReader[]{
                new BufferedReader(new StringReader("1\n3\n5\n")),
                new BufferedReader(new StringReader("2\n4\n6\n")),
                new BufferedReader(new StringReader("0\n7\n8\n"))
        };
        loserTree = new WinnerTree(readers);
    }

    @Test
    void testWinnerTreeInitialization() {
        // 检查构造函数是否正确初始化
        assertNotNull(loserTree);
        assertEquals(0, loserTree.getWinner()); // 0 是最小值
    }

    @Test
    void testGetWinner() {
        // 检查初始的胜者（最小值）
        assertEquals(0, loserTree.getWinner());
    }

    @Test
    void testRePlay() throws IOException {
        // 初始时最小值是0
        assertEquals(0, loserTree.getWinner());

        // 调用 `rePlay()` 使得树重新读取数据流
        loserTree.rePlay();
        // 此时胜者应为 1，因为流0中的1被读取
        assertEquals(1, loserTree.getWinner());

        // 再次调用 `rePlay()` 使得树重新读取数据流
        loserTree.rePlay();
        // 此时胜者应为 2，因为流1中的2是下一个最小值
        assertEquals(2, loserTree.getWinner());

        // 再次调用 `rePlay()` 使得树重新读取数据流
        loserTree.rePlay();
        // 此时胜者应为 3，因为流0中的3是下一个最小值
        assertEquals(3, loserTree.getWinner());
    }
}
