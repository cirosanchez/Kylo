package me.cirosanchez.kylo.logger;

import me.cirosanchez.kylo.Kylo;
import org.javacord.api.entity.channel.Channel;
import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.message.MessageBuilder;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.entity.user.User;

import me.cirosanchez.kylo.util.Color;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class Logger {
    private Channel channel;

    public Logger(Kylo kylo) {
        this.channel = kylo.getApi().getServerById(1117665811054723132L).get().getChannelById(1189026236446547979L).get();
    }

    public void logVerification(User user){

        EmbedBuilder builder = new EmbedBuilder()
                .setColor(java.awt.Color.decode("#d8f589"))
                .setTitle("Nuevo usuario verificado!")
                .setDescription("Hay un nuevo usuario verificado!")
                .addField("Nombre", user.getMentionTag())
                .addField("ID", user.getIdAsString())
                .addField("Date", Calendar.getInstance(TimeZone.getTimeZone("GMT-5")).getTime().toString())
                .setThumbnail(user.getAvatar())
                .setAuthor(user);

        new MessageBuilder()
                .addEmbed(builder)
                .setContent("**NUEVO USUARIO!**")
                .send(channel.asTextChannel().get());
    }

    public void logColor(User user, Color color){
        EmbedBuilder builder = new EmbedBuilder()
                .setColor(java.awt.Color.decode("#d8f589"))
                .setTitle("Cambio de color!")
                .setDescription("Un usuario ha cambiado su color!")
                .addField("Usuario", user.getMentionTag())
                .addField("Color", color.toString())
                .setAuthor(user)
                .addField("Date", Calendar.getInstance(TimeZone.getTimeZone("GMT-5")).getTime().toString())
                .setFooter("Kylo", user.getAvatar());


        new MessageBuilder()
                .addEmbed(builder)
                .setContent("**CAMBIO DE COLOR!**")
                .send(channel.asTextChannel().get());
    }
    public void logNuke(User user, TextChannel channel){
        EmbedBuilder builder = new EmbedBuilder()
                .setColor(java.awt.Color.decode("#911c1c"))
                .setTitle("Nuke!")
                .setDescription("Un administrador ha nukeado el canal!")
                .addField("Admin", user.getMentionTag()+ " de ID: "+ user.getIdAsString())
                .addField("Canal", "<#"+channel.getIdAsString()+">")
                .setAuthor(user)
                .addField("Date", Calendar.getInstance(TimeZone.getTimeZone("GMT-5")).getTime().toString())
                .setFooter("Kylo", user.getAvatar());

        new MessageBuilder()
                .addEmbed(builder)
                .setContent("**NUKE!**")
                .send(this.channel.asTextChannel().get());
    }
}
