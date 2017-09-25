package com.mauwahid.imd.friendsManagement.service;

import com.mauwahid.imd.friendsManagement.entity.Person;
import com.mauwahid.imd.friendsManagement.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    public Set<Person> getFriendList(String email){
        Person person = null;

        try{
            person = personRepository.findByEmail(email).stream().findFirst().get();
            return personRepository.getAllFriend(person);

        }catch (NoSuchElementException ex){
            return null;
        }
    }

    public void blockPerson(String requestorEmail, String targetEmail){
        Person requestor = personRepository.findByEmail(requestorEmail).stream().findFirst().orElse(null);
        Person target = personRepository.findByEmail(targetEmail).stream().findFirst().orElse(null);

        if(requestor==null){
            requestor = new Person();
            requestor.setEmail(requestorEmail);
            personRepository.save(requestor);
        }

        if(target==null){
            target = new Person();
            target.setEmail(targetEmail);
            personRepository.save(target);
        }

        if(requestor.getBlockedPeople()!=null){
            requestor.getBlockedPeople().add(target);
        }else{
            Set<Person> personSet = new HashSet<>();
            personSet.add(target);

            requestor.setBlockedPeople(personSet);
        }
        personRepository.save(requestor);
    }

    public void subscribe(String requestorEmail, String targetEmail){
        Person requestor = personRepository.findByEmail(requestorEmail).stream().findFirst().orElse(null);
        Person target = personRepository.findByEmail(targetEmail).stream().findFirst().orElse(null);

        if(requestor==null){
            requestor = new Person();
            requestor.setEmail(requestorEmail);
            personRepository.save(requestor);
        }

        if(target==null){
            target = new Person();
            target.setEmail(targetEmail);
            personRepository.save(target);
        }


        if(requestor.getSubscribersPeople()!=null){
            requestor.getSubscribersPeople().add(target);
        }else{
            Set<Person> personSet = new HashSet<>();
            personSet.add(target);

            requestor.setSubscribedPeople(personSet);
        }

        personRepository.save(requestor);
    }


    public boolean hasSubscribe(String requestorEmail, String targetEmail){
        Person requestor = personRepository.findByEmail(requestorEmail).stream().findFirst().orElse(null);
        Person target = personRepository.findByEmail(targetEmail).stream().findFirst().orElse(null);

        if(requestor==null|| target==null)
            return false;


        if(requestor.getSubscribersPeople()!=null && requestor.getSubscribersPeople().contains(target))
            return true;


        return false;
    }

    public boolean hasBlocked(String requestorEmail, String targetEmail){
        Person requestor = personRepository.findByEmail(requestorEmail).stream().findFirst().orElse(null);
        Person target = personRepository.findByEmail(targetEmail).stream().findFirst().orElse(null);

        if(requestor==null|| target==null){
            return false;
        }

        if(requestor.getBlockedPeople()!=null && requestor.getBlockedPeople().contains(target)){
            return true;
        };

        return false;
    }

}
