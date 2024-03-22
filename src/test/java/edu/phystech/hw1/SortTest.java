package edu.phystech.hw1;

import java.sql.SQLOutput;
import java.util.Arrays;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class SortTest {

    private static int[] sort(int[] nums) {
        int length = nums.length;
        int[] sorted_nums = nums.clone();
        for (int i = 0; i < length - 1; i++) {
            for (int j = 0; j < length - 1; j++) {
                if (sorted_nums[j] > sorted_nums[j + 1]) {
                    int tmp = sorted_nums[j];
                    sorted_nums[j] = sorted_nums[j + 1];
                    sorted_nums[j + 1] = tmp;
                }
            }
        }
        return sorted_nums;
    }

    @Test
    public void sortWorks() {
        Assertions.assertArrayEquals(new int[]{1}, sort(new int[]{1}));
        Assertions.assertArrayEquals(new int[]{1, 2, 3, 4, 5}, sort(new int[]{5, 4, 3, 2, 1}));
    }

    @Test
    public void sortReturnsNewArray() {
        int[] input = {9, 1, 3, 11, 45, 499};
        int[] copy = Arrays.copyOf(input, input.length);

        int[] sorted = sort(input);

        Assertions.assertArrayEquals(new int[]{1, 3, 9, 11, 45, 499}, sorted);
        Assertions.assertArrayEquals(copy, input);
    }
}
