package com.jobtrack.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jobtrack.entity.Job;
import com.jobtrack.repository.JobRepository;

@Service
public class JobService {

    @Autowired
    private JobRepository repository;

    // Save Job
    public Job saveJob(Job job) {

        return repository.save(job);
    }

    // Get All Jobs
    public List<Job> getAllJobs() {

        return repository.findAll();
    }

    // Get Jobs By User Email
    public List<Job> getJobsByUserEmail(String email) {

        return repository.findByUserEmail(email);
    }

    // Get Job By ID
    public Job getJobById(Long id) {

        return repository.findById(id).orElse(null);
    }

    // Update Job
    public Job updateJob(Long id, Job job) {

        Job existingJob =
                repository.findById(id).orElse(null);

        if (existingJob != null) {

            existingJob.setCompany(
                    job.getCompany());

            existingJob.setPosition(
                    job.getPosition());

            existingJob.setStatus(
                    job.getStatus());

            existingJob.setLocation(
                    job.getLocation());

            existingJob.setAppliedDate(
                    job.getAppliedDate());

            existingJob.setUserEmail(
                    job.getUserEmail());

            return repository.save(existingJob);
        }

        return null;
    }

    // Delete Job
    public void deleteJob(Long id) {

        repository.deleteById(id);
    }
}