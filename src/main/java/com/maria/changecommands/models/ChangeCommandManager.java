package com.maria.changecommands.models;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.Sound;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class ChangeCommandManager {

    private FileConfiguration config;

    private Map<String, ChangeCommand> changeCommandsMap;

    public ChangeCommandManager(FileConfiguration config) {
        this.config = config;

        changeCommandsMap = new HashMap<>();

        setupChangedCommand();
    }

    private void changeCommand(String path, ChangeCommand changeCommand) {
        changeCommandsMap.put(path, changeCommand);
    }

    public ChangeCommand getChangedCommand(String command) {
        return changeCommandsMap.entrySet().stream().map(Map.Entry::getValue).filter(cmd -> cmd.getNewCommand().equalsIgnoreCase(command)).findFirst().orElse(null);
    }

    private void setupChangedCommand() {
        for (String path : config.getConfigurationSection("Trocar comandos").getKeys(false)) {
            ConfigurationSection key = config.getConfigurationSection("Trocar comandos." + path);

            String command = key.getString("Comando");
            String newCommand = key.getString("Novo comando");
            String permission = key.getString("Permissao");
            String noPermissionMessage = key.getString("Sem permissao").replace("&", "§").replace("{permission}", permission).replace("{command}", newCommand);
            Sound usedCommand = Sound.valueOf(key.getString("Sons.Usou"));
            Sound noPermissionSound = Sound.valueOf(key.getString("Sons.Sem permissao"));
            boolean addPermission = key.getBoolean("Adicionar permissao");

            ChangeCommand changeCommand = new ChangeCommand(command, newCommand, permission, noPermissionMessage, usedCommand, noPermissionSound, addPermission);
            changeCommand(path, changeCommand);
        }

    }

}
