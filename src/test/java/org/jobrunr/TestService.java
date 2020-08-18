package org.jobrunr;

import java.util.concurrent.TimeUnit;

public class TestService {

    public void doWorkThatTakesLong(int seconds) throws InterruptedException {
        try {
            TimeUnit.SECONDS.sleep(seconds);
            System.out.println("WORK IS DONE!!!!!!!!");
        } catch (InterruptedException e) {
            System.out.println("Thread has been interrupted");
            throw e;
        }
    }

}
