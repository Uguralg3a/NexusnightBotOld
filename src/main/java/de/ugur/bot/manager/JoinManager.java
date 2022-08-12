package de.ugur.bot.manager;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.guild.GuildJoinEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

public class JoinManager extends ListenerAdapter {

    @Override
    public void onGuildMemberJoin(@NotNull GuildMemberJoinEvent event) {

        Member yourMember = event.getMember();

        String mentid = "<@" + yourMember.getId() + ">";

        String mention = yourMember.getEffectiveName();

        EmbedBuilder embed = new EmbedBuilder()
                .setTitle("Wilkommen")
                .setDescription("Hey " + mentid +  " Herzlich willkommen auf dem\n\uD835\uDDAD\uD835\uDDBE\uD835\uDDD1\uD835\uDDCE\uD835\uDDCC\uD835\uDDC7\uD835\uDDC2\uD835\uDDC0\uD835\uDDC1\uD835\uDDCD.\uD835\uDDC7\uD835\uDDBE\uD835\uDDCD × Community Discord!\n" + "\nAkzpetiere unsere Discord Regeln in <#1001577988044898446>\num mit anderen zu schreiben!\n" + "\nIch wünsche dir viel Spaß auf \uD835\uDDAD\uD835\uDDBE\uD835\uDDD1\uD835\uDDCE\uD835\uDDCC\uD835\uDDC7\uD835\uDDC2\uD835\uDDC0\uD835\uDDC1\uD835\uDDCD.\uD835\uDDC7\uD835\uDDBE\uD835\uDDCD!")
                .setColor(Color.YELLOW);
        event.getGuild().getTextChannelById("1001577993094836325").sendMessageEmbeds(embed.build()).queue();
       // event.getGuild().getTextChannelById("970728897291354233").sendMessage("Willkommen!\n" +
           //     "Hey,  " + mention + " Herzlich willkommen auf dem\n" +
         //       "\uD835\uDDAD\uD835\uDDBE\uD835\uDDD1\uD835\uDDCE\uD835\uDDCC\uD835\uDDC7\uD835\uDDC2\uD835\uDDC0\uD835\uDDC1\uD835\uDDCD.\uD835\uDDC7\uD835\uDDBE\uD835\uDDCD × Community Discord!\n" +
             //   "\n" +
               // "Akzpetiere unsere Discord Regeln in\n" +
                //"\uD83D\uDCDA┃Regeln "*/ "<#970721900852543540> um mit anderen zu schreiben! \n" +
                //"\n" +
                //"Ich wünsche dir viel Spaß auf DDBE\uD835\uDDCD!").queue();
    }

    @Override
    public void onGuildJoin(@NotNull GuildJoinEvent event) {
        EmbedBuilder embedBuilder = new EmbedBuilder()
                .setTitle("Nexusnight.net")
                .setDescription("Ich wurde eingeladen und habe Pizza mitgebracht.");
        event.getGuild().getTextChannelById("");
    }
}
