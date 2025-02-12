package com.example.webapp.services;

public interface SortService {
    int[] heapSort(int[] array);
    int[] quickSort(int[] array);
    int[] mergeSort(int[] array);
    int[] radixSort(int[] array);
    int[] bucketSort(int[] array);
}

