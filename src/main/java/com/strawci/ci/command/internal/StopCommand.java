package com.strawci.ci.command.internal;

import com.strawci.ci.command.Command;
import com.strawci.ci.command.CommandContext;
import com.strawci.ci.command.CommandListener;

@Command(
    name = "stop",
    permission = "strawci.stop"
)
public class StopCommand extends CommandListener {
    @Override
    public void onHandle(CommandContext ctx) {
        ctx.getCI().stop();
    }
}
