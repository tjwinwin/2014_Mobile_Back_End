package com.company;
// SimpleFramework:  xml을 파싱
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;
import java.util.List;

public class HandlerListData {

    @ElementList(entry = "handler", inline = true)
    private List<HandlerData> handler;

    // 서버의 이름을 구분하기 위해 Attribute를 설정
    @Attribute
    private String name;

    public List<HandlerData> getHandler() {
        return handler;
    }
    public String getName() {
        return name;
    }
}
