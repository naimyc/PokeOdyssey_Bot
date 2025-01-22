package com.users;

import net.dv8tion.jda.api.entities.User;

public class MC_User {
    public enum edition
    {
        JAVA, BEDROCK
    }
    
    private int user_id, xuuid;
    private edition mc_edition;
    double balance;
    private String name, surname, discordName, discordId, mcpe_name, mc_name, email;
    boolean isBanned, isMuted, isRegistered;

    // Constructor for existing MC_Users
    public MC_User(int user_id)
    {
        this.user_id = user_id;
    }

    // Constructor for new MC_Users
    public MC_User(User user, String mc_name, edition mc_edition)
    {

        this.mc_name = mc_name;
        this.discordId = user.getId();
        this.discordName = user.getName();
        this.mc_edition = mc_edition;

        balance = 0.0;
        isBanned = false;
        isRegistered = false;
    }

    public edition getMc_edition() {
        return mc_edition;
    }

    public void setMc_edition(edition mc_edition) {
        this.mc_edition = mc_edition;
    }

    public String getDiscordId() {
        return discordId;
    }

    public void setDiscordId(String discordId) {
        this.discordId = discordId;
    }

    public int getXuuid() {
        return xuuid;
    }

    public void setXuuid(int xuuid) {
        this.xuuid = xuuid;
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

    public boolean isRegistered() {
        return isRegistered;
    }

    public void setRegistered(boolean registered) {
        isRegistered = registered;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public boolean isBanned() {
        return isBanned;
    }

    public void setBanned(boolean banned) {
        isBanned = banned;
    }

    public boolean isMuted() {
        return isMuted;
    }

    public void setMuted(boolean muted) {
        isMuted = muted;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
}
