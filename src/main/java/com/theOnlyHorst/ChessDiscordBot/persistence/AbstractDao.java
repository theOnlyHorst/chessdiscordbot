package com.theOnlyHorst.ChessDiscordBot.persistence;

import com.theOnlyHorst.ChessDiscordBot.Model.AbstractPersistable;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityManager;
import java.util.List;

@RequiredArgsConstructor
public abstract class AbstractDao<P extends AbstractPersistable> implements Dao<P> {

    protected final EntityManager entityManager;

    public P findById(Long id) {
        return entityManager.find(getClassName(), id);
    }

    @Override
    public List<P> findAll() {
        return entityManager.createQuery(
                "select o from " + getClassName().getSimpleName() + " o",
                getClassName()
        ).getResultList();
    }

    public P save(P persistable) {
        entityManager.persist(persistable);
        return persistable;
    }

    public P delete(P persistable) {
        entityManager.remove(persistable);
        persistable.afterDelete();
        return persistable;
    }

    protected abstract Class<P> getClassName();
}
