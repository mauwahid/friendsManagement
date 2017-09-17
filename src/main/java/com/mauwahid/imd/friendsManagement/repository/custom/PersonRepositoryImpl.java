package com.mauwahid.imd.friendsManagement.repository.custom;

import com.mauwahid.imd.friendsManagement.entity.Person;
import com.mauwahid.imd.friendsManagement.repository.PersonFriendshipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
public class PersonRepositoryImpl implements CustomPersonRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    PersonFriendshipRepository personFriendshipRepository;

    @Override
    public Set<Person> getAllFriend(Person person) {
        Set<Person> friendList = new HashSet<>();

        personFriendshipRepository.findByPersonAcceptor(person).stream().forEach(i ->
        {
            friendList.add(i.getPersonRequestor());
        });

        personFriendshipRepository.findByPersonRequestor(person).stream().forEach(i->
        {
            friendList.add((i.getPersonAcceptor()));
        });


        return friendList;
    }

    @Override
    public Set<Person> findMutualFriend(Person person1, Person person2) {
        Set<Person> common = new HashSet<>();

        Set<Person> person1Friends =  getAllFriend(person1).stream().filter(
                data -> data.getId()!= person2.getId()
        ).collect(Collectors.toSet());

        Set<Person> person2Friends = getAllFriend(person2).stream().filter(
                data -> data.getId()!= person1.getId()
        ).collect(Collectors.toSet());

        common.addAll(person1Friends);
        common.retainAll(person2Friends);

        return common;
    }






}
