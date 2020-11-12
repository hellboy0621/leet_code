package com.transformers.advanced.week04.class07.atomic.count;

public class Count extends BaseCount {
    @Override
    public void add() {
        num++;
    }

    @Override
    public int get() {
        return num;
    }
}
