package com.company.models.impl;

import com.company.models.Block;

import java.io.Serial;
import java.io.Serializable;

public class StandardBlock implements Block, Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Integer id;
    private Long timestamp;
    private Long magicNumber;
    private String prevBlockHash;
    private String blockHash;
    private Long generatingTime;
    private int createdBy;
    private int N;

    public void setId(Integer id) {
        this.id = id;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setMagicNumber(Long magicNumber) {
        this.magicNumber = magicNumber;
    }

    @Override
    public String getPrevHash() {
        return prevBlockHash;
    }

    @Override
    public void setPrevHash(String prevBlockHash) {
        this.prevBlockHash = prevBlockHash;
    }

    @Override
    public Long getGeneratingTime() {
        return generatingTime;
    }

    @Override
    public void setGeneratingTime(Long generatingTime) {
        this.generatingTime = generatingTime;
    }

    @Override
    public String getHash() {
        return blockHash;
    }

    @Override
    public void setHash(String blockHash) {
        this.blockHash = blockHash;
    }

    @Override
    public void setCreatedBy(int createdBy) {
        this.createdBy = createdBy;
    }

    @Override
    public void setN(int n) {
        N = n;
    }

    @Override
    public String toString() {
        return "Block:\n" +
                "Created by miner # " + createdBy + '\n' +
                "Id: " + id + '\n' +
                "Timestamp: " + timestamp + '\n' +
                "Magic number: " + magicNumber + '\n' +
                "Hash of the previous block: \n" + prevBlockHash + '\n' +
                "Hash of the block: \n" + blockHash + '\n' +
                "Block was generating for " + generatingTime + " seconds\n" +
                "N was increased to " + N;
    }
}
