package com.strawci.ci.users;

import java.io.File;

public class UsersManager {
    private File directory;
    
    public UsersManager(File directory) {
        this.directory = directory;

        if (!this.directory.exists()) {
            this.directory.mkdirs();
        }
    }

    public User getUser(String username) {
        return null;
    }

    public void loadUsers() {
    }
}
