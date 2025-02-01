package com.entities;

import java.time.LocalDateTime;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.Event;

public class Alliance {
	private int allianceID, memberCount;
	private String allianceName, discordID, ownerID, ownerUsername, newsChannel, botChannel;
	private LocalDateTime allianceDate;

	/**
	 * @param guild
	 */
	public Alliance(Guild guild) {

		Member owner = guild.retrieveOwner().complete();

		this.allianceID = -1;
		this.allianceName = guild.getName();
		this.discordID = guild.getId();
		this.memberCount = guild.getMemberCount();

		this.ownerID = owner.getId();
		this.ownerUsername = owner.getEffectiveName();

		this.newsChannel = guild.getDefaultChannel().getId();
		this.botChannel = null;

		this.allianceDate = LocalDateTime.now();
	}

	/**
	 * @param alliance_id
	 */
	public Alliance(String guildID) {
		this.discordID = guildID;
		
		
	}

	public TextChannel getNewsChannel(Event event) {
		return event.getJDA().getTextChannelById(allianceID);
	}

}
