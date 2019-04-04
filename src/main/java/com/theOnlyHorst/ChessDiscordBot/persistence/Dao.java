package com.theOnlyHorst.ChessDiscordBot.persistence;

import com.theOnlyHorst.ChessDiscordBot.Model.AbstractPersistable;

import java.util.List;

public interface Dao<P extends AbstractPersistable> {

    P findById(Long id);

    List<P> findAll();

    P save();

    P delete();




}
