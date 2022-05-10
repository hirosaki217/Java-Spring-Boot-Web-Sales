package com.nhom11.webseller.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.nhom11.webseller.model.Catergory;

@Repository
public interface CatergoryRepository extends JpaRepository<Catergory, Long>{
    @Query(value = "select * from catergories where name like '%'+:name+'%'" , nativeQuery = true)
	List<Catergory> findByNameContaining(@Param(value = "name") String name);
}
