package com.mauwahid.imd.friendsManagement.service;

import com.mauwahid.imd.friendsManagement.entity.Person;
import com.mauwahid.imd.friendsManagement.entity.PersonStory;
import com.mauwahid.imd.friendsManagement.repository.PersonRepository;
import com.mauwahid.imd.friendsManagement.repository.PersonStoryRepository;
import com.mauwahid.imd.friendsManagement.utils.Common;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PersonStoryService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PersonStoryRepository personStoryRepository;

    public Set<String> postStatus(String emailSender, String text){

        Person person = personRepository.findByEmail(emailSender).stream().findFirst().orElse(null);
        PersonStory personStory = new PersonStory();
        personStory.setPerson(person);
        personStory.setText(text);
        personStoryRepository.save(personStory);

        Set<String> emailsMentioned = Common.getEmailsFromText(text);
        Set<Person> memberMentioned = personRepository.findByEmailIn(emailsMentioned);

        Set<String> memberEmails = memberMentioned.stream().map(data->
                data.getEmail()
        ).collect(Collectors.toSet());

        emailsMentioned.removeAll(memberEmails);
        emailsMentioned.forEach(email ->
        {
            final Person nonMemberToSave = new Person();
            personRepository.save(person);
        });

        Set<Person> friends = personRepository.getAllFriend(person);
        Set<Person> blocked;
        Set<Person> subscribers;

        try{
            blocked = person.getBlockedPeople();
        }catch (NullPointerException ex){
            blocked = new HashSet<>();
        }

        try{
            subscribers = person.getSubscribersPeople();
        }catch (NullPointerException ex){
            subscribers = new HashSet<>();
        }



        Set<Person> receiverPeople = memberMentioned;
        receiverPeople.addAll(subscribers);
        receiverPeople.addAll(friends);
        receiverPeople.removeAll(blocked);

        Set<String> finalRecepients = receiverPeople.stream().map(data -> data.getEmail()
        ).collect(Collectors.toSet());
        finalRecepients.addAll(emailsMentioned);

        return finalRecepients;

    }

}
