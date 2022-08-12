package de.ugur.bot.ticketsystem;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.EnumSet;
import java.util.TimeZone;

import static de.ugur.bot.ticketsystem.TicketButtonSystem.claimButton;
import static de.ugur.bot.ticketsystem.TicketButtonSystem.closedButton;

public class PlayerReport extends ListenerAdapter {

    @Override
    public void onButtonInteraction(ButtonInteractionEvent event) {
        event.deferEdit().queue();
        if (event.getButton().getId().equals("playerreport")) {
            String roles = String.valueOf(event.getMember().getRoles());
            int min = 1000;
            int max = 1000;
            int random_int = (int) Math.floor(Math.random()) * (max - min + 1) + min;
            Guild guild = event.getGuild();
            SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
            Date date = new Date();
            formatter.setTimeZone(TimeZone.getTimeZone("Europe/Germany"));
            EmbedBuilder embed = new EmbedBuilder();
            embed.setColor(Color.GREEN);
            embed.setTitle(event.getUser().getName() + "'s Player Report");
            embed.setDescription("Willkommen im Ticket Support. Bitte schreibe uns schonmal deinen Player Report, und ein Teammitglied wird sich gleich um dich kümmern!");
            guild.createTextChannel("player-report-von-" + event.getUser().getName(), guild.getCategoryById("1001577966435827862"))
                    .addPermissionOverride(event.getMember(), EnumSet.of(Permission.VIEW_CHANNEL), null)
                    .addPermissionOverride(guild.getPublicRole(), null, EnumSet.of(Permission.VIEW_CHANNEL))
                    .addPermissionOverride(guild.getRoleById("1001577950682042448"), EnumSet.of(Permission.VIEW_CHANNEL), null)
                    .complete().sendMessageEmbeds(embed.build()).setActionRow(closedButton(), claimButton()).queue();
            EmbedBuilder teamEmbed = new EmbedBuilder();
            teamEmbed.setColor(Color.GREEN);
            teamEmbed.setTitle("Ticket System");
            teamEmbed.setDescription("Typ: Player Report");
            teamEmbed.addField("Person", event.getMember().getAsMention(), true);
            teamEmbed.addField("Datum", formatter.format(date), true);
            guild.getTextChannelById("1001578018772353114").sendMessageEmbeds(teamEmbed.build()).queue();
        }
    }
}
