package de.ugur.bot.slashCommands.moderation;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.InteractionHook;
import net.dv8tion.jda.api.interactions.commands.DefaultMemberPermissions;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

import static net.dv8tion.jda.api.interactions.commands.OptionType.*;

public class KickCommand extends ListenerAdapter {

    private final JDA jda;

    public KickCommand(JDA jda, String guildId) {
        this.jda = jda;
        jda.addEventListener(this);
        registerCommands(guildId);
    }

    private void registerCommands(String guildId) {
        Guild guild = jda.getGuildById(guildId);

        guild.upsertCommand("kick", "Kicks a user from this server. Requires permission to kick users.").addOptions(new OptionData(USER, "user", "The user to ban").setRequired(true)).addOptions(new OptionData(STRING, "reason", "The ban reason to use (default: Banned by <user>)")).setGuildOnly(true).setDefaultPermissions(DefaultMemberPermissions.enabledFor(Permission.BAN_MEMBERS)).queue();

    }

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        // Only accept commands from guilds
        if (event.getGuild() == null)
            return;
        switch (event.getName()) {
            case "kick":
                Member member = event.getOption("user").getAsMember(); // the "user" option is required, so it doesn't need a null-check here
                User user = event.getOption("user").getAsUser();
                kick(event, user, member);
                break;
        }
    }

    public void kick(SlashCommandInteractionEvent event, User user, Member member) {
        event.deferReply(false).queue(); // Let the user know we received the command before doing anything else
        InteractionHook hook = event.getHook(); // This is a special webhook that allows you to send messages without having permissions in the channel and also allows ephemeral messages
        hook.setEphemeral(false); // All messages here will now be ephemeral implicitly
        if (!event.getMember().hasPermission(Permission.KICK_MEMBERS)) {
            hook.sendMessage("Du hast nicht die Rechte, um dieses Mitglied zu kicken!").queue();
            return;
        }

        Member selfMember = event.getGuild().getSelfMember();
        if (!selfMember.hasPermission(Permission.KICK_MEMBERS)) {
            hook.sendMessage("Ich kann keine Mitglieder von diesem Server kicken!").queue();
            return;
        }

        if (member != null && !selfMember.canInteract(member) || member.getRoles().equals("1001577950682042448") ||member.hasPermission(Permission.ADMINISTRATOR, Permission.BAN_MEMBERS)) {
            hook.sendMessage("Ich kann dieses Mitglied nicht kicken, weil er zu mächtig ist!").queue();
            return;
        }

        // optional ban reason with a lazy evaluated fallback (supplier)
        String reason = event.getOption("reason",
                () -> "Gekickt von " + event.getUser().getAsTag(), // used if getOption("reason") is null (not provided)
                OptionMapping::getAsString); // used if getOption("reason") is not null (provided)

        // Ban the user and send a success response
        event.getGuild().kick(user, reason)
                .reason(reason) // audit-log reason
                .flatMap(v -> hook.sendMessage(user.getAsTag() + " wurde gekickt!"))
                .queue();

    }
}
