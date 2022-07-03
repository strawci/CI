package com.strawci.ci.command;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.strawci.ci.StrawCI;

public abstract class CommandProcessor {
    private StrawCI ci;
    private boolean started;

    public CommandProcessor(StrawCI ci) {
        this.ci = ci;
        this.started = false;
    }

    public abstract void process(CommandContext ctx);

    private CommandContext createContext(String input) {
        String commandName = null;
        List<String> arguments = new ArrayList<>();

        for (String chunk : input.split(" ")) {
            if (commandName == null) {
                commandName = chunk;
            } else {
                arguments.add(chunk);
            }
        }

        CommandContext context = new CommandContext(ci, commandName, arguments);
        return context;
    }
    
    public void start() {
        this.started = true;

        Thread thread = new Thread(() -> {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            while(this.started) {    
                try {
                    String input = reader.readLine();
                    CommandContext context = this.createContext(input);
                    this.process(context);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        thread.setName("command-processor");
        thread.start();
    }

    public void stop() {
        this.started = false;
    }
}
