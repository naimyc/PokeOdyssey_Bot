package com.interfaces;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class Interfaces {

	public interface Command extends MC_ExceptionMessages{
		String logChannel = "1333933562948354122";

		public void execute(SlashCommandInteractionEvent event);
	}

	public interface MC_ExceptionMessages {
		String unexpectedError = "Unexpected Error: Please try later again :(";
		String userNotExists = "Please register first to execute the command!";
		String userExistsError = "SQL Error: U are already in the Whitelist! ";
		String sqlError = "SQL Error: Please try again! :(";
		String xBoxError = "XboxError Error: Please try to enter your mc_tag again! :(";
		String xboxTagNotFound = "Xbox Error: Minecraft Tag has not been found!";
	
	}

}
