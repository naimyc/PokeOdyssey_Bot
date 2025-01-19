package com.commands;

import com.interfaces.Interfaces.Command;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public class Ping implements Command {

	@Override
	public void execute(SlashCommandInteractionEvent event) {

		event.getChannel().sendMessage("Pong!").queue();
	}
}
