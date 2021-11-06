package com.maria.changecommands.api;

import com.maria.changecommands.models.ChangeCommand;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

@Getter
@Setter
public class ChangeCommandEvent extends Event implements Cancellable {

    private static HandlerList handlerList = new HandlerList();
    private boolean cancelled;

    private Player player;
    private ChangeCommand changeCommand;
    private String command;
    private String newCommand;

    public ChangeCommandEvent(Player player, ChangeCommand changeCommand, String command, String newCommand) {
        this.player = player;
        this.changeCommand = changeCommand;
        this.command = command;
        this.newCommand = newCommand;

        cancelled = false;
    }

    @Override
    public HandlerList getHandlers() {
        return handlerList;
    }

    public static HandlerList getHandlerList() {
        return handlerList;
    }

}
