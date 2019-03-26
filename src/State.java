

import java.util.ArrayList;

public class State {
	private int 				code;
	private String 				name;
	private ArrayList<State> 	transitions;
	
	public State(int code, String name) {
		this.code = code;
		this.name = name;
		transitions = new ArrayList<State>();
	}
	
	/* Setters */
	public void setCode(int code) 								{ this.code = code; }
	public void setName(String name) 							{ this.name = name; }
	public void setTransitions(ArrayList<State> transitions) 	{ this.transitions = transitions; }
	
	/* Getters */
	public int 				getCode() 			{ return code; }
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
		return code == o.code;
	}
	
	
}
