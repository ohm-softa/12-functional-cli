package ohm.softa.a12.cnjdb;

import ohm.softa.a12.model.JokeDto;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

import java.util.concurrent.CompletableFuture;

/**
 * @author Peter Kurfer
 * Created on 12/28/17.
 */
public interface CNJDBApi {

	/**
	 * Fetch a random joke
	 *
	 * @return JokeDto wrapped in a ResponseWrapper object
	 */
	@GET("/jokes/random")
	CompletableFuture<JokeDto> getRandomJoke();

	/**
	 * Fetch a random joke
	 *
	 * @param categories Search categories
	 * @return JokeDto wrapped in a ResponseWrapper object
	 */
	@GET("/jokes/random")
	CompletableFuture<JokeDto> getRandomJokeByCategory(@Query("category") String categories);

	/**
	 * Fetch the joke corresponding to a search query
	 *
	 * @return JokeDto[] wrapped in a ResponseWrapper object
	 */
	@GET("/jokes/search")
	CompletableFuture<JokeDto[]> getRandomJokesBySearch(@Query("query") String query);

	@GET("/jokes/{id}")
	CompletableFuture<JokeDto> getJokeById(@Path("id") String identifier);

}
