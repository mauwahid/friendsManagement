package com.mauwahid.imd.friendsManagement.repository;

import com.mauwahid.imd.friendsManagement.entity.Person;
import com.mauwahid.imd.friendsManagement.entity.PersonFriendship;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PersonFriendshipRepository extends CrudRepository<PersonFriendship,Long> {

    List<PersonFriendship> findByPersonRequestor(Person person);

    List<PersonFriendship> findByPersonAcceptor(Person person);

}
