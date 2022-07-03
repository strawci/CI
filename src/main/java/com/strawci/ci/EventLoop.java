package com.strawci.ci;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class EventLoop {
    private List<Runnable> tasks;
    private boolean started;
    private int timeWaitBetweenTick;

    public EventLoop(int tickPerSecond) {
        this.tasks = new ArrayList<>();
        this.started = false;
        this.timeWaitBetweenTick = 1000 / tickPerSecond;
    }

    public void runTask(Runnable runnable) {
        this.tasks.add(runnable);
    }

    public void lockThread() {
        this.started = true;

        while(this.started) {
            Iterator<Runnable> taskIterator = this.tasks.iterator();
            while(taskIterator.hasNext()) {
                Runnable task = taskIterator.next();
                task.run();
                taskIterator.remove();
            }

            try {
                Thread.sleep(timeWaitBetweenTick);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void stop() {
        this.started = false;
    }
}
