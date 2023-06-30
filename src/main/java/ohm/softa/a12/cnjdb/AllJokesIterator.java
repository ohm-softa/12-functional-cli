package ohm.softa.a12.cnjdb;

import ohm.softa.a12.model.JokeDto;
import org.apache.commons.lang3.NotImplementedException;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Iterator to retrieve all jokes of the ICNDB until collision.
 */
public final class AllJokesIterator implements Iterator<JokeDto> {

    /* ICNDB API proxy to retrieve jokes */
    private final CNJDBApi icndbApi;

	private Set<String> ids = new HashSet<>();
	private JokeDto cur = retrieve();

	@Override
	public boolean hasNext() {
		return cur != null;
	}

	private JokeDto retrieve() {
		try {
			JokeDto j = icndbApi.getRandomJoke();
			if (ids.contains(j.getId()))
				return null;
			return j;
		} catch(Exception e) {
			return null;
		}
	}

	@Override
	public JokeDto next() {
		JokeDto tmp = cur;
		cur = retrieve();
		return tmp;
	}

    public AllJokesIterator() {
        icndbApi = CNJDBService.getInstance();
    }
}
