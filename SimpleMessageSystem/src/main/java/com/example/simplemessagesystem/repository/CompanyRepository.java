package com.example.simplemessagesystem.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.simplemessagesystem.model.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Integer>{

	Optional<Company> findByName(String name);

}
