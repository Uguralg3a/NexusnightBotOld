package de.ugur.bot.commands.admin;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageDeleteEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.Command;
import net.dv8tion.jda.api.interactions.commands.DefaultMemberPermissions;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.SubcommandData;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ClearCommand extends ListenerAdapter {

    private final JDA jda;

    public ClearCommand(JDA jda, String guildId) {
        this.jda = jda;
        registerCommands(guildId);
        jda.addEventListener(this);
    }

    private void registerCommands(String guildId) {
        Guild guild = jda.getGuildById(guildId); // Discord Server von der übergebenden ID auflösen// Discord Server von der übergebenden ID auflösen

        guild.upsertCommand("clear", "Löscht die Anzahl der Nachrichten die du angegeben hast") // Commandname und Beschreibung
                        .addOption(OptionType.INTEGER, "anzahl", "Die Anzahl der Nachrichten welche du löschen willst", true)  // Argumente welche der Befehl hat
                        .setDefaultPermissions(DefaultMemberPermissions.DISABLED) // Standartmäßig das niemand den Befehl ausführen. Lässt sich dann bei Discord unter den Integrations Einstellungen ändern.
                    .queue();
            }

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        if (event.getName().equals("clear")) { // Falls es der /ping Befehl ist
            OptionMapping anzahl = event.getOption("anzahl");
            anzahl.getAsInt();
            event.reply("Coming Soon!");


        }
    }

}
