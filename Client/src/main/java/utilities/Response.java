package utilities;

import dragon.Dragon;

import java.io.Serializable;
import java.util.ArrayList;

public class Response implements Serializable {

    private ArrayList<Dragon> collection;
    private ArrayList<String> answer;
    private String message;
    private boolean successfulConnect;
    private boolean toExit;

    private static final long serialVersionUID = 215L;

    public Response() {
        answer = null;
        message = "";
        toExit = false;
        successfulConnect = false;
    }


    public Response(ArrayList<String> list) {
        this.answer = list;
    }

    public void setAnswer(ArrayList<String> collection) {
        this.answer = collection;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setToExit(boolean flag) {
        toExit = flag;
    }

    public boolean getToExit() {
        return toExit;
    }

    public ArrayList<String> getAnswer() {
        return answer;
    }

    public String getMessage() {
        return message;
    }

    public ArrayList<Dragon> getCollection() {
        return collection;
    }

    public boolean isSuccessfulConnect() {
        return successfulConnect;
    }
}
