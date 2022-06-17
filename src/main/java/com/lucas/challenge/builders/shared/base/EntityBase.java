package com.lucas.challenge.builders.shared.base;

import java.io.Serializable;

public abstract class EntityBase<T extends Serializable> implements Serializable {
    private static final long serialVersionUID = -48502165504787035L;

    public abstract T getId();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EntityBase)) return false;
        EntityBase<?> that = (EntityBase<?>) o;
        return getId() != null ? getId().equals(that.getId()) : that.getId() == null;
    }

    @Override
    public int hashCode() {
        return getId() != null ? getId().hashCode() : 0;
    }

    @Override
    public String toString() {
        String entity = this.getClass().getSimpleName();
        return "Entity [ " + entity + " ] {" + "id=" + getId() + '}';
    }
}
