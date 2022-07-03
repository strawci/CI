package com.strawci.ci.jobs;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class JobManager {
    private File directory;
    private Map<String, Job> jobs;
    
    public JobManager(File directory) {
        this.directory = directory;
        this.jobs = new HashMap<>();

        if (!this.directory.exists()) {
            this.directory.mkdirs();
        }
    }

    public void addJob(Job job) {
        this.jobs.put(job.getID(), job);
    }

    public Job getJob(String id) {
        return this.jobs.get(id.toLowerCase());
    }

    public Job createJob(String id) {
        id = id.toLowerCase();

        if (this.getJob(id) != null) {
            return null;
        }

        File jobDirectory = new File(this.directory, id);
        if (jobDirectory.exists()) {
            return null;
        } else {
            jobDirectory.mkdirs();
        }

        Job job = new Job(directory);
        job.createData();
        return job;
    }

    public void loadJobs() {
        for (File subdirectory : this.directory.listFiles()) {
            if (subdirectory.isDirectory()) {
                Job job = new Job(subdirectory);
                job.loadData();
                this.addJob(job);
            }
        }
    }
}
