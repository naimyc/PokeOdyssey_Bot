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

import com.events.CommandListener;
import com.events.GuildListener;

import io.github.cdimascio.dotenv.Dotenv;

import static net.dv8tion.jda.api.interactions.commands.OptionType.*;

public class App {
	static Dotenv dotenv = Dotenv.load();
	static String token = dotenv.get("TOKEN");
	// https://discord.com/oauth2/authorize?client_id=YOUR_CLIENT_ID&scope=bot+applications.commands&permissions=2056

	public static void main(String[] args) {
		JDALogger.setFallbackLoggerEnabled(false);
		JDA jda = JDABuilder.createDefault(token, EnumSet.allOf(GatewayIntent.class)) // Enables all gateway intents
		        .addEventListeners(
		        		new CommandListener(),
		        		new GuildListener())
		        .build();


		// These commands might take a few minutes to be active after
		// creation/update/delete
		CommandListUpdateAction commands = jda.updateCommands();

		// Simple reply commands
		commands.addCommands(Commands.slash("ping", "Ping command"), 
				Commands.slash("register", "Register command")
				.addOptions(
						new OptionData(OptionType.INTEGER, "mc_edition", "Enter the edition of your minecraft!")
						.addChoice("JAVA", 0)
						.addChoice("BEDROCK", 1)
						.addChoice("NONE", -1)
						.setRequired(true))
				.addOptions(
						new OptionData(STRING, "mc_tag", "Enter your mc_tag here")
						.setRequired(true)),
				Commands.slash("daily", "Earn 100 daily coins :D!")
				)
				.queue();

		// Send the new set of commands to discord, this will override any existing
		// global commands with the new set provided here
		commands.queue();
		System.out.println("Bot has been initialized correctly!");
	}
}