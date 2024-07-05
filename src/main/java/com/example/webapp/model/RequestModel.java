package com.example.webapp.model;

public class RequestModel {
    private String array;
    private String algorithm;

    public RequestModel() {

    }
    public RequestModel(String array, String algorithm) {
        this.array = array;
    }
    public String getArray() {
        return array;
    }
    public void setArray(String array) {
        this.array = array;
    }
    public String getAlgorithm() {
        return algorithm;
    }
    public void setAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }

}
