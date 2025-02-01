package com.entities;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.exceptions.MC_Exception;
import com.exceptions.PokeMemberExist;
import com.exceptions.PokeMemberNotExists;
import com.exceptions.XboxException;
import com.exceptions.XBoxTagException;
import com.http.HTTP;
import com.sql.SQL;

import net.dv8tion.jda.api.entities.User;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class MC_PokeMember extends PokeMember {
	// private SQL sql = new SQL();
	// private HTTP http = new HTTP();

	private int userID, mcEdition;
	private double balance;
	private String xuuid, name, surname, discordName, discordID, mcpeName, mcName, email, password;
	private boolean isBanned, isMuted, isRegistered, isActive;

	// Constructor for existing MC_Users
	public MC_PokeMember(User user) throws PokeMemberNotExists, JSONException, SQLException {
		super(user);
	}

	// Constructor for new MC_Users
	public MC_PokeMember(User user, int mc_edition, String tag)
			throws JSONException, MC_Exception, SQLException, IOException {
		super(user, mc_edition, (mc_edition == MC_EDITION.NONE) ? null : getXuuidByTag(tag), tag);
	}
}
