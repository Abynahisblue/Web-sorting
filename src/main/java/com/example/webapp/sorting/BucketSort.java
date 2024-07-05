package com.example.webapp.sorting;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class BucketSort {

    public int[] sort(int[] array) {
        if (array.length == 0) {
            return array;
        }
        int maxValue = Arrays.stream(array).max().orElse(0);
        int numberOfBuckets = (int) Math.sqrt(array.length);
        List<Integer>[] buckets = new List[numberOfBuckets];
        for (int i = 0; i < numberOfBuckets; i++) {
            buckets[i] = new ArrayList<>();
        }
        for (int num : array) {
            int bucketIndex = (num * numberOfBuckets) / (maxValue + 1);
            buckets[bucketIndex].add(num);
        }
        int[] sortedArray = new int[array.length];
        int index = 0;
        for (List<Integer> bucket : buckets) {
            int[] bucketArray = bucket.stream().mapToInt(Integer::intValue).toArray();
            Arrays.sort(bucketArray);
            for (int num : bucketArray) {
                sortedArray[index++] = num;
            }
        }
        return sortedArray;
    }

}
