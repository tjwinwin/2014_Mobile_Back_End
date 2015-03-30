package com.company;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import java.io.File;
import java.util.List;

public class ServerInitializer {

    public static void main(String[] args) {

        // 접속에 사용할 포트를 5000으로 설정
        int port = 5000;
        System.out.println("Server ON: " + port);

        Reactor reactor = new Reactor(port);

        try {
            // SimpleFramework로 xml파일을 객체로 생성
            Serializer serializer = new Persister();
            File source = new File("HandlerList.xml");
            ServerListData serverList = serializer.read(ServerListData.class, source);

            // 서버정보를 가져온 후 서버1의 핸들러들을 받아옴
            for (HandlerListData handlerListData : serverList.getServer()) {
                if("server1".equals(handlerListData.getName())) {
                    List<HandlerData> handlerList = handlerListData.getHandler();
                    for (HandlerData handler: handlerList){
                        try {
                            reactor.registerHandler(handler.getHeader(),
                                    (EventHandler)Class.forName(handler.getHandler()).newInstance());
                        } catch (InstantiationException e) {
                            e.printStackTrace();
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
//        reactor.registerHandler(new StreamSayHelloEventHandler());
//        reactor.registerHandler(new StreamUpdateProfileEventHandler());
        reactor.startServer();
    }
}

// 이하코드 내용은 Reactor 클래스에서 처리함
//// 접속에 사용할 포트를 5000으로 설정
//int port = 5000;
//System.out.println("Server ON: " + port);
//
//        // 서버 소켓을 생성함
//        try{
//            ServerSocket serverSocket = new ServerSocket(port);
////            Socket connection;
//            // Dispatcher에게 ServerSocket을 전달하도록 수정
//            Dispatcher dispatcher = new Dispatcher();
//
//            // 서버에 접속된 연결로부터 한 줄을 읽어와 콘솔에 출력함
//            while (true) {
////                connection = serverSocket.accept();
////                InputStreamReader inputStreamReader = new InputStreamReader(connection.getInputStream());
////                BufferedReader bufferedReader  = new BufferedReader(inputStreamReader);
////                String line = bufferedReader.readLine();
////
////                System.out.println("Read: " + line);
//
//                dispatcher.dispatcher(serverSocket);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//}
