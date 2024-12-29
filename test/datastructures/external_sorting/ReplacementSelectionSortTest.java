package datastructures.external_sorting;

import org.junit.jupiter.api.*;
import java.io.*;
import java.nio.file.*;
import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.*;

class ReplacementSelectionSortTest {

    private Path tempDir;
    private Path inputFile;

    @BeforeEach
    void setUp() throws IOException {
        // 创建临时目录和输入文件
        tempDir = Files.createTempDirectory("external_sort_test");
        inputFile = Files.createFile(tempDir.resolve("input.txt"));
    }

    @AfterEach
    void tearDown() throws IOException {
        // 删除临时目录和所有文件
        Files.walk(tempDir)
                .sorted(Comparator.reverseOrder())
                .map(Path::toFile)
                .forEach(File::delete);
    }

    private void writeInputFile(String content) throws IOException {
        Files.write(inputFile, content.getBytes());
    }

    private String readFile(Path filePath) throws IOException {
        return new String(Files.readAllBytes(filePath));
    }

    @Test
    void testSortBasicFunctionality() throws IOException {
        // 输入数据
        writeInputFile("10\n20\n5\n30\n15\n25\n");

        // 创建并运行排序算法
        ReplacementSelectionSort sorter = new ReplacementSelectionSort(
                inputFile.toString(), tempDir.toString(), 3
        );
        sorter.sort();

        // 验证输出文件内容
        Path output1 = tempDir.resolve("1.txt");

        assertTrue(Files.exists(output1));

        assertEquals("5\n10\n15\n20\n25\n30\n", readFile(output1)); // 第一块排序输出
    }

    @Test
    void testEmptyInputFile() throws IOException {
        // 空输入文件
        writeInputFile("");

        // 创建并运行排序算法
        ReplacementSelectionSort sorter = new ReplacementSelectionSort(
                inputFile.toString(), tempDir.toString(), 3
        );
        sorter.sort();

        // 验证没有输出文件
        assertEquals(1, Files.list(tempDir).filter(Files::isRegularFile).count());
    }

    @Test
    void testInputFileSmallerThanHeap() throws IOException {
        // 输入数据少于堆容量
        writeInputFile("10\n20\n");

        // 创建并运行排序算法
        ReplacementSelectionSort sorter = new ReplacementSelectionSort(
                inputFile.toString(), tempDir.toString(), 5
        );
        sorter.sort();

        // 验证输出文件内容
        Path output1 = tempDir.resolve("1.txt");

        assertTrue(Files.exists(output1));
        assertEquals("10\n20\n", readFile(output1));
    }

    @Test
    void testInvalidInputFilePath() {
        // 无效的输入文件路径
        assertThrows(IOException.class, () -> {
            new ReplacementSelectionSort("invalid_path.txt", tempDir.toString(), 3).sort();
        });
    }
}
