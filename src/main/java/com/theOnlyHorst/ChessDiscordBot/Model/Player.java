package com.theOnlyHorst.ChessDiscordBot.Model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.time.LocalDate;

@Getter
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@EqualsAndHashCode(callSuper = true)
@Builder
public class Player extends AbstractPersistable {

    private String name;
    private Integer elo;
    @Temporal(TemporalType.DATE)
    private LocalDate lastUpdated;

}
