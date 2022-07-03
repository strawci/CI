package com.strawci.ci.command;

import java.util.HashMap;
import java.util.Map;

import com.strawci.ci.StrawCI;
import com.strawci.ci.logs.Logger;

public class CommandHandler {
    private Map<String, CommandListener> commands;
    private CommandProcessor processor;

    public CommandHandler(StrawCI ci) {
        this.commands = new HashMap<>();
        this.processor = new CommandProcessor(ci) {
            @Override
            public void process(CommandContext ctx) {
                ci.getEventLoop().runTask(() -> {
                    CommandListener command = commands.get(ctx.getCommandName());
                    if (command != null) {
                        Command info = command.getInfo();
                        ctx.parseArguments(info.arguments());
                        command.onHandle(ctx);
                    } else {
                        onUnknownCommand(ctx.getCommandName());
                    }
                });
            }
        };
    }

    private void onUnknownCommand(String command) {
        Logger.critical("Unknown command " + command);
    }

    public void registerCommand(CommandListener listener) {
        Command info = listener.getInfo();
        this.commands.put(info.name(), listener);
    }

    public void start() {
        this.processor.start();
    }

    public void stop() {
        this.processor.stop();
    }
}
