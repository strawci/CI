package com.strawci.ci.jobs;

public class JobData {
    private String displayName;
    private String description;
    private String gitRepository;
    private int nextBuildNumber;

    public String getDisplayName() {
        return this.displayName;
    }

    public String getDescription() {
        return this.description;
    }

    public String getGitRepository() {
        return this.gitRepository;
    }

    public int getNextBuildNumber() {
        return this.nextBuildNumber;
    }

    public JobData setDisplayName(String displayName) {
        this.displayName = displayName;
        return this;
    }

    public JobData setDescription(String description) {
        this.description = description;
        return this;
    }

    public JobData setGitRepository(String gitRepository) {
        this.gitRepository = gitRepository;
        return this;
    }

    public JobData setNextBuildNumber(int nextBuildNumber) {
        this.nextBuildNumber = nextBuildNumber;
        return this;
    }

    public static JobData create(String id) {
        return new JobData()
            .setDisplayName(id)
            .setDescription("")
            .setGitRepository("")
            .setNextBuildNumber(1);
    }
}
