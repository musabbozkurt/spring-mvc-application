package com.mb.mvc.data.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Developer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String firstName;
    private String lastName;
    private String email;

    @ManyToMany
    private List<Skill> skills;

    public Developer(String firstName, String lastName, String email, List<Skill> skills) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.skills = skills;
    }

    public boolean hasSkill(Skill skill) {
        for (Skill containedSkill : getSkills()) {
            if (containedSkill.getId() == skill.getId()) {
                return true;
            }
        }
        return false;
    }

}
