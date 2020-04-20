package com.rainier.service;

public interface MyFileService {
    int insertMyFile(String name, String bendiUrl, String fuwuqiUrl);

    void quartzDeleteMyFile();

    void deleteFile();
}
