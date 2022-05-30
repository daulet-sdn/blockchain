package com.company.models.impl;

import com.company.config.BlockchainConfig;
import com.company.models.Block;
import com.company.models.Blockchain;
import com.company.utils.SerializationUtils;
import com.company.validator.BlockchainValidator;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Objects;

public class BlockchainImpl extends Blockchain implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private final Deque<Block> blocks = new ArrayDeque<>();
    private final transient BlockchainValidator validator;
    private static Blockchain blockchain;

    private BlockchainImpl(BlockchainValidator validator) {
        BlockchainImpl blockchain = (BlockchainImpl) SerializationUtils.deserialize(BlockchainConfig.SERIALIZE_PATH);
        if (Objects.nonNull(blockchain)) {
            this.blocks.addAll(blockchain.getBlocks());
        }
        this.validator = validator;
    }

    public static Blockchain getInstance(BlockchainValidator validator) {
        if (Objects.nonNull(blockchain)) {
            return blockchain;
        }
        blockchain = new BlockchainImpl(validator);
        return blockchain;
    }

    @Override
    public synchronized Deque<Block> getBlocks() {
        return this.blocks;
    }

    @Override
    public synchronized void addBlock(Block block) {
        validator.validateBlock(block, blocks.peekLast());
        if (block.getGeneratingTime() <= 60) {
            N++;
        } else {
            if (N > 0) {
                N--;
            }
        }
        blocks.offerLast(block);
        SerializationUtils.serialize(this, BlockchainConfig.SERIALIZE_PATH);
    }
}
