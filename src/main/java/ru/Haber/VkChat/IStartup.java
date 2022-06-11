package ru.Haber.VkChat;


import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.Contract;

import lombok.NonNull;
import ru.Haber.api.Configuration.ConfigurationHandler;

public interface IStartup<MAIN extends JavaPlugin> extends Plugin,ConfigurationHandler{

	
	void onEnable();
	
	void onDisable();
	
	
	@NonNull
	IStartup<MAIN> registerInstance();
	
	default FileConfiguration saveAndDetectDefaultConfig() {
		this.saveDefaultConfig();
		
		return registerConfiguration(this.getConfig());
		
	}
	
	
	default void installEvents(@NonNull Listener ev, @NonNull IStartup<MAIN> plugin){
	
		Bukkit.getPluginManager().registerEvents(ev, plugin);
		
	}
	
	
}
