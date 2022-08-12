package de.ugur.bot.slashCommands.moderation;

import de.ugur.bot.String.Embeds;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.DefaultMemberPermissions;

import java.awt.*;
import java.time.OffsetDateTime;

public class ServerInfoCommand extends ListenerAdapter {

    private final JDA jda;

    public ServerInfoCommand(JDA jda, String guildId) {
        this.jda = jda;
        registerCommands(guildId);
        jda.addEventListener(this);
    }

    private void registerCommands(String guildId) {
        Guild guild = jda.getGuildById(guildId); // Discord Server von der übergebenden ID auflösen

        guild.upsertCommand("serverinfo", "serverinfo").setDefaultPermissions(DefaultMemberPermissions.ENABLED).queue();
    }

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        if (event.getName().equals("serverinfo")) {
            Guild guild = event.getGuild();
            TextChannel tc;
            int textChannel = guild.getTextChannels().size();
            int sprachKanäle = guild.getVoiceChannels().size();
            int rollen = guild.getRoles().size();
            OffsetDateTime createdAt = guild.getTimeCreated();
            String owner = String.valueOf(guild.getOwner())     ;
            String ownerid = guild.getOwnerId();
            String serverName = guild.getName();
            int memberCount = guild.getMemberCount();
            int boosterLevel = guild.getBoostCount();

            EmbedBuilder botEmbed = new EmbedBuilder()
                    .setTitle("Serverinfo für " + serverName)
                   /* .addField("Member",  serverMitglieder + " Mitglieder", Boolean.parseBoolean(""))
                    .addField("Channels", textChannel + " Text Kanäle "  + "und " + sprachKanäle + " Sprachkanäle", Boolean.parseBoolean(""))
                    .addField("Rollen", rollen + " Rollen", Boolean.parseBoolean(""))
                    .setImage("https://cdn.discordapp.com/icons/970326059780292638/4fc53d6849eb1baefb4e3933e62ab37a.png?size=1024")
                    .setColor(Color.BLACK);*/
                    .addField("Owner", "```" + owner + "```", true)
                    .addField("OwnerID", "```" + ownerid + "```", true)
                    .addField("Erstellt am", "```" + createdAt + "```", true)
                    .addField("Mitglieder", "```" + memberCount + "```", true)
                    .addField("Rollen", "```" + rollen + "```", true)
                    .addField("Booster Level", "```" + boosterLevel + "```", true)
                    .addField("Text Kanäle", "```" + textChannel + "```", true)
                    .addField("Sprach Kanäle", "```" + sprachKanäle + "```", true)
                            .setColor(Color.GREEN);
            if (event.getMember().hasPermission(Permission.MANAGE_PERMISSIONS)) {
                event.replyEmbeds(botEmbed.build()).queue();
                botEmbed.clear();
            } else {
                event.replyEmbeds(Embeds.noPerm.build()).queue();
            }
        }
    }
}
