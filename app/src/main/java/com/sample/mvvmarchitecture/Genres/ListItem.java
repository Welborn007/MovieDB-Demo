package com.sample.mvvmarchitecture.Genres;

public class ListItem{
	private String image;
	private String career;
	private String address;
	private String phone;
	private String city;
	private String codebar;
	private String campus;
	private int V;
	private String name;
	private String id;
	private String email;
	private String dni;

	public void setImage(String image){
		this.image = image;
	}

	public String getImage(){
		return image;
	}

	public void setCareer(String career){
		this.career = career;
	}

	public String getCareer(){
		return career;
	}

	public void setAddress(String address){
		this.address = address;
	}

	public String getAddress(){
		return address;
	}

	public void setPhone(String phone){
		this.phone = phone;
	}

	public String getPhone(){
		return phone;
	}

	public void setCity(String city){
		this.city = city;
	}

	public String getCity(){
		return city;
	}

	public String getCodebar() {
		return codebar;
	}

	public void setCodebar(String codebar) {
		this.codebar = codebar;
	}

	public void setCampus(String campus){
		this.campus = campus;
	}

	public String getCampus(){
		return campus;
	}

	public void setV(int V){
		this.V = V;
	}

	public int getV(){
		return V;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setEmail(String email){
		this.email = email;
	}

	public String getEmail(){
		return email;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	@Override
 	public String toString(){
		return 
			"ListItem{" + 
			"image = '" + image + '\'' + 
			",career = '" + career + '\'' + 
			",address = '" + address + '\'' + 
			",phone = '" + phone + '\'' + 
			",city = '" + city + '\'' + 
			",codebar = '" + codebar + '\'' + 
			",campus = '" + campus + '\'' + 
			",__v = '" + V + '\'' + 
			",name = '" + name + '\'' + 
			",_id = '" + id + '\'' + 
			",email = '" + email + '\'' + 
			",dni = '" + dni + '\'' + 
			"}";
		}
}
