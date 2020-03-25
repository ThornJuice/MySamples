package com.ju.designpatterns.observer.Rxjava;

import java.util.concurrent.Executor;

public class Scheduler {
    final Executor executor;

    public Scheduler(Executor executor) {
        this.executor = executor;
    }

    public Worker createWork() {
        return new Worker(executor);
    }

    public static class Worker {
        final Executor executor;

        public Worker(Executor executor) {
            this.executor = executor;
        }

        public void schedule(Runnable runnable) {
            executor.execute(runnable);
        }
    }
}
