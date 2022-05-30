package com.company.validator;

import com.company.models.Block;
import com.company.models.Blockchain;

public interface BlockchainValidator {
    void validateBlockchain(Blockchain blockchain) throws InvalidBlockchainException;
    void validateBlock(Block block, Block prevBlock) throws InvalidBlockchainException;
}
