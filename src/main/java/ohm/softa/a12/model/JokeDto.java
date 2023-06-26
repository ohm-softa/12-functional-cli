package ohm.softa.a12.model;

import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * Data transfer object (DTO) for CNJDB jokes
 *
 * @author Peter Kurfer
 */
public class JokeDto {

	/**
	 * ID of the joke
	 */
	@SerializedName("id")
	private String identifier;

	/**
	 * joke content
	 */
	@SerializedName("value")
	private String joke;

	/**
	 * Categories of the joke
	 */
	@SerializedName("categories")
	private List<String> categories;

	public JokeDto() {
		categories = new ArrayList<>();
	}

	public String getId() {
		return identifier;
	}

	public void setId(String identifier) {
		this.identifier = identifier;
	}

	public String getJoke() {
		return joke;
	}

	public void setJoke(String joke) {
		this.joke = joke;
	}

	public List<String> getCategories() {
		return categories;
	}

	public void setCategories(List<String> categories) {
		if (categories == null) return;
		this.categories = categories;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;

		if (!(o instanceof JokeDto)) return false;

		var joke1 = (JokeDto) o;

		return new EqualsBuilder()
			.append(getId(), joke1.getId())
			.append(getJoke(), joke1.getJoke())
			.append(getCategories(), joke1.getCategories())
			.isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(17, 37)
			.append(getId())
			.append(getJoke())
			.append(getCategories())
			.toHashCode();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this)
			.append("identifier", identifier)
			.append("joke", joke)
			.append("categories", categories)
			.toString();
	}

}
