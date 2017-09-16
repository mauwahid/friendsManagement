package com.mauwahid.imd.friendsManagement.service;

import com.mauwahid.imd.friendsManagement.entity.Person;
import com.mauwahid.imd.friendsManagement.entity.PersonFriendship;
import com.mauwahid.imd.friendsManagement.repository.PersonFriendshipRepository;
import com.mauwahid.imd.friendsManagement.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class FriendService {

    @Autowired
    PersonRepository personRepository;

    @Autowired
    PersonFriendshipRepository personFriendshipRepository;

    String email = "dadut@gmail.com";


    public void addFriend(){
        Person requester  = personRepository.findByEmail("test@gmail.com").stream().findAny().get();
        Person acceptor = personRepository.findByEmail(email).stream().findAny().get();

        if(requester==null){
            requester = new Person();
            requester.setEmail(email);
            personRepository.save(requester);

        }

        if(acceptor==null){
            acceptor = new Person();
            acceptor.setEmail(email);
            personRepository.save(acceptor);
        }

        PersonFriendship personFriendship = new PersonFriendship();
        personFriendship.setPersonRequestor(requester);
        personFriendship.setPersonAcceptor(acceptor);

        personFriendshipRepository.save(personFriendship);


    }


    public void getFriendList(String email){
        Person person = personRepository.findByEmail(email).stream().findFirst().get();

        personRepository.getAllFriend(person);
    }





}
