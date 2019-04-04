package com.theOnlyHorst.ChessDiscordBot.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Match {

    private Long id;
    private Player white;
    private Player black;
    private MatchStatus status;
    private MatchResult result;
    private EndReason ResultReason;
    private Integer turn;



    public enum MatchStatus
    {
        CHALLENGED,
        PLAYING,
        FINISHED,
        DENIED,
        CANCELED
    }
    public enum EndReason
    {
        RESIGNATION,
        CHECKMATE,
        TIMEOUT,
        STALEMATE,
        DRAWAGREED,
        NOMATERIAL,
        REPETITION
    }
    public enum MatchResult
    {

        WHITEWINS("1-0"),
        BLACKWINS("0-1"),
        DRAW("1/2-1/2");

        private String shortForm;
        MatchResult(String shortForm)
        {
            this.shortForm = shortForm;
        }
        public String getShortForm()
        {
            return shortForm;
        }
    }
}
