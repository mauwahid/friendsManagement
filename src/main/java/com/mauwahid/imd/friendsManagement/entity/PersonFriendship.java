package com.mauwahid.imd.friendsManagement.entity;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
public class PersonFriendship {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name="id_person_requestor")
    private Person personRequestor;

    @ManyToOne
    @JoinColumn(name="id_person_acceptor")
    private Person personAcceptor;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date friendShipDate;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Person getPersonRequestor() {
        return personRequestor;
    }

    public void setPersonRequestor(Person personRequestor) {
        this.personRequestor = personRequestor;
    }

    public Person getPersonAcceptor() {
        return personAcceptor;
    }

    public void setPersonAcceptor(Person personAcceptor) {
        this.personAcceptor = personAcceptor;
    }

    public Date getFriendShipDate() {
        return friendShipDate;
    }

    public void setFriendShipDate(Date friendShipDate) {
        this.friendShipDate = friendShipDate;
    }
}
