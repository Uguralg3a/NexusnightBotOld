package de.ugur.bot.commands.info;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.SelectMenuInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.selections.SelectMenu;
import net.dv8tion.jda.api.interactions.components.selections.SelectOption;

public class HelpCommand extends ListenerAdapter {

    private final JDA jda;

    public HelpCommand(JDA jda, String guildId) {
        this.jda = jda;
        registerCommands(guildId);
        jda.addEventListener(this);
    }

    private void registerCommands(String guildId) {
        Guild guild = jda.getGuildById(guildId); // Discord Server von der übergebenden ID auflösen

        guild.upsertCommand("help", "Hilfe").queue();
    }

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        if (event.getName().equals("help")) {
            event.reply("Wähle deine Hilfe Kategory aus!")
                    .addActionRow(
                            SelectMenu.create("Wähle die Hilfe Kategory aus!")
                                    .addOption("Ad,om", "Admin", "Classic") // SelectOption with only the label, value, and description
                                    .addOptions(SelectOption.of("Info", "Info") // another way to create a SelectOption
                                            .withDescription("Admin haben die volle Macht :D")
                                            .withDefault(true)) // while also being the default option
                                            .build())
                    .queue();
        }
    }

    @Override
    public void onSelectMenuInteraction(SelectMenuInteractionEvent event) {
        if (event.getComponentId().equals("help")) {
            event.reply("Du wählst " + event.getValues().get(0)).queue();
        }
    }

}
