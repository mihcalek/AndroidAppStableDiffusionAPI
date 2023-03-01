package com.example.asynctaskai;

import java.util.Arrays;

public class ResponseResult {
    private String status;
    private double generationTime;
    private int id;
    String[] output;

    public ResponseResult(String status, double generationTime, int id, String[] output) {
        this.status = status;
        this.generationTime = generationTime;
        this.id = id;
        this.output = output;
    }

    public ResponseResult() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getGenerationTime() {
        return generationTime;
    }

    public void setGenerationTime(double generationTime) {
        this.generationTime = generationTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String[] getOutput() {
        return output;
    }

    public void setOutput(String[] output) {
        this.output = output;
    }

    @Override
    public String toString() {
        return "ResponseResult{" +
                "status='" + status + '\'' +
                ", generationTime=" + generationTime +
                ", id=" + id +
                ", output=" + Arrays.toString(output) +
                '}';
    }
}
