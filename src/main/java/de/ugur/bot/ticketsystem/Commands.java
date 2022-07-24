package de.ugur.bot.ticketsystem;

import de.ugur.bot.Config;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.emoji.Emoji;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.SelectMenuInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.ActionRow;
import net.dv8tion.jda.api.interactions.components.ItemComponent;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import net.dv8tion.jda.api.interactions.components.selections.SelectMenu;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

public class Commands extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (event.getMessage().getContentStripped().equalsIgnoreCase(Config.get("prefix") + "setticket")) {
            String roles = String.valueOf(event.getMember().getRoles());
            if (roles.contains("™️ × TEAM")) {
                EmbedBuilder embed = new EmbedBuilder();
                embed.setColor(Color.GREEN);
                embed.setTitle("Ticket System");
                embed.setDescription("Klicke auf den Knopf, um ein Ticket zu öffnen!");
                //event.getChannel().sendMessageEmbeds(embed.build()).setActionRow(openTicket(), openBewerbung()).queue();
                event.getChannel().sendMessageEmbeds(embed.build())
                .setActionRow(
                        SelectMenu.create("ticket")
                                .addOption("Support", "support")
                                .addOption("Bug Report", "bugreport")
                                .addOption("Player Report", "playerreport")
                                .addOption("Teamlerreport", "teamlerreport")
                                .addOption("Bewerbung", "bewerbung")
                                .build())
                        .queue();

            }
        }
    }

    //private Button openBewerbung() {
      //  return Button.secondary("openBewerbung", "Bewerbung erstellen");
    //}

    //private Button openTicket() {
      //  return Button.success("openTicket", "Support");
    //}


}
