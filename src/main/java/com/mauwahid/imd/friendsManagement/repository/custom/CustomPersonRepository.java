package com.mauwahid.imd.friendsManagement.repository.custom;

import com.mauwahid.imd.friendsManagement.entity.Person;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Set;

public interface CustomPersonRepository {


    Set<Person> getAllFriend(Person person);

    Set<Person> findMutualFriend(Person person1, Person person2);

 }
