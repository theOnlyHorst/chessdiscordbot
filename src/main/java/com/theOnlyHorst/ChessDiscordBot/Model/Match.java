package com.theOnlyHorst.ChessDiscordBot.Model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
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
    private Integer wMoveTime;
    private Integer bMoveTime;
    private Integer wMatchTime;
    private Integer bMatchTime;


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
                currentMov.setWhiteMove(move);
                currentMov.setToMove(Move.PlayerToMove.BLACK);
                break;
            case BLACK:
                currentMov.setBlackMove(move);
                currentMov.setToMove(Move.PlayerToMove.NONE);
                this.currentMove++;
                moves.add(Move.builder().toMove(Move.PlayerToMove.WHITE).match(this).moveNum(currentMove).build());
                break;
        }
    }

    public enum MatchStatus
    {
        PENDING,
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
        REPETITION,
        FIFTYMOVE
    }
    public enum MatchResult
    {

        WHITEWINS("1-0"),
        BLACKWINS("0-1"),
        DRAW("1/2-1/2");

        @Getter
        private String shortForm;
        MatchResult(String shortForm) {
            this.shortForm = shortForm;
        }



    }
}
