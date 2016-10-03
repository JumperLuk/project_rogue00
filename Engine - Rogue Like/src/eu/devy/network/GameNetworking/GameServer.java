package eu.devy.network.GameNetworking;

import eu.devy.network.Server.Server;

/**
 * Created by schueler on 01.10.2016.
 */
public class GameServer extends Server {

    public GameServer(int port)
    {
        super(port);
    }

    @Override
    public void processMessage(String str)
    {
        //Empfangene Angaben immer Spielerinfromationen -> an andere Spieler weiterleiten

    }
}
