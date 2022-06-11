package ru.Haber.api.Configuration;

import org.bukkit.configuration.file.FileConfiguration;
import org.jetbrains.annotations.NotNull;


public interface ConfigurationHandler {
	
	default Configurable newConfigurable() {
		return new Configurable();
	}
	
	default Configurable useConfig() {
			return Configurable.configImpl(this);
		
	}
	default FileConfiguration registerConfiguration(@NotNull FileConfiguration config) {
		Configurable.setupConfig(config);
		return config;
	}

}
