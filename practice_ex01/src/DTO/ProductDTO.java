package DTO;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class ProductDTO {
	private SimpleStringProperty id;
	private SimpleStringProperty name;
	private SimpleIntegerProperty number;
	private SimpleIntegerProperty price;
	private SimpleStringProperty origin;
	private SimpleStringProperty listId;
	
	public ProductDTO(String id, String name, Integer number, Integer price, String origin, String list) {
		this.setId(id);
		this.setName(name);
		this.setNumber(number);
		this.setPrice(price);
		this.setOrigin(origin);
		this.setListId(list);
	}
	
	public String getId() {
		return id.get();
	}
	
	public void setId(String id) {
		this.id = new SimpleStringProperty(id);
	}
	
	public String getName() {
		return name.get();
	}
	
	public void setName(String name) {
		this.name = new SimpleStringProperty(name);
	}
	
	public Integer getNumber() {
		return number.get();
	}
	
	public void setNumber(Integer number) {
		this.number = new SimpleIntegerProperty(number);
	}
	
	public Integer getPrice() {
		return price.get();
	}
	
	public void setPrice(Integer price) {
		this.price = new SimpleIntegerProperty(price);
	}
	
	public String getOrigin() {
		return origin.get();
	}
	
	public void setOrigin(String origin) {
		this.origin = new SimpleStringProperty(origin);
	}
	
	public String getListId() {
		return listId.get();
	}
	
	public void setListId(String list) {
		this.listId = new SimpleStringProperty(list);
	}
}
