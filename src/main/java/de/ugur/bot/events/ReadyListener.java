package de.ugur.bot.events;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.events.ShutdownEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class ReadyListener extends ListenerAdapter {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReadyListener.class);

    @Override
    public void onReady(@Nonnull ReadyEvent event) {
        System.out.printf("{} is ready", event.getJDA().getSelfUser().getAsTag());
        EmbedBuilder readyEmbed = new EmbedBuilder()
                .setTitle("Bereit")
                .setDescription("Ich wurde gestartet *leider*");
    }
}