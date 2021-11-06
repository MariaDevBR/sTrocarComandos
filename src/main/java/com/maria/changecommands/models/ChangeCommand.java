package com.maria.changecommands.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Sound;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class ChangeCommand {

    private String command;
    private String newCommand;
    private String permission;
    private String noPermissionMessage;
    private Sound usedCommand;
    private Sound noPermissionSound;
    private boolean addPermission;
}
