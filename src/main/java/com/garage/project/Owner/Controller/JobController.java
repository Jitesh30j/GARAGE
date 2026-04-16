package com.garage.project.Owner.Controller;

import com.garage.project.Owner.Dto.JobRequest;
import com.garage.project.Owner.Service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/job")
public class JobController {

    @Autowired
    private JobService service;

    // CREATE
    @PostMapping("/create")
    public Map<String, Object> create(@RequestBody JobRequest request) {
        return service.createJob(request);
    }

    // GET ALL
    @PostMapping("/all")
    public Map<String, Object> all(@RequestBody JobRequest request) {
        return service.getAllJobs(request);
    }

    // GET SINGLE
    @PostMapping("/single")
    public Map<String, Object> single(@RequestBody JobRequest request) {
        return service.getSingleJob(request);
    }

    // UPDATE
    @PostMapping("/update")
    public Map<String, Object> update(@RequestBody JobRequest request) {
        return service.updateJob(request);
    }

    // DELETE
    @PostMapping("/delete")
    public Map<String, Object> delete(@RequestBody JobRequest request) {
        return service.deleteJob(request);
    }
}
