/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcpexample3;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Matthew
 */
public class TextProtocol implements Serializable{
    
    private String header;
    private String message;
    private ArrayList<String> stringArray;

    public TextProtocol(String header) {
        this.header = header;
    }
    
    public TextProtocol(String header, String message) {
        this.header = header;
        this.message = message;
    }

    public TextProtocol(String header, ArrayList<String> stringArray) {
        this.header = header;
        this.stringArray = stringArray;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ArrayList<String> getStringArray() {
        return stringArray;
    }

    public void setStringArray(ArrayList<String> stringArray) {
        this.stringArray = stringArray;
    }
    
    
    
}
