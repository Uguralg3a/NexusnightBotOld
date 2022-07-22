package de.ugur.bot;

import me.duncte123.botcommons.BotCommons;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;

public class Listeners extends ListenerAdapter {

    private static final Logger LOGGER = LoggerFactory.getLogger(Listeners.class);

    @Override
    public void onReady(@Nonnull ReadyEvent event) {
        System.out.printf("{} is ready", event.getJDA().getSelfUser().getAsTag());
    }

}