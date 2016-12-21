package botchan;

import org.w3c.dom.events.EventListener;
import sx.blah.discord.api.ClientBuilder;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.api.events.EventDispatcher;
import sx.blah.discord.api.events.IListener;
import sx.blah.discord.handle.impl.events.ReadyEvent;
import sx.blah.discord.util.DiscordException;

/**
 * Created by echo on 21/12/2016.
 */
public class Bot {

    public String token;
    public IDiscordClient client;
    public EventDispatcher dispatcher;

    public Bot(String token)
    {
        this.token = token;
        ClientBuilder b = new ClientBuilder();
        b.withToken(this.token);
        try {
            this.client = b.login();
            this.dispatcher = this.client.getDispatcher();
        }catch(DiscordException e)
        {
            e.printStackTrace();
        }
    }

    public void AddListener(IListener l)
    {
        this.dispatcher.registerListener(l);
    }
}
