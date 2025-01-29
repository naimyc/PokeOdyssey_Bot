package com.commands;

import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.Channel;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import okhttp3.Response;
import okhttp3.ResponseBody;

import org.json.JSONObject;

import com.http.HTTP;
import com.interfaces.Interfaces.Command;
import com.users.MC_User;

public class Register implements Command {
	HTTP http = new HTTP();

	@Override
	public void execute(SlashCommandInteractionEvent event) {
		try {
			
			// https://api.mojang.com/users/profiles/minecraft/
			// https://crafthead.net/avatar/268720cf258940b0b63b5166c91ea799
			
			User user = event.getUser();
			String tag = event.getOption("mc_tag").getAsString();

			int mc_edition = event.getOption("mc_edition").getAsInt();

			String discordName = user.getName();
			String discordId = user.getId();

			Response response = http.getResponse("https://api.mojang.com/users/profiles/minecraft/" + tag);
			ResponseBody responseBody = response.body();

			int responseCode = response.code();

			if (responseBody == null || responseCode == 404) {
				String errorLog = String.format(
						"EROR %d\n\nError while getting data of \n\nUser: %s\nUser_id: %s\nMC_Edition: %d\nTag: %s",
						responseCode, discordName, discordId, mc_edition, tag);

				TextChannel channel = event.getJDA().getTextChannelById(logChannel);
				channel.sendMessage(errorLog).queue();
				
				event.reply("Unexpected error: Please try later again :(").queue();
				return;
			}

			JSONObject jsonObject = new JSONObject(responseBody.string());
			String id = jsonObject.getString("id");

			

			MC_User mc_user = new MC_User(user, id, mc_edition, (mc_edition == MC_User.Mc_Edition.JAVA) ? tag : null,
					(mc_edition == MC_User.Mc_Edition.BEDROCK) ? tag : null);

			event.reply(user.getName().toUpperCase() + " has been registered with the tag: " + tag).queue();
		} catch (Exception e) {
			// TODO: handle exception
			event.reply("Unexpected Error: Please try later again :(").queue();
			System.out.println(e);
		}
	}
}