package ohm.softa.a12.cnjdb;

import ohm.softa.a12.model.JokeDto;
import retrofit2.Retrofit;
import retrofit2.adapter.java8.Java8CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.concurrent.CompletableFuture;

/**
 * CNJDB singleton service
 * acts as proxy to an underlying Retrofit proxy object
 *
 * @author Peter Kurfer
 */
public final class CNJDBService implements CNJDBApi {

	/**
	 * Singleton instance
	 */
	private static final CNJDBService instance = new CNJDBService();

	/**
	 * Retrofit proxy
	 */
	private final CNJDBApi cnjdbApi;

	/**
	 * Access the singleton instance
	 */
	public static CNJDBService getInstance() {
		return instance;
	}

	private CNJDBService() {

		/* Initialize Retrofit */
		var retrofit = new Retrofit.Builder()
			.baseUrl("https://api.chucknorris.io")
			.addConverterFactory(GsonConverterFactory.create())
			/* CallAdapterFactory required to wrap calls in CompletableFutures */
			.addCallAdapterFactory(Java8CallAdapterFactory.create())
			.build();

		cnjdbApi = retrofit.create(CNJDBApi.class);
	}

	@Override
	public CompletableFuture<JokeDto> getRandomJoke() {
		return cnjdbApi.getRandomJoke();
	}

	@Override
	public CompletableFuture<JokeDto> getRandomJokeByCategory(String categories) {
		return cnjdbApi.getRandomJokeByCategory(categories);
	}

	@Override
	public CompletableFuture<JokeDto[]> getRandomJokesBySearch(String query) {
		return cnjdbApi.getRandomJokesBySearch(query);
	}

	@Override
	public CompletableFuture<JokeDto> getJokeById(String identifier) {
		return cnjdbApi.getJokeById(identifier);
	}

}
