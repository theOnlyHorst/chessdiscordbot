package com.theOnlyHorst.ChessDiscordBot.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Player {

    private Long id;
    private String name;
    private Integer elo;
    private LocalDate lastUpdated;

}
