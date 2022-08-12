package de.ugur.bot.slashCommands.info;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.SelectMenuInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.selections.SelectMenu;

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
            EmbedBuilder homeEmbed = new EmbedBuilder()
                    .setTitle("Home")
                    .setDescription("Bitte wähle eine Kategorie aus!")
                    .setImage("https://cdn.discordapp.com/icons/970326059780292638/4fc53d6849eb1baefb4e3933e62ab37a.png?size=1024");
            event.replyEmbeds(homeEmbed.build()).setEphemeral(true)
                    //.addActionRows(ActionRow.of(Button.primary("Home", "Home"), Button.primary("Test", "Test"), Button.primary("Info", "Info"), Button.primary("Admin", "Admin")))
                    .addActionRow(
                            SelectMenu.create("help")
                                    .addOption("Home", "Home")
                                    .addOption("Test", "Test")
                                    .addOption("Info", "Info")
                                    .addOption("Admin", "Admin")
                                    .addOption("tag", "tag")
                                    .addOption("Moderation", "Moderation")
                                    .build())
                    .queue();

        }
    }

    @Override
    public void onSelectMenuInteraction(SelectMenuInteractionEvent event) {
        if (event.getSelectMenu().getId().equals("help")) {
            if (event.getValues().contains("Test")) {
                EmbedBuilder testEmbed = new EmbedBuilder()
                        .setTitle("Test Commands")
                        .setDescription("Hier werden alle Test Commands aufgelistet!")
                        .addField("Test", "Ist ein Test Command!", true)
                        .addField("Uptime", "Ich weiß selber nicht wofür der da ist", true)
                        .setImage("https://cdn.discordapp.com/icons/970326059780292638/4fc53d6849eb1baefb4e3933e62ab37a.png?size=1024");

                event.editMessageEmbeds(testEmbed.build()).queue();
            } else if (event.getValues().contains("Home")) {
                EmbedBuilder homeEmbed = new EmbedBuilder()
                        .setTitle("Home")
                        .setDescription("Bitte wähle eine Kategorie aus!")
                        .setImage("https://cdn.discordapp.com/icons/970326059780292638/4fc53d6849eb1baefb4e3933e62ab37a.png?size=1024");
                event.editMessageEmbeds(homeEmbed.build()).queue();
            } else if (event.getValues().contains("Info")) {
                EmbedBuilder infoEmbed = new EmbedBuilder()
                        .setTitle("Info Commands")
                        .setDescription("Hier werden alle Info Commands aufgelistet!")
                        .addField("Help", "Zeigt dir diese Liste an", Boolean.parseBoolean(""))
                        .addField("Info", "Zeigt dir Informationen an", Boolean.parseBoolean(""))
                        .setImage("https://cdn.discordapp.com/icons/970326059780292638/4fc53d6849eb1baefb4e3933e62ab37a.png?size=1024");

                event.editMessageEmbeds(infoEmbed.build()).queue();
            } else if (event.getValues().contains("Admin")) {
                EmbedBuilder adminEmbed = new EmbedBuilder()
                        .setTitle("Admin Commands")
                        .setDescription("Hier werden alle Admin Commands aufgelistet!")
                        .addField("setticket", "Setzt das Ticket", true)
                        .setImage("https://cdn.discordapp.com/icons/970326059780292638/4fc53d6849eb1baefb4e3933e62ab37a.png?size=1024");

                event.editMessageEmbeds(adminEmbed.build()).queue();
            } else if (event.getValues().contains("tag")) {
                EmbedBuilder tagEmbed = new EmbedBuilder()
                        .setTitle("/tag tag");
                event.editMessageEmbeds(tagEmbed.build()).queue();
            } else if (event.getValues().contains("Moderation")) {
                EmbedBuilder mod = new EmbedBuilder()
                        .setTitle("Moderation Commands")
                        .setDescription("Hier werden alle Moderation Commands aufgelistet!")
                        .addField("serverinfo", "Zeigt dir info über dem server an", true)
                        .addField("userinfo", "Zeigt dir info über den user an", true)
                        .addField("clear", "Löscht nachrichten", true)
                        .addField("ban", "Bannt mitglieder", true)
                        .addField("kick", "kickt mitglieder", true);

                event.editMessageEmbeds(mod.build()).queue();
            }
        }
    }
}
