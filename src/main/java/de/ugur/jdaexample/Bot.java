package de.ugur.jdaexample;


import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.cache.CacheFlag;
import javax.security.auth.login.LoginException;

public class Bot {

    private final JDA jda;

    public Bot() throws LoginException, InterruptedException {

        JDABuilder builder = JDABuilder.createDefault(Config.get("token"));
        builder.disableCache(CacheFlag.MEMBER_OVERRIDES, CacheFlag.VOICE_STATE);
        builder.setBulkDeleteSplittingEnabled(false);
        builder.disableIntents(GatewayIntent.GUILD_PRESENCES, GatewayIntent.GUILD_MESSAGE_TYPING);
        builder.enableIntents(GatewayIntent.GUILD_MESSAGES, GatewayIntent.GUILD_MEMBERS, GatewayIntent.MESSAGE_CONTENT, GatewayIntent.GUILD_MEMBERS);
        builder.setLargeThreshold(50);

        builder.setActivity(Activity.listening("Cool Music"));
        //You can set the Activity type to different types. Activity.watching("TV")); oder Activity.listening("Cool Music")); or Activity.playing("With my Code")); or Acitvity.streaming("My Code"));

        //Register a normal command with a prefix
        builder.addEventListeners(new PrefixCommand());



        jda = builder.build();

        jda.awaitReady();
        loadFeatures();
    }

    //Load the Slash Commands
    public void loadFeatures(){
        new SlashCommand(jda, Config.get("guildid"));

    }

    public static void main(String[] args) {
        try {
            new Bot();
        } catch (LoginException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


    public void shutdown(){
        jda.shutdown();
    }

    public JDA getJda() {
        return jda;
    }


}
