package com.strawci.ci;

import java.io.File;

import com.strawci.ci.command.CommandHandler;
import com.strawci.ci.command.internal.StopCommand;
import com.strawci.ci.jobs.JobManager;
import com.strawci.ci.logs.Logger;
import com.strawci.ci.users.UsersManager;

public class StrawCI {
    private JobManager jobManager;
    private UsersManager usersManager;

    private CommandHandler commandHandler;
    private EventLoop eventLoop;

    public StrawCI() {
        File current = new File(System.getProperty("user.dir"));

        this.jobManager = new JobManager(new File(current, "jobs"));
        this.usersManager = new UsersManager(new File(current, "users"));

        this.commandHandler = new CommandHandler(this);
        this.eventLoop = new EventLoop(20);
    }

    public void start() {
        long startTimestamp = System.currentTimeMillis();
        Logger.info("Starting CI platform...");

        Logger.info("Loading jobs from ./jobs directory.");
        this.jobManager.loadJobs();
        Logger.info("Loading users from ./users directory.");
        this.usersManager.loadUsers();

        Logger.info("Initializing command handler.");
        this.commandHandler.start();

        Logger.info("Registering internal commands.");
        this.commandHandler.registerCommand(new StopCommand());

        long endTimestamp = System.currentTimeMillis();
        long took = endTimestamp - startTimestamp;
        Logger.info("StrawCI is ready and running (took " + took + "ms)");
        this.eventLoop.lockThread();
    }

    public void stop(int exitCode) {
        Logger.info("Stopping CI platform...");
        this.commandHandler.stop();
        this.eventLoop.stop();
        Logger.info("Service is now shutdown, good bye.");
        System.exit(exitCode);
    }

    public void stop() {
        this.stop(0);
    }

    public CommandHandler getCommandHandler() {
        return this.commandHandler;
    }

    public EventLoop getEventLoop() {
        return this.eventLoop;
    }

    public JobManager getJobManager() {
        return this.jobManager;
    }

    public UsersManager getUsersManager() {
        return this.usersManager;
    }
}
