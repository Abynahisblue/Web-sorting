package com.example.webapp.sorting;

import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class RadixSort {
    public int[] sort(int[] array) {
        int max = Arrays.stream(array).max().orElse(0);
        for (int exp = 1; max / exp > 0; exp *= 10) {
            array = countingSortByDigit(array, exp);
        }
        return array;
    }

    private int[] countingSortByDigit(int[] array, int exp) {
        int[] output = new int[array.length];
        int[] count = new int[10];
        for (int i : array) {
            count[(i / exp) % 10]++;
        }
        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }
        for (int i = array.length - 1; i >= 0; i--) {
            output[count[(array[i] / exp) % 10] - 1] = array[i];
            count[(array[i] / exp) % 10]--;
        }
        return output;
    }

}
