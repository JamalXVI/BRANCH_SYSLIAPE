package br.com.liape.sistemaGerenciamento.adServerModel;

import java.io.Serializable;

public class OrganizationalUnit implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6002726895345962390L;
	private String Name;
	private String Ou;
	private String Description;
	
	
	public OrganizationalUnit(String Name,String Ou, String Description){
		this.Name = Name;
		this.Ou = Ou;
		this.Description = Description;
		
	}
	
	public OrganizationalUnit(){
		
		this("","","");
	}
	
	
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getOu() {
		return Ou;
	}
	public void setOu(String ou) {
		Ou = ou;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}

	@Override
	public String toString() {
		return Description;
	}
}
