package ohm.softa.a12.cnjdb.suppliers;

import ohm.softa.a12.cnjdb.CNJDBApi;
import ohm.softa.a12.cnjdb.CNJDBService;
import ohm.softa.a12.model.JokeDto;

import java.util.concurrent.ExecutionException;
import java.util.function.Supplier;

/**
 * @author Peter Kurfer
 */
public final class CategoryJokeSupplier implements Supplier<JokeDto> {

	/**
	 * CNJDBAPI proxy
	 */
	private final CNJDBApi cnjdbApi;

	private final String category;

	public CategoryJokeSupplier(String category) {
		cnjdbApi = CNJDBService.getInstance();
		this.category = category;
	}

	@Override
	public JokeDto get() {
		/*
		 * alternate more function solution
		 * return tryCatch(() -> cnjdbApi.getRandomJoke().get(), () -> null); */
		try {
			/*try to fetch next random joke */
			return cnjdbApi.getRandomJokeByCategory(this.category).get();
		} catch (InterruptedException | ExecutionException e) {
			/* return null if error occurred */
			return null;
		}
	}
}
