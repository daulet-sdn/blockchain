package com.company.service;

import com.company.models.Block;

public abstract class BlockFactory {

    public Block createBlock(int id, String prevHash) {
        Block block = getNewBlock(id, prevHash);
        applyHash(block);
        System.out.println(block);
        return block;
    }

    protected abstract Block getNewBlock(int id, String prevHash);
    protected abstract void applyHash(Block block);
}
