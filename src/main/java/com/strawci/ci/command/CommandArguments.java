package com.strawci.ci.command;

import java.util.ArrayList;
import java.util.List;

import com.strawci.ci.StrawCI;
import com.strawci.ci.jobs.Job;
import com.strawci.ci.users.User;

public class CommandArguments {
    private StrawCI ci;
    private List<String> rawArguments;
    private List<Object> arguments;

    public CommandArguments(StrawCI ci, List<String> rawArguments) {
        this.ci = ci;
        this.rawArguments = rawArguments;
        this.arguments = new ArrayList<>();
    }

    private Object parse(Argument type, String raw) {
        try {
            switch(type) {
                case BOOL:
                    return Boolean.parseBoolean(raw);
                case INT:
                    return Integer.parseInt(raw);
                case JOB:
                    return this.ci.getJobManager().getJob(raw);
                case USER:
                    return this.ci.getUsersManager().getUser(raw);
                case STRING:
                default:
                    return raw;
            }
        } catch (Exception e) {
            return null;
        }
    }

    public void parse(Argument[] types) {
        int argIndex = -1;
        
        for (Argument type : types) {
            argIndex++;

            if (argIndex < rawArguments.size()) {
                String raw = rawArguments.get(argIndex);
                Object arg = this.parse(type, raw);
                this.arguments.add(arg);
            }
        }
    }

    public boolean hasIndex(int index) {
        return index < arguments.size() && index >= 0;
    }

    public Object get(int index) {
        if (this.hasIndex(index)) {
            return this.arguments.get(index);
        } else {
            return null;
        }
    }

    public String getString(int index) {
        Object value = this.get(index);
        if (value != null && value instanceof String) {
            return (String) value;
        } else {
            return null;
        }
    }

    public int getInt(int index) {
        Object value = this.get(index);
        if (value != null && value instanceof Integer) {
            return (Integer) value;
        } else {
            return 0;
        }
    }

    public boolean getBoolean(int index) {
        Object value = this.get(index);
        if (value != null && value instanceof Boolean) {
            return (Boolean) value;
        } else {
            return false;
        }
    }

    public Job getJob(int index) {
        Object value = this.get(index);
        if (value != null && value instanceof Job) {
            return (Job) value;
        } else {
            return null;
        }
    }

    public User getUser(int index) {
        Object value = this.get(index);
        if (value != null && value instanceof User) {
            return (User) value;
        } else {
            return null;
        }
    }
}
