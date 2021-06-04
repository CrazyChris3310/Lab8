package utilities;

import utilities.commands.Command;

import java.io.Serializable;
import java.nio.channels.SocketChannel;

public class Request implements Serializable {
    private String login;
    private byte[] password;
    private Command command;
    private boolean registering;
    private static final long serialVersionUID = 123456L;

    public Request(String login, byte[] password) {
        this.login = login;
        this.password = password;
    }

    public byte[] getPassword() {
        return password;
    }

    public String getLogin() {
        return login;
    }

    public void setCommand(Command command) {
        this.command = command;
    }

    public void setRegistering(boolean registering) {
        this.registering = registering;
    }

    public Command getCommand() {
        return command;
    }


}
