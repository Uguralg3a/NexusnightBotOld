package de.ugur.bot.ticketsystem;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.component.SelectMenuInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.ActionRow;
import net.dv8tion.jda.api.interactions.components.buttons.Button;

import java.awt.*;

public class SelectMenu extends ListenerAdapter {

    @Override
    public void onSelectMenuInteraction(SelectMenuInteractionEvent event) {
        if (event.getSelectMenu().getId().equals("ticket")) {
            if (event.getValues().contains("support")) {
                EmbedBuilder embed = new EmbedBuilder();
                embed.setColor(Color.GREEN);
                embed.setTitle("Bestätigen");
                embed.setDescription("Bitte drücke auf 'Ticket Erstellen', um deine Auswahl zu bestätigen!");

                event.replyEmbeds(embed.build()).addActionRows(ActionRow.of(net.dv8tion.jda.api.interactions.components.buttons.Button.success("openTicket", "Ticket Erstellen"))).setEphemeral(true).queue();
            } else if (event.getValues().contains("bugreport")) {
                EmbedBuilder embed = new EmbedBuilder();
                embed.setColor(Color.GREEN);
                embed.setTitle("Bestätigen");
                embed.setDescription("Bitte drücke auf 'Ticket Erstellen', um deine Auswahl zu bestätigen!");

                event.replyEmbeds(embed.build()).addActionRows(ActionRow.of(net.dv8tion.jda.api.interactions.components.buttons.Button.success("bugreport", "Ticket Erstellen"))).setEphemeral(true).queue();
            } else if (event.getValues().contains("playerreport")) {
                EmbedBuilder embed = new EmbedBuilder();
                embed.setColor(Color.GREEN);
                embed.setTitle("Bestätigen");
                embed.setDescription("Bitte drücke auf 'Ticket Erstellen', um deine Auswahl zu bestätigen!");

                event.replyEmbeds(embed.build()).addActionRows(ActionRow.of(net.dv8tion.jda.api.interactions.components.buttons.Button.success("playerreport", "Ticket Erstellen"))).setEphemeral(true).queue();
            } else if (event.getValues().contains("bewerbung")) {
                EmbedBuilder embed = new EmbedBuilder();
                embed.setColor(Color.GREEN);
                embed.setTitle("Bestätigen");
                embed.setDescription("Bitte drücke auf 'Ticket Erstellen', um deine Auswahl zu bestätigen!");

                event.replyEmbeds(embed.build()).addActionRows(ActionRow.of(Button.success("bewerbung", "Ticket Erstellen"))).setEphemeral(true).queue();
            }else if (event.getValues().contains("teamlerreport")) {
                EmbedBuilder embed = new EmbedBuilder();
                embed.setColor(Color.GREEN);
                embed.setTitle("Bestätigen");
                embed.setDescription("Bitte drücke auf 'Ticket Erstellen', um deine Auswahl zu bestätigen!");

                event.replyEmbeds(embed.build()).addActionRows(ActionRow.of(Button.success("teamlerreport", "Ticket Erstellen").asDisabled())).setEphemeral(true).queue();
            }
        }
    }

}
