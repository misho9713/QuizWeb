package model.common;

import java.util.Collection;

public interface EntityManager<E extends Entity, S extends EntitySeeker> {
    Collection<E> findEntities(S seeker);

    boolean addEntry(E entry);

    boolean remove(int id);
}
