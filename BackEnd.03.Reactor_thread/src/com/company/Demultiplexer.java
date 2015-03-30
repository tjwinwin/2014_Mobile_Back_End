package com.company;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

// 지연 문제가 발생되는 demultiplex()를 별도의 스레드로 분리
public class Demultiplexer implements Runnable {

    // 외부로 받아야 할 값을 생성자로 처리
    private final int HEADER_SIZE = 6;
    private Socket socket;
    private HandleMap handleMap;

    public Demultiplexer(Socket socket, HandleMap handleMap) {
        this.socket = socket;
        this.handleMap = handleMap;
    }

    // 스레드로 만들기 위해 Runnable을 받아옵니다.
    @Override
    public void run() {
        // Dispatcher의 demultiplex부분을 옮김
        // 프로토콜을 받으면 헤더정보를 보고 데이터를 분배
        try {
            InputStream inputStream = socket.getInputStream();
            // 헤더 사이즈(6)만큼 읽어들이기
            byte[] buffer = new byte[HEADER_SIZE];
            inputStream.read(buffer);
            String header = new String(buffer);

            handleMap.get(header).handleEvent(inputStream);

            // 헤더의 분기점 작성. String switch는 jdk1.7부터 지원
            if (header == "0x5001") {
                StreamSayHelloEventHandler sayHelloProtocol = new StreamSayHelloEventHandler();
                sayHelloProtocol.handleEvent(inputStream);
            } if (header == "0x6001") {
                StreamUpdateProfileEventHandler updateProfileProtocol = new StreamUpdateProfileEventHandler();
                updateProfileProtocol.handleEvent(inputStream);
            }   // 프로세스의 종류가 늘어날 때 마다 분기점을 생성해야 한다.
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
