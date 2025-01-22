package com.commands;

import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

import com.interfaces.Interfaces.Command;
import com.users.MC_User;;

public class Register implements Command
{
	@Override
    public void execute(SlashCommandInteractionEvent event)
    {
           // MessageChannel channel = event.getChannel();
            String mc_tag = event.getOption("mc_tag").getAsString();
            User user = event.getUser();
            
            MC_User mc_user = new MC_User(user, mc_tag, MC_User.edition.JAVA);
            System.out.println("User " + mc_user.getDiscordName() + " has been registered with the tag " + mc_user.getMc_name());
            
            
            event.reply(user.getName() + " has been registered with the tag: " + mc_user.getMc_name()).queue();;
    }
}