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




}
