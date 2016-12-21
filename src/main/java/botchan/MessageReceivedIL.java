package botchan;

import sx.blah.discord.api.events.IListener;
import sx.blah.discord.handle.impl.events.MessageReceivedEvent;
import sx.blah.discord.util.MessageBuilder;

/**
 * Created by echo on 21/12/2016.
 */
public class MessageReceivedIL implements IListener<MessageReceivedEvent> {
    public void handle(MessageReceivedEvent event) {
        try {

            new MessageBuilder(event.getClient()).withChannel(event.getMessage().getChannel())
                    .withContent(event.getMessage().getContent()).build();
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
