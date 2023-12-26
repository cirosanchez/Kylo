package me.cirosanchez.kylo.logger;

import me.cirosanchez.kylo.Kylo;
import org.javacord.api.entity.channel.Channel;
import org.javacord.api.entity.message.MessageBuilder;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.entity.user.User;

import java.awt.*;
import java.util.Date;

public class Logger {
    private Channel channel;

    public Logger(Kylo kylo) {
        this.channel = kylo.getApi().getServerById(1117665811054723132L).get().getChannelById(1189026236446547979L).get();
    }

    public void logVerification(User user){

        EmbedBuilder builder = new EmbedBuilder()
                .setColor(Color.decode("#d8f589"))
                .setTitle("Nuevo usuario verificado!")
                .setDescription("Hay un nuevo usuario verificado!")
                .addField("Nombre", user.getMentionTag())
                .addField("ID", user.getIdAsString())
                .addField("Date", new Date().toString())
                .setThumbnail(user.getAvatar())
                .setAuthor(user);

        new MessageBuilder()
                .addEmbed(builder)
                .setContent("**NUEVO USUARIO!**")
                .send(channel.asTextChannel().get());
    }
}
