package ohm.softa.a12.cnjdb;

import ohm.softa.a12.model.JokeDto;
import org.apache.commons.lang3.NotImplementedException;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * Generator instance to provide streams of jokes
 */

public final class JokeGenerator {

    /**
     * Generate an infinite Stream of random jokes using the RandomJokeSupplier
     */
    public Stream<JokeDto> randomJokesStream() {
        return Stream.generate(new RandomJokeSupplier());
    }

    /**
     * Generator a finite Stream of jokes using the AllJokesIterator
     */
    public Stream<JokeDto> allJokesStream() {
		return StreamSupport.stream(Spliterators.spliteratorUnknownSize(new AllJokesIterator(), Spliterator.DISTINCT), false);
	}
}
