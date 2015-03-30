package com.company;

import java.net.ServerSocket;

public class ThreadPoolDispatcher {

    static final String NUMBERTHREADS = "8";
    static final String THREADPROP = "Threads";

    private int numThreads;

    public ThreadPoolDispatcher() {
        // 시스템 정보의 스레드 수를 가져오거나 기본 값을 가져옴
        numThreads = Integer.parseInt(System.getProperty(THREADPROP, NUMBERTHREADS));
    }

    public void dispatch(final ServerSocket serverSocket, final HandleMap handleMap) {
        for (int i = 0; i < (numThreads - 1); i++) {
            public void run() {
                dipatchLoop (serverSocket, handleMap);
            }
        }
        thread.start;
        System.out.println("Created and started Thread " + thread.getName());
    }
    System.out.println("Iterative server starting in main thread " + Thread.currentThread().getName());
    dispatchLoop(serverSocket, handleMap);
}
