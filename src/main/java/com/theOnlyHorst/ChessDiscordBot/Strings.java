package com.theOnlyHorst.ChessDiscordBot;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;
import lombok.Getter;

import java.io.InputStreamReader;

@Getter
public class Strings {

    @SerializedName("helpText")
    private String helpText;

    @SerializedName("challengeText")
    private String challengeText;


    public Strings()
    {
    }

    private static Strings strings;
    public static void loadStrings(Gson gson)
    {
        strings = gson.fromJson(new InputStreamReader(ChessDiscordBot.class.getClassLoader().getResourceAsStream("strings.json")),new TypeToken<Strings>(){}.getType());
    }

    public static Strings getStrings()
    {
        return strings;
    }





}
