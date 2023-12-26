package me.cirosanchez.kylo.cmd;

import me.cirosanchez.kylo.Kylo;
import net.fellbaum.jemoji.Emoji;
import net.fellbaum.jemoji.EmojiManager;
import org.javacord.api.entity.message.MessageBuilder;
import org.javacord.api.entity.message.MessageFlag;
import org.javacord.api.entity.message.component.ActionRow;
import org.javacord.api.entity.message.component.SelectMenu;
import org.javacord.api.entity.message.component.SelectMenuOption;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.event.interaction.SlashCommandCreateEvent;
import org.javacord.api.interaction.SlashCommand;
import org.javacord.api.listener.interaction.SlashCommandCreateListener;
import org.javacord.api.entity.message.component.Button;

import java.awt.*;
import java.util.ArrayList;

public class ColorsCommand implements SlashCommandCreateListener {
    private final Kylo kylo;

    public ColorsCommand(Kylo kylo) {
        this.kylo = kylo;
        SlashCommand cmd = SlashCommand.with("colors", "sends the color module")
                .createGlobal(kylo.getApi())
                .join();
    }

    @Override
    public void onSlashCommandCreate(SlashCommandCreateEvent event) {
        if (!event.getSlashCommandInteraction().getCommandName().equalsIgnoreCase("colors")){
            return;
        }
        if (!event.getSlashCommandInteraction().getUser().getRoles(kylo.getServer()).contains(kylo.getAdminRole())){
            event.getSlashCommandInteraction().createImmediateResponder()
                    .setContent("No tienes permiso para ejecutar este comando!")
                    .setFlags(MessageFlag.EPHEMERAL)
                    .respond();
        }


        EmbedBuilder builder = new EmbedBuilder()
                .setColor(Color.decode("#72f2c1"))
                .setAuthor(kylo.getApi().getYourself())
                .setTitle("Colores")
                .setDescription("Elige tu color favorito!")
                .setFooter("Kylo", kylo.getApi().getYourself().getAvatar());

        ActionRow row = ActionRow.of(
                SelectMenu.create("colors", "Elige tu color...", options())
        );

        new MessageBuilder()
                .addEmbed(builder)
                .addComponents(row)
                .setContent(kylo.getServer().getEveryoneRole().getMentionTag())
                .send(event.getSlashCommandInteraction().getChannel().get()).exceptionally(e -> {
                    e.printStackTrace();
                    return null;
                });

        event.getSlashCommandInteraction().createImmediateResponder()
                .setContent("Enviando modulo...")
                .setFlags(MessageFlag.EPHEMERAL)
                .respond();
    }
    private ArrayList<SelectMenuOption> options() {
        ArrayList<SelectMenuOption> options = new ArrayList<>();

        Emoji cloud = EmojiManager.getByAlias(":cloud:").get();
        Emoji blackHeart = EmojiManager.getByAlias(":black_heart:").get();
        Emoji pig = EmojiManager.getByAlias(":pig:").get();
        Emoji chick = EmojiManager.getByAlias(":baby_chick:").get();
        Emoji lion = EmojiManager.getByAlias(":lion_face:").get();
        Emoji fourTwenty = EmojiManager.getByAlias(":leaves:").get();
        Emoji discord = EmojiManager.getByAlias(":octopus:").get();
        Emoji sky = EmojiManager.getByAlias(":parachute:").get();
        Emoji sea = EmojiManager.getByAlias(":ocean:").get();
        Emoji rose = EmojiManager.getByAlias(":rose:").get();

        options.add(SelectMenuOption.create("Blanco", "white","El color de una nube!", cloud.getUnicode()));
        options.add(SelectMenuOption.create("Negro", "black","El color del vacío!", blackHeart.getUnicode()));
        options.add(SelectMenuOption.create("Rosado", "pink","El color de un cerdito!", pig.getUnicode()));
        options.add(SelectMenuOption.create("Amarillo", "yellow","El color de un pollito!", chick.getUnicode()));
        options.add(SelectMenuOption.create("Naranja", "orange","El color de un león!", lion.getUnicode()));
        options.add(SelectMenuOption.create("Verde", "green","420...", fourTwenty.getUnicode()));
        options.add(SelectMenuOption.create("Blurple", "blurple","El color de discord!", discord.getUnicode()));
        options.add(SelectMenuOption.create("Cyan", "cyan","El color del cielo!", sky.getUnicode()));
        options.add(SelectMenuOption.create("Azul", "blue","El color del mar!", sea.getUnicode()));
        options.add(SelectMenuOption.create("Rojo", "red","Una rosa para otra rosa!", rose.getEmoji()));

        return options;
    }
}
