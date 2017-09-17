package com.mauwahid.imd.friendsManagement.service;

import com.mauwahid.imd.friendsManagement.entity.Person;
import com.mauwahid.imd.friendsManagement.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class PersonService {

    @Autowired
    PersonRepository personRepository;

    public Set<Person> getFriendList(String email){
        Person person = personRepository.findByEmail(email).stream().findFirst().get();
        return personRepository.getAllFriend(person);
    }

    public void blockPerson(String requestorEmail, String targetEmail){
        Person person = personRepository.findByEmail(requestorEmail).stream().findFirst().orElse(null);
        Person person2 = personRepository.findByEmail(targetEmail).stream().findFirst().orElse(null);

        if(person==null){
            person = new Person();
            person.setEmail(requestorEmail);
            personRepository.save(person);
        }

        if(person2==null){
            person2 = new Person();
            person2.setEmail(targetEmail);
            personRepository.save(person2);
        }

        if(person.getBlockedPeople()!=null){
            person.getBlockedPeople().add(person2);
        }else{
            Set<Person> personSet = new HashSet<>();
            personSet.add(person2);

            person.setBlockedPeople(personSet);
        }
        personRepository.save(person);
    }

    public void subscribe(String requestorEmail, String targetEmail){
        Person person = personRepository.findByEmail(requestorEmail).stream().findFirst().orElse(null);
        Person person2 = personRepository.findByEmail(targetEmail).stream().findFirst().orElse(null);

        if(person==null){
            person = new Person();
            person.setEmail(requestorEmail);
            personRepository.save(person);
        }

        if(person2==null){
            person2 = new Person();
            person2.setEmail(targetEmail);
            personRepository.save(person2);
        }

        person2.getSubscribersPeople().add(person);
        personRepository.save(person);
    }


}
