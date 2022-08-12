package de.ugur.bot.slashCommands.devonly;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.emoji.Emoji;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

public class ReactionRoles extends ListenerAdapter {

    String one = "\u20E3\u0031";
    Emoji two = Emoji.fromUnicode("\u20E3\u0032");
    Emoji three = Emoji.fromUnicode("\u20E3\u0033");
    Emoji four = Emoji.fromUnicode("\u20E3\u0034");
    Emoji five = Emoji.fromUnicode("\u20E3\u0035");

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        if (event.getMessage().getContentStripped().equals("duwirstdasniekönnen!rr")) {
            EmbedBuilder embedBuilder = new EmbedBuilder()
                    .setTitle("**Sozial Media**")
                    .setDescription("Wähle deine Ping Rolle aus!")
                    .addField("Sozial Media", "  :one:\n", true)
                    .addField("News", " :two:\n", true)
                    .addField("Spoiler", " :three:\n", true)
                    .addField("Gewinnspiele", " :four:\n", true)
                    .addField("Abstimmungen", " :five:\n", true)
                    .setColor(Color.BLACK);
            event.getChannel().sendMessageEmbeds(embedBuilder.build()).queue();
        }
    }
}
