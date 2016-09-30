import java.net.Socket;
import java.util.ArrayList;

/**
 * Created by schueler on 30.09.2016.
 */
public class Controller {

    private ListenerThread listenerT;
    private SenderThread senderT;
    private ArrayList<Socket> clients;

    public Controller()
    {
    }


    public ListenerThread getListenerT() {
        return listenerT;
    }

    public void setListenerT(ListenerThread listenerT) {
        this.listenerT = listenerT;
    }

    public SenderThread getSenderT() {
        return senderT;
    }

    public void setSenderT(SenderThread senderT) {
        this.senderT = senderT;
    }

    public ArrayList<Socket> getClients() {
        return clients;
    }

    public void setClients(ArrayList<Socket> clients) {
        this.clients = clients;
    }
}
