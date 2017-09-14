package com.mauwahid.imd.friendsManagement.repository;

import com.mauwahid.imd.friendsManagement.entity.Person;
import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<Person,Long> {
}
