package com.company;

import java.io.IOException;
import java.net.ServerSocket;

// Reactor Pattern
// HandleMap에 Handler를 등록을 하고 호출을 하면 HandleMap의 key값에 해당하는 EvntHandler를 실행시키는 것
// 연결을 받아, demultiplex시키고 HandleMap에 헤더인 key값과 EventHandler를 연결시키는 역할
public class Reactor {

    // 서버 메인에서 하던 역할을 대신
    // 생성자로 서버소켓을 생성하고 스타트 서버 메서드에서 디스패치를 진행하게 함
    private ServerSocket serverSocket;
    private HandleMap handleMap;

    public Reactor(int port){
        handleMap = new HandleMap();
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void startServer() {

        Dispatcher dispatcher = new Dispatcher();

        while (true) {
            //Reactor에 전달 인자가 부족한 문제를 HandleMap을 넘겨주어서 해결합니다.
            dispatcher.dispatcher(serverSocket, handleMap);
        }
    }

    // Handler클래스 내에서 getHandler()로 헤더를 가져올 수 있지만
    // xml에서 헤더명을 가져와서 등록을 할때를 위해
    // Reactor클래스에 있는 resisterHandler함수를 오버로딩해서
    // String header로 헤더를 추가할 수 있게 함
    public void registerHandler(String header, EventHandler handler) {
        handleMap.put(header, handler);
    }

    // HandleMap에 EventHandler를 등록
    public void registerHandler(EventHandler handler) {
        handleMap.put(handler.getHandler(), handler);
    }

    public void removeHandler(EventHandler handler) {
        handleMap.remove(handler.getHandler());
    }
}
