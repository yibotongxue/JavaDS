package datastructures.sorting;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class Person implements Comparable<Person> {
    String name;
    int age;

    Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public int compareTo(Person other) {
        return Integer.compare(this.age, other.age);  // 按 age 排序
    }

    @Override
    public String toString() {
        return name + ":" + age;
    }
}

public class InsertionSortDirectTest {

    @Test
    public void testEmptyArray() {
        Integer[] array = {};
        InsertionSortDirect.sort(array);
        assertArrayEquals(new Integer[]{}, array, "空数组应该保持为空");
    }

    @Test
    public void testSingleElementArray() {
        Integer[] array = {42};
        InsertionSortDirect.sort(array);
        assertArrayEquals(new Integer[]{42}, array, "单元素数组应该保持不变");
    }

    @Test
    public void testSortedArray() {
        Integer[] array = {1, 2, 3, 4, 5};
        InsertionSortDirect.sort(array);
        assertArrayEquals(new Integer[]{1, 2, 3, 4, 5}, array, "已排序数组应该保持不变");
    }

    @Test
    public void testReverseSortedArray() {
        Integer[] array = {5, 4, 3, 2, 1};
        InsertionSortDirect.sort(array);
        assertArrayEquals(new Integer[]{1, 2, 3, 4, 5}, array, "降序数组应该正确排序");
    }

    @Test
    public void testArrayWithDuplicates() {
        Integer[] array = {3, 1, 2, 3, 2, 1};
        InsertionSortDirect.sort(array);
        assertArrayEquals(new Integer[]{1, 1, 2, 2, 3, 3}, array, "包含重复元素的数组应该正确排序");
    }

    @Test
    public void testStringArray() {
        String[] array = {"banana", "apple", "cherry"};
        InsertionSortDirect.sort(array);
        assertArrayEquals(new String[]{"apple", "banana", "cherry"}, array, "字符串数组应该按照字母顺序排序");
    }

    @Test
    public void testStabilityWithEqualElements() {
        Person[] people = {
                new Person("Alice", 30),
                new Person("Bob", 25),
                new Person("Charlie", 30),  // 和 Alice 有相同的 age
                new Person("David", 25)     // 和 Bob 有相同的 age
        };

        // 排序前的顺序
        System.out.println("Before Sorting:");
        for (Person p : people) {
            System.out.print(p + " ");
        }
        System.out.println();

        // 使用直接插入排序进行排序
        InsertionSortDirect.sort(people);

        // 排序后的顺序
        System.out.println("After Sorting:");
        for (Person p : people) {
            System.out.print(p + " ");
        }
        System.out.println();

        // 验证排序后的数组是否稳定
        assertEquals("Bob:25", people[0].toString());  // Bob 应该排在 David 前面
        assertEquals("David:25", people[1].toString());  // David 应该排在 Bob 后面
        assertEquals("Alice:30", people[2].toString());  // Alice 应该排在 Charlie 前面
        assertEquals("Charlie:30", people[3].toString());  // Charlie 应该排在 Alice 后面
    }
}

