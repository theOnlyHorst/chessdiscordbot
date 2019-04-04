package com.theOnlyHorst.ChessDiscordBot;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;
import lombok.Getter;

import java.io.InputStreamReader;
import java.util.HashMap;

public class ResourceLoader {

    public static HashMap<String,String> strings;

    private ResourceLoader()
    {}

    public static void loadStrings(Gson gson)
    {
        strings = gson.fromJson(new InputStreamReader(ChessDiscordBot.class.getClassLoader().getResourceAsStream("strings.json")),new TypeToken<HashMap<String,String>>(){}.getType());
    }

    public static HashMap<String,String> getStrings()
    {
        return strings;
    }





}
