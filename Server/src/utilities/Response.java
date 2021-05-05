package utilities;

import java.io.Serializable;
import java.util.ArrayList;

public class Response implements Serializable {

    ArrayList<String> answer;
    private String message;

    private static final long serialVersionUID = 215L;

    public Response(){
        answer = null;
        message = null;
    }

    public Response(ArrayList<String> list) {
        this.answer = list;
    }

    public void setCollection(ArrayList<String> collection) {
        this.answer = collection;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ArrayList<String> getCollection() {
        return answer;
    }

    public String getMessage() {
        return message;
    }
}