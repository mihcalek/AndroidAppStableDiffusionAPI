package com.example.asynctaskai;

public class PostRequest {
    private String key;
    private String prompt;
    private String negativePrompt;

    public PostRequest() {
    }

    public PostRequest(String key, String prompt, String negativePrompt) {
        this.key = key;
        this.prompt = prompt;
        this.negativePrompt = negativePrompt;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getPrompt() {
        return prompt;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    public String getNegativePrompt() {
        return negativePrompt;
    }

    public void setNegativePrompt(String negativePrompt) {
        this.negativePrompt = negativePrompt;
    }

    @Override
    public String toString() {
        return "HTTPRequest{" +
                "key='" + key + '\'' +
                ", prompt='" + prompt + '\'' +
                ", negativePrompt='" + negativePrompt + '\'' +
                '}';
    }
}
