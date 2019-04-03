import java.util.Date;

public class StateSequence {
	private State state;
	private User userInCharge;
	private Date date;
	
	StateSequence() {
		this.date = new Date();
	}
	/* Setters*/
	public void setState(State state) 				{ this.state = state; }
	public void setEmpInCharge(User user) 			{ this.userInCharge= user; }
	public void setDate(Date date) 					{ this.date = date; }
	
	/* Getters*/
	public State 	getState() 			{ return state; } 
	public User getEmpInCharge() 		{ return userInCharge; }
	public Date 	getDate() 			{ return date; } 
	
	
}
