package de.ugur.bot.commands.admin;

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

public class BanCommand extends ListenerAdapter {

    private final JDA jda;

    public BanCommand(JDA jda, String guildId) {
        this.jda = jda;
        jda.addEventListener(this);
        registerCommands(guildId);
    }

    private void registerCommands(String guildId) {
        Guild guild = jda.getGuildById(guildId);

        guild.upsertCommand("ban", "Um ein Mitglied zu bannen, benötigst du die Rechte zum Bannen!").addOptions(new OptionData(USER, "user", "The user to ban").setRequired(true)).addOptions(new OptionData(INTEGER, "del_days", "Delete messages from the past days.").setRequiredRange(0, 7)).addOptions(new OptionData(STRING, "reason", "The ban reason to use (default: Banned by <user>)")).setGuildOnly(true).setDefaultPermissions(DefaultMemberPermissions.enabledFor(Permission.BAN_MEMBERS)).queue();

    }

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        // Only accept commands from guilds
        if (event.getGuild() == null)
            return;
        switch (event.getName()) {
            case "ban":
                Member member = event.getOption("user").getAsMember(); // the "user" option is required, so it doesn't need a null-check here
                User user = event.getOption("user").getAsUser();
                ban(event, user, member);
                break;
        }
    }

    public void ban(SlashCommandInteractionEvent event, User user, Member member) {
        event.deferReply(false).queue(); // Let the user know we received the command before doing anything else
        InteractionHook hook = event.getHook(); // This is a special webhook that allows you to send messages without having permissions in the channel and also allows ephemeral messages
        hook.setEphemeral(false); // All messages here will now be ephemeral implicitly
        if (!event.getMember().hasPermission(Permission.BAN_MEMBERS)) {
            hook.sendMessage("Du hast nicht die Rechte, um dieses Mitglied zu bannen!").queue();
            return;
        }

        Member selfMember = event.getGuild().getSelfMember();
        if (!selfMember.hasPermission(Permission.BAN_MEMBERS)) {
            hook.sendMessage("Ich kann keine Mitglieder von diesem Server bannen!").queue();
            return;
        }

        if (member != null && !selfMember.canInteract(member) || member.getRoles().equals("970752386681417759") ||member.hasPermission(Permission.ADMINISTRATOR, Permission.BAN_MEMBERS)) {
            hook.sendMessage("Ich kann dieses Mitglied nicht bannen, weil er zu mächtig ist!").queue();
            return;
        }

        // optional command argument, fall back to 0 if not provided
        int delDays = event.getOption("del_days", 0, OptionMapping::getAsInt); // this last part is a method reference used to "resolve" the option value

        // optional ban reason with a lazy evaluated fallback (supplier)
        String reason = event.getOption("reason",
                () -> "Gebannt von " + event.getUser().getAsTag(), // used if getOption("reason") is null (not provided)
                OptionMapping::getAsString); // used if getOption("reason") is not null (provided)

        // Ban the user and send a success response
        event.getGuild().ban(user, delDays, reason)
                .reason(reason) // audit-log reason
                .flatMap(v -> hook.sendMessage(user.getAsTag() + " wurde gebannt!"))
                .queue();
    }
}