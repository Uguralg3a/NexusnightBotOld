package de.ugur.bot;

import de.ugur.bot.bewerben.SetBewerbung;
import de.ugur.bot.slashCommands.devonly.ReactionRoles;
import de.ugur.bot.slashCommands.devonly.RulesCommand;
import de.ugur.bot.slashCommands.devonly.UpdateCommands;
import de.ugur.bot.slashCommands.info.HelpCommand;
import de.ugur.bot.slashCommands.info.InfoCommand;
import de.ugur.bot.slashCommands.moderation.*;
import de.ugur.bot.slashCommands.test.TestCommand;
import de.ugur.bot.manager.JoinManager;
import de.ugur.bot.slashCommands.tags.TagCommand;
import de.ugur.bot.ticketsystem.*;
import de.ugur.bot.events.ReadyListener;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.cache.CacheFlag;

import javax.security.auth.login.LoginException;

public class Bot {

    private final JDA jda;

    String guildid = Config.get("guildid");
    public Bot() throws LoginException, InterruptedException {



        JDABuilder builder = JDABuilder.createDefault(Config.get("token"));
        builder.disableCache(CacheFlag.MEMBER_OVERRIDES, CacheFlag.VOICE_STATE);
        builder.setBulkDeleteSplittingEnabled(false);
        builder.disableIntents(GatewayIntent.GUILD_PRESENCES, GatewayIntent.GUILD_MESSAGE_TYPING);
        builder.enableIntents(GatewayIntent.GUILD_MESSAGES, GatewayIntent.GUILD_MEMBERS, GatewayIntent.MESSAGE_CONTENT, GatewayIntent.GUILD_MEMBERS, GatewayIntent.GUILD_EMOJIS_AND_STICKERS);
        builder.setLargeThreshold(50);

        builder.setActivity(Activity.playing("auf Nexusnight.net")).setStatus(OnlineStatus.ONLINE);
        builder.addEventListeners(new ReadyListener());
        builder.addEventListeners(new TicketButtonSystem());
        builder.addEventListeners(new SelectMenu());

        //Ticket System
        builder.addEventListeners(new Bewerbung());
        builder.addEventListeners(new BugReport());
        builder.addEventListeners(new PlayerReport());
        builder.addEventListeners(new TeamlerReport());
        builder.addEventListeners(new TicketButtonSystem());
        builder.addEventListeners(new SelectMenu());

        //Manager
        builder.addEventListeners(new JoinManager());
        builder.addEventListeners(new ReactionRoles());


        //devonly

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
        new Commands(jda, Config.get("guildid"));
        new ClearCommand(jda, Config.get("guildid"));
        new BanCommand(jda, Config.get("guildid"));
        new KickCommand(jda, Config.get("guildid"));
        new SayCommand(jda, Config.get("guildid"));
        new UnbanCommand(jda, Config.get("guildid"));
        new RulesCommand(jda, Config.get("guildid"));
        new TagCommand(jda, Config.get("guildid"));
        new SetBewerbung(jda, guildid);
        new UpdateCommands(jda, guildid);
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
