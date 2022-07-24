package de.ugur.bot.commands.admin;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.DefaultMemberPermissions;

import java.awt.*;

public class ServerInfoCommand extends ListenerAdapter {

    private final JDA jda;

    public ServerInfoCommand(JDA jda, String guildId) {
        this.jda = jda;
        registerCommands(guildId);
        jda.addEventListener(this);
    }

    private void registerCommands(String guildId) {
        Guild guild = jda.getGuildById(guildId); // Discord Server von der übergebenden ID auflösen

        guild.upsertCommand("serverinfo", "serverinfo").setDefaultPermissions(DefaultMemberPermissions.DISABLED).queue();
    }

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        if (event.getName().equals("serverinfo")) {
            Guild guild = event.getGuild();
            TextChannel tc;
            int serverMitglieder = guild.getMemberCount();
            int textChannel = guild.getTextChannels().size();
            int sprachKanäle = guild.getVoiceChannels().size();
            int rollen = guild.getRoles().size();

            EmbedBuilder botEmbed = new EmbedBuilder()
                    .setTitle("Server")
                    .addField("Member",  serverMitglieder + " Mitglieder", Boolean.parseBoolean(""))
                    .addField("Channels", textChannel + " Text Kanäle "  + "und " + sprachKanäle + " Sprachkanäle", Boolean.parseBoolean(""))
                    .addField("Rollen", rollen + " Rollen", Boolean.parseBoolean(""))
                    .setImage("https://cdn.discordapp.com/icons/970326059780292638/4fc53d6849eb1baefb4e3933e62ab37a.png?size=1024")
                    .setColor(Color.BLACK);

            event.replyEmbeds(botEmbed.build()).queue();
            botEmbed.clear();
        }
    }
}
