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

    public MessageReceivedIL()
    {
        this.builder = new SentenceBuilder();
    }

    public void handle(MessageReceivedEvent event) {

        String message = builder.NoSymbols(event.getMessage().getContent());
        IChannel channel = event.getMessage().getChannel();
        IDiscordClient client = event.getClient();
        String translated = builder.TranslateSentence(message);
        try {
            new MessageBuilder(client).withChannel(channel).withContent(translated).build();
        }catch (Exception e)
        {
            System.out.print("Error while processing message");
            e.printStackTrace();
        }
    }
}
