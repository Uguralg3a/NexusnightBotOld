package de.ugur.bot.String;

import net.dv8tion.jda.api.EmbedBuilder;

import java.awt.*;

public class Embeds {

    public static EmbedBuilder noPerm = new EmbedBuilder()
            .setTitle("Missing Permissions!")
            .setDescription("Du hast nicht die benötigten Rechte, um diesen Befehl auszuführen! Sollte dies nicht der Fall sein, melde dich bitte bei einem Administrator oder bei dem Entwickler von diesem Bot!")
            .setColor(Color.RED);


}
