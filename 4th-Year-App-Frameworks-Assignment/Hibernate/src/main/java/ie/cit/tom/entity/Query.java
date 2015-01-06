package ie.cit.tom.entity;

import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.NotEmpty;

@XmlRootElement
public class Query {
	@NotEmpty
	private String search;

	public String getSearch() {
		return search;
	}
	public void setSearch(String search) {
		this.search = search;
	}
}
