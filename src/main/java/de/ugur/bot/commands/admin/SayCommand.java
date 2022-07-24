package de.ugur.bot.commands.admin;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

import static net.dv8tion.jda.api.interactions.commands.OptionType.STRING;

public class SayCommand extends ListenerAdapter {

    private final JDA jda;

    public SayCommand(JDA jda, String guildId) {
        this.jda = jda;
        registerCommands(guildId);
        jda.addEventListener(this);
    }

    private void registerCommands(String guildId) {
        Guild guild = jda.getGuildById(guildId); // Discord Server von der übergebenden ID auflösen

        guild.upsertCommand("say", "say").addOptions(new OptionData(STRING, "content", "Was soll der Bot sagen", true)).queue();
    }

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        // Only accept commands from guilds
        if (event.getGuild() == null)
            return;
        switch (event.getName()) {
            case "say":
                say(event, event.getOption("content").getAsString()); // content is required so no null-check here
                break;
        }
    }

    private void say(SlashCommandInteractionEvent event, String content) {
        if (event.getMember().getId().equals("654029828193779732") || event.getMember().getId().equals("743100698127761481")) {
            event.reply(content).queue(); // This requires no permissions!
        } else {

            MessageEmbed notUgur = new EmbedBuilder().setTitle("Du bist nicht Ugur?").setDescription("**SELBSTZERSTÖRUNG WIRD EINGELEITET!** 3... 2... 1...").setImage("https://media.giphy.com/media/HhTXt43pk1I1W/giphy.gif").build();

            event.replyEmbeds(notUgur).setEphemeral(true).queue();
        }
    }
}
