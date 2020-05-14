package com.bmdb.db;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bmdb.business.Credit;

public interface CreditRepository extends JpaRepository<Credit, Integer> {
	List<Credit> findAllByActorLastName(String lastName);
	
	List<Credit> findAllByActorFirstName(String firstName);
	
	List<Credit> findAllByActorId(int id);
	
	List<Credit> findAllByMovieId(int id);
}
