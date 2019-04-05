package com.theOnlyHorst.ChessDiscordBot.ChessPlayer;

import com.theOnlyHorst.ChessDiscordBot.Model.Match;
import lombok.AllArgsConstructor;
import lombok.Getter;
import net.dv8tion.jda.core.entities.User;

@Getter
@AllArgsConstructor
public class MatchThread implements Runnable {

    private Match playedMatch;
    private User uWhite;
    private User uBlack;
    private String boardFEN;

    @Override
    public void run() {

    }
}
