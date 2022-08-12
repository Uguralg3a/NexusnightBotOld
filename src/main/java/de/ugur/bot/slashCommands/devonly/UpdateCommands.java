package de.ugur.bot.slashCommands.devonly;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import org.jetbrains.annotations.NotNull;

import static net.dv8tion.jda.api.interactions.commands.OptionType.STRING;

public class UpdateCommands extends ListenerAdapter {
    private final JDA jda;

    public UpdateCommands(JDA jda, String guildId) {
        this.jda = jda;
        registerCommands(guildId);
        jda.addEventListener(this);
    }

    private void registerCommands(String guildId) {
        Guild guild = jda.getGuildById(guildId); // Discord Server von der übergebenden ID auflösen

        guild.upsertCommand("cmdupdate", "Updated die Commands").queue();
    }

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        if (event.getName().equals("cmdupdate")) {
            Guild guild = event.getGuild();
            guild.getJDA().updateCommands().queue();
            event.reply("Die Commands wurden geupdated!").queue();
        }
    }
}
