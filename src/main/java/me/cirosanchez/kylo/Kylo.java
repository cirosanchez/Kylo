package me.cirosanchez.kylo;

import lombok.Getter;
import me.cirosanchez.kylo.btn.ButtonListener;
import me.cirosanchez.kylo.btn.ColorsListener;
import me.cirosanchez.kylo.cmd.ColorsCommand;
import me.cirosanchez.kylo.cmd.NukeCommand;
import me.cirosanchez.kylo.cmd.VerificationCommand;
import me.cirosanchez.kylo.logger.Logger;
import me.cirosanchez.kylo.token.Token;
import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.entity.activity.ActivityType;
import org.javacord.api.entity.channel.Channel;
import org.javacord.api.entity.permission.Role;
import org.javacord.api.entity.server.Server;
import org.javacord.api.entity.user.UserStatus;

public class Kylo {
    @Getter
    private final DiscordApi api;
    @Getter
    private Server server;
    @Getter
    private Role verifiedRole;
    @Getter
    private Role adminRole;
    @Getter
    private Logger logger;

    public Kylo() {
        this.api = new DiscordApiBuilder().setAllIntents().setToken(Token.TOKEN).login().join();
    }
    public void start(){
        server = api.getServerById(1117665811054723132L).get();
        verifiedRole = server.getRoleById(1188310974948708422L).get();
        adminRole = server.getRoleById(1189010066322509955L).get();
        logger = new Logger(this);

        api.addSlashCommandCreateListener(new VerificationCommand(this));
        api.addButtonClickListener(new ButtonListener(this));
        api.addSlashCommandCreateListener(new ColorsCommand(this));
        api.addSelectMenuChooseListener(new ColorsListener(this));
        api.addSlashCommandCreateListener(new NukeCommand(this));

        api.updateActivity(ActivityType.WATCHING, "https://cirosanchez.me");
        api.updateStatus(UserStatus.IDLE);


        System.out.println("Kylo is online!");
        System.out.println("Invite link: " + api.createBotInvite());
    }
}
