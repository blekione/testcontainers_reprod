package org.testcontainers.repro;

import java.time.Duration;

import org.junit.jupiter.api.Test;
import org.testcontainers.containers.BindMode;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
public class ReproExampleTest {

    @Container
	static final private MongoDBContainer mongoDb  = new MongoDBContainer("mongo:5.0")
		.withFileSystemBind("init/mongo-init.js", 
			"/docker-entrypoint-initdb.d/mongo-init.js", 
			BindMode.READ_ONLY)
		// tried also with 
		// .waitingFor(Wait.forListeningPort()
		.waitingFor(Wait.forLogMessage("Start_script_now", 1))
		.withStartupTimeout(Duration.ofSeconds(10));
	;
	

    /**
     * Placeholder for a piece of code that demonstrates the bug. You can use this as a starting point, or replace
     * entirely.
     * <p>
     * Ideally this would be a failing test. If it's excessively difficult to form as a test (e.g. relates to log
     * output, teardown or other side effects) then it would be sufficient to explain the behaviour in the issue
     * description.
     */
    @Test
    public void demonstration() {

		System.out.println("Port!!!!!!!!!!!!!!!!!!!!!!!!!!!!!: " + mongoDb.getReplicaSetUrl());

    }
}
