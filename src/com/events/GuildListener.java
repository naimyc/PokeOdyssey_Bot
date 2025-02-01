package com.events;

import com.entities.Alliance;

import net.dv8tion.jda.api.events.guild.GuildJoinEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class GuildListener extends ListenerAdapter {

	@Override
	public void onGuildJoin(GuildJoinEvent event) {

		// https://discord.com/oauth2/authorize?client_id=1325605543603081277&scope=bot&permissions=139788028992
		String guildName = event.getGuild().getName();
		String guildId = event.getGuild().getId();
		int memberCount = event.getGuild().getMemberCount();

		Alliance alliance = new Alliance(event.getGuild());

		System.out.println("Bot added to a new server: " + guildName + " (ID: " + guildId + ")");
		System.out.println("Member count: " + memberCount);

		alliance.getNewsChannel(event).sendMessage("Hello! Thanks for adding me to the server!")
				.queue();
		super.onGuildJoin(event);
	}

}
