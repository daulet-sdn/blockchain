package com.company.models;

public interface Block {
    String getHash();
    void setHash(String hash);
    String getPrevHash();
    void setPrevHash(String hash);
    Long getGeneratingTime();
    void setGeneratingTime(Long generationTime);
    void setCreatedBy(int createdBy);
    void setN(int n);
}
