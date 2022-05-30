package com.company.models;

import java.util.Deque;

public abstract class Blockchain {
    public static volatile int N;
    abstract public Deque<Block> getBlocks();
    abstract public void addBlock(Block block);
}
