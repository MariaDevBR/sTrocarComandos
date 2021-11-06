package com.maria.changecommands.methods;

import com.maria.changecommands.Main;
import com.maria.changecommands.models.ChangeCommand;
import org.bukkit.entity.Player;

public class ChangeCommandMethods {

    protected Main main;

    public ChangeCommandMethods(Main main) {
        this.main = main;
    }

    public boolean hasPermission(Player player, ChangeCommand changeCommand) {
        if (!player.hasPermission(changeCommand.getPermission())) {
            main.sendMessage(player, changeCommand.getNoPermissionMessage());
            main.playSound(player, changeCommand.getNoPermissionSound());
            return false;

        }
        return true;
    }

}
