package io;


import java.io.Serializable;

public class Message implements Serializable{
    private String header;

    private Object obj;

    public String getHeader() {
        return header;
    }
    public Object getObj() {
        return obj;
    }

}
