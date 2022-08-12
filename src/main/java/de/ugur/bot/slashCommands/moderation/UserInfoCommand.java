package de.ugur.bot.slashCommands.moderation;

import de.ugur.bot.String.Embeds;
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

        guild.upsertCommand("userinfo", "userinfo").addOption(OptionType.USER, "user", "user", true).setDefaultPermissions(DefaultMemberPermissions.ENABLED).queue();
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
            String username = member.getUser().getAsTag();
            String isBot = String.valueOf(member.getUser().isBot());
            String highestRole = member.getRoles().toString();
            String color = String.valueOf(member.getColor());
            String isBooster = String.valueOf(member.isBoosting());


            EmbedBuilder botEmbed = new EmbedBuilder()
                    .setTitle(">>> Userinfo für " + name)
                    .addField("Name", "```" + username + "```", true)
                    .addField("Bot", "```" + isBot + "```", true)
                    .addField("Nickname", "```" + member.getNickname() + "```", true)
                    .addField("UserID ", "```" + userId + "```", true)
                    //.addField("UserID ", " " + userId, Boolean.parseBoolean(""))
                    //.addField("Perms", " " + perms, Boolean.parseBoolean(""))
                    .addField("Server beigetreten", "```" + joinDate + "```", true)
                    .addField("Discord beigetreten", "```"  + createdAt + "```", true)
                    //.addField("Joined At", "Am " + joinDate, Boolean.parseBoolean(""))
                    .addField("Rollen", "```" + rollen + "```", true)
                    // .addField("Rechte", "```" + perms + "```", true)
                    .addField("Farbe", "```" + color + "```", true)
                    .addField("Booster", "```" + isBooster + "```", true)
                    //.setImage("https://cdn.discordapp.com/icons/970326059780292638/4fc53d6849eb1baefb4e3933e62ab37a.png?size=1024")
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
