package com.commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import java.awt.Color;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import org.json.JSONException;
import com.entities.MC_PokeMember;
import com.entities.PokeMember;
import com.exceptions.MC_ErrorLog;
import com.exceptions.MC_Exception;
import com.exceptions.XBoxTagException;
import com.exceptions.XboxException;
import com.interfaces.Interfaces.Command;
import com.interfaces.Interfaces.MC_ExceptionMessages;

public class Register implements Command, MC_ExceptionMessages {
	TextChannel log_channel;

	// https://api.mojang.com/users/profiles/minecraft/BatmanPro77281 

	// **Image API
	// https://crafthead.net/avatar/268720cf258940b0b63b5166c91ea799
	@Override
	public void execute(SlashCommandInteractionEvent event) {

		User user = event.getUser();

		String tag = event.getOption("mc_tag").getAsString();
		int mc_edition = event.getOption("mc_edition").getAsInt();
		Role pokeRole = event.getJDA().getRoleById("1335300084086214758");

		String discordName = user.getName();
		String discordId = user.getId();

		try {

			// Create new user in the db
			EmbedBuilder eb = new EmbedBuilder();
			boolean userExists = new PokeMember().userExists(user);

			new MC_PokeMember(user, mc_edition, tag);

			String statusStr = "### 🛠️ __Estado__:\n### || Procesando usuario...||";

			if (!userExists)
				eb.setDescription("```" + discordName.toUpperCase()
						+ " \"'s profile has added to the database successfully!" + tag + "!```\n" + statusStr);
			else
				eb.setDescription(
						"```" + discordName.toUpperCase() + "'s profile has been changed successfully!```" + statusStr);

			eb.setThumbnail(user.getAvatarUrl()).setColor(new Color(005511));

			event.replyEmbeds(eb.build()).queue();
			event.getGuild().addRoleToMember(user, pokeRole).queue();

		} catch (XboxException e) {

			String errorLog = String.format("ERROR %d\n\nUser: %s\nUser_id: %s\nMC_Edition: %d\nTag: %s",
					e.getResponseCode(), discordName, discordId, mc_edition, tag);

			log_channel = event.getJDA().getTextChannelById(logChannel);
			log_channel.sendMessage(errorLog).queue();

			new MC_ErrorLog(xBoxError, e);
			event.reply(xBoxError).queue();
		} catch (XBoxTagException e) {

			String errorLog = String.format("ERROR %d\n\nUser: %s\nUser_id: %s\nMC_Edition: %d\nTag: %s",
					e.getResponseCode(), discordName, discordId, mc_edition, tag);

			log_channel = event.getJDA().getTextChannelById(logChannel);
			log_channel.sendMessage(errorLog).queue();
			new MC_ErrorLog(xBoxError, e);
			event.reply(xBoxError).queue();
		} catch (MC_Exception e) {

			event.reply(unexpectedError).queue();
			new MC_ErrorLog(unexpectedError, e);

			e.printStackTrace();
		} catch (JSONException e) {

			event.reply(unexpectedError).queue();
			new MC_ErrorLog(unexpectedError, e);

			e.printStackTrace();
		} catch (IOException e) {

			event.reply(unexpectedError).queue();
			new MC_ErrorLog(unexpectedError, e);

			e.printStackTrace();
		} catch (SQLIntegrityConstraintViolationException e) {

			event.reply(userExistsError).queue();
			new MC_ErrorLog(userExistsError, e);

			e.printStackTrace();
		} catch (SQLException e) {

			event.reply(sqlError).queue();
			new MC_ErrorLog(sqlError, e);

			e.printStackTrace();
		}
	}
}