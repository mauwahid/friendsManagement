package com.mauwahid.imd.friendsManagement.repository;

import com.mauwahid.imd.friendsManagement.entity.Person;
import com.mauwahid.imd.friendsManagement.repository.custom.CustomPersonRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface PersonRepository extends CrudRepository<Person,Long>,CustomPersonRepository {


    Set<Person> findByEmail(String email);

    Set<Person> findByEmailIn(Set<String> emails);


}
