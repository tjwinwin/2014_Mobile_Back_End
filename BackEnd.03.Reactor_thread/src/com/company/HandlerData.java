package com.company;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Text;

public class HandlerData {

    // Attribute(헤더) Text(클래스명)을 지정하고 Get함수를 만들어 줍니다.
    @Attribute(required = false)
    private String header;

    @Text
    private String text;

    public String getHeader() {
        return header;
    }

    public String getHandler() {
        return text;
    }
}
