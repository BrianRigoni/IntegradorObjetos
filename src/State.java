

import java.util.ArrayList;

public class State {
	private String 				name;
	private ArrayList<State> 	transitions;
	
	public State(String name) {
		this.name = name;
		transitions = new ArrayList<State>();
	}
	
	/* Setters */
	public void setName(String name) 							{ this.name = name; }
	public void setTransitions(ArrayList<State> transitions) 	{ this.transitions = transitions; }
	
	/* Getters */
	public String 			getName() 			{ return name; }
	public ArrayList<State> getTransitions() 	{ return transitions; }
	
	
	/* ----------------- TRANSICIONES DEL ESTADO ----------------- */
	
	/* Verifica si el estado a agregar en las transiciones existe*/
	public boolean checkTransitionExistence(State state) {
		for (State s : transitions) {
			if (s.equals(state))
				return true;
		}
		return false;
	}
	
	/* Agrega un estado al arraylist de transiciones */
	public boolean addTransition(State state) {
		if ( !(checkTransitionExistence(state)) ) {
			transitions.add(state);
			return true;
		}
		return false;
	}
	
	public boolean removeTransition(State state ) {
		if( !(checkTransitionExistence(state)) ) {
			transitions.remove(state);
			return true;
		}
		return false;
		
	}
	
	@Override
	public boolean equals(Object obj) {
		State o = (State)obj;
		return name == o.name;
	}
	
	public String toString() {
		return this.name;
	}
	
}
