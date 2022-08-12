package de.ugur.bot.slashCommands.tags;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.*;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;

import static net.dv8tion.jda.api.interactions.commands.OptionType.STRING;

public class TagCommand extends ListenerAdapter {

    private final JDA jda;
    private final Logger logger = LoggerFactory.getLogger("dclink-discord");

    public TagCommand(JDA jda, String guildId) {
        this.jda = jda;
        registerCommands(guildId);
        jda.addEventListener(this);
    }

    private void registerCommands(String guildId) {
        Guild guild = jda.getGuildById(guildId);
        guild.upsertCommand("tag", "TAG TAG TAG TAG")
                    .addOptions(new OptionData(STRING, "tag", "tag", true, true)).setDefaultPermissions(DefaultMemberPermissions.ENABLED)
                    .queue();
    }


    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        // Only accept commands from guilds
        if (event.getGuild() == null)
            return;
        switch (event.getName()) {
            case "tag":
                tag(event, event.getOption("tag").getAsString()); // content is required so no null-check here
                break;
        }
    }
    private void tag(SlashCommandInteractionEvent event, String tag) {
        if (tag.equals("test") || tag.equals("Test")) {
            event.reply("test").queue();
        } else if (tag.equals("tags") || tag.equals("Tags")) {
            EmbedBuilder tags = new EmbedBuilder()
                    .setTitle("Tags")
                    .setDescription("`test`, `tags`, `hilfmir`, `rindfleischetikettierungsueberwachungsgesetz`, `f`, `sozialmedia`, `ping`, `botinfo`, `dubistdoof`, `sorry`");
            event.replyEmbeds(tags.build()).queue();
        } else if (tag.equals("hilfmir") || tag.equals("Hilfmir")) {
            String hyperemoji = "<:hypers:1001778162167984148>";
            String pepesad = "<:pepesad:1001778238395256893>";
            event.reply("\uD83E\uDE84 ...⭐        ....⭐ .     ...⭐   \uD83C\uDF11 \n" +
                    "...⭐      ...⭐      ✈️  ...⭐ \n" +
                    "    ...⭐      ...⭐        ...⭐ \n" +
                    " \n" +
                    "\uD83D\uDEEB  - ----- -- - - - - - - - - - - - - - - -  \uD83D\uDEEC \n" +
                    "                        /          //\n" +
                    "                              \uD83D\uDD2E \n" +
                    "                      /    /\n" +
                    "\n" +
                    "oh, wie es scheint ist die Glaskugel während des Fluges rausgefallen... \uD83D\uDE31\n" +
                    "\n" +
                    "\n" +
                    "  ... aber wer ist denn da? Pepe, er hat die Kugel gefangen!!\n" +
                    "\n" +
                    "\n" +
                    "\uD83D\uDD2E\n" +
                    hyperemoji + "\n" +
                    "\n" +
                    "___\n" +
                    "\n" +
                    "Oh nein! Sie ist ihm aus der Hand gefallen " + pepesad + "\n" +
                    "Jetzt musst du doch dein Problem senden, damit dir geholfen werden kann" + pepesad).queue();
        } else if (tag.equals("rindfleischetikettierungsueberwachungsgesetz")) {
            event.reply("DU HAST ES WIRKLICH GETAN?! DU HAST DAS LÄNSGTE WORT GESCHRIEBEN GLÜCKWUNSCH!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!").queue();
        } else if (tag.equals("f") || tag.equals("F")) {
            event.reply("F").queue();
        } else if (tag.equals("sozialmedia") || tag.equals("sozial")) {
            EmbedBuilder embedBuilder = new EmbedBuilder()
                    .setTitle("Sozial Media")
                    .addField("twitch", "http://twitch.nexusnight.net/", true)
                    .addField("Instagram", "http://instagram.nexusnight.net/", true)
                    .addField("TikTok", "http://tiktok.nexusnight.net/", true);
            event.replyEmbeds(embedBuilder.build()).queue();
        } else if (tag.equals("ping") || tag.equals("Ping")) {
            event.reply("PONG 🏓" + event.getMember().getAsMention()).queue();
        } else if (tag.equals("botinfo") || tag.equals("botinformation")) {
            EmbedBuilder botEmbed = new EmbedBuilder()
                    .setTitle("Bot")
                    .addField("Entwickler", "Ugur", Boolean.parseBoolean(""))
                    .addField("Commands", "Um meine Commands sehen zu können, nutze /help", Boolean.parseBoolean(""))
                    .addField("Mein Code ist open source :)", "https://github.com/Uguralg3a/NexusnightBot Wer funktionen oder so hinzufügen will kann gerne nen PR erstellen :D", true)
                    .setImage("https://cdn.discordapp.com/icons/970326059780292638/4fc53d6849eb1baefb4e3933e62ab37a.png?size=1024")
                    .setColor(Color.BLACK);

            event.replyEmbeds(botEmbed.build()).queue();
        } else if (tag.equals("dubistdoof")) {
            event.reply("https://tenor.com/bvm50.gif").queue();
        } else if (tag.equals("sorry")) {
            event.reply("https://tenor.com/pK7GobZgz6R.gif").queue();
        } else if (tag.equals("release")) {
            EmbedBuilder embedBuilder = new EmbedBuilder() .setTitle("Release").setDescription("SEH ICH AUS WIE NEN HELlSEhER? JA?!?!?!?!?!? Ne spaß bei seite kp wann der server releast wird.");
            event.replyEmbeds(embedBuilder.build()).queue();
        } else if (tag.equals("ee")) {
            event.reply("HGW DU HAST DAS EASTEREGG GEFUNDEN").queue();
        } else if(tag.equals("girlpower")) {
            event.reply("YES GIRL GOOOOOOOOOOOOOOO").queue();
        } else if(tag.equals("logindata")) {
     EmbedBuilder logindatarr = new EmbedBuilder().setTitle("Anmeldetaten").setDescription("Meine [Anmeldetaten](https://www.youtube.com/watch?v=dQw4w9WgXcQ)");
    event.replyEmbeds(logindatarr.build()).queue();
        }else {
            event.reply("DIESEN TAG GIBTS NICHT!").queue();
        }
    }
}

