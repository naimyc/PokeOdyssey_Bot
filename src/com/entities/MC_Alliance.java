package com.entities;

import net.dv8tion.jda.api.entities.Guild;

public class MC_Alliance extends Alliance {
	private String server_ip, server_port;
	
	public MC_Alliance(Guild guild) {
		super(guild);
		// TODO Auto-generated constructor stub
	}

	public MC_Alliance(String discord_id) {
		super(discord_id);
		// TODO Auto-generated constructor stub
	}

}
