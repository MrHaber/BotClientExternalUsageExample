package ru.Haber.VkChat.BotElement;

import org.checkerframework.common.reflection.qual.GetClass;

import ru.Haber.VkAPI.Annotations.BotConfigurableHandler;
import ru.Haber.VkAPI.Annotations.BotHandler;
import ru.Haber.VkAPI.bootstrap.BotClient;

@BotConfigurableHandler(path = "\\plugins\\ConfigPath",id = "id",token = "token")
public class BukkitBot extends BotClient{

	public BukkitBot() {

	}
	

	@Override
	public void onStart() {
		
		debug(true);
		
		systemLocale("ru");
		
		getInstaller().installHandler((VkEventObject)new BukkitVkBotChat());
		
	}

}
