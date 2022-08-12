package de.ugur.bot.bewerben;

import de.ugur.bot.slashCommands.test.TestCommand;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.DefaultMemberPermissions;

import java.awt.*;

public class SetBewerbung extends ListenerAdapter {



    private final JDA jda;

    public SetBewerbung(JDA jda, String guildId) {
        this.jda = jda;
        registerCommands(guildId);
        jda.addEventListener(this);
    }

    private void registerCommands(String guildId) {
        Guild guild = jda.getGuildById(guildId); // Discord Server von der übergebenden ID auflöse

        guild.upsertCommand("setbewerben", "setBewerbung").setDefaultPermissions(DefaultMemberPermissions.enabledFor(Permission.ADMINISTRATOR)).queue();
    }
    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        if (event.getName().equals("setbewerben")) {
            String roles = String.valueOf(event.getMember().getRoles());
            if (roles.contains("™️ × TEAM")) {
                Role support = event.getGuild().getRoleById("1001577936392032317");
                Role builder = event.getGuild().getRoleById("1001577933963546735");
                Role developer = event.getGuild().getRoleById("1001577926791274536");

                EmbedBuilder embed = new EmbedBuilder();
                embed.setColor(Color.GREEN);
                embed.setTitle("Team Bewerbung");
                embed.setDescription("\nFolgende Stellen suchen wir:\n- " + support + "\n- " + builder + "\n- " + developer + "\nFormular \n" + "https://forms.gle/zeip2ztfkaEQ7Yd57");
                //event.getChannel().sendMessageEmbeds(embed.build()).setActionRow(openTicket(), openBewerbung()).queue();
                event.getChannel().sendMessageEmbeds(embed.build()).queue();

            }
        }
    }


}
