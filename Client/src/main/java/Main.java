import gui.InputKillerFrame;
import gui.MainFrame;
import gui.RemovalFrame;
import gui.ServerDataFrame;
import utilities.Process;
import input.ConsoleInput;
import utilities.ConnectionManager;

public class Main {


    public static void main(String[] args) {

        ConnectionManager cm = new ConnectionManager();
        ServerDataFrame dataFrame = new ServerDataFrame(cm);

//        new MainFrame(new Process(new ConsoleInput(), cm));

//        InputKillerFrame frame = new InputKillerFrame();

//        RemovalFrame frame = new RemovalFrame();
//        frame.init();

    }
}
