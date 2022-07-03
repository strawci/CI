package com.strawci.ci.jobs;

import java.io.File;

import com.google.gson.Gson;
import com.strawci.ci.utils.FileUtils;

public class Job {
    private File directory;
    private Gson gson;

    private JobData data;

    public Job(File directory) {
        this.directory = directory;
        this.gson = new Gson();
    }

    public JobData getData() {
        return this.data;
    }

    public File getDataFile() {
        return new File(this.directory, "job.json");
    }

    public String getID() {
        return this.directory.getName();
    }

    public void saveData() {
        if (!this.directory.exists()) {
            this.directory.mkdirs();
        }

        File file = this.getDataFile();
        String raw = this.gson.toJson(this.data);

        try {
            FileUtils.writeFile(file, raw);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createData() {
        this.data = JobData.create(this.getID());
        this.saveData();
    }

    public void loadData() {
        String raw = FileUtils.readFile(this.getDataFile());
        this.data = this.gson.fromJson(raw, JobData.class);
    }
}
