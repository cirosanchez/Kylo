package me.cirosanchez.kylo.btn;

import me.cirosanchez.kylo.Kylo;
import org.javacord.api.entity.message.MessageFlag;
import org.javacord.api.entity.user.User;
import org.javacord.api.event.interaction.ButtonClickEvent;
import org.javacord.api.listener.interaction.ButtonClickListener;

public class ButtonListener implements ButtonClickListener {
    private final Kylo kylo;

    public ButtonListener(Kylo kylo) {
        this.kylo = kylo;
    }

    @Override
    public void onButtonClick(ButtonClickEvent event) {
        if (!event.getButtonInteraction().getCustomId().equalsIgnoreCase("verify")){
            return;
        }
        if (event.getButtonInteraction().getUser().getRoles(kylo.getServer()).contains(kylo.getVerifiedRole())){
            event.getButtonInteraction().createImmediateResponder()
                    .setContent("Ya est\u00E1s verificado!")
                    .setFlags(MessageFlag.EPHEMERAL)
                    .respond();
            return;
        }

        User user = event.getButtonInteraction().getUser();
        user.addRole(kylo.getVerifiedRole());

        kylo.getLogger().logVerification(user);

        event.getButtonInteraction().createImmediateResponder()
                .setContent("Te has verificado correctamente!")
                .setFlags(MessageFlag.EPHEMERAL)
                .respond();
    }

}
