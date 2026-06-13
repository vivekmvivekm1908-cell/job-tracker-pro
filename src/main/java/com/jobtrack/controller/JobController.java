package com.jobtrack.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.jobtrack.entity.Job;
import com.jobtrack.security.JwtUtil;
import com.jobtrack.service.JobService;

@RestController
@RequestMapping("/api/jobs")
@CrossOrigin("*")
public class JobController {

    @Autowired
    private JobService service;

    @Autowired
    private JwtUtil jwtUtil;

    // Save Job
    @PostMapping
    public Job saveJob(@RequestBody Job job) {

        System.out.println("Saving Job: " + job.getCompany());

        return service.saveJob(job);
    }

    // Get All Jobs
    @GetMapping
    public List<Job> getAllJobs() {

        System.out.println("Loading All Jobs");

        return service.getAllJobs();
    }

    // Get Logged In User Jobs
    @GetMapping("/my-jobs")
    public List<Job> getMyJobs(
            @RequestHeader("Authorization")
            String authHeader) {

        String token = authHeader.substring(7);

        String email =
                jwtUtil.extractEmail(token);

        System.out.println(
                "Loading Jobs For User: "
                        + email);

        return service.getJobsByUserEmail(email);
    }

    // Get Job By Id
    @GetMapping("/{id}")
    public Job getJob(
            @PathVariable Long id) {

        return service.getJobById(id);
    }

    // Update Job
    @PutMapping("/{id}")
    public Job updateJob(
            @PathVariable Long id,
            @RequestBody Job job) {

        return service.updateJob(id, job);
    }

    // Delete Job
    @DeleteMapping("/{id}")
    public String deleteJob(
            @PathVariable Long id) {

        service.deleteJob(id);

        return "Job Deleted Successfully";
    }
}