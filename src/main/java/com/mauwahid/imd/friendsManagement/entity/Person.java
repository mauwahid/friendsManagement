package com.mauwahid.imd.friendsManagement.entity;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Set;

@Entity
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;

    @Column(unique = true)
    private String email;

    @OneToMany(mappedBy = "person")
    private Set<PersonStatus> personStatuses;

    @OneToMany
    @JoinTable(name = "blocked_people",
    joinColumns = {
            @JoinColumn(name="id_person",referencedColumnName = "id",nullable = false)
    },
    inverseJoinColumns = {
            @JoinColumn(name="id_blocked_person",referencedColumnName = "id",nullable = false)
    })
    private Set<Person> blockedPeople;

    @OneToMany(mappedBy = "personRequestor")
    private Set<PersonFriendship> personRequestor;

    @OneToMany(mappedBy = "personAcceptor")
    private Set<PersonFriendship> personAcceptor;

  /*  @OneToMany
    @JoinTable(name = "friendship",
            joinColumns = {
                    @JoinColumn(name="id_person_requester",referencedColumnName = "id",nullable = false)
            },
            inverseJoinColumns = {
                    @JoinColumn(name="id_person_acceptor",referencedColumnName = "id",nullable = false)
            })
    private Set<Person> requestedFriend;
*/


    @OneToMany
    @JoinTable(name = "subscriptions",
            joinColumns = {
                    @JoinColumn(name="id_person",referencedColumnName = "id",nullable = false)
            },
            inverseJoinColumns = {
                    @JoinColumn(name="id_subscriber",referencedColumnName = "id",nullable = false)
            })
    private Set<Person> subscribers;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date joinDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Person> getBlockedPeople() {
        return blockedPeople;
    }

    public void setBlockedPeople(Set<Person> blockedPeople) {
        this.blockedPeople = blockedPeople;
    }

    public Set<Person> getSubscribers() {
        return subscribers;
    }

    public void setSubscribers(Set<Person> subscribers) {
        this.subscribers = subscribers;
    }

    public Date getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
    }

    public Set<PersonStatus> getPersonStatuses() {
        return personStatuses;
    }

    public void setPersonStatuses(Set<PersonStatus> personStatuses) {
        this.personStatuses = personStatuses;
    }

    public Set<PersonFriendship> getPersonRequestor() {
        return personRequestor;
    }

    public void setPersonRequestor(Set<PersonFriendship> personRequestor) {
        this.personRequestor = personRequestor;
    }

    public Set<PersonFriendship> getPersonAcceptor() {
        return personAcceptor;
    }

    public void setPersonAcceptor(Set<PersonFriendship> personAcceptor) {
        this.personAcceptor = personAcceptor;
    }
}
