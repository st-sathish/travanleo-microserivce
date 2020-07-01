package com.travanleo.auth.domain;

import com.travanleo.core.domain.AbstractPersistableCustom;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity(name = "Role")
@Table(name = "roles")
public class Role extends AbstractPersistableCustom implements Serializable {

    @Column(name = "name", nullable = false)
    private String name;

    public Role(){}

    public Role(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
