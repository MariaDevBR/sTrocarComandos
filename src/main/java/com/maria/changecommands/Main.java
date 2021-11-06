package com.maria.changecommands;

import lombok.Getter;

import com.maria.changecommands.files.ChangeCommandsFile;
import com.maria.changecommands.listeners.ChangeCommandListener;
import com.maria.changecommands.methods.ChangeCommandMethods;
import com.maria.changecommands.models.ChangeCommandManager;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public final class Main extends JavaPlugin {

    private ChangeCommandsFile changeCommandsFile;

    private ChangeCommandManager changeCommandManager;

    private ChangeCommandMethods changeCommandMethods;

    @Override
    public void onEnable() {
        saveDefaultConfig();

        ConsoleCommandSender console = Bukkit.getConsoleSender();
        console.sendMessage("§6[" + getDescription().getName() + "] §fIniciado com sucesso");
        console.sendMessage("§6[" + getDescription().getName() + "] §fEntre em meu Discord");
        console.sendMessage("§6[" + getDescription().getName() + "] §fDiscord: §6https://discord.gg/ysQMPe5tPh");
        console.sendMessage("§6[" + getDescription().getName() + "] §fCriado por §6Maria_BR");
        registerObjects();
        registerFunctions();
    }

    @Override
    public void onDisable() {
        ConsoleCommandSender console = Bukkit.getConsoleSender();
        console.sendMessage("§6[" + getDescription().getName() + "] §fDesligado");
        console.sendMessage("§6[" + getDescription().getName() + "] §fEntre em meu Discord");
        console.sendMessage("§6[" + getDescription().getName() + "] §fDiscord: §6https://discord.gg/ysQMPe5tPh");
        console.sendMessage("§6[" + getDescription().getName() + "] §fCriado por §6Maria_BR");
    }

    private void registerFunctions() {
        new ChangeCommandListener(this);
    }

    private void registerObjects() {
        ChangeCommandsFile.createChangeCommandsFile();
        changeCommandsFile = new ChangeCommandsFile();

        changeCommandManager = new ChangeCommandManager(changeCommandsFile.getConfig());

        changeCommandMethods = new ChangeCommandMethods(this);
    }

    public void sendMessage(Player player, String message) {
        String prefix = getConfig().getString("Prefix").replace("&", "§");

        if (getConfig().getBoolean("Usar prefix"))
            player.sendMessage(prefix + " " + message);

        else
            player.sendMessage(message);
    }

    public void playSound(Player player, Sound sound) {
        player.playSound(player.getLocation(), sound, 1.0F, 1.0F);
    }

}
