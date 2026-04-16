package com.garage.project.Owner.Service;

import com.garage.project.Owner.Dto.JobRequest;

import java.util.Map;

public interface JobService {

    Map<String, Object> createJob(JobRequest request);

    Map<String, Object> getAllJobs(JobRequest request);

    Map<String, Object> getSingleJob(JobRequest request);

    Map<String, Object> updateJob(JobRequest request);

    Map<String, Object> deleteJob(JobRequest request);
}
