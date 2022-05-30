package com.company.validator.impl;

import com.company.config.BlockchainConfig;
import com.company.models.Block;
import com.company.models.Blockchain;
import com.company.validator.InvalidBlockException;
import com.company.validator.InvalidBlockchainException;
import com.company.validator.BlockchainValidator;

import java.util.Objects;

public class BlockchainValidatorImpl implements BlockchainValidator {

    @Override
    public void validateBlockchain(Blockchain blockchain) throws InvalidBlockchainException {
        proofOfWorkValidation(blockchain);
        hashValidation(blockchain);
    }

    @Override
    public void validateBlock(Block block, Block prevBlock) throws InvalidBlockException {
        proofOfWorkBlockValidation(block);
        hashOfBlockValidation(block, prevBlock);
    }

    private void proofOfWorkValidation(Blockchain blockchain) throws InvalidBlockchainException {
        for (Block block : blockchain.getBlocks()) {
            proofOfWorkBlockValidation(block);
        }
    }

    private void hashValidation(Blockchain blockchain) throws InvalidBlockchainException {
        var iterator = blockchain.getBlocks().iterator();
        var prevBlock = iterator.next();
        while (iterator.hasNext()) {
            var block = iterator.next();
            hashOfBlockValidation(block, prevBlock);
            prevBlock = block;
        }
    }

    private void proofOfWorkBlockValidation(Block block) {
        var amountOfZeros = "0".repeat(Blockchain.N);
        if (!block.getHash().startsWith(amountOfZeros)) {
            throw new InvalidBlockException("Not confirmed proof of work!");
        }
    }

    private void hashOfBlockValidation(Block block, Block prevBlock) {
        if (!Objects.equals(block.getPrevHash(), prevBlock.getHash())) {
            throw new InvalidBlockException("Invalid hashes!");
        }
    }
}
