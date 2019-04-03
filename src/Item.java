import java.util.ArrayList;

public class Item {
	private String 						name;
	private ItemType 					itemType;
	private ArrayList<StateSequence>	history;
	
	
	public Item(String name) {
		this.name = name;
		history = new ArrayList<StateSequence>();
	}
	public Item(String name, StateSequence firstInHistory) {
		this.name = name;
		history = new ArrayList<StateSequence>();
		history.add(firstInHistory);
	}
	
	/* Setters */
	public void setName(String name) 						{ this.name = name; }
	public void setItemType(ItemType itemType) 				{ this.itemType = itemType;	}
	
	/* Getters */
	public String 						getName() 			{ return name; }
	public ItemType 					getItemType() 		{ return itemType; }
	public ArrayList<StateSequence> 	getHistory() 		{ return history; }
	/* */
	
	
	public void addStateToHistory(StateSequence stateSequence) {
		history.add(stateSequence);
	}
	
	public void changeEmpInCharge(User user) {
		StateSequence st = new StateSequence();
		st.setEmpInCharge(user);
		st.setState(history.get(history.size() -1).getState());
		addStateToHistory(st);
	}
	
	public boolean changeState(String stateName) {
		ArrayList<State> states = itemType.getStates();
		State state = null;
		int i = 0;
		do {
			state = states.get(i);
			i++;
		} while(stateName != state.getName());
		
		if(stateName.equals(state.getName())) {
			StateSequence st = new StateSequence();
			st.setState(state);
			st.setEmpInCharge(history.get(history.size()-1).getEmpInCharge());
			addStateToHistory(st);
			return true;
		}
		else {
			return false;
		}
	}
	@Override
	public boolean equals(Object obj) {
		Item o = (Item)obj;
		return name.contentEquals(o.name);
	}
	
	
	@Override
	public String toString() {
		return this.name;
	}
}
