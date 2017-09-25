package com.mauwahid.imd.friendsManagement.entity;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    private String email;

    @OneToMany(mappedBy = "person")
    private Set<PersonStory> personStories;

    @ManyToMany(cascade={CascadeType.ALL},fetch = FetchType.EAGER)
    @JoinTable(name = "blocked_people",
    joinColumns = {
            @JoinColumn(name="id_person")
    },
    inverseJoinColumns = {
            @JoinColumn(name="id_blocked_person")
    })
    private Set<Person> blockedPeople;

    @ManyToMany(mappedBy = "blockedPeople",fetch = FetchType.EAGER)
    private Set<Person> blockerPeople;


    @OneToMany(mappedBy = "personRequestor")
    private Set<PersonFriendship> personRequestor;

    @OneToMany(mappedBy = "personAcceptor")
    private Set<PersonFriendship> personAcceptor;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "subscriptions",
            joinColumns = {
                    @JoinColumn(name="id_person")
            },
            inverseJoinColumns = {
                    @JoinColumn(name="id_subscriber")
            })
    private Set<Person> subscribedPeople;

    @ManyToMany(mappedBy = "subscribedPeople",fetch = FetchType.EAGER)
    private Set<Person> subscribersPeople;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date joinDate;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Set<Person> getSubscribersPeople() {
        return subscribersPeople;
    }

    public void setSubscribersPeople(Set<Person> subscribersPeople) {
        this.subscribersPeople = subscribersPeople;
    }

    public Set<Person> getBlockerPeople() {
        return blockerPeople;
    }

    public void setBlockerPeople(Set<Person> blockerPeople) {
        this.blockerPeople = blockerPeople;
    }

    public Set<Person> getSubscribedPeople() {
        return subscribedPeople;
    }

    public void setSubscribedPeople(Set<Person> subscribedPeople) {
        this.subscribedPeople = subscribedPeople;
    }

    public Date getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
    }

    public Set<PersonStory> getPersonStories() {
        return personStories;
    }

    public void setPersonStories(Set<PersonStory> personStories) {
        this.personStories = personStories;
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
