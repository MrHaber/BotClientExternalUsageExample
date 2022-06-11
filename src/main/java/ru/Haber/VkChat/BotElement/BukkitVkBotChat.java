package ru.Haber.VkChat.BotElement;

import org.bukkit.Bukkit;

import org.jetbrains.annotations.NotNull;

import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.GroupActor;
import com.vk.api.sdk.objects.messages.Message;

import ru.Haber.VkAPI.Annotations.AsyncInit;
import ru.Haber.VkAPI.bootstrap.BotClient.IBotUtils;
import ru.Haber.VkAPI.bootstrap.BotClient.VkCommandExecutor;
import ru.Haber.VkChat.EventElements.VkChatEvent;

@AsyncInit(asyncId = 1)
public class BukkitVkBotChat implements VkCommandExecutor{

	public BukkitVkBotChat() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCommand(@NotNull IBotUtils bot, @NotNull VkApiClient client, @NotNull GroupActor actor,
			@NotNull Message message, @NotNull String[] args) {
		
		if(!bot.isConversation()) {
			return;
		}
		Bukkit.getPluginManager().callEvent(new VkChatEvent(message, client, actor));
	}



}
