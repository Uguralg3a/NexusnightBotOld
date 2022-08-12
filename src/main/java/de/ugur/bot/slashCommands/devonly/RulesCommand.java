package de.ugur.bot.slashCommands.devonly;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.DefaultMemberPermissions;
import net.dv8tion.jda.api.interactions.components.buttons.Button;

import java.awt.*;

public class RulesCommand extends ListenerAdapter {

    private final JDA jda;

    public RulesCommand(JDA jda, String guildId) {
        this.jda = jda;
        registerCommands(guildId);
        jda.addEventListener(this);
    }

    private void registerCommands(String guildId) {
        Guild guild = jda.getGuildById(guildId); // Discord Server von der übergebenden ID auflösen

        guild.upsertCommand("rules", "rules").setDefaultPermissions(DefaultMemberPermissions.enabledFor(Permission.ADMINISTRATOR)).queue();
    }

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        if (event.getName().equals("rules")) {
            EmbedBuilder e = new EmbedBuilder()
                    .setTitle("Regeln")
                    .addField("§1Verhalten", "Beleidigungen\nBeleidigungen in jeglicher Form gegenüber Mitspielern / Außenstehenden / dem Server oder anderen Beteiligten sind strikt untersagt. Jeder hat das Recht darauf, respektvoll und höflich behandelt zu werden.\nSpam & Caps\nDas Wiederholen von einem oder mehreren gleichen oder ähnlichen Wortlauten innerhalb einer kurzen Zeit ist verboten.", true)
                    .setColor(Color.RED);

            EmbedBuilder e2 = new EmbedBuilder()
                    .setColor(Color.RED)
                    .addField(" ", "Ebenfalls ist das Großschreiben (Caps) im Chat untersagt.\nFormen des Ausdrucks\nRadikalismus, nationalsozialistische oder sexuelle Inhalte, sowie  jugendgefährdende Äußerungen sind nicht gestattet und können gegebenenfalls eine Anzeige nach sich ziehen.\nWerbung\nJegliche Form von Fremdwerbung innerhalb und außerhalb des Netzwerks Nexusnight.de ist untersagt.\nDrohung & Provokation\nDrohungen in jeglicher Form sind verboten, ebenfalls können böswillige Provokationen im öffentlichen Chat eine Strafe nach sich ziehen.\nGriefing\nGriefing ist bei uns untersagt. Unter Griefing fallen z.B.:\nDas Setzen von Lava oder Wasser an fremdem Eigentum\nDas Zerstören von fremden Bauwerken\nJegliche Art von Diebstahl\n", true);

            EmbedBuilder e3 = new EmbedBuilder()
                    .addField("§2 Bauregeln", "Verboten sind unter anderen\nBauwerke mit rassistischem, nationalistischem, sexuellem oder beleidigendem Hintergrund\nDauerhaft laufende Redstone Schaltungen\nAnlagen, die explizit dazu dienen, den Server zu schädigen\nFarmen, die nicht abschaltbar sind und auch ohne Anwesenheit des Besitzers laufen\nKakteenfarmen, mit mehr als 1.000 Kakteen\n", true)
                    .setColor(Color.RED);

            EmbedBuilder e4 = new EmbedBuilder()
                    .addField("§2.1 Observerclock", "Grundsätzlich müssen Farmen vom Spieler aktiviert werden, um so Serverkapazität zu sichern. Observerfarmen sind somit nur eingeschränkt erlaubt. Farmen dürfen sich grundsätzlich nicht selber aktivieren.\nAnfechtbarkeit der Strafe & Recht auf Anhörung\nEmpfindet ein Mitglied die Strafe als unangemessen, hat er das Recht, im Discord einen sachlichen Antrag auf Milderung oder Aufhebung der Strafe zu stellen. In diesem Antrag wird dann mit dem User gemeinsam nach einer Lösung gesucht und die Situation geprüft. Bei Strafbegehen besteht ebenfalls dieses Recht, hierbei entscheidet ein Teammitglied objektiv über den Entbannungsantrag.\nHausrecht\nJeder Spieler hat Recht darauf, sein Hausrecht und seine Privatsphäre zu schützen. Der Zugriff zu einem Haus muss mit einer Genehmigung des Eigentümers einhergehen. Das Team hat das Recht, bei einem triftigen Grund in das Hausrecht einzugreifen.", true)
                    .setColor(Color.RED);

            EmbedBuilder e5 = new EmbedBuilder()
                    .addField("§3 Grundsatz", "Jegliche Modifikationen des Clients, welche dem Spieler einen Vorteil gegenüber anderen Mitspielern verschaffen, sind grundsätzlich untersagt, da ansonsten die Fairness gegenüber den Spielern, die nicht mit solchen Modifikationen spielen, nicht mehr gewährleistet werden kann. Unerlaubte Modifikationen sind zum Beispiel X-Ray (auch X-Ray Resourcepacks), jegliche hacked Clients, Worlddownloader, Autoklicker o.ä.\nWeitere Regeln\nSpielernamen\nDie Verwendung von irreführenden, anstößigen oder beleidigenden Namen sind verboten und werden mit einer Strafe geahndet." , true)
                    .setColor(Color.RED);

            EmbedBuilder e6 = new EmbedBuilder()
                    .addField(" ", "Bugs\nJegliche Form von Bugs und deren Ausnutzung sind streng untersagt. Wenn ihr ein Bug entdeckt, solltet ihr es beim Team melden.\n Minecraft-Skins\nMoralisch verwerfliche und anstößige Minecraft-Skins die gegen den Verhaltenskodex verstoßen sind verboten. \nUmgehung von Strafen\nWer der Auffassung ist, eine bereits bestehende Strafe mit einem zweiten Account zu umgehen, wird zum einen härter bestraft und zum anderen wird der zweite Account ebenfalls bestraft.\n Accountverantwortlichkeit\nJeder ist für seinen eigenen Account verantwortlich. Daher wird eine Strafe auch dann ausgesprochen, wenn eine zweite Person außer dem Eigentümer einen Regelverstoß begeht." , true)
                            .setColor(Color.RED);
            event.replyEmbeds(e.build(), e2.build(), e3.build(), e4.build(), e5.build(), e6.build()).addActionRow(regelj(), regelr()).queue();
        }
    }

    private Button regelj() {
        return Button.success("regelnj", "Regeln Akzeptieren");
    }
    private Button regelr() {
        return Button.danger("regelc", "Entfernen");
    }

    @Override
    public void onButtonInteraction(ButtonInteractionEvent event) {
        if (event.getButton().getId().equals("regelnj")) {
            Guild guild = event.getGuild();
            Member member = event.getMember();
            Role role = guild.getRoleById("1000863399183405057");
            //event.getMember().getRoles().add(role);

            MessageEmbed embed = new EmbedBuilder()
                    .setTitle("Akzeptiert")
                            .setDescription("DU hast die regeln akzeptiert!")
                                    .setColor(Color.GREEN).build();


            guild.addRoleToMember(member, role).queue();
        } else if (event.getButton().getId().equals("regelnr")) {
            Guild guild = event.getGuild();
            Member member = event.getMember();
            Role role = guild.getRoleById("1000863399183405057");
            guild.removeRoleFromMember(member, role).queue();
        }
    }
}