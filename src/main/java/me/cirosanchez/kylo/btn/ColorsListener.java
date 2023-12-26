package me.cirosanchez.kylo.btn;

import me.cirosanchez.kylo.Kylo;
import me.cirosanchez.kylo.logger.Logger;
import me.cirosanchez.kylo.util.Color;
import org.javacord.api.entity.message.MessageFlag;
import org.javacord.api.entity.permission.Role;
import org.javacord.api.entity.server.Server;
import org.javacord.api.entity.user.User;
import org.javacord.api.event.interaction.ButtonClickEvent;
import org.javacord.api.event.interaction.SelectMenuChooseEvent;
import org.javacord.api.listener.interaction.ButtonClickListener;
import org.javacord.api.listener.interaction.SelectMenuChooseListener;

public class ColorsListener implements SelectMenuChooseListener {
    private final Kylo kylo;
    private Server server;

    public ColorsListener(Kylo kylo) {
        this.kylo = kylo;
        this.server = kylo.getServer();
    }

    @Override
    public void onSelectMenuChoose(SelectMenuChooseEvent event) {
        String id = event.getSelectMenuInteraction().getChosenOptions().get(0).getValue();
        User user = event.getSelectMenuInteraction().getUser();
        switch (id){
            case "white":
                user.addRole(server.getRoleById(Color.WHITE.getLong()).get());
                event.getSelectMenuInteraction().createImmediateResponder()
                        .setContent("A\u00F1adiste el color Blanco!")
                        .setFlags(MessageFlag.EPHEMERAL)
                        .respond();
                kylo.getLogger().logColor(user, Color.WHITE);
                break;
            case "black":
                user.addRole(server.getRoleById(Color.BLACK.getLong()).get());
                event.getSelectMenuInteraction().createImmediateResponder()
                        .setContent("A\u00F1adiste el color Negro!")
                        .setFlags(MessageFlag.EPHEMERAL)
                        .respond();
                kylo.getLogger().logColor(user, Color.BLACK);
                break;
            case "pink":
                user.addRole(server.getRoleById(Color.PINK.getLong()).get());
                event.getSelectMenuInteraction().createImmediateResponder()
                        .setContent("A\u00F1adiste el color Rosado!")
                        .setFlags(MessageFlag.EPHEMERAL)
                        .respond();
                kylo.getLogger().logColor(user, Color.PINK);
                break;
            case "yellow":
                user.addRole(server.getRoleById(Color.YELLOW.getLong()).get());
                event.getSelectMenuInteraction().createImmediateResponder()
                        .setContent("A\u00F1adiste el color Amarillo!")
                        .setFlags(MessageFlag.EPHEMERAL)
                        .respond();
                kylo.getLogger().logColor(user, Color.YELLOW);
                break;
            case "orange":
                user.addRole(server.getRoleById(Color.ORANGE.getLong()).get());
                event.getSelectMenuInteraction().createImmediateResponder()
                        .setContent("A\u00F1adiste el color Naranja!")
                        .setFlags(MessageFlag.EPHEMERAL)
                        .respond();
                kylo.getLogger().logColor(user, Color.ORANGE);
                break;
            case "green":
                user.addRole(server.getRoleById(Color.GREEN.getLong()).get());
                event.getSelectMenuInteraction().createImmediateResponder()
                        .setContent("A\u00F1adiste el color Verde!")
                        .setFlags(MessageFlag.EPHEMERAL)
                        .respond();
                kylo.getLogger().logColor(user, Color.GREEN);
                break;
            case "blurple":
                user.addRole(server.getRoleById(Color.BLURPLE.getLong()).get());
                event.getSelectMenuInteraction().createImmediateResponder()
                        .setContent("A\u00F1adiste el color Blurple!")
                        .setFlags(MessageFlag.EPHEMERAL)
                        .respond();
                kylo.getLogger().logColor(user, Color.BLURPLE);
                break;
            case "cyan":
                user.addRole(server.getRoleById(Color.CYAN.getLong()).get());
                event.getSelectMenuInteraction().createImmediateResponder()
                        .setContent("A\u00F1adiste el color Cyan!")
                        .setFlags(MessageFlag.EPHEMERAL)
                        .respond();
                kylo.getLogger().logColor(user, Color.CYAN);
                break;
            case "blue":
                user.addRole(server.getRoleById(Color.BLUE.getLong()).get());
                event.getSelectMenuInteraction().createImmediateResponder()
                        .setContent("A\u00F1adiste el color Azul!")
                        .setFlags(MessageFlag.EPHEMERAL)
                        .respond();
                kylo.getLogger().logColor(user, Color.BLUE);
                break;
            case "red":
                user.addRole(server.getRoleById(Color.RED.getLong()).get());
                event.getSelectMenuInteraction().createImmediateResponder()
                        .setContent("A\u00F1adiste el color Rojo!")
                        .setFlags(MessageFlag.EPHEMERAL)
                        .respond();
                kylo.getLogger().logColor(user, Color.RED);
                break;
            default:
                break;
        }
    }
}
