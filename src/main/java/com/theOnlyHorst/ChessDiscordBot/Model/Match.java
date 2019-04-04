package com.theOnlyHorst.ChessDiscordBot.Model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@EqualsAndHashCode(callSuper = true)
@Builder
public class Match extends AbstractPersistable {

    @ManyToOne(cascade = CascadeType.PERSIST,optional = false)
    @NonNull
    private Player white;
    @ManyToOne
    @NonNull
    private Player black;
    @Enumerated(EnumType.STRING)
    @NonNull
    private MatchStatus status;
    @Enumerated(EnumType.STRING)
    private MatchResult result;
    @Enumerated(EnumType.STRING)
    private EndReason ResultReason;
    private Integer currentMove;
    @OneToMany
    private List<Move> moves;






    public void makeMove(String move)
    {
        Move currentMov=null;
        for (Move m: moves)
        {
            if(m.getMoveNum()==currentMove)
            {
                currentMov = m;
                break;
            }
        }
        if(currentMov==null)
        {
            throw new RuntimeException("the Match state is corrupted");
        }
        switch (currentMov.getToMove())
        {
            case WHITE:
                
        }
    }

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
