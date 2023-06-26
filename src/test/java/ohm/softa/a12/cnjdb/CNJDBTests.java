package ohm.softa.a12.cnjdb;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.concurrent.ExecutionException;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Peter Kurfer
 */

public class CNJDBTests {
	private static final Logger logger = Logger.getLogger(CNJDBTests.class.getName());
	private static final int REQUEST_COUNT = 100;

	private CNJDBApi cnjdbApi;

	public CNJDBTests() {
		this.cnjdbApi = CNJDBService.getInstance();
	}

	@Test
	void testCollision() throws ExecutionException, InterruptedException {
		var jokeNumbers = new HashSet<>();
		var requests = 0;
		var collision = false;

		while (requests++ < REQUEST_COUNT) {
			var jokeCall = cnjdbApi.getRandomJoke();

			var joke = jokeCall.get();

			if (jokeNumbers.contains(joke.getId())) {
				logger.info(String.format("Collision at joke %s", joke.getId()));
				collision = true;
				break;
			}

			jokeNumbers.add(joke.getId());
			logger.info(joke.toString());
		}

		assertTrue(collision, String.format("Completed %d requests without collision; consider increasing REQUEST_COUNT", requests));
	}

	@Test
	void testGetJokeById() throws ExecutionException, InterruptedException {

		var randomIds = new ArrayList<String>(10);

		for (var i = 0; i < 10; i++) {
			randomIds.add(cnjdbApi.getRandomJoke().get().getId());
		}

		for (var id : randomIds) {
			var j = cnjdbApi.getJokeById(id).get();
			assertNotNull(j);
			assertTrue(randomIds.contains(j.getId()));
			logger.info(j.toString());
		}
	}

}
