package com.theOnlyHorst.ChessDiscordBot.Model;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Command {

    private String commandName;
    private String description;
    private String usage;

}
