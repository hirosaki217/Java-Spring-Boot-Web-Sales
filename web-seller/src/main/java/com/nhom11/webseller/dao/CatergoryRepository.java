package com.nhom11.webseller.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nhom11.webseller.model.Catergory;

@Repository
public interface CatergoryRepository extends JpaRepository<Catergory, Long>{

}
