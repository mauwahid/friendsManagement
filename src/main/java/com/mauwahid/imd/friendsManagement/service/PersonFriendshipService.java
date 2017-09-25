package com.mauwahid.imd.friendsManagement.service;

import com.mauwahid.imd.friendsManagement.entity.Person;
import com.mauwahid.imd.friendsManagement.entity.PersonFriendship;
import com.mauwahid.imd.friendsManagement.repository.PersonFriendshipRepository;
import com.mauwahid.imd.friendsManagement.repository.PersonRepository;
import com.mauwahid.imd.friendsManagement.repository.PersonStoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PersonFriendshipService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PersonFriendshipRepository personFriendshipRepository;

    @Autowired
    private PersonStoryRepository personStoryRepository;

    Logger logger = LoggerFactory.getLogger(PersonFriendshipService.class);


    public void addFriendRelation(String email1, String email2){
        Person person = personRepository.findByEmail(email1).stream().findFirst().orElse(null);
        Person person2 = personRepository.findByEmail(email2).stream().findFirst().orElse(null);

        if(person==null){
            person = new Person();
            person.setEmail(email1);
            personRepository.save(person);
        }

        if(person2==null){
            person2 = new Person();
            person2.setEmail(email2);
            personRepository.save(person2);
        }

        PersonFriendship personFriendship = new PersonFriendship();
        personFriendship.setPersonRequestor(person);
        personFriendship.setPersonAcceptor(person2);

        personFriendshipRepository.save(personFriendship);

    }

    public boolean isFriendship(String email1,String email2){

        Person person = personRepository.findByEmail(email1).stream().findFirst().orElse(null);
        Person person2 = personRepository.findByEmail(email2).stream().findFirst().orElse(null);

        if(person==null || person2==null){
            return false;
        }

        List<PersonFriendship> personFriendships = personFriendshipRepository.findByPersonRequestorAndPersonAcceptor(person,person2);

        if(personFriendships!=null && personFriendships.size()!=0){
           return  true;
        }


        List<PersonFriendship> personFriendships2 = personFriendshipRepository.findByPersonRequestorAndPersonAcceptor(person2,person);

        if(personFriendships2!=null && personFriendships2.size()!=0){
            return true;
        }


        return false;
    }

    public Set<String> getCommonFriends(String email1,String email2){
        Person person = personRepository.findByEmail(email1).stream().findFirst().orElse(null);
        Person person2 = personRepository.findByEmail(email2).stream().findFirst().orElse(null);

        if(person == null || person2 == null){
            return null;
        }
        return personRepository.findMutualFriend(person,person2).stream().map(
                data -> data.getEmail()
        ).collect(Collectors.toSet());


    }



}
