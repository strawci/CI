package com.strawci.ci.command;

import java.util.List;

import com.strawci.ci.StrawCI;

public class CommandContext {
    private StrawCI ci;
    private String commandName;
    private CommandArguments arguments;
    
    public CommandContext(StrawCI ci, String commandName, List<String> rawCommands) {
        this.ci = ci;
        this.commandName = commandName;
        this.arguments = new CommandArguments(ci, rawCommands);
    }

    public void parseArguments(Argument[] types) {
        this.arguments.parse(types);
    }

    public StrawCI getCI() {
        return this.ci;
    }

    public String getCommandName() {
        return this.commandName;
    }

    public CommandArguments getArguments() {
        return this.arguments;
    }
}
