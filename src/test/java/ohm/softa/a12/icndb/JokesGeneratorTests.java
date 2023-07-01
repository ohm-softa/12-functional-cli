package ohm.softa.a12.icndb;

import ohm.softa.a12.cnjdb.JokeGenerator;
import ohm.softa.a12.model.JokeDto;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

class JokesGeneratorTests {

    private JokeGenerator jokeGenerator = new JokeGenerator();

    @Test
    void testRandomStream() {
        jokeGenerator.randomJokesStream()
			.limit(5)
			.forEach(System.out::println);
    }

    @Test
    void testAllJokesStream() {
		// try to retrieve all tests...
		Set<JokeDto> all = jokeGenerator.allJokesStream().collect(Collectors.toSet());
		System.out.println("Retrieved all " + all.size() + " jokes.");
		all.stream()
			.map(JokeDto::getValue)
			.forEach(System.out::println);
    }
}
