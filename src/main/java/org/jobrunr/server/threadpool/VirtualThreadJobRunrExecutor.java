package org.jobrunr.server.threadpool;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VirtualThreadJobRunrExecutor implements JobRunrExecutor {

    private static final Logger LOGGER = LoggerFactory.getLogger(VirtualThreadJobRunrExecutor.class);

    @Override
    public int getPriority() {
        return 5;
    }

    @Override
    public void start() {
        LOGGER.info("JobRunrExecutor of type 'VirtualThreadJobRunrExecutor' started");
    }

    @Override
    public void stop() {
        // nothing to do
    }

    @Override
    public void execute(Runnable runnable) {
        Thread.startVirtualThread(runnable);
    }
}
