package com.mb.mvc.data.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Skill {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String label;
    private String description;

    public Skill(String label, String description) {
        super();
        this.label = label;
        this.description = description;
    }
}
