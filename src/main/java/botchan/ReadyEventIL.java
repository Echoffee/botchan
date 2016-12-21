package botchan;

import sx.blah.discord.api.events.IListener;
import sx.blah.discord.handle.impl.events.MessageReceivedEvent;
import sx.blah.discord.handle.impl.events.ReadyEvent;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.util.MessageBuilder;

import java.util.List;

/**
 * Created by echo on 21/12/2016.
 */
public class ReadyEventIL implements IListener<ReadyEvent> {

    public void handle(ReadyEvent event) {
        List<IChannel> channels = event.getClient().getChannels();
        try {
            new MessageBuilder(event.getClient())
                    .withContent("Salut !").withChannel(channels.get(0)).build();
        } catch (Exception e) {
e.printStackTrace();
        }
    }


}
