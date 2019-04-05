package com.theOnlyHorst.ChessDiscordBot.Model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;

@Entity
@Getter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@Builder
public class Move extends AbstractPersistable {

    @Setter
    private String whiteMove;
    @Setter
    private String blackMove;
    private Integer moveNum;
    @ManyToOne
    private Match match;
    @Enumerated(EnumType.STRING)
    @Setter
    private PlayerToMove toMove;

    public enum PlayerToMove
    {
        WHITE,
        BLACK,
        NONE
    }

}
