package de.ugur.bot.commands.admin;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.DefaultMemberPermissions;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;

import java.awt.*;
import java.time.OffsetDateTime;
import java.util.EnumSet;

public class UserInfoCommand extends ListenerAdapter {

    private final JDA jda;

    public UserInfoCommand(JDA jda, String guildId) {
        this.jda = jda;
        registerCommands(guildId);
        jda.addEventListener(this);
    }

    private void registerCommands(String guildId) {
        Guild guild = jda.getGuildById(guildId); // Discord Server von der übergebenden ID auflösen

        guild.upsertCommand("userinfo", "userinfo").addOption(OptionType.USER, "user", "user", true).setDefaultPermissions(DefaultMemberPermissions.DISABLED).queue();
    }

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        if (event.getName().equals("userinfo")) {
            OptionMapping user = event.getOption("user");
            Guild guild = event.getGuild();
            Member member = event.getOption("user", OptionMapping::getAsMember);
            // = event.getInteraction().getMember();
            TextChannel tc;
            int rollen = member.getRoles().size();
            String name = member.getNickname();
            OffsetDateTime joinDate = member.getTimeJoined();
            OffsetDateTime createdAt = member.getTimeCreated();
            String userId = member.getUser().getId();
            EnumSet<Permission> perms = member.getPermissions();

            EmbedBuilder botEmbed = new EmbedBuilder()
                    .setTitle("User " + name)
                    .addField("UserID ", " " + userId, Boolean.parseBoolean(""))
                    .addField("UserTag ", " " + member.getUser().getAsTag(), Boolean.parseBoolean(""))
                    .addField("Perms", " " + perms, Boolean.parseBoolean(""))
                    .addField("Created At", "Am "  + createdAt, Boolean.parseBoolean(""))
                    .addField("Joined At", "Am " + joinDate, Boolean.parseBoolean(""))
                    .addField("Rollen", rollen + " Rollen", Boolean.parseBoolean(""))
                    .setImage("https://cdn.discordapp.com/icons/970326059780292638/4fc53d6849eb1baefb4e3933e62ab37a.png?size=1024")
                    .setColor(Color.BLACK);

            event.replyEmbeds(botEmbed.build()).queue();
            botEmbed.clear();
        }
    }
}
