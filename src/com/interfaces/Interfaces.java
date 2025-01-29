package com.interfaces;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class Interfaces {

    public interface Command {
    	String logChannel = "1333933562948354122";

		public void execute(SlashCommandInteractionEvent event);
    }

}
