package com.example.webapp.services.Impl;

import com.example.webapp.services.SortService;
import com.example.webapp.sorting.HeapSort;
import com.example.webapp.sorting.QuickSort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SortServiceImpl implements SortService {

    @Autowired
    private HeapSort heapSort;

    @Autowired
    private QuickSort quickSort;

    @Override
    public int[] heapSort(int[] array) {
        return heapSort.sort(array);
    }

    @Override
    public int[] quickSort(int[] array) {
        return quickSort.sort(array);
    }
}
