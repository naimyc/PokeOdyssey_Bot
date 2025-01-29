package com.users;

import com.exceptions.MCDiscordExceptions;
import net.dv8tion.jda.api.entities.User;

public class MC_User {

	public interface Mc_Edition {
		int JAVA = 0, BEDROCK = 1, BOTH = 2, NONE = -1;
	}

	private int user_id;
	private int edition;
	private double balance;

	private String xuuid, name, surname, discordName, discordId, mcpe_name, mc_name, email, password;
	
	private boolean isBanned, isMuted, isRegistered, isActive;

	// Constructor for existing MC_Users
	public MC_User(int user_id) {
		this.user_id = user_id;
	}

	// Constructor for new MC_Users
	public MC_User(User user, String xuuid, int mc_edition, String mc_tag, String mcpe_tag) throws MCDiscordExceptions {

		String discordId = user.getId();
		String discordName = user.getName();
		
		System.out.println(mc_tag);
		System.out.println(mcpe_tag);
		
		System.out.println(mc_edition);

		if (discordId == null)
			throw new MCDiscordExceptions("User_id must be given!");
		else if (xuuid == null)
			throw new MCDiscordExceptions("Xuuid must must be given!");
		else if (discordName == null)
			throw new MCDiscordExceptions("Discord Name must be given!");
		else if (mc_edition == Mc_Edition.NONE)
			throw new MCDiscordExceptions("MC Edition must be given!");
		else if (mc_edition == Mc_Edition.BEDROCK && mcpe_tag == null)
			throw new MCDiscordExceptions("MCPE_Tag Name must be given!");
		else if (mc_edition == Mc_Edition.JAVA && mc_tag == null)
			throw new MCDiscordExceptions("MC_Tag must be given!");

		// Mandatory Fields

		this.xuuid = xuuid;
		this.edition = mc_edition;

		this.discordId = discordId;
		this.discordName = discordName;

		this.mc_name = mc_tag;
		this.mcpe_name = mcpe_tag;

		// Default Fields

		balance = 0.0;

		isBanned = false;
		isRegistered = false;
		isActive = false;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getXuuid() {
		return xuuid;
	}

	public void setXuuid(String xuuid) {
		this.xuuid = xuuid;
	}

	public int getEdition() {
		return edition;
	}

	public void setEdition(int edition) {
		this.edition = edition;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getDiscordName() {
		return discordName;
	}

	public void setDiscordName(String discordName) {
		this.discordName = discordName;
	}

	public String getDiscordId() {
		return discordId;
	}

	public void setDiscordId(String discordId) {
		this.discordId = discordId;
	}

	public String getMcpe_name() {
		return mcpe_name;
	}

	public void setMcpe_name(String mcpe_name) {
		this.mcpe_name = mcpe_name;
	}

	public String getMc_name() {
		return mc_name;
	}

	public void setMc_name(String mc_name) {
		this.mc_name = mc_name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isBanned() {
		return isBanned;
	}

	public void setBanned(boolean isBanned) {
		this.isBanned = isBanned;
	}

	public boolean isMuted() {
		return isMuted;
	}

	public void setMuted(boolean isMuted) {
		this.isMuted = isMuted;
	}

	public boolean isRegistered() {
		return isRegistered;
	}

	public void setRegistered(boolean isRegistered) {
		this.isRegistered = isRegistered;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

}
