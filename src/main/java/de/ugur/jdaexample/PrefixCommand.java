package de.ugur.jdaexample;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class PrefixCommand extends ListenerAdapter { //Extends the ListenerAdapter Method to use Listeners

    //The MessageRecivedEvent is the event, which will help us, to make the normal command :)

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        if (event.getMessage().getContentStripped().equals(Config.get("prefix") + "embed")) { //Checks if the message that we send contains the prefix and the command name
            EmbedBuilder embedBuilder = new EmbedBuilder()
                    .setTitle("You can make this to whatever you want")
                    .setDescription("You can make this to whatever you want")
                    .addField("You can make this to whatever you want", "I mean realy whatever you want", true)
                    .addBlankField(true) //A Blank field
                    .setFooter(event.getMember().getAvatarUrl()); // Sets the Footer to the Avatar Url

            event.getChannel().sendMessageEmbeds(embedBuilder.build()).queue(); //Sends the Embed to the Channel, there we used the command. Dont forget the .queue(); at the end ^^
        } else if (event.getMessage().getContentStripped().equals(Config.get("prefix") + "test")) {
            event.getChannel().sendMessage("Test"); //Sends a normal message
        }
    }
}
