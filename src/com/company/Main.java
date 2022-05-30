package com.company;

import com.company.models.Blockchain;
import com.company.models.impl.BlockchainImpl;
import com.company.service.BlockFactory;
import com.company.service.Miner;
import com.company.service.impl.MinerImpl;
import com.company.service.impl.StandardBlockFactory;
import com.company.validator.BlockchainValidator;
import com.company.validator.impl.BlockchainValidatorImpl;

public class Main {

    public static void main(String[] args) {
        BlockFactory blockFactory = new StandardBlockFactory();
        BlockchainValidator blockchainValidator = new BlockchainValidatorImpl();
        Blockchain blockchain = BlockchainImpl.getInstance(blockchainValidator);
        Miner miner = new MinerImpl(blockchain, blockFactory);
        miner.mine();
        blockchain.getBlocks().forEach(System.out::println);
    }
}
