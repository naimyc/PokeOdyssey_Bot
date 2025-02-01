package com.events;

import com.commands.Daily;
import com.commands.Ping;
import com.commands.Register;
import com.exceptions.MC_ErrorLog;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class CommandListener extends ListenerAdapter
{
    Register register = new Register();
    Ping ping = new Ping();
    Daily daily = new Daily();

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event)
    {
        // Only accept commands from guilds
        if (event.getGuild() == null)
            return;
        
        switch (event.getName())
        {
            case "ping":
                ping.execute(event);
                break;
            case "daily":
            	daily.execute(event);
            	break;
            case "register":
                register.execute(event);
                break;
            default:
                event.reply("I can't handle that command right now :(").setEphemeral(false).queue();
                new MC_ErrorLog("I can't handle that command right now :(", null);
                break;
        }
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event)
    {
        if (event.getAuthor().isBot()) return;
        // We don't want to respond to other bot accounts, including ourself
        Message message = event.getMessage();
        String content = message.getContentRaw();


        // getContentRaw() is an atomic getter
        // getContentDisplay() is a lazy getter which modifies the content for e.g. console view (strip discord formatting)
        if (content.equals("!ping"))
        {
            MessageChannel channel = event.getChannel();
            channel.sendMessage("Pong!").queue(); // Important to call .queue() on the RestAction returned by sendMessage(...)
        }
    }
}