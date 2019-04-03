

import java.util.ArrayList;

public class Project {
	private String 				name;
	private ArrayList<User> 	team;
	private User				leader;
	private User				defaultUserInCharge;
	private ArrayList<Item> 	items;
	private ArrayList<ItemType> itemTypes;
	
	//El proyecto se puede crear solo por nombre para luego agregar equipo e items
	public Project (String name) {
		this.name = name;
		team = new ArrayList<User>();
		items = new ArrayList<Item>();
		itemTypes = new ArrayList<ItemType>();
	}
	
	/* Setters */
	public void setName(String name) 						{ this.name = name; }
	public void setTeam(ArrayList<User> team) 				{ this.team = team; }
	public void setItems(ArrayList<Item> items) 			{ this.items = items; }
	public void itemTypes(ArrayList<ItemType> itemTypes) 	{ this.itemTypes = itemTypes; }
	public Project setDefaultUserInCharge(User defaultUserInCharge)	{ 
		if(team.isEmpty()) {
			this.defaultUserInCharge = defaultUserInCharge; 
			team.add(defaultUserInCharge);
		}
		return this;
	}
	public boolean setLeader(User leader) { 
		if (checkTeamMemberExistence(leader)) {
			this.leader = leader;
			return true;
			
		} 
		return false;  
	}
	
	/* Getters */
	public String 				getName() 		{ return name;  }
	public ArrayList<User> 		getTeam() 		{ return team;  }
	public ArrayList<Item> 		getItems() 		{ return items; } 
	public ArrayList<ItemType>  getItemTypes() 	{ return itemTypes; }
	public User getDefaultInCharge() 			{ return defaultUserInCharge; }
	public User getLeader()						{ return leader; }
	/* ----------------- ITEMS DEL PROYECTO ----------------- */

	/*Verifica la existencia de un tipo de item en el proyecto*/
	public boolean checkItemTypeExistence(ItemType itemType) {
		for (ItemType ip : itemTypes) {
			if (ip.equals(itemType))
				return true;
		}
		return false;
	}
	/*Agrega un tipo de item al proyecto*/
	public boolean addItemType(ItemType itemType) {
		if (! (checkItemTypeExistence(itemType)) ) {
			itemTypes.add(itemType);
			return true;
		}
		return false;
	}

	
	
	/* Verifica la existencia de un item, en los items del proyecto*/
	public boolean checkItemExistence(Item item) {
		for (Item i : items) {
			if (i.equals(item)) 
				return true;
		}
		return false;
	}
	
	
	/* Agrega un item en el arraylist de items */
	public boolean addItem(Item item) {
		if ( !(checkItemExistence(item)) ) {
			StateSequence st = new StateSequence();
			st.setEmpInCharge(defaultUserInCharge);
			st.setState(item.getItemType().getStates().get(0));
			item.addStateToHistory(st);
			items.add(item);
			return true;
		}
		return false;
	}
	
	/* ----------------- EMPLEADOS DEL PROYECTO ----------------- */
	
	/* Verifica si el empleado que se quiere añadir al proyecto ya se encuentra en el equipo */
	public boolean checkTeamMemberExistence(User user) {
		for (User u : team) {
			if (u.equals(user)) 
				return true;		
		}
		return false;
	}
	/* Agrega un empleado en el arraylist de empleados que conforman el equipo */
	public boolean addTeamMember(User user) {
		if ( !(checkTeamMemberExistence(user)) ) {
			if(team.isEmpty()) {
				team.add(user);
				defaultUserInCharge = user;
			}
			else {
				team.add(user);
			}
			return true;
		}
		return false;
	}
	
	public boolean removeTeamMember(User user) {
		if ((checkTeamMemberExistence(user)) ) {
			team.remove(user);
			return true;
		}
		return false;
	}
	
	
	@Override
	public boolean equals(Object other) {
		Project o = (Project)other;
		return name.equals(o.name);
	}
	
	@Override
	public String toString() {
		return name;
	}

	public boolean removeItem(Item item) {
		if (checkItemExistence(item)) {
			items.remove(item);
			return true;
		}
		return false;
	}
	
	public boolean changeEmpInCharge(String username, Item item) {
		User u = null;
		for(User us : team) {
			if(us.getUsername().equals(username)) {
				u = us;
				break;
			}
		}
		if(u != null) {
			item.changeEmpInCharge(u);
		return true;
		}
		else {
			return false;
		}
		
	}


}
