package utilities;

import utilities.commands.Command;

import java.io.Serializable;
import java.nio.channels.SocketChannel;

public class Request implements Serializable {
    private String login;
    private String password;
    private Command command;
    private boolean registering;
    transient private SocketChannel source;
    transient private String receivingError;

    private static final long serialVersionUID = 123456L;

    public String getPassword() {
        return password;
    }

    public String getLogin() {
        return login;
    }

    public void setSource(SocketChannel source) {
        this.source = source;
    }

    public SocketChannel getSource() {
        return source;
    }

    public Command getCommand() {
        return command;
    }

    public String getReceivingError() {
        return receivingError;
    }

    public void setReceivingError(String receivingError) {
        this.receivingError = receivingError;
    }

    public boolean isRegistering() {
        return registering;
    }
}
