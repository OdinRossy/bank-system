package com.odinrossy.banksystem.models

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class Role {

    @Id
    @GeneratedValue
    private Long id

    private String value

    Role(String value) {
        this.value = value
    }

    Long getId() {
        return id
    }

    void setId(Long id) {
        this.id = id
    }

    String getValue() {
        return value
    }

    void setValue(String value) {
        this.value = value
    }
}
