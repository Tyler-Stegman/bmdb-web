package com.bmdb.db;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bmdb.business.Actor;

public interface ActorRepository extends JpaRepository<Actor, Integer> {
	
	List<Actor> findAllByBirthDateBefore(LocalDate birthDate);
}
