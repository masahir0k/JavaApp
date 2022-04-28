package com.example.demo.repositries;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.InquiryForm;
import com.example.demo.models.InquiryForm2;

@Repository
public interface InquiryRepository2 extends JpaRepository<InquiryForm2, String>{
	Optional<InquiryForm2> findById(String id);
	List<InquiryForm2> findAll();
}