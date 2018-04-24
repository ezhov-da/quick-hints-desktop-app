package ru.ezhov.regularexpression;

/**
 * @author RRNDeonisiusEZH
 */
public class ApplicationObject {
	private int id;
	private String idKey;
	private String text;
	private String description;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIdKey() {
		return idKey;
	}

	public void setIdKey(String idKey) {
		this.idKey = idKey;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		String str = idKey + " -> " + description;
		return str;
	}


}
