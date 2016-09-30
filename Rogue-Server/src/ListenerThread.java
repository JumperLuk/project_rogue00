import java.net.ServerSocket;

/**
 * Created by schueler on 30.09.2016.
 */
public class ListenerThread extends Thread{

    private ServerSocket serverSock;
    private Controller controller;
    private boolean endListening;

    public ListenerThread(ServerSocket serverSock)
    {
        this.serverSock = serverSock;
        endListening = false;
    }

    @Override
    public void run()
    {
        while (true)
        {
            if(!endListening)
                break;


        }
    }


}
