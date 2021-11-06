package com.maria.changecommands.listeners;

import com.maria.changecommands.Main;
import com.maria.changecommands.api.ChangeCommandEvent;
import com.maria.changecommands.methods.ChangeCommandMethods;
import com.maria.changecommands.models.ChangeCommand;
import com.maria.changecommands.models.ChangeCommandManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class ChangeCommandListener implements Listener {

    protected Main main;

    private final ChangeCommandManager changeCommandManager;
    private final ChangeCommandMethods changeCommandMethods;

    public ChangeCommandListener(Main main) {
        this.main = main;

        Bukkit.getPluginManager().registerEvents(this, main);

        changeCommandManager = main.getChangeCommandManager();
        changeCommandMethods = main.getChangeCommandMethods();
    }

    @EventHandler
    void changeCommand(PlayerCommandPreprocessEvent e) {
        Player player = e.getPlayer();
        String message = e.getMessage();

        ChangeCommand changeCommand = changeCommandManager.getChangedCommand(message);

        if (changeCommand == null)
            return;

        ChangeCommandEvent changeCommandEvent = new ChangeCommandEvent(player, changeCommand, message, changeCommand.getNewCommand());
        Bukkit.getPluginManager().callEvent(changeCommandEvent);

        if (!changeCommandEvent.isCancelled()) {
            if (changeCommand.isAddPermission()) {
                if (!changeCommandMethods.hasPermission(player, changeCommand)) {
                    e.setCancelled(true);
                    return;
                }

            }
            main.playSound(player, changeCommand.getUsedCommand());
            e.setMessage(changeCommand.getCommand());
        }

    }

}
