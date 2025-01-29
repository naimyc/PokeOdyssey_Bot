package com.core;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.interactions.commands.OptionType;
// import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.requests.restaction.CommandListUpdateAction;
import net.dv8tion.jda.internal.utils.JDALogger;

import java.util.EnumSet;

import io.github.cdimascio.dotenv.Dotenv;

import static net.dv8tion.jda.api.interactions.commands.OptionType.*;

public class App {
	static Dotenv dotenv = Dotenv.load();
	static String token = dotenv.get("TOKEN");

	public static void main(String[] args) {
		JDALogger.setFallbackLoggerEnabled(false);
		JDA jda = JDABuilder.createLight(token, EnumSet.noneOf(GatewayIntent.class)) // slash commands don't need any
																						// intents
				.addEventListeners(new Listener()).build();

		// These commands might take a few minutes to be active after
		// creation/update/delete
		CommandListUpdateAction commands = jda.updateCommands();

		// Simple reply commands
		commands.addCommands(Commands.slash("ping", "Ping command"), Commands.slash("register", "Register command")
				.addOptions(new OptionData(STRING, "mc_tag", "Enter your mc_tag here").setRequired(true))
				.addOptions(new OptionData(OptionType.INTEGER, "mc_edition", "Enter the edition of your minecraft!")
						.addChoice("BEDROCK", 0).addChoice("JAVA", 1).setRequired(true)))
				.queue();

		// Send the new set of commands to discord, this will override any existing
		// global commands with the new set provided here
		commands.queue();
		System.out.println("Bot has been initialized correctly!");
	}
}