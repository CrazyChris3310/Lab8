package utilities;

import dragon.Dragon;

import java.io.Serializable;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;

public class Response implements Serializable {

    private ArrayList<Dragon> collection;
    private ArrayList<String> answer;
    private String message;
    private boolean toExit;
    private boolean successfulConnect;
    transient SocketChannel destination;

    private static final long serialVersionUID = 215L;

    public Response() {
        answer = null;
        toExit = false;
        message = "";
        successfulConnect = false;
    }

    public Response(ArrayList<String> list) {
        this.answer = list;
    }

    public void setToExit(boolean flag) {
        toExit = flag;
    }

    public void setAnswer(ArrayList<String> collection) {
        this.answer = collection;
    }

    public ArrayList<Dragon> getCollection() {
        return collection;
    }

    public void setCollection(ArrayList<Dragon> collection) {
        this.collection = collection;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ArrayList<String> getAnswer() {
        return answer;
    }

    public String getMessage() {
        return message;
    }

    public void setDestination(SocketChannel channel) {
        this.destination = channel;
    }

    public SocketChannel getDestination() {
        return this.destination;
    }

    public void setSuccessfulConnect(boolean successfulConnect) {
        this.successfulConnect = successfulConnect;
    }
}
