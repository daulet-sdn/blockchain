package com.company.service.impl;

import com.company.service.BlockFactory;
import com.company.models.Blockchain;
import com.company.service.Miner;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class MinerImpl extends Miner {

    private final static int POOL_SIZE = 11;
    private final static int NUMBER_OF_MINERS = 22;

    private int createdBy;

    private final Blockchain blockchain;
    private final BlockFactory blockFactory;

    public MinerImpl(Blockchain blockchain, BlockFactory blockFactory) {
        this.blockchain = blockchain;
        this.blockFactory = blockFactory;
    }

    @Override
    public void mine() {
        var executor = Executors.newFixedThreadPool(POOL_SIZE);
        var startSize = blockchain.getBlocks().size();
        for (var i = 1; i <= NUMBER_OF_MINERS; i++) {
            createdBy = i;
            executor.submit(() -> {
                var currentId = blockchain.getBlocks().size();
                var block = blockFactory.createBlock(++currentId, getPrevBlockHash());
                block.setCreatedBy(createdBy);
                blockchain.addBlock(block);
            });
        }

        while (true) {
            if (blockchain.getBlocks().size() >= startSize + 5) {
                executor.shutdown();
                break;
            }
        }
    }

    private String getPrevBlockHash() {
        var blocks = blockchain.getBlocks();
        return blocks.isEmpty() ? "0" : blocks.peekLast().getHash();
    }
}
