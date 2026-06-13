package com.jobtrack.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jobtrack.entity.Job;

public interface JobRepository extends JpaRepository<Job, Long> {

    List<Job> findByUserEmail(String userEmail);

}