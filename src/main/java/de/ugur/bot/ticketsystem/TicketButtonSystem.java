package de.ugur.bot.ticketsystem;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.IPermissionHolder;
import net.dv8tion.jda.api.entities.PermissionOverride;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.buttons.Button;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.EnumSet;
import java.util.TimeZone;

public class TicketButtonSystem extends ListenerAdapter {

    @Override
    public void onButtonInteraction(ButtonInteractionEvent event) {
        event.deferEdit().queue();
        if (event.getButton().getId().equals("openTicket")) {
            String roles = String.valueOf(event.getMember().getRoles());
            if (!roles.contains("❌ × TicketBan")) {
                int min = 1000;
                int max = 1000;
                int random_int = (int) Math.floor(Math.random()) * (max - min + 1) + min;
                Guild guild = event.getGuild();
                SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
                Date date = new Date();
                formatter.setTimeZone(TimeZone.getTimeZone("Europe/Germany"));
                EmbedBuilder embed = new EmbedBuilder();
                embed.setColor(Color.GREEN);
                embed.setTitle(event.getUser().getName() + "'s Ticket");
                embed.setDescription("Willkommen im Ticket Support. Bitte schildere uns schonmal dein Anliegen, und ein Teammitglied wird sich gleich um dich kümmern!");
                guild.createTextChannel("ticket-von-" + event.getUser().getName(), guild.getCategoryById("1001577966435827862"))
                        .addPermissionOverride(event.getMember(), EnumSet.of(Permission.VIEW_CHANNEL), null)
                        .addPermissionOverride(guild.getPublicRole(), null, EnumSet.of(Permission.VIEW_CHANNEL))
                        .addPermissionOverride(guild.getRoleById("1001577950682042448"), EnumSet.of(Permission.VIEW_CHANNEL), null)
                        .complete().sendMessageEmbeds(embed.build()).setActionRow(closedButton(), claimButton()).queue();
                EmbedBuilder teamEmbed = new EmbedBuilder();
                teamEmbed.setColor(Color.GREEN);
                teamEmbed.setTitle("Ticket System");
                teamEmbed.addField("Person", event.getMember().getAsMention(), true);
                teamEmbed.addField("Datum", formatter.format(date), true);
                guild.getTextChannelById("1001578018772353114").sendMessageEmbeds(teamEmbed.build()).queue();
            } else {
                EmbedBuilder embed = new EmbedBuilder();
                embed.setColor(Color.RED);
                embed.setTitle("Ticket System");
                embed.setDescription("Du hast eine Ticket Sperre");
                event.getUser().openPrivateChannel().complete().sendMessageEmbeds(embed.build()).queue();
            }
        } else if (event.getButton().getId().equals("closeButton")) {
            String roles = String.valueOf(event.getMember().getRoles());
            if (roles.contains("™️ × TEAM")) {
                event.getInteraction().getChannel().delete().queue();
            }
        } else if (event.getButton().getId().equals("claimButton")) {
            if (event.getInteraction().getMember().hasPermission(Permission.KICK_MEMBERS)) {
                TextChannel channel = event.getChannel().asTextChannel();
                event.getInteraction().getMessage().delete().queue();
                EmbedBuilder embed = new EmbedBuilder();
                embed.setColor(Color.RED);
                embed.setTitle("Ticket System");
                embed.setDescription(event.getInteraction().getUser().getAsMention() + " hat das Ticket geclaimt!");
                channel.sendMessageEmbeds(embed.build()).setActionRow(closedButton()).queue();
                PermissionOverride permissionOverride = channel.getPermissionOverride(event.getMember());
                IPermissionHolder iPermissionHolder = event.getGuild().getRoleById("1001577950682042448");
                //channel.upsertPermissionOverride(iPermissionHolder).setDenied(Permission.VIEW_CHANNEL).queue();
                //channel.upsertPermissionOverride(event.getMember()).setAllowed(Permission.VIEW_CHANNEL).queue();

                if (permissionOverride == null) {
                    channel.upsertPermissionOverride(event.getMember()).setAllowed(Permission.VIEW_CHANNEL).queue();
                } else {
                    permissionOverride.getManager().setAllowed(Permission.VIEW_CHANNEL).queue();
                }
                PermissionOverride permissionOverride1 = channel.getPermissionOverride(event.getGuild().getRoleById("1001577950682042448"));
                if (permissionOverride1 == null) {
                    channel.upsertPermissionOverride(iPermissionHolder).setDenied(Permission.VIEW_CHANNEL).queue();
                } else {
                    permissionOverride1.getManager().setDenied(Permission.VIEW_CHANNEL).queue();
                }
            }
        } else if (event.getButton().getId().equals("closeButton")) {
            String roles = String.valueOf(event.getMember().getRoles());
            if (roles.contains("™️ × TEAM")) {
                event.getInteraction().getChannel().delete().queue();
            }
        } else if (event.getButton().getId().equals("claimButton")) {
            if (event.getInteraction().getMember().hasPermission(Permission.KICK_MEMBERS)) {
                TextChannel channel = event.getChannel().asTextChannel();
                event.getInteraction().getMessage().delete().queue();
                EmbedBuilder embed = new EmbedBuilder();
                embed.setColor(Color.RED);
                embed.setTitle("Ticket System");
                embed.setDescription(event.getInteraction().getUser().getAsMention() + " hat das Ticket geclaimt!");
                channel.sendMessageEmbeds(embed.build()).setActionRow(closedButton(), reopenButton()).queue();
                PermissionOverride permissionOverride = channel.getPermissionOverride(event.getMember());
                IPermissionHolder iPermissionHolder = event.getGuild().getRoleById("1001577966435827862");
                //channel.upsertPermissionOverride(iPermissionHolder).setDenied(Permission.VIEW_CHANNEL).queue();
                //channel.upsertPermissionOverride(event.getMember()).setAllowed(Permission.VIEW_CHANNEL).queue();

                if (permissionOverride == null) {
                    channel.upsertPermissionOverride(event.getMember()).setAllowed(Permission.VIEW_CHANNEL).queue();
                } else {
                    permissionOverride.getManager().setAllowed(Permission.VIEW_CHANNEL).queue();
                }
                PermissionOverride permissionOverride1 = channel.getPermissionOverride(event.getGuild().getRoleById("1001577950682042448"));
                if (permissionOverride1 == null) {
                    channel.upsertPermissionOverride(iPermissionHolder).setDenied(Permission.VIEW_CHANNEL).queue();
                } else {
                    permissionOverride1.getManager().setDenied(Permission.VIEW_CHANNEL).queue();
                }
            }/* else if (event.getButton().getId().equals("reopenButton")) {
                if (event.getInteraction().getMember().hasPermission(Permission.KICK_MEMBERS)) {
                    TextChannel channel = event.getChannel().asTextChannel();
                    event.getInteraction().getMessage().delete().queue();
                    EmbedBuilder embed = new EmbedBuilder();
                    embed.setColor(Color.RED);
                    embed.setTitle("Ticket System");
                    embed.setDescription(event.getInteraction().getUser().getAsMention() + " hat das Ticket wieder geöffnet!");
                    channel.sendMessageEmbeds(embed.build()).setActionRow(closedButton(), claimButton()).queue();
                    PermissionOverride permissionOverride = channel.getPermissionOverride(event.getMember());
                    IPermissionHolder iPermissionHolder = event.getGuild().getRoleById("1001577966435827862");
                    //channel.upsertPermissionOverride(iPermissionHolder).setDenied(Permission.VIEW_CHANNEL).queue();
                    //channel.upsertPermissionOverride(event.getMember()).setAllowed(Permission.VIEW_CHANNEL).queue();

                    if (permissionOverride == null) {
                        channel.upsertPermissionOverride(event.getMember()).setAllowed(Permission.VIEW_CHANNEL).queue();
                    } else {
                        permissionOverride.getManager().setAllowed(Permission.VIEW_CHANNEL).queue();
                    }
                    PermissionOverride permissionOverride1 = channel.getPermissionOverride(event.getGuild().getRoleById("1001577950682042448"));
                    if (permissionOverride1 == null) {
                        channel.upsertPermissionOverride(iPermissionHolder).setAllowed(Permission.VIEW_CHANNEL).queue();
                    } else {
                        permissionOverride1.getManager().setAllowed(Permission.VIEW_CHANNEL).queue();
                    }
                }
            }*/
        } else if (event.getButton().getId().equals("reopenButton")) {
            if (event.getInteraction().getMember().hasPermission(Permission.KICK_MEMBERS)) {
                TextChannel channel = event.getChannel().asTextChannel();
                event.getInteraction().getMessage().delete().queue();
                EmbedBuilder embed = new EmbedBuilder();
                embed.setColor(Color.RED);
                embed.setTitle("Ticket System");
                embed.setDescription(event.getInteraction().getUser().getAsMention() + " hat das Ticket wieder geöffnet!");
                channel.sendMessageEmbeds(embed.build()).setActionRow(closedButton(), claimButton()).queue();
                PermissionOverride permissionOverride = channel.getPermissionOverride(event.getMember());
                IPermissionHolder iPermissionHolder = event.getGuild().getRoleById("1001577966435827862");
                //channel.upsertPermissionOverride(iPermissionHolder).setDenied(Permission.VIEW_CHANNEL).queue();
                //channel.upsertPermissionOverride(event.getMember()).setAllowed(Permission.VIEW_CHANNEL).queue();

                if (permissionOverride == null) {
                    channel.upsertPermissionOverride(event.getMember()).setAllowed(Permission.VIEW_CHANNEL).queue();
                } else {
                    permissionOverride.getManager().setAllowed(Permission.VIEW_CHANNEL).queue();
                }
                PermissionOverride permissionOverride1 = channel.getPermissionOverride(event.getGuild().getRoleById("1001577950682042448"));
                if (permissionOverride1 == null) {
                    channel.upsertPermissionOverride(iPermissionHolder).setAllowed(Permission.VIEW_CHANNEL).queue();
                } else {
                    permissionOverride1.getManager().setAllowed(Permission.VIEW_CHANNEL).queue();
                }
            }
        }
    }


    public static Button closedButton() {
        return Button.danger("closeButton", "Schließen");
    }

    public static Button claimButton() {
        return Button.danger("claimButton", "Claimen");
    }

    public static Button reopenButton() {
        return Button.success("reopenButton", "Wieder öffnen");
    }
}
