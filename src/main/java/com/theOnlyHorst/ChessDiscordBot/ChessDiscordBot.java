package com.theOnlyHorst.ChessDiscordBot;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import javax.security.auth.login.LoginException;
import java.io.InputStreamReader;
import java.util.List;

public class ChessDiscordBot extends ListenerAdapter {

    public static JDA jda;
    public static Guild botHome;



    public static String apiKey;

    public static final String DEFAULT_PREFIX = "c!";

    public static void main(String[] args) throws LoginException {
        Gson gson = new Gson();
        List<String> botSecrets;

        botSecrets = gson.fromJson(new InputStreamReader(ChessDiscordBot.class.getClassLoader().getResourceAsStream("secrets.json")),new TypeToken<List<String>>(){}.getType());

        apiKey = botSecrets.get(0);

        jda = new JDABuilder(AccountType.BOT).setToken(apiKey).addEventListener(new ChessDiscordBot()).build();

    }

}
