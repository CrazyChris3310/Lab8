import gui.InputKillerFrame;
import gui.RemovalFrame;

public class Main {


    public static void main(String[] args) {

//        ConnectionManager cm = new ConnectionManager();
//        ServerDataFrame dataFrame = new ServerDataFrame(cm);

//        new MainFrame(new Process(new ConsoleInput(), cm));

//        InputKillerFrame frame = new InputKillerFrame();

        RemovalFrame frame = new RemovalFrame();
        frame.init();

    }
}
