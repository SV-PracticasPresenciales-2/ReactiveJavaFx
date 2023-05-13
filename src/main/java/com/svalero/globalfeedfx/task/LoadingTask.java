package com.svalero.globalfeedfx.task;

import javafx.concurrent.Task;

public class LoadingTask extends Task<Integer> {
    private String username;

    public LoadingTask(String username){
        this.username = username;
    }
    @Override
    protected Integer call() throws Exception {
        return null;
    }
}
