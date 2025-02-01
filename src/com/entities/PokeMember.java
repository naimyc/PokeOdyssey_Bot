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

public class PokeMember {

	public interface MC_EDITION {
		int JAVA = 0, BEDROCK = 1, BOTH = 2, NONE = -1;
	}

	private static SQL sql = new SQL();
	private static HTTP http = new HTTP();

	private int userID, mcEdition;
	private double balance;
	private String xuuid, name, surname, discordName, discordID, mcpeName, mcName, email, password;
	private boolean isBanned, isMuted, isRegistered, isActive;

	public PokeMember() {
	}

	// Constructor for existing MC_Users
	public PokeMember(User user) throws PokeMemberNotExists, JSONException, SQLException {

		JSONArray userdata = getUser(user);
		if (userdata.length() < 1)
			throw new PokeMemberNotExists(user.getName() + " <" + user.getId() + "> is not in the whitelist!");

		JSONObject userObj = userdata.getJSONObject(0);

		this.userID = userObj.getInt("userID");
		this.discordID = userObj.getString("discordID");
		this.discordName = userObj.getString("discordName");

		this.balance = userObj.getDouble("balance");

		this.xuuid = userObj.getString("xuuid");
		this.mcEdition = userObj.getInt("mcEdition");

		this.mcName = userObj.getString("mcName");
		this.mcpeName = userObj.getString("mcpeName");

		this.name = userObj.getString("name");
		this.surname = userObj.getString("surname");

		this.email = userObj.getString("email");
		this.password = userObj.getString("password");

		this.isBanned = (userObj.getInt("isBanned") == 0) ? false : true;
		this.isMuted = (userObj.getInt("isMuted") == 0) ? false : true;
		this.isRegistered = (userObj.getInt("isRegistered") == 0) ? false : true;
		this.isActive = (userObj.getInt("isActive") == 0) ? false : true;

	}

	// Constructor for new MC_Users
	public PokeMember(User user, int mc_edition, String xuuid, String tag)
			throws PokeMemberExist, JSONException, MC_Exception, SQLException, IOException {

		String discordId = user.getId();
		String discordName = user.getName();
		boolean userExists = userExists(user);

		if (discordId == null)
			throw new MC_Exception("Discord ID must be given!");
		else if (discordName == null)
			throw new MC_Exception("Discord Name must be given!");
		else if (mc_edition == MC_EDITION.JAVA && tag == null)
			throw new MC_Exception("MC Tag must be given!");
		else if (mc_edition == MC_EDITION.BEDROCK && tag == null)
			throw new MC_Exception("MCPE Tag Name must be given!");

		// Mandatory Fields

		this.xuuid = xuuid;
		this.mcEdition = mc_edition;

		this.discordID = discordId;
		this.discordName = discordName;

		this.mcName = (mcEdition == MC_EDITION.JAVA) ? tag : null;
		this.mcpeName = (mcEdition == MC_EDITION.BEDROCK) ? tag : null;

		// Default Fields

		balance = 0.0;

		isBanned = false;
		isRegistered = false;
		isActive = false;

		StringBuffer insert_query = new StringBuffer(
				"INSERT INTO pokeMembers (userID, xuuid, mcEdition, discordName ");
		List<Object> data = new ArrayList<Object>();

		StringBuffer update_query = new StringBuffer(
				"UPDATE pokeMembers SET xuuid = ?, mcEdition = ?, discordName = ?  ");

		data.add(xuuid);
		data.add(mcEdition);
		data.add(discordName);

		if (mcEdition == MC_EDITION.JAVA) {
			insert_query.append(", mcName ");
			update_query.append(", mcName = ? ");
			data.add(mcName);
		} else if (mcEdition == MC_EDITION.BEDROCK) {
			insert_query.append(", mcpeName ");
			update_query.append(", mcpeName = ? ");
			data.add(mcpeName);
		}

		data.add(discordID);
		update_query.append("WHERE discordID = ?;");
		insert_query.append(", discordID ) SELECT IFNULL(MAX(userID), 0) + 1, ?, ?, ?, ?, ? FROM pokeMembers;");

		int start = insert_query.indexOf("?,"), end = insert_query.indexOf("?,") + 2;

		if (mcEdition == MC_EDITION.NONE && !userExists)
			insert_query.delete(start, end);
		
		System.out.println(update_query);
		System.out.println(insert_query);

		if (!userExists)
			insertPokeMember(insert_query.toString(), data.toArray());
		else
			updatePokeMember(update_query.toString(), data.toArray());
	}

	public static void insertPokeMember(String query, Object[] data) throws SQLException {

		sql.insertQuery(query.toString(), data);
	}

	public static void updatePokeMember(String query, Object[] data) throws SQLException {

		sql.insertQuery(query.toString(), data);
	}

	public static String getXuuidByTag(String tag) throws IOException, XboxException, XBoxTagException, JSONException {
		Response response = http.GET("https://api.mojang.com/users/profiles/minecraft/" + tag);
		ResponseBody responseBody = response.body();

		int responseCode = response.code();

		if (responseBody == null)
			throw new XboxException(responseCode);
		else if (responseCode == 404)
			throw new XBoxTagException(responseCode);

		JSONObject jsonObject = new JSONObject(responseBody.string());
		String xuuid = jsonObject.getString("id");

		return xuuid;
	}

	public JSONArray getUser(User user) throws JSONException, SQLException {
		Object[] data = { user.getId() };
		JSONArray userdata = sql.queryDatabase("SELECT * FROM pokeMembers WHERE discordID = ?", data)
				.getJSONArray("data");

		return userdata;
	}

	public boolean userExists(User user) throws JSONException, SQLException {

		JSONArray userdata = getUser(user);

		if (userdata.length() > 0)
			return true;
		else
			return false;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		String s = "{userID = %d, xuuid = %s, mc_edition= %d, discordId= %s, discordName=%s, mc_name= %s, mcpe_name= %s}";

		return String.format(s, userID, xuuid, mcEdition, discordID, discordName, mcName, mcpeName);
	}

}
