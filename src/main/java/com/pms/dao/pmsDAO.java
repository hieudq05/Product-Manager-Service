package com.pms.dao;

import java.util.List;

public abstract class pmsDAO<entityType, keyType> {
    public abstract void insert(entityType entity);
    public abstract void update(entityType entity);
    public abstract void delete(keyType id);
    public abstract List<entityType> selectAll();
    public abstract entityType selectByID(keyType id);
}
