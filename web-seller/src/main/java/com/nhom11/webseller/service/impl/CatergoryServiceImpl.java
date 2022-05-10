package com.nhom11.webseller.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nhom11.webseller.model.Catergory;
import com.nhom11.webseller.repository.CatergoryRepository;
import com.nhom11.webseller.service.CatergoryService;

@Service
@Transactional
public class CatergoryServiceImpl implements CatergoryService{
	@Autowired
	private CatergoryRepository catergoryRepository;


	@Override
	public List<Catergory> findAll() {
		return catergoryRepository.findAll();
	}


	@Override
	public <S extends Catergory> S save(S entity) {
		return catergoryRepository.save(entity);
	}

	@Override
	public Page<Catergory> findAll(Pageable pageable) {
		return catergoryRepository.findAll(pageable);
	}

	@Override
	public List<Catergory> findAll(Sort sort) {
		return catergoryRepository.findAll(sort);
	}

	@Override
	public List<Catergory> findAllById(Iterable<Long> ids) {
		return catergoryRepository.findAllById(ids);
	}

	@Override
	public Optional<Catergory> findById(Long id) {
		return catergoryRepository.findById(id);
	}

	@Override
	public <S extends Catergory> List<S> saveAll(Iterable<S> entities) {
		return catergoryRepository.saveAll(entities);
	}

	@Override
	public void flush() {
		catergoryRepository.flush();
	}

	@Override
	public <S extends Catergory> S saveAndFlush(S entity) {
		return catergoryRepository.saveAndFlush(entity);
	}

	@Override
	public boolean existsById(Long id) {
		return catergoryRepository.existsById(id);
	}

	@Override
	public <S extends Catergory> List<S> saveAllAndFlush(Iterable<S> entities) {
		return catergoryRepository.saveAllAndFlush(entities);
	}

	@Override
	public void deleteInBatch(Iterable<Catergory> entities) {
		catergoryRepository.deleteInBatch(entities);
	}

	@Override
	public void deleteAllInBatch(Iterable<Catergory> entities) {
		catergoryRepository.deleteAllInBatch(entities);
	}

	@Override
	public long count() {
		return catergoryRepository.count();
	}

	@Override
	public void deleteById(Long id) {
		catergoryRepository.deleteById(id);
	}

	@Override
	public void deleteAllByIdInBatch(Iterable<Long> ids) {
		catergoryRepository.deleteAllByIdInBatch(ids);
	}

	@Override
	public void delete(Catergory entity) {
		catergoryRepository.delete(entity);
	}

	@Override
	public void deleteAllById(Iterable<? extends Long> ids) {
		catergoryRepository.deleteAllById(ids);
	}

	@Override
	public void deleteAllInBatch() {
		catergoryRepository.deleteAllInBatch();
	}

	@Override
	public void deleteAll(Iterable<? extends Catergory> entities) {
		catergoryRepository.deleteAll(entities);
	}

	@Override
	public void deleteAll() {
		catergoryRepository.deleteAll();
	}

	@Override
	public Catergory getById(Long id) {
		return catergoryRepository.getById(id);
	}

	@Override
	public List<Catergory> findByNameContaining(String name) {
		name = name.trim();
		return catergoryRepository.findByNameContaining(name);
	}


	
}
