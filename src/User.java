
public class User {
	private String 	surname;
	private String 	firstName;
	private String 	email;
	private String 	username;
	private String 	password;
	private boolean developer;
	private boolean admin = false;
	
	public User() {}
	
	public User(String surname, String firstName, String email, String username, String password) {
		this.surname = surname;
		this.firstName = firstName;
		this.email = email;
		this.username = username;
		this.password = password;
	}
	
	/* Setters */
	public void setSurname(String surname) 		{ this.surname = surname;}
	public void setFirstName(String firstName) 	{ this.firstName = firstName; }
	public void setEmail(String email) 			{ this.email = email; }
	public void setUsername(String username) 	{ this.username = username; }
	public void setPassword(String password) 	{ this.password = password; }
	public User setDeveloper(boolean developer) { this.developer = developer; return this;}
	public User setAdmin(boolean admin)			{ this.admin = admin; return this; }
	
	/* Getters */
	public String getSurname() 		{ return surname;	}
	public String getFirstName() 	{ return firstName; }
	public String getEmail()		{ return email; 	}
	public String getUsername() 	{ return username; 	}
	public String getPassword() 	{ return password; 	}
	public boolean isDeveloper() 	{ return developer; }
	public boolean isAdmin()		{ return admin;		}

	
	// Usado para el login al autentificar
	public boolean authenticate(String username, String password) {
		return (this.username.equals(username)) && (this.password.equals(password));
	}
	
	@Override
	public boolean equals(Object obj) {
		User o = (User) obj;
		return username.equals(o.getUsername());
		
	}
	
	@Override
	public String toString() {
		return username +": " +surname+" "+firstName;
		
	}

}
