package com.nhom11.webseller.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;

import com.nhom11.webseller.model.Manufacturer;

public interface ManufacturerService {

	Manufacturer getById(Long id);

	void deleteAll();

	void delete(Manufacturer entity);

	void deleteById(Long id);

	<S extends Manufacturer> boolean exists(Example<S> example);

	long count();

	boolean existsById(Long id);

	Optional<Manufacturer> findById(Long id);

	<S extends Manufacturer> S save(S entity);

	List<Manufacturer> findAll();

	

	List<Manufacturer> findByNameContaining(String name);


}
