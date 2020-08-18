package org.jobrunr;

import org.jobrunr.configuration.JobRunr;
import org.jobrunr.jobs.states.StateName;
import org.jobrunr.scheduling.BackgroundJob;
import org.jobrunr.server.BackgroundJobServer;
import org.jobrunr.storage.InMemoryStorageProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.awaitility.Awaitility.await;
import static org.jobrunr.server.BackgroundJobServerConfiguration.usingStandardConfiguration;

public class BackgroundJobTest {

    private TestService testService;
    private InMemoryStorageProvider storageProvider;
    private BackgroundJobServer backgroundJobServer;

    @BeforeEach
    void setUpTestService() {
        testService = new TestService();
        storageProvider = new InMemoryStorageProvider();
        backgroundJobServer = new BackgroundJobServer(storageProvider, null, usingStandardConfiguration().andPollIntervalInSeconds(5));
        JobRunr.configure()
                .useStorageProvider(storageProvider)
                .useBackgroundJobServer(backgroundJobServer)
                .initialize();
        backgroundJobServer.start();
    }

    @Test
    public void testWithLoom() {
        BackgroundJob.enqueue(getWorkStream(5), (uuid) -> testService.doWorkThatTakesLong(5));

        await()
                .until(() -> storageProvider.countJobs(StateName.SUCCEEDED) == 5);
    }

    private Stream<UUID> getWorkStream(int amount) {
        return IntStream.range(0, amount)
                .mapToObj(i -> UUID.randomUUID());
    }
}
