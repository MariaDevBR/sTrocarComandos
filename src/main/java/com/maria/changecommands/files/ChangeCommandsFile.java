package com.maria.changecommands.files;

import com.maria.changecommands.utils.DateManager;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;

import java.io.File;

@Getter
@Setter
public class ChangeCommandsFile {

    private FileConfiguration config;

    public ChangeCommandsFile() {
        config = DateManager.getConfig("trocar comandos");
    }

    public static void createChangeCommandsFile() {
        File file = DateManager.getFile("trocar comandos");

        if (!file.exists()) {
            DateManager.createConfig("trocar comandos");

            Bukkit.getConsoleSender().sendMessage("§6[sTrocarComandos] §fArquivo §6trocar comandos.yml §fcriado com sucesso");
        }

    }

}
