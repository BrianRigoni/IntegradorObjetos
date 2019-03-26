

import java.util.ArrayList;

public class Controller {

	private ArrayList<Project> projects;
	private ArrayList<User> users;
	
	public Controller() {
		projects = new ArrayList<Project>();
		users = new ArrayList<User>();
		fakeData();
	}
	
	//Datos que se crean cuando inicia el programa para demostracion
	public void fakeData() {
		/* Usuarios */
		users.add(new User("Ignacio", "Perez", "iperez@algo.com", "iperez", "123456").setDeveloper(true));
		users.add(new User("Rigoni", "Brian", "brigoni@algo.com", "brigoni", "123456").setAdmin(true).setDeveloper(true));
		users.add(new User("Nombre1", "Apellido1", "usuario1@algo.com", "usuario1", "123456"));
		/* Proyectos  */
		Project project = new Project("Olivia").setDefaultUserInCharge(users.get(0));
		for (int i = 0; i <= 1; i++) {
			User u = users.get(i);
			project.addTeamMember(u);
		}
		
		// tipos de item
		ItemType itemType = new ItemType("Reporte de bug");

		State st1 = new State(1,"Creado");
		State st2 = new State(2,"Desarrollo");
		State st3 = new State(3,"Validacion");
		State st4 = new State(4,"Terminado");
		
		st1.addTransition(st2);
		st2.addTransition(st3);
		st3.addTransition(st2);
		st3.addTransition(st4);
		
		itemType.addState(st1);
		itemType.addState(st2);
		itemType.addState(st3);
		itemType.addState(st4);
		project.addItemType(itemType);
		
		
		ItemType itemType2 = new ItemType("Mejora");
		
		State st21 = new State(1, "En revision");
		State st22 = new State(2, "Aceptado");
		State st23 = new State(3, "Rechazada"); // Estado final cuando se rechazo
		State st24 = new State(4, "En analisis");
		State st25 = new State(5, "En desarrollo");
		State st26 = new State(6, "En pruebas");
		State st27 = new State(7, "Implementado."); // Estado final cuando se acepto

		
		st21.addTransition(st22);
		st21.addTransition(st23);
		st22.addTransition(st24);
		st24.addTransition(st25);
		st25.addTransition(st24);
		st25.addTransition(st26);
		st26.addTransition(st25);
		st26.addTransition(st27);
		itemType2.addState(st21);
		itemType2.addState(st22);
		itemType2.addState(st23);
		itemType2.addState(st24);
		itemType2.addState(st25);
		itemType2.addState(st26);
		itemType2.addState(st27);
		
		project.addItem(new Item("Mejora de interfaz"));
		project.addItemType(itemType2);

	
		projects.add(project);
		
		
	}
	
	
	/* Getters para los lists */
	public ArrayList<Project> 	getProjects() 	{ return this.projects; }
	public ArrayList<User>		getUsers()		{ 
		ArrayList<User> usersList = new ArrayList<User>();
		for(User u : users) {
			u.setPassword(null);
			usersList.add(u);
		}
		return usersList;
	}
	
	 /* USER */
	
	public User login(String username, String password) {
		for(User u : users) {
			if (u.authenticate(username, password)) {
				u.setPassword(null); // Para que el usuario que se retorna a la interfaz tenga la password en null post-autentificacion
				return u;
			}
		}
		return null;
	}
	
	public boolean userExists(User u) {
		for(User user : users)
		{
			if (user.equals(u))  
				return true;
		}
		return false;
	}
	
	public boolean signUp(User u) {
		if(userExists(u)) {
				return false;
		}
		users.add(u);
		return true;
	}
	
	
	public void giveAdmin(User u, boolean b) {
		for(User user : users) {
			if(user.equals(u)) {
				user.setAdmin(b);
			}
		}
		
	}

	public void makeDeveloper(User u, boolean b) {
		for(User user : users) {
			if(user.equals(u)) {
				user.setDeveloper(b);
			}
		}
		
	}

	public boolean updateUsername(User u, String username) {
		for(User user : users) {
			if(user.equals(u)) {
				user.setUsername(username);
				return true;
			}
		}
		return false;
	}
	
	public boolean updateUserEmail(User u, String email) {
		for(User user : users) {
			if(user.equals(u)) {
				user.setEmail(email);
				return true;
			}
		}
		return false;
	}
	
	public boolean updateUserPassword(User u, String password) {
		for(User user : users) {
			if(user.equals(u)) {
				user.setPassword(password);
				return true;
			}
		}
		return false;
	}
	

	public boolean deleteUser(User u) {
		if (userExists(u)) {
			users.remove(u);
			return true;
		}
		return false;
	}
	/*PROJECT*/
	
	public boolean checkProjectExistence(Project p) {
		for(Project project : projects) {
			if (project.equals(p)) 
				return true;
		}
		return false;
	}
	
	public boolean addProject(Project p) {
		if ( !(checkProjectExistence(p)) ) {
			projects.add(p);
			return true;
		}
		return false;
	}

	public boolean removeProject(Project p) {
		if (checkProjectExistence(p)) {
			projects.remove(p);
			return true;
		}
		return false;
	}

	
	
}
