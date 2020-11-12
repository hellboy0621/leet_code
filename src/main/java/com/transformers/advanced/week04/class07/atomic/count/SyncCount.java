package com.transformers.advanced.week04.class07.atomic.count;

public class SyncCount extends BaseCount {
    @Override
    public synchronized void add() {
        num++;
    }

    @Override
    public int get() {
        return num;
    }
}
