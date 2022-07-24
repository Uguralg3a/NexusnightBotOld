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

public class UnbanCommand extends ListenerAdapter {

    private final JDA jda;

    public UnbanCommand(JDA jda, String guildId) {
        this.jda = jda;
        jda.addEventListener(this);
        registerCommands(guildId);
    }

    private void registerCommands(String guildId) {
        Guild guild = jda.getGuildById(guildId);

        guild.upsertCommand("unban", "Um ein Mitglied zu entbannen, benötigst du die Rechte zum entbannen!").addOptions(new OptionData(USER, "user", "das mitglied welches du entbannen willst (id)").setRequired(true)).setDefaultPermissions(DefaultMemberPermissions.enabledFor(Permission.BAN_MEMBERS)).queue();

    }

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        // Only accept commands from guilds
        if (event.getGuild() == null)
            return;
        switch (event.getName()) {
            case "unban":
                Member member = event.getOption("user").getAsMember(); // the "user" option is required, so it doesn't need a null-check here
                User user = event.getOption("user").getAsUser();
                unban(event, user, member);
                break;
        }
    }

    public void unban(SlashCommandInteractionEvent event, User user, Member member) {
        event.deferReply(false).queue(); // Let the user know we received the command before doing anything else
        InteractionHook hook = event.getHook(); // This is a special webhook that allows you to send messages without having permissions in the channel and also allows ephemeral messages
        hook.setEphemeral(false); // All messages here will now be ephemeral implicitly
        if (!event.getMember().hasPermission(Permission.BAN_MEMBERS)) {
            hook.sendMessage("Du hast nicht die Rechte, um dieses Mitglied zu entbannen!").queue();
            return;
        }

        Member selfMember = event.getGuild().getSelfMember();
        if (!selfMember.hasPermission(Permission.BAN_MEMBERS)) {
            hook.sendMessage("Ich kann keine Mitglieder von diesem Server entbannen!").queue();
            return;
        }

        //if (member != null && !selfMember.canInteract(member) || member.getRoles().equals("970752386681417759") ||member.hasPermission(Permission.ADMINISTRATOR, Permission.BAN_MEMBERS)) {
          //  hook.sendMessage("Ich kann dieses Mitglied nicht entbannen, weil er zu mächtig ist!").queue();
            //return;
        //}

        // Ban the user and send a success response
        event.getGuild().unban(user) // audit-log reason
                .flatMap(v -> hook.sendMessage(user.getAsTag() + " wurde entbannt!"))
                .queue();
    }
}