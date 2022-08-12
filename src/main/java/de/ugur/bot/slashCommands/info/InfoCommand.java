package de.ugur.bot.slashCommands.info;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;

public class InfoCommand extends ListenerAdapter {

    private final JDA jda;
    private final Logger logger = LoggerFactory.getLogger("dclink-discord");

    public InfoCommand(JDA jda, String guildId) {
        this.jda = jda;
        registerCommands(guildId);
        jda.addEventListener(this);
    }

    private void registerCommands(String guildId) {
        Guild guild = jda.getGuildById(guildId);
        guild.upsertCommand("botinfo", "Get information")
                .setDefaultPermissions(DefaultMemberPermissions.ENABLED)
                .queue();
    }


    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event){
        if (event.getName().equals("botinfo")){
            infoCommand(event);
        }
    }

    private void infoCommand(SlashCommandInteractionEvent event) {

            EmbedBuilder botEmbed = new EmbedBuilder()
                    .setTitle("Bot")
                    .addField("Entwickler", "Ugur", Boolean.parseBoolean(""))
                    .addField("Commands", "Um meine Commands sehen zu können, nutze /help", Boolean.parseBoolean(""))
                    .addField("Mein Code ist open source :)", "https://github.com/Uguralg3a/NexusnightBot Wer funktionen oder so hinzufügen will kann gerne nen PR erstellen :D", true)
                    .setImage("https://cdn.discordapp.com/icons/970326059780292638/4fc53d6849eb1baefb4e3933e62ab37a.png?size=1024")
                    .setColor(Color.BLACK);

            event.replyEmbeds(botEmbed.build()).queue();
            botEmbed.clear();
        }
}

