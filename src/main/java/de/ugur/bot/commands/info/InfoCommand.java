package de.ugur.bot.commands.info;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.*;
import net.dv8tion.jda.api.interactions.commands.build.SubcommandData;
import net.dv8tion.jda.api.utils.cache.SortedSnowflakeCacheView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.util.Optional;

public class InfoCommand extends ListenerAdapter {

    private final JDA jda;
    private final Logger logger = LoggerFactory.getLogger("dclink-discord");

    public InfoCommand(JDA jda, String guildId) {
        this.jda = jda;
        registerCommands(guildId);
        jda.addEventListener(this);
    }

    private void registerCommands(String guildId) {
        Guild guild = jda.getGuildById(guildId);
        if (guild == null) {
            logger.error("Could not find guild with id {}", guildId);
            return;
        }


        Optional<Command> infoCommand = guild.retrieveCommands().complete().stream().filter(command -> command.getName().equals("info")).findAny();

        if (infoCommand.isEmpty()) {
            guild.upsertCommand("info", "Get information").addSubcommands(
                            new SubcommandData("bot", "Get Information about the bot"))
                    .setDefaultPermissions(DefaultMemberPermissions.ENABLED)
                    .queue();
        }
    }


    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event){
        if (event.getName().equals("info")){
            infoCommand(event);
        } else {
            String id = "rmCmd" + event.getCommandId();
        }
    }

    private void infoCommand(SlashCommandInteractionEvent event) {
        String subcommandName = event.getSubcommandName();
        if (subcommandName == null) {
            return;
        }

        if (subcommandName.equals("bot")) {

            EmbedBuilder botEmbed = new EmbedBuilder()
                    .setTitle("Bot")
                    .setDescription("Coming soon...")
                    .addField("Entwickler", "Ugur", Boolean.parseBoolean(""))
                    .addField("Commands", "Um meine Commands sehen zu können, nutze /help", Boolean.parseBoolean(""))
                    .setImage("https://cdn.discordapp.com/icons/970326059780292638/4fc53d6849eb1baefb4e3933e62ab37a.png?size=1024")
                    .setColor(Color.BLACK);

            event.replyEmbeds(botEmbed.build()).queue();
            botEmbed.clear();
        }
    }
}

