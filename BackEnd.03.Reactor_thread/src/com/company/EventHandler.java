package com.company;

import java.io.InputStream;

// Map형태로 되어있는 HandleMap에서 헤더에 해당하는 키를 가진 값을 찾습니다.
// 프로토콜 대신 핸들러로 분류합니다.
//HandleMap에서 다형성을 이용하여 클래스를 관리 할 수 있도록 먼저 Handler의 형태를 정의
public interface EventHandler {

    // Handler마다 가지고 있는 고유의 키값을(HandlerMap에 키값으로 등록하기 위해) 반환
    public String getHandler();
    // 데이터를 받아서 처리
    public void handleEvent(InputStream inputStream);

}
