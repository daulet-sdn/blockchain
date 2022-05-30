package com.company.service.impl;

import com.company.models.Block;
import com.company.models.impl.StandardBlock;
import com.company.service.BlockFactory;
import com.company.models.Blockchain;
import com.company.utils.StringUtil;

import java.util.Date;
import java.util.Random;
import java.util.regex.Pattern;

public class StandardBlockFactory extends BlockFactory {

    @Override
    protected Block getNewBlock(int id, String prevHash) {
        var block = new StandardBlock();
        block.setId(id);
        block.setTimestamp(new Date().getTime());
        block.setPrevHash(prevHash);
        block.setN(Blockchain.N);
        return block;
    }

    @Override
    protected void applyHash(Block block) {
        var newBlock = (StandardBlock) block;
        var random = new Random();
        newBlock.setMagicNumber(random.nextLong());
        var hash = StringUtil.applySha256(newBlock.toString());
        var regex = String.format("0{%d}.+", Blockchain.N);
        var pattern = Pattern.compile(regex);
        var matcher = pattern.matcher(hash);
        while (!matcher.matches()) {
            newBlock.setMagicNumber(random.nextLong());
            hash = StringUtil.applySha256(newBlock.toString());
            matcher = pattern.matcher(hash);
        }
        newBlock.setHash(hash);
        newBlock.setGeneratingTime((new Date().getTime() - newBlock.getTimestamp()) / 1000);
    }
}
