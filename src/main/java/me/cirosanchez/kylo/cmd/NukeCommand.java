package me.cirosanchez.kylo.cmd;

import me.cirosanchez.kylo.Kylo;
import org.javacord.api.entity.channel.Channel;
import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.message.Message;
import org.javacord.api.entity.message.MessageFlag;
import org.javacord.api.event.interaction.SlashCommandCreateEvent;
import org.javacord.api.interaction.SlashCommand;
import org.javacord.api.listener.interaction.SlashCommandCreateListener;

import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class NukeCommand implements SlashCommandCreateListener {
    private final Kylo kylo;

    public NukeCommand(Kylo kylo) {
        this.kylo = kylo;
        SlashCommand cmd = SlashCommand.with("nuke", "Elimina todos los mensajes!").createGlobal(kylo.getApi()).join();
    }

    @Override
    public void onSlashCommandCreate(SlashCommandCreateEvent event) {
        if (!event.getSlashCommandInteraction().getCommandName().equalsIgnoreCase("nuke")){
            return;
        }
        if (!event.getSlashCommandInteraction().getUser().getRoles(kylo.getServer()).contains(kylo.getAdminRole())){
            event.getSlashCommandInteraction().createImmediateResponder()
                    .setContent("No tienes permiso para ejecutar este comando!")
                    .setFlags(MessageFlag.EPHEMERAL)
                    .respond();
        }

        TextChannel channel = event.getSlashCommandInteraction().getChannel().get();
        for (Message msg : channel.getMessages(1000).join()){
            msg.delete();
        }



        kylo.getLogger().logNuke(event.getSlashCommandInteraction().getUser(), channel);
        event.getSlashCommandInteraction().createImmediateResponder()
                .setContent("Canal nukeado!")
                .respond();
    }
}
