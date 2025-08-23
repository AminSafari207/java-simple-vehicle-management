package com.app.model;

import jakarta.persistence.*;

@MappedSuperclass
public class BaseEntity<ID> {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BaseEntityGenerator")
    @SequenceGenerator(name = "BaseEntityGenerator", sequenceName = "base-seq", allocationSize = 0)
    private ID id;

    public ID getId() {
        return id;
    }

    public void setId(ID id) {
        this.id = id;
    }
}
