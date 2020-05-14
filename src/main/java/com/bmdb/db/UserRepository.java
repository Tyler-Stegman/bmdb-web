package com.bmdb.db;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.bmdb.business.User;

public interface UserRepository extends CrudRepository<User, Integer>{
	
	Optional<User> findByUserNameAndPassword(String uname, String pwd);

}
