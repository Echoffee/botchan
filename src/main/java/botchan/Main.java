package botchan;

import sx.blah.discord.api.ClientBuilder;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.api.events.EventDispatcher;
import sx.blah.discord.util.DiscordException;

import java.io.*;

public class Main {

    public static void main(String[] args) {
        String token = "";
        try {
            token = Main.getToken();
        }catch (IOException e)
        {
            System.out.print("Token file not found");
            e.printStackTrace();
        }
        Bot waifu = new Bot(token);
        waifu.AddListener(new ReadyEventIL());
        waifu.AddListener(new MessageReceivedIL());
    }

    public static String getToken() throws FileNotFoundException, IOException
    {
        BufferedReader br = new BufferedReader(new FileReader("token.txt"));
        return br.readLine();
    }

}