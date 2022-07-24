package de.ugur.bot.commands.test;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.util.List;

public class UptimeCommand extends ListenerAdapter {

    private final JDA jda;

    public UptimeCommand(JDA jda, String guildId) {
        this.jda = jda;
        registerCommands(guildId);
        jda.addEventListener(this);
    }

    private void registerCommands(String guildId) {
        Guild guild = jda.getGuildById(guildId); // Discord Server von der übergebenden ID auflösen

        guild.upsertCommand("uptime", "uptime").queue();
    }


    public void handle(@Nonnull List<String> args, @Nonnull SlashCommandInteractionEvent event) {
        RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();
        long uptime = runtimeMXBean.getUptime();
        long uptimeInSeonds = uptime / 1000;
        long numberOfHours = uptimeInSeonds / (60 * 60);
        long numberOfMinutes = (uptimeInSeonds / 60) - (numberOfHours * 60);
        long numberOfSeconds = uptimeInSeonds % 60;

        if (event.getName().equals("uptime")) {
            event.replyFormat("Ich bin seit `%s Stunden, %s Minuten und %s Sekunden`", numberOfHours, numberOfMinutes, numberOfSeconds).queue();
        }
    }
}
