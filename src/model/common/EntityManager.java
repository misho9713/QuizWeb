package model.common;

import java.util.List;

public interface EntityManager<E extends Entity, S extends EntitySeeker> {
    List<E> findEntities(S seeker);

    boolean addEntry(E entry);

    boolean remove(int id);
}
