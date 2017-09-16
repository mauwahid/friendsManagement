package com.mauwahid.imd.friendsManagement.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
public class PersonStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn
    private Person person;
    private String personStatus;

    @OneToMany
    @JoinTable(name = "mentioned",
            joinColumns = {
                    @JoinColumn(name="id_status",referencedColumnName = "id",nullable = false)
            },
            inverseJoinColumns = {
                    @JoinColumn(name="id_mentioned",nullable = false)
            })
    private Set<Person> mentionedPeople;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public String getPersonStatus() {
        return personStatus;
    }

    public void setPersonStatus(String personStatus) {
        this.personStatus = personStatus;
    }

    public Set<Person> getMentionedPeople() {
        return mentionedPeople;
    }

    public void setMentionedPeople(Set<Person> mentionedPeople) {
        this.mentionedPeople = mentionedPeople;
    }
}
