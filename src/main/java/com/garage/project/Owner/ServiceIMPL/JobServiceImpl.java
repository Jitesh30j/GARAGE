package com.garage.project.Owner.ServiceIMPL;

import com.garage.project.Owner.Dto.JobRequest;
import com.garage.project.Owner.Entity.Job;
import com.garage.project.Owner.Repo.JobRepository;
import com.garage.project.Owner.Service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class JobServiceImpl implements JobService {

    @Autowired
    private JobRepository repo;

    // CREATE
    @Override
    public Map<String, Object> createJob(JobRequest request) {

        Job job = new Job();

        job.setGarageId(request.getGarageId());
        job.setCustomerId(request.getCustomerId());
        job.setVehicleId(request.getVehicleId());
        job.setMechanicId(request.getMechanicId());
        job.setProblemDescription(request.getProblemDescription());

        repo.save(job);

        return success(job, "job created");
    }

    // GET ALL
    @Override
    public Map<String, Object> getAllJobs(JobRequest request) {

        List<Job> list = repo.findByGarageId(request.getGarageId());

        Map<String, Object> res = new HashMap<>();
        res.put("data", list);
        res.put("status", "success");
        return res;
    }

    // GET SINGLE
    @Override
    public Map<String, Object> getSingleJob(JobRequest request) {

        Optional<Job> opt =
                repo.findByJobIdAndGarageId(request.getJobId(), request.getGarageId());

        if (opt.isEmpty()) return error("job not found");

        return success(opt.get(), "found");
    }

    // UPDATE
    @Override
    public Map<String, Object> updateJob(JobRequest request) {

        Optional<Job> opt =
                repo.findByJobIdAndGarageId(request.getJobId(), request.getGarageId());

        if (opt.isEmpty()) return error("job not found");

        Job job = opt.get();

        job.setCustomerId(request.getCustomerId());
        job.setVehicleId(request.getVehicleId());
        job.setMechanicId(request.getMechanicId());
        job.setProblemDescription(request.getProblemDescription());
        job.setJobStatus(request.getJobStatus());

        repo.save(job);

        return success(job, "updated");
    }

    // DELETE
    @Override
    public Map<String, Object> deleteJob(JobRequest request) {

        Optional<Job> opt =
                repo.findByJobIdAndGarageId(request.getJobId(), request.getGarageId());

        if (opt.isEmpty()) return error("job not found");

        repo.delete(opt.get());

        return success(null, "deleted");
    }

    // HELPERS
    private Map<String, Object> success(Object data, String msg) {
        Map<String, Object> res = new HashMap<>();
        res.put("data", data);
        res.put("message", msg);
        res.put("status", "success");
        return res;
    }

    private Map<String, Object> error(String msg) {
        Map<String, Object> res = new HashMap<>();
        res.put("message", msg);
        res.put("status", "failed");
        return res;
    }
}
