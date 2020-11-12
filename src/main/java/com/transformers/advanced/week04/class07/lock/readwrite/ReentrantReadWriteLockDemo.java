package com.transformers.advanced.week04.class07.lock.readwrite;

public class ReentrantReadWriteLockDemo {
    public static void main(String[] args) {
        Count2 count = new Count2();
        for (int i = 0; i < 5; i++) {
            new Thread(count::get).start();
        }
        for (int i = 0; i < 5; i++) {
            new Thread(count::put).start();
        }
    }
    /**
     * Thread-0 get start
     * Thread-3 get start
     * Thread-1 get start
     * Thread-4 get start
     * Thread-2 get start
     * Thread-3 get end
     * Thread-4 get end
     * Thread-0 get end
     * Thread-2 get end
     * Thread-1 get end
     * Thread-5 put start
     * Thread-5 put end
     * Thread-6 put start
     * Thread-6 put end
     * Thread-7 put start
     * Thread-7 put end
     * Thread-8 put start
     * Thread-8 put end
     * Thread-9 put start
     * Thread-9 put end
     */
}
