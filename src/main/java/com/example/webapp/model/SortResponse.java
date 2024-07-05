package com.example.webapp.model;


import org.springframework.hateoas.RepresentationModel;

public class SortResponse extends RepresentationModel<SortResponse> {
    private int[] sortedArray;

    public SortResponse(int[] sortedArray) {
        this.sortedArray = sortedArray;
    }

    public int[] getSortedArray() {
        return sortedArray;
    }

    public void setSortedArray(int[] sortedArray) {
        this.sortedArray = sortedArray;
    }
}
