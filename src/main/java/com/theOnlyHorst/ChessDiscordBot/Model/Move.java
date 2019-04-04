package com.theOnlyHorst.ChessDiscordBot.Model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.swing.text.StyledEditorKit;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@EqualsAndHashCode(callSuper = true)
@Builder
public class Move extends AbstractPersistable {

    private String whiteMove;
    private String blackMove;
    private Integer moveNum;
    @ManyToOne
    private Match match;
    @Enumerated(EnumType.STRING)
    private PlayerToMove toMove;

    public enum PlayerToMove
    {
        WHITE,
        BLACK,
        NONE
    }

}
