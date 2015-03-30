package com.company;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ThreadPerDispatcher implements Dispatcher {

    private final int HEADER_SIZE = 6;

    // HandleMap을 받아서 헤더값으로 get한 EventHandler에게 이벤트를 발생시킵니다.
    public void dispatcher (ServerSocket serverSocket, HandleMap handleMap) {

        // Reactor에서 진행한 루프를 대신함
        while (true) {
            // 서버 main으로부터 ServerSocket을 받아옵니다.
            // accept를 받아 Socket을 생성
            try {
                Socket socket = serverSocket.accept();

                Runnable demultiplexer = new Demultiplexer(socket, handleMap);
                Thread thread = new Thread(demultiplexer);
                thread.start();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
