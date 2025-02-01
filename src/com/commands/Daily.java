package com.commands;

import java.sql.SQLException;

import org.json.JSONException;
import org.json.JSONObject;

import com.entities.PokeMember;
import com.exceptions.MC_ErrorLog;
import com.exceptions.PokeMemberNotExists;
import com.interfaces.Interfaces.Command;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public class Daily implements Command {

	@Override
	public void execute(SlashCommandInteractionEvent event) {
		// TODO Auto-generated method stub
		try {
			
			PokeMember user = new PokeMember(event.getUser());
			JSONObject userdata = user.getUser(event.getUser()).getJSONObject(0);

			double balance = userdata.getDouble("balance") +100;

			event.reply("You have earned 100 Coins!\nCurrent balance: " + balance).queue();

		} catch (PokeMemberNotExists e) {

			event.reply("U have to register to be able to execute this command!").queue();
			new MC_ErrorLog(userNotExists, e);
			e.printStackTrace();
		} catch (JSONException e) {

			event.reply("Retry the command!").queue();
			new MC_ErrorLog(unexpectedError, e);
			e.printStackTrace();
		} catch (SQLException e) {

			event.reply(sqlError).queue();
			new MC_ErrorLog(sqlError, e);
			e.printStackTrace();
		}
	}

}
