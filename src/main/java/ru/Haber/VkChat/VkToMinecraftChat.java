package ru.Haber.VkChat;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import lombok.NonNull;
import lombok.val;
import ru.Haber.VkAPI.bootstrap.BotClient;
import ru.Haber.VkChat.BotElement.BukkitBot;
import ru.Haber.VkChat.EventElements.VkChatEvent;

public class VkToMinecraftChat extends JavaPlugin implements IStartup<VkToMinecraftChat>{

	@NotNull static VkToMinecraftChat bootstrap;
	
	public VkToMinecraftChat() {
		registerInstance();
	}
	
	private static final BotClient client = BotClient.newRemoteExec(BukkitBot::new, false);
	
	@Override
	public void onEnable() {
		
		saveAndDetectDefaultConfig();
		
		this.useConfig();
		
		this.getCommand("vk").setExecutor(new VkCommand());
		
		this.installEvents(new VkListener(), this);
	}

	@Override
	public @NonNull IStartup<VkToMinecraftChat> registerInstance() {
		
		return bootstrap = this;
	}
	
	private class VkCommand implements CommandExecutor{

		@Override
		public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
			
			if(args.length <= 0) {return true;};
			val sb = new StringBuilder();
	          for (int i = 0; i < args.length; ++i) {
	              sb.append(args[i]).append(" ");
	          }
			BukkitBot.sendMessageTo("Игрок: " + sender.getName() + " написал сообщение в группу: " + sb.toString().trim(), 2000000003);
			
			return false;
		}
		
	}
	
	private class VkListener implements Listener {
		
		@EventHandler
		public void onChat(VkChatEvent event) {
			
			Bukkit.broadcastMessage("VK: " + event.getMessage().getText());
			
		}
		
	}

}
