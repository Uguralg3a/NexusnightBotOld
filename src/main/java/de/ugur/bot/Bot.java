package de.ugur.bot;

import de.ugur.bot.commands.admin.*;
import de.ugur.bot.commands.info.HelpCommand;
import de.ugur.bot.commands.info.InfoCommand;
import de.ugur.bot.commands.test.TestCommand;
import de.ugur.bot.commands.test.UptimeCommand;
import de.ugur.bot.manage.MySQL;
import de.ugur.bot.manage.SQLManager;
import de.ugur.bot.ticketsystem.ButtonClick;
import de.ugur.bot.ticketsystem.Commands;
import de.ugur.bot.events.ReadyListener;
import de.ugur.bot.ticketsystem.SelectMenu;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.cache.CacheFlag;

import javax.security.auth.login.LoginException;

public class Bot {

    private final JDA jda;

    public Bot() throws LoginException, InterruptedException {

        MySQL.connect();
        SQLManager.onCreate();

        JDABuilder builder = JDABuilder.createDefault(Config.get("token"));
        builder.disableCache(CacheFlag.MEMBER_OVERRIDES, CacheFlag.VOICE_STATE);
        builder.setBulkDeleteSplittingEnabled(false);
        builder.disableIntents(GatewayIntent.GUILD_PRESENCES, GatewayIntent.GUILD_MESSAGE_TYPING);
        builder.enableIntents(GatewayIntent.GUILD_MESSAGES, GatewayIntent.GUILD_MEMBERS, GatewayIntent.MESSAGE_CONTENT);
        builder.setLargeThreshold(50);

        builder.setActivity(Activity.playing("Minecraft"));
        builder.addEventListeners(new ReadyListener());
        builder.addEventListeners(new ButtonClick());
        builder.addEventListeners(new SelectMenu());

        //Commands
        builder.addEventListeners(new Commands());


        jda = builder.build();

        jda.awaitReady();
        loadFeatures();
    }

    public void loadFeatures(){
        new HelpCommand(jda, Config.get("guildid"));
        new InfoCommand(jda, Config.get("guildid"));
        new TestCommand(jda, Config.get("guildid"));
        new ServerInfoCommand(jda, Config.get("guildid"));
        new UserInfoCommand(jda, Config.get("guildid"));
        new ClearCommand(jda, Config.get("guildid"));
        new BanCommand(jda, Config.get("guildid"));
        new KickCommand(jda, Config.get("guildid"));
        new SayCommand(jda, Config.get("guildid"));
        new UptimeCommand(jda, Config.get("guildid"));
        new UnbanCommand(jda, Config.get("guildid"));
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
