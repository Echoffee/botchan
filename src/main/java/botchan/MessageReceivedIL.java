package botchan;

import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.api.events.IListener;
import sx.blah.discord.handle.impl.events.MessageReceivedEvent;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.util.MessageBuilder;

/**
 * Created by echo on 21/12/2016.
 */
public class MessageReceivedIL implements IListener<MessageReceivedEvent> {

    public SentenceBuilder builder;

    public String waifu;

    public MessageReceivedIL()
    {
        this.builder = new SentenceBuilder();
    }

    public void handle(MessageReceivedEvent event) {

        String message = builder.NoSymbols(event.getMessage().getContent());
        IChannel channel = event.getMessage().getChannel();
        IDiscordClient client = event.getClient();
        Sentence translated = builder.TranslateMessage(message);
        try {
            if (translated.Matches("__greeting__ __botchan__"))
                new MessageBuilder(client).withChannel(channel).withContent("win").build();

            if (translated.Matches("__je__ __etre__ $$waifu$$"))
            {
                translated.ComputeVariables();
                waifu = translated.variables.get("$$waifu$$");
                new MessageBuilder(client).withChannel(channel).withContent("ok").build();
            }

            if (translated.Matches("__qui__ __etre__ __je__ ?"))
            {
                new MessageBuilder(client).withChannel(channel).withContent("Tu es " + waifu).build();
            }

        }catch (Exception e)
        {
            System.out.print("Error while processing message");
            e.printStackTrace();
        }
    }
}
