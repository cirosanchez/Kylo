package me.cirosanchez.kylo.cmd;

import me.cirosanchez.kylo.Kylo;
import org.javacord.api.DiscordApi;
import org.javacord.api.entity.message.MessageBuilder;
import org.javacord.api.entity.message.MessageFlag;
import org.javacord.api.entity.message.component.ActionRow;
import org.javacord.api.entity.message.component.Button;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.event.interaction.SlashCommandCreateEvent;
import org.javacord.api.interaction.SlashCommand;
import org.javacord.api.listener.interaction.SlashCommandCreateListener;

public class VerificationCommand implements SlashCommandCreateListener {
    private final Kylo kylo;
    public VerificationCommand(Kylo kylo) {
        this.kylo = kylo;
        SlashCommand cmd = SlashCommand.with("verification", "Sends the verification module")
                .createGlobal(kylo.getApi())
                .join();
    }


    @Override
    public void onSlashCommandCreate(SlashCommandCreateEvent event) {
        if (!event.getSlashCommandInteraction().getCommandName().equalsIgnoreCase("verification")){
            return;
        }

        if (!event.getSlashCommandInteraction().getUser().getRoles(kylo.getServer()).contains(kylo.getAdminRole())){
            event.getSlashCommandInteraction().createImmediateResponder()
                    .setContent("No tienes permiso para ejecutar este comando!")
                    .setFlags(MessageFlag.EPHEMERAL)
                    .respond();
        }

        EmbedBuilder embedBuilder = new EmbedBuilder()
                .setTitle("Verificaci\u00F3n")
                .setDescription("Clickea \u2705 para ser verificado!")
                .setAuthor(kylo.getApi().getYourself());

        ActionRow row = ActionRow.of(
                Button.success("verify","Verificate", "\u2705")
        );

        new MessageBuilder()
                .addEmbed(embedBuilder)
                        .addComponents(row)
                                .setContent(kylo.getServer().getEveryoneRole().getMentionTag())
                                        .send(event.getSlashCommandInteraction().getChannel().get());
        event.getSlashCommandInteraction().createImmediateResponder()
                .setContent("Enviando modulo...")
                .setFlags(MessageFlag.EPHEMERAL)
                .respond();
    }
}
