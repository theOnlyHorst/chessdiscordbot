package com.theOnlyHorst.ChessDiscordBot;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.theOnlyHorst.ChessDiscordBot.Model.Command;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.*;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import javax.security.auth.login.LoginException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ChessDiscordBot extends ListenerAdapter {

    public static JDA jda;
    public static Guild botHome;


    public static List<Command> commList;


    public static String apiKey;

    public static final String DEFAULT_PREFIX = "c!";

    public static void main(String[] args) throws LoginException {
        Gson gson = new Gson();
        List<String> botSecrets;

        botSecrets = gson.fromJson(new InputStreamReader(ChessDiscordBot.class.getClassLoader().getResourceAsStream("secrets.json")),new TypeToken<List<String>>(){}.getType());

        apiKey = botSecrets.get(0);
        ResourceLoader.loadStrings(gson);

        jda = new JDABuilder(AccountType.BOT).setToken(apiKey).addEventListener(new ChessDiscordBot()).build();

        commList = gson.fromJson(new InputStreamReader(ChessDiscordBot.class.getClassLoader().getResourceAsStream("commands.json")),new TypeToken<List<Command>>(){}.getType());

    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        super.onMessageReceived(event);
        String msg = event.getMessage().getContentRaw();
        Member mSent = event.getMember();
        User uSent = event.getAuthor();
        Guild server = event.getGuild();
        TextChannel cSent = event.getTextChannel();
        if(msg.startsWith(DEFAULT_PREFIX))
        {
            if(msg.startsWith(DEFAULT_PREFIX+"help"))
            {
                String commandsText ="";
                for (Command c : commList)
                {
                    commandsText += DEFAULT_PREFIX+c.getCommandName()+"\t\t" + c.getDescription()+"\n";
                }
                String commandsTextFinal = commandsText;
                uSent.openPrivateChannel().queue((privateChannel -> privateChannel.sendMessage(ResourceLoader.getStrings().get("helpText")+"\n"+commandsTextFinal).queue()));
            }

            if(msg.startsWith(DEFAULT_PREFIX+"match"))
            {
                ArrayList<String> args = new ArrayList<String>(Arrays.asList(msg.split(" ")));
                if(args.size()>=2) {
                    List <Member> foundM = server.getMembersByName(args.get(1),false);

                    if(foundM.size()==1)
                    {
                        User matchedU = foundM.get(0).getUser();

                        matchedU.openPrivateChannel().queue(chan->chan.sendMessage(String.format(ResourceLoader.getStrings().get("challengeText"),uSent.getName())).queue());
                        cSent.sendMessage("An Invitation has been sent").queue();
                    }
                }
            }
        }
    }
}
