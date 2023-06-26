package ohm.softa.a12.cnjdb.suppliers;

import ohm.softa.a12.cnjdb.CNJDBApi;
import ohm.softa.a12.cnjdb.CNJDBService;
import ohm.softa.a12.model.JokeDto;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Supplier;

/**
 * Supplier implementation to retrieve all jokes of the CNJDB in a linear way
 *
 * @author Peter Kurfer
 */

public final class AllJokesSupplier implements Supplier<JokeDto> {

	private static final int REQUEST_COUNT = 100;

	private final CNJDBApi cnjdbApi;

	private final List<JokeDto> jokes;


	public AllJokesSupplier() {
		this.jokes = new LinkedList<>();
		cnjdbApi = CNJDBService.getInstance();
	}

	@Override
	public JokeDto get() {
		try {

			int requests = 0;

			do {

				// Check not contained in jokes
				JokeDto joke = cnjdbApi.getRandomJoke().get();

				if (!this.jokes.contains(joke)) {
					this.jokes.add(joke);
					return joke;
				}

				requests++;

			} while (requests < REQUEST_COUNT);

			throw new RuntimeException("No new joke found");

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}

