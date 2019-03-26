

import java.util.ArrayList;

public class ItemType {
	private String 				name;
	private ArrayList<State>	states;
	//La secuencia real de estados se obtiene a partir de cada estado con sus posibles transiciones
	
	public ItemType(String name) {
		this.name = name;
		this.states = new ArrayList<State>();
	}
	
	/* Setters */	
	public void setName(String name) 				{ this.name = name; }
	public void setStates(ArrayList<State> states) 	{ this.states = states; }
	
	/* Getters */
	public String 			getName() 		{ return name; }
	public ArrayList<State> getStates() 	{ return states; }
	
	/*-----------------  ESTADOS DEL TIPO DE ITEM  ----------------- */
	
	/* Verifica si un estado existe en sus estados actuales */
	public boolean verifStateExistence(State state) {
		for(State s : states) {
			if (s.equals(state))
				return true;
		}
		return false;
	}
	/* Agrega un estado al tipo de item*/
	public boolean addState(State state) {
		if ( !(verifStateExistence(state)) ) {
			states.add(state);
			return true;
		}
		return false;
	}
	
	@Override 
	public boolean equals(Object obj) {
		ItemType o = (ItemType) obj;
		return name.equals(o.name);
	}
	
	@Override
	public String toString() {
		return this.name;
	}
}
