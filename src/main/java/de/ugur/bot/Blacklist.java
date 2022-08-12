package de.ugur.bot;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.DefaultMemberPermissions;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

public class Blacklist extends ListenerAdapter {

    private final JDA jda;

    public Blacklist(JDA jda, String guildId) {
        this.jda = jda;
        registerCommands(guildId);
        jda.addEventListener(this);
    }

    private void registerCommands(String guildId) {
        Guild guild = jda.getGuildById(guildId); // Discord Server von der übergebenden ID auflösen

        guild.upsertCommand("blacklist", "blacklist").setDefaultPermissions(DefaultMemberPermissions.ENABLED).queue();
    }

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        if (event.getName().equals("blacklist")) {
            EmbedBuilder noBlacklist = new EmbedBuilder()
                    .setColor(Color.RED)
                    .setTitle("Blacklist")
                    //.setDescription("Niemand ist auf der Blacklist!");
                    .addField(" ", "King_Gamer_M#0505 ist auf der Blacklist.", true);
            event.replyEmbeds(noBlacklist.build()).queue();
        }
    }
}
