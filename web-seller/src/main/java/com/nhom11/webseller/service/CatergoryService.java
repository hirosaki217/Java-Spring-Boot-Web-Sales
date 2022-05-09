package com.nhom11.webseller.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.nhom11.webseller.model.Catergory;
import com.nhom11.webseller.model.Product;

public interface CatergoryService {

	

	Catergory getById(Long id);

	void deleteAll();

	void deleteAll(Iterable<? extends Catergory> entities);

	void deleteAllInBatch();

	void deleteAllById(Iterable<? extends Long> ids);

	void delete(Catergory entity);

	void deleteAllByIdInBatch(Iterable<Long> ids);

	void deleteById(Long id);

	long count();

	void deleteAllInBatch(Iterable<Catergory> entities);

	void deleteInBatch(Iterable<Catergory> entities);

	<S extends Catergory> List<S> saveAllAndFlush(Iterable<S> entities);

	boolean existsById(Long id);

	<S extends Catergory> S saveAndFlush(S entity);

	void flush();

	<S extends Catergory> List<S> saveAll(Iterable<S> entities);

	Optional<Catergory> findById(Long id);

	List<Catergory> findAllById(Iterable<Long> ids);

	List<Catergory> findAll(Sort sort);

	Page<Catergory> findAll(Pageable pageable);

	<S extends Catergory> S save(S entity);

	List<Catergory> findAll();

}
