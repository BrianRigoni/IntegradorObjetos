import java.util.ArrayList;

public class Item {
	private String 						name;
	private ItemType 					itemType;
	private ArrayList<StateSequence>	history;
	
	public Item(String name) {
		this.name = name;
	}
	public Item(String name, StateSequence firstInHistory) {
		this.name = name;
		history = new ArrayList<StateSequence>();
		history.add(firstInHistory);
	}
	
	/* Setters */
	public void setName(String name) 						{ this.name = name; }
	public void setItemType(ItemType itemType) 				{ this.itemType = itemType;	}
	public void setSequence(StateSequence stateSequence) 	{ this.history = history; }
	
	/* Getters */
	public String 						getName() 			{ return name; }
	public ItemType 					getItemType() 		{ return itemType; }
	public ArrayList<StateSequence> 	getSequence() 		{ return history; }
	/* */
	
	
	public void addStateToHistory(StateSequence stateSequence) {
		history.add(stateSequence);
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
