package de.ugur.bot.slashCommands.info;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.DefaultMemberPermissions;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

import static net.dv8tion.jda.api.interactions.commands.OptionType.USER;

public class GetMentionCommand extends ListenerAdapter {

    private final JDA jda;

    public GetMentionCommand(JDA jda, String guildId) {
        this.jda = jda;
        registerCommands(guildId);
        jda.addEventListener(this);
    }

    private void registerCommands(String guildId) {
        Guild guild = jda.getGuildById(guildId);
        Member ugur = guild.getMemberById("654029828193779732");
        guild.upsertCommand("getmention", "bekomme die anzahl der pings von einem mitglied :)")
                .addOptions(new OptionData(USER, "user", "user", true)).setDefaultPermissions(DefaultMemberPermissions.ENABLED)
                .queue();
    }

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        // Only accept commands from guilds
        if (event.getGuild() == null)
            return;
        switch (event.getName()) {
            case "mentionmember":
                Member member = event.getOption("user").getAsMember();
                user(event, event.getOption("user").getAsUser().getAsMention(), member); // content is required so no null-check here
                break;
        }
    }

    private void user(SlashCommandInteractionEvent event, String user, Member member) {
        if (user.contains((CharSequence) event.getGuild().getMember(member).getUser())) {
            event.reply(user).queue();
        }
    }
}
