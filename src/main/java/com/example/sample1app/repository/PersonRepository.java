package com.example.sample1app.repository;

import java.util.Optional;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.sample1app.entity.PersonEntity;

@Repository
public interface PersonRepository extends JpaRepository<PersonEntity,Long>{

	public Optional<PersonEntity> findById(long name);
	public List<PersonEntity> findByNameLike(String name);
	public List<PersonEntity> findByIdNotNullOrderByIdDesc();
	public List<PersonEntity> findByAgeGreaterThan(Integer age);
	public List<PersonEntity> findByAgeBetween(Integer age1, Integer age2);
	
	@Query("SELECT d FROM PersonEntity d ORDER BY d.name")
	List<PersonEntity> findAllOrderByName();
	
	@Query("from PersonEntity where age > :min and age < :max")
	public List<PersonEntity> findByAge(@Param("min")int min, @Param("max")int max);
}
