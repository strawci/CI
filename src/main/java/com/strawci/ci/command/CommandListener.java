package com.strawci.ci.command;

public abstract class CommandListener {
    public abstract void onHandle(CommandContext ctx);

    public Command getInfo() {
        return this.getClass().getAnnotation(Command.class);
    }
}
