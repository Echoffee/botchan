package botchan;

import sx.blah.discord.api.ClientBuilder;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.api.events.EventDispatcher;
import sx.blah.discord.util.DiscordException;

import java.io.*;

public class Main {

    public static void main(String[] args) {
        try {
            IDiscordClient client = Main.getClient(getToken(), true); // Gets the client object (from the first example)
            EventDispatcher dispatcher = client.getDispatcher(); // Gets the EventDispatcher instance for this client instance
            dispatcher.registerListener(new InterfaceListener()); // Registers the IListener example class from above
        }catch (Exception e)
        {
            System.out.print("Error");
        }
    }

    public static String getToken() throws FileNotFoundException, IOException
    {
        BufferedReader br = new BufferedReader(new FileReader("token.txt"));
        return br.readLine();
    }

    public static IDiscordClient getClient(String token, boolean login) throws DiscordException{ // Returns an instance of the Discord client
        ClientBuilder clientBuilder = new ClientBuilder(); // Creates the ClientBuilder instance
        clientBuilder.withToken(token); // Adds the login info to the builder
        if (login) {
            return clientBuilder.login(); // Creates the client instance and logs the client in
        } else {
            return clientBuilder.build(); // Creates the client instance but it doesn't log the client in yet, you would have to call client.login() yourself
        }
    }
}