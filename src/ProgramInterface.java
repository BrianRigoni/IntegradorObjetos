import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.CardLayout;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Cursor;

import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.JTabbedPane;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.border.LineBorder;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.awt.SystemColor;
import java.awt.event.MouseMotionAdapter;
import javax.swing.JComboBox;
import javax.swing.border.MatteBorder;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;


public class ProgramInterface extends JFrame {

	/*Variables de la interfaz que necesito acceder desde metodos fuera del constructor*/
	private JPanel 				contentPane;
	private JTextField 			tf_username;
	private JPasswordField 		pf_Password;
	private JTextField 			tf_signUpUsername;
	private JPasswordField 		pf_SignUpPassword;
	private JTextField 			tf_email;
	private JButton				btn_signIn;
	private JButton				btn_signUp;
	private JTextField 			tf_newProject;
	private JTabbedPane 		tabbedPane_home;
	private JList<Project>  	jlist_projects;
	private JButton 			btn_addProject;
	private JLabel 				lblLogeadoComo;
	private JLabel 				lblRol;
	private JLabel 				labelLogeadoComo2;
	private JLabel 				labelRol2;
	private JList<ItemType>		jlist_itemTypes;
	private JList<Item>			jlist_items;
	private JList<User>			jlist_teamMembers;
	private JComboBox<User>		jcbx_users;
	private JList<State>		jlist_history;
	private JList<User> 		jlist_allUsers;
	private JCheckBox 			chckbxDeveloper;
	private JCheckBox 			chckbxAdmin;
	private JButton				btn_deleteProject;
	private JButton 			btn_viewProject;
	private JCheckBox			chckbxDefaultInCharge;
	private JComboBox<ItemType> jcbx_itemType;
	private JComboBox<Project>  jcbx_project;
	private JTextField tf_name;
	private JTextField tf_surname;
	private JTextField tf_item;
	private JTextField tf_profileUsername;
	private JTextField tf_profileEmail;
	private JPasswordField pf_profilePasswordField;
	private JButton btn_changeUsername;
	private JButton btn_changeEmail;
	private JButton btn_changePassword;
	
	
	/*Variables que comparten los distintos paneles*/
	private Controller 		controller;
	private User 			currentUser;
	private Project			currentProject;
	/* los string son constantes que establecen las rutas de los paneles del card layout agregados al contentPane*/	
	final String HOME = 	"home";
	final String SIGNIN  = 	"sign_in";
	final String SIGNUP  = 	"sign_up";
	final String PROJECT = 	"project";


	
	
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					ProgramInterface frame = new ProgramInterface();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ProgramInterface() {
		setResizable(false);
		// Instancio el controlador
		currentUser = new User();
		controller = new Controller();
		this.setTitle("BugTracker");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 614, 461);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));
		
		JPanel panel_signIn = new JPanel();
		contentPane.add(panel_signIn, SIGNIN);
		panel_signIn.setLayout(null);
		
		tf_username = new JTextField();
		tf_username.setFont(new Font("Verdana", Font.PLAIN, 13));
		tf_username.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				if( pf_Password.getPassword().length >= 1 && tf_username.getText().length() >= 1) {
					btn_signIn.setEnabled(true);
				}
				else {
					btn_signIn.setEnabled(false);
				}
			}
		});
		tf_username.setBounds(200, 169, 196, 27);
		panel_signIn.add(tf_username);
		tf_username.setColumns(10);
		
		
		
		
		// CLICK INGRESAR
		btn_signIn = new JButton("Ingresar");
		btn_signIn.setEnabled(false);
		btn_signIn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				authenticate(tf_username.getText(), pf_Password.getText());
			}
		});
			
		btn_signIn.setForeground(new Color(0, 0, 0));
		btn_signIn.setBackground(SystemColor.menu);
		btn_signIn.setFont(new Font("Verdana", Font.PLAIN, 11));
		btn_signIn.setBounds(231, 284, 121, 31);
		panel_signIn.add(btn_signIn);
		
		JButton btn_goSignUp = new JButton("Registrarse");
		btn_goSignUp.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				changePanel(SIGNUP);
			}
		});
		btn_goSignUp.setBackground(SystemColor.menu);
		btn_goSignUp.setFont(new Font("Verdana", Font.PLAIN, 11));
		btn_goSignUp.setBounds(231, 326, 121, 32);
		panel_signIn.add(btn_goSignUp);
		
		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setFont(new Font("Verdana", Font.PLAIN, 13));
		lblUsuario.setBounds(132, 170, 68, 20);
		panel_signIn.add(lblUsuario);
		
		pf_Password = new JPasswordField();
		pf_Password.setFont(new Font("Verdana", Font.PLAIN, 13));
		pf_Password.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if( pf_Password.getPassword().length >= 1 && tf_username.getText().length() >= 1) {
					btn_signIn.setEnabled(true);
				}
				else {
					btn_signIn.setEnabled(false);
				}
			}
		});
		pf_Password.setBounds(200, 224, 196, 27);
		panel_signIn.add(pf_Password);
		
		JLabel lblContrasea = new JLabel("Contrase\u00F1a");
		lblContrasea.setFont(new Font("Verdana", Font.PLAIN, 13));
		lblContrasea.setBounds(106, 228, 84, 14);
		panel_signIn.add(lblContrasea);
		
		JLabel lblInicioDeSesion = new JLabel("Inicio de sesion");
		lblInicioDeSesion.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblInicioDeSesion.setHorizontalAlignment(SwingConstants.CENTER);
		lblInicioDeSesion.setBounds(145, 41, 296, 50);
		panel_signIn.add(lblInicioDeSesion);
		
		JSeparator separator_signIn = new JSeparator();
		separator_signIn.setBackground(new Color(0, 191, 255));
		separator_signIn.setBounds(86, 98, 410, 27);
		panel_signIn.add(separator_signIn);
		
		/* sign up */
		JPanel panel_signUp = new JPanel();
		contentPane.add(panel_signUp, SIGNUP);
		panel_signUp.setLayout(null);
		
		JLabel lblRegistro = new JLabel("Registro");
		lblRegistro.setHorizontalAlignment(SwingConstants.CENTER);
		lblRegistro.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblRegistro.setBounds(147, 49, 296, 50);
		panel_signUp.add(lblRegistro);
		
		JSeparator separator_signup = new JSeparator();
		separator_signup.setBackground(new Color(0, 191, 255));
		separator_signup.setBounds(89, 107, 410, 27);
		panel_signUp.add(separator_signup);
		
		
		pf_SignUpPassword = new JPasswordField();
		pf_SignUpPassword.setFont(new Font("Verdana", Font.PLAIN, 11));
		pf_SignUpPassword.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (tf_signUpUsername.getText().length() >= 1 &&
						tf_email.getText().length() >= 1 &&
						pf_SignUpPassword.getPassword().length >= 1) {
						
						btn_signUp.setEnabled(true);
					}
					else {
						btn_signUp.setEnabled(false);
					}
			}
		});
		pf_SignUpPassword.setBounds(207, 270, 196, 20);
		panel_signUp.add(pf_SignUpPassword);
		
		
		tf_email = new JTextField();
		tf_email.setFont(new Font("Verdana", Font.PLAIN, 11));
		tf_email.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (tf_signUpUsername.getText().length() >= 1 &&
						tf_email.getText().length() >= 1 &&
						pf_SignUpPassword.getPassword().length >= 1) {
						
						btn_signUp.setEnabled(true);
					}
					else {
						btn_signUp.setEnabled(false);
					}
			}
		});
		tf_email.setBounds(207, 239, 196, 20);
		panel_signUp.add(tf_email);
		tf_email.setColumns(10);
		
		tf_signUpUsername = new JTextField();
		tf_signUpUsername.setFont(new Font("Verdana", Font.PLAIN, 11));
		tf_signUpUsername.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (tf_signUpUsername.getText().length() >= 1 &&
					tf_email.getText().length() >= 1 &&
					pf_SignUpPassword.getPassword().length >= 1) {
					
					btn_signUp.setEnabled(true);
				}
				else {
					btn_signUp.setEnabled(false);
				}
			}
			
		});
		tf_signUpUsername.setColumns(10);
		tf_signUpUsername.setBounds(207, 208, 196, 20);
		panel_signUp.add(tf_signUpUsername);
		
		JLabel labelSignUpUsuario = new JLabel("Usuario");
		labelSignUpUsuario.setHorizontalAlignment(SwingConstants.RIGHT);
		labelSignUpUsuario.setFont(new Font("Verdana", Font.PLAIN, 11));
		labelSignUpUsuario.setBounds(136, 208, 68, 20);
		panel_signUp.add(labelSignUpUsuario);

		
		JLabel labelSignUpPassword = new JLabel("Contrase\u00F1a");
		labelSignUpPassword.setHorizontalAlignment(SwingConstants.RIGHT);
		labelSignUpPassword.setFont(new Font("Verdana", Font.PLAIN, 11));
		labelSignUpPassword.setBounds(120, 273, 84, 14);
		panel_signUp.add(labelSignUpPassword);

		
		JLabel labelEmail = new JLabel("E-mail");
		labelEmail.setHorizontalAlignment(SwingConstants.RIGHT);
		labelEmail.setFont(new Font("Verdana", Font.PLAIN, 11));
		labelEmail.setBounds(147, 239, 54, 20);
		panel_signUp.add(labelEmail);
		
		JButton btn_goSignIn = new JButton("Volver");
		btn_goSignIn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				resetSignUp();
				changePanel(SIGNIN);
			}
		});
		btn_goSignIn.setForeground(Color.BLACK);
		btn_goSignIn.setFont(new Font("Verdana", Font.PLAIN, 11));
		btn_goSignIn.setBackground(SystemColor.menu);
		btn_goSignIn.setBounds(239, 370, 121, 31);
		panel_signUp.add(btn_goSignIn);
		
		btn_signUp = new JButton("Registrarse");
		btn_signUp.setEnabled(false);
		btn_signUp.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
			{
					User u = new User(tf_surname.getText(), tf_name.getText(), tf_email.getText(), tf_signUpUsername.getText(), pf_SignUpPassword.getText());
					if (controller.signUp(u) ) {
						JOptionPane.showMessageDialog(null, "Usuario registrado exitosamente!");
						resetSignUp();
						changePanel(SIGNIN);
						
					}
					else {
						JOptionPane.showMessageDialog(null, "Error al registrar, usuario y/o email ya existen.");
					}
				}
			}
		});
		btn_signUp.setFont(new Font("Verdana", Font.PLAIN, 11));
		btn_signUp.setBackground(SystemColor.menu);
		btn_signUp.setBounds(239, 327, 121, 32);
		panel_signUp.add(btn_signUp);
		
		tf_name = new JTextField();
		tf_name.setFont(new Font("Verdana", Font.PLAIN, 11));
		tf_name.setColumns(10);
		tf_name.setBounds(207, 177, 196, 20);
		panel_signUp.add(tf_name);
		
		tf_surname = new JTextField();
		tf_surname.setFont(new Font("Verdana", Font.PLAIN, 11));
		tf_surname.setColumns(10);
		tf_surname.setBounds(207, 146, 196, 20);
		panel_signUp.add(tf_surname);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNombre.setFont(new Font("Verdana", Font.PLAIN, 11));
		lblNombre.setBounds(136, 177, 68, 20);
		panel_signUp.add(lblNombre);
		
		JLabel lblSurname = new JLabel("Apellido");
		lblSurname.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSurname.setFont(new Font("Verdana", Font.PLAIN, 11));
		lblSurname.setBounds(136, 145, 68, 20);
		panel_signUp.add(lblSurname);
		

		/* panel general */ 
		JPanel panel_home = new JPanel();
		contentPane.add(panel_home, HOME);
		panel_home.setLayout(null);
		
		tabbedPane_home = new JTabbedPane(SwingConstants.TOP);
		tabbedPane_home.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
			}
		});
		tabbedPane_home.setBounds(0, 30, 588, 382);
		panel_home.add(tabbedPane_home);
		
		JPanel panel_Profile = new JPanel();
		tabbedPane_home.addTab("Perfil", null, panel_Profile, null);
		panel_Profile.setLayout(null);
		
		JPanel panel_profile = new JPanel();
		panel_profile.setBorder(new MatteBorder(1, 1, 1, 1, new Color(0, 0, 0)));
		panel_profile.setBounds(128, 35, 331, 269);
		panel_Profile.add(panel_profile);
		panel_profile.setLayout(null);
		
		JLabel label_3 = new JLabel("Usuario");
		label_3.setHorizontalAlignment(SwingConstants.RIGHT);
		label_3.setFont(new Font("Verdana", Font.PLAIN, 11));
		label_3.setBounds(34, 76, 68, 20);
		panel_profile.add(label_3);
		
		tf_profileUsername = new JTextField();
		tf_profileUsername.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				if(tf_profileUsername.getText().length() >= 1) {
					btn_changeUsername.setEnabled(true);
				}
				else {
					btn_changeUsername.setEnabled(false);
				}
			}
		});
		tf_profileUsername.setFont(new Font("Verdana", Font.PLAIN, 11));
		tf_profileUsername.setColumns(10);
		tf_profileUsername.setBounds(112, 76, 110, 20);
		panel_profile.add(tf_profileUsername);
		
		JLabel label_4 = new JLabel("E-mail");
		label_4.setHorizontalAlignment(SwingConstants.RIGHT);
		label_4.setFont(new Font("Verdana", Font.PLAIN, 11));
		label_4.setBounds(44, 110, 54, 20);
		panel_profile.add(label_4);
		
		tf_profileEmail = new JTextField();
		tf_profileEmail.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(tf_profileEmail.getText().length() >= 1) {
					btn_changeEmail.setEnabled(true);
				}
				else {
					btn_changeEmail.setEnabled(false);
				}
			}
		});
		tf_profileEmail.setFont(new Font("Verdana", Font.PLAIN, 11));
		tf_profileEmail.setColumns(10);
		tf_profileEmail.setBounds(112, 110, 110, 20);
		panel_profile.add(tf_profileEmail);
		
		JLabel label_5 = new JLabel("Contrase\u00F1a");
		label_5.setHorizontalAlignment(SwingConstants.RIGHT);
		label_5.setFont(new Font("Verdana", Font.PLAIN, 11));
		label_5.setBounds(18, 149, 84, 14);
		panel_profile.add(label_5);
		
		pf_profilePasswordField = new JPasswordField();
		pf_profilePasswordField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(pf_profilePasswordField.getPassword().length >= 1) {
					btn_changePassword.setEnabled(true);
				}
				else {
					btn_changePassword.setEnabled(false);
				}
			}
		});
		pf_profilePasswordField.setFont(new Font("Verdana", Font.PLAIN, 11));
		pf_profilePasswordField.setBounds(112, 146, 110, 20);
		panel_profile.add(pf_profilePasswordField);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(77, 29, 161, 2);
		panel_profile.add(separator);
		
		JLabel lblModificacionYBorrado = new JLabel("Modificacion y borrado de perfil");
		lblModificacionYBorrado.setHorizontalAlignment(SwingConstants.CENTER);
		lblModificacionYBorrado.setFont(new Font("Verdana", Font.PLAIN, 13));
		lblModificacionYBorrado.setBounds(21, 11, 284, 14);
		panel_profile.add(lblModificacionYBorrado);
		
		JButton btn_deleteSelfUser = new JButton("Eliminar");
		btn_deleteSelfUser.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				if (controller.deleteUser(currentUser)) {
					JOptionPane.showMessageDialog(null, "Cuenta eliminada exitosamente");
					logout();
				}
				else {
					JOptionPane.showMessageDialog(null, "Error al eliminar cuenta");
				}
				
			}
		});
		btn_deleteSelfUser.setToolTipText("Dar de baja su cuenta");
		btn_deleteSelfUser.setFont(new Font("Verdana", Font.PLAIN, 11));
		btn_deleteSelfUser.setBounds(121, 194, 101, 30);
		panel_profile.add(btn_deleteSelfUser);
		
		btn_changeUsername = new JButton("Cambiar");
		btn_changeUsername.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (controller.updateUsername(currentUser, tf_profileUsername.getText())) {
					JOptionPane.showMessageDialog(null, "Nombre de usuario actualizado.");
					lblLogeadoComo.setText("Logeado como: "+ currentUser.getUsername());
				}
				else {
					JOptionPane.showMessageDialog(null, "Error al cambiar nombre de usuario.");
				}
			}
		});
		btn_changeUsername.setEnabled(false);
		btn_changeUsername.setBounds(232, 76, 89, 20);
		panel_profile.add(btn_changeUsername);
		
		btn_changeEmail = new JButton("Cambiar");
		btn_changeEmail.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(controller.updateUserEmail(currentUser, tf_profileEmail.getText())) {
					JOptionPane.showMessageDialog(null, "Email actualizado.");
				}
				else {
					JOptionPane.showMessageDialog(null, "Error al cambiar email.");
				}
			}
		});
		btn_changeEmail.setEnabled(false);
		btn_changeEmail.setBounds(232, 110, 89, 20);
		panel_profile.add(btn_changeEmail);
		
		btn_changePassword = new JButton("Cambiar");
		btn_changePassword.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(controller.updateUserPassword(currentUser, pf_profilePasswordField.getText())) {
					JOptionPane.showMessageDialog(null, "Password actualizada.");
				}
				else {
					JOptionPane.showMessageDialog(null, "Error al actualizar password.");
				}
			}
		});
		btn_changePassword.setEnabled(false);
		btn_changePassword.setBounds(232, 146, 89, 20);
		panel_profile.add(btn_changePassword);
		
		JPanel panel_itemReport = new JPanel();
		tabbedPane_home.addTab("Item", null, panel_itemReport, null);
		panel_itemReport.setLayout(null);
		
		JPanel panel_itemCreate = new JPanel();
		panel_itemCreate.setBorder(new MatteBorder(1, 1, 1, 1, new Color(0, 0, 0)));
		panel_itemCreate.setBounds(138, 31, 304, 312);
		panel_itemReport.add(panel_itemCreate);
		panel_itemCreate.setLayout(null);
		
		jcbx_project = new JComboBox<Project>();
		jcbx_project.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent arg0) {

	            if (arg0.getStateChange() == ItemEvent.SELECTED) {
					loadItemTypes((Project) jcbx_project.getSelectedItem());
	            }
			}
		});
		jcbx_project.setBounds(54, 79, 191, 20);
		panel_itemCreate.add(jcbx_project);
		jcbx_project.setToolTipText("Elija el responsable del item");
		jcbx_project.setFont(new Font("Verdana", Font.PLAIN, 11));
		
		jcbx_itemType = new JComboBox<ItemType>();
		jcbx_itemType.setBounds(54, 139, 191, 20);
		panel_itemCreate.add(jcbx_itemType);
		jcbx_itemType.setToolTipText("Elija el tipo de item del item a agregar");
		jcbx_itemType.setFont(new Font("Verdana", Font.PLAIN, 11));
		
		tf_item = new JTextField();
		tf_item.setBounds(54, 195, 191, 20);
		panel_itemCreate.add(tf_item);
		tf_item.setColumns(10);
		
		JButton btn_report = new JButton("Reportar");
		btn_report.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				addNewItem((Project)jcbx_project.getSelectedItem(), tf_item.getText());

			}
		});
		btn_report.setBounds(97, 249, 101, 30);
		panel_itemCreate.add(btn_report);
		btn_report.setFont(new Font("Verdana", Font.PLAIN, 11));
		
		JLabel lbl_project = new JLabel("Proyecto");
		lbl_project.setBounds(54, 54, 191, 14);
		panel_itemCreate.add(lbl_project);
		
		JLabel lblTipoDeItem = new JLabel("Tipo de item");
		lblTipoDeItem.setBounds(54, 110, 191, 14);
		panel_itemCreate.add(lblTipoDeItem);
		
		JLabel lblItem = new JLabel("Item");
		lblItem.setBounds(54, 170, 191, 14);
		panel_itemCreate.add(lblItem);
		
		JLabel label_CreateItem = new JLabel("Alta Item");
		label_CreateItem.setHorizontalAlignment(SwingConstants.CENTER);
		label_CreateItem.setFont(new Font("Verdana", Font.PLAIN, 13));
		label_CreateItem.setBounds(10, 11, 284, 14);
		panel_itemCreate.add(label_CreateItem);
		
		JSeparator separator_createItem = new JSeparator();
		separator_createItem.setBounds(66, 29, 161, 2);
		panel_itemCreate.add(separator_createItem);
		
		JPanel panel_Projects = new JPanel();
		tabbedPane_home.addTab("Proyectos", null, panel_Projects, null);
		panel_Projects.setLayout(null);
		jlist_projects = new JList<Project>();
		
				jlist_projects.setBounds(10, 30, 236, 313);
				jlist_projects.setBorder(new LineBorder(new Color(0, 0, 0)));
				panel_Projects.add(jlist_projects);
				
				JSeparator separator_projects = new JSeparator();
				separator_projects.setBounds(250, 57, 290, 21);
				panel_Projects.add(separator_projects);
				
				JLabel lblAbmProyectos = new JLabel("Alta y Baja de Proyectos");
				lblAbmProyectos.setFont(new Font("Verdana", Font.PLAIN, 13));
				lblAbmProyectos.setHorizontalAlignment(SwingConstants.CENTER);
				lblAbmProyectos.setBounds(250, 30, 290, 16);
				panel_Projects.add(lblAbmProyectos);
				
				btn_addProject = new JButton("Agregar");
				btn_addProject.setFont(new Font("Verdana", Font.PLAIN, 11));
				btn_addProject.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {
						addNewProject(tf_newProject.getText());
					}
				});
				btn_addProject.setEnabled(false);
				btn_addProject.setToolTipText("Agregar nuevo proyecto");
				btn_addProject.setBounds(332, 134, 101, 30);
				panel_Projects.add(btn_addProject);
				
				btn_deleteProject = new JButton("Eliminar");
				btn_deleteProject.setFont(new Font("Verdana", Font.PLAIN, 11));
				btn_deleteProject.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {
						removeProject(jlist_projects.getSelectedValue());
					}
				});
				btn_deleteProject.setToolTipText("Eliminar el proyecto seleccionado en la lista");
				btn_deleteProject.setBounds(250, 270, 101, 30);
				panel_Projects.add(btn_deleteProject);
				
				btn_viewProject = new JButton("Ver");
				btn_viewProject.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						viewProject(jlist_projects.getSelectedValue());
					}
				});
				btn_viewProject.setFont(new Font("Verdana", Font.PLAIN, 11));
				btn_viewProject.setToolTipText("Ver proyecto seleccionado");
				btn_viewProject.setBounds(250, 313, 101, 30);
				panel_Projects.add(btn_viewProject);
				
				tf_newProject = new JTextField();
				// Solo si el usuario es desarrollador puede crear proyectos
				tf_newProject.addKeyListener(new KeyAdapter() {
					@Override
					public void keyTyped(KeyEvent e) {
						if (tf_newProject.getText().length() >= 0 && currentUser.isDeveloper()) {
							btn_addProject.setEnabled(true);
						}
					}
				});
				tf_newProject.setFont(new Font("Verdana", Font.PLAIN, 11));
				tf_newProject.setToolTipText("tf_newProject");
				tf_newProject.setBounds(332, 89, 146, 21);
				panel_Projects.add(tf_newProject);
				tf_newProject.setColumns(10);
				
				JLabel lblNombreNewProject = new JLabel("Nombre");
				lblNombreNewProject.setFont(new Font("Verdana", Font.PLAIN, 11));
				lblNombreNewProject.setBounds(276, 92, 46, 14);
				panel_Projects.add(lblNombreNewProject);
		
		JPanel panel_users = new JPanel();
		tabbedPane_home.addTab("Usuarios", null, panel_users, null);
		panel_users.setLayout(null);
		
		jlist_allUsers = new JList<User>();
		jlist_allUsers.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				chckbxAdmin.setSelected(jlist_allUsers.getSelectedValue().isAdmin());
				chckbxDeveloper.setSelected(jlist_allUsers.getSelectedValue().isDeveloper());
			}
		});
		jlist_allUsers.setBorder(new LineBorder(new Color(0, 0, 0)));
		jlist_allUsers.setBounds(10, 30, 236, 313);
		panel_users.add(jlist_allUsers);
		
		chckbxAdmin = new JCheckBox("Administrador");
		chckbxAdmin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				adminValueChanged(jlist_allUsers.getSelectedValue());
			}
		});
		chckbxAdmin.setHorizontalAlignment(SwingConstants.CENTER);
		chckbxAdmin.setBounds(252, 103, 325, 23);
		panel_users.add(chckbxAdmin);
		
		chckbxDeveloper = new JCheckBox("Desarrollador");
		chckbxDeveloper.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				developerValueChanged(jlist_allUsers.getSelectedValue());
			}
		});
		chckbxDeveloper.setHorizontalAlignment(SwingConstants.CENTER);
		chckbxDeveloper.setBounds(252, 199, 325, 23);
		panel_users.add(chckbxDeveloper);
		
		JButton btn_deleteUser = new JButton("Eliminar");
		btn_deleteUser.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				deleteUser(jlist_allUsers.getSelectedValue());
			}
		});
		btn_deleteUser.setToolTipText("Eliminar el proyecto seleccionado en la lista");
		btn_deleteUser.setFont(new Font("Verdana", Font.PLAIN, 11));
		btn_deleteUser.setBounds(259, 313, 101, 30);
		panel_users.add(btn_deleteUser);
		
		JLabel lblRolDeUsuario = new JLabel("Rol de usuario");
		lblRolDeUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		lblRolDeUsuario.setBounds(256, 64, 321, 14);
		panel_users.add(lblRolDeUsuario);
		
		JLabel lblRolEnProyectos = new JLabel("Rol en proyectos");
		lblRolEnProyectos.setHorizontalAlignment(SwingConstants.CENTER);
		lblRolEnProyectos.setBounds(256, 161, 321, 14);
		panel_users.add(lblRolEnProyectos);
		
		
		/* --- Project */
		
		/*----Users */
		lblLogeadoComo = new JLabel("Logeado como:");
		lblLogeadoComo.setFont(new Font("Verdana", Font.PLAIN, 13));
		lblLogeadoComo.setBounds(10, 5, 214, 23);
		panel_home.add(lblLogeadoComo);

		lblRol = new JLabel("Rol:");
		lblRol.setFont(new Font("Verdana", Font.PLAIN, 13));
		lblRol.setBounds(233, 5, 180, 23);
		panel_home.add(lblRol);
		
		JLabel lblCerrarSesion = new JLabel("Cerrar sesion");
		lblCerrarSesion.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent arg0) {

				Cursor cursor = new Cursor(Cursor.HAND_CURSOR);
		        lblCerrarSesion.setCursor(cursor);
			}
		});
		lblCerrarSesion.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				logout();
			}
		});
		lblCerrarSesion.setForeground(SystemColor.textHighlight);
		lblCerrarSesion.setFont(new Font("Verdana", Font.PLAIN, 13));
		lblCerrarSesion.setBackground(SystemColor.textHighlight);
		lblCerrarSesion.setBounds(479, 5, 99, 23);
		panel_home.add(lblCerrarSesion);
		
		JPanel panel_project = new JPanel();
		contentPane.add(panel_project, PROJECT);
		panel_project.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(SwingConstants.TOP);
		tabbedPane.setBounds(0, 30, 588, 392);
		panel_project.add(tabbedPane);
		
		JPanel panel_Team = new JPanel();
		tabbedPane.addTab("Equipo", null, panel_Team, null);
		panel_Team.setLayout(null);
		
		JLabel lblMiembros = new JLabel("Miembros del equipo");
		lblMiembros.setFont(new Font("Verdana", Font.PLAIN, 13));
		lblMiembros.setHorizontalAlignment(SwingConstants.CENTER);
		lblMiembros.setBounds(10, 12, 236, 26);
		panel_Team.add(lblMiembros);
		
		JLabel lblAddTeamMember = new JLabel("A\u00F1adir usuario al equipo");
		lblAddTeamMember.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddTeamMember.setFont(new Font("Verdana", Font.PLAIN, 11));
		lblAddTeamMember.setBounds(318, 70, 189, 14);
		panel_Team.add(lblAddTeamMember);
		
		JButton btn_addTeamMember = new JButton("A\u00F1adir");
		btn_addTeamMember.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				addNewTeamMember((User)jcbx_users.getSelectedItem());
			}

		});
		btn_addTeamMember.setBounds(359, 149, 101, 30);
		panel_Team.add(btn_addTeamMember);
		
		jlist_teamMembers = new JList<User>();
		jlist_teamMembers.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				chckbxDefaultInCharge.setEnabled(true);
				chckbxDefaultInCharge.setSelected(currentProject.getDefaultInCharge().equals(jlist_teamMembers.getSelectedValue()));
			}
		});
		jlist_teamMembers.setFont(new Font("Verdana", Font.PLAIN, 11));
		jlist_teamMembers.setBorder(new LineBorder(new Color(0, 0, 0)));
		jlist_teamMembers.setBounds(10, 49, 236, 301);
		panel_Team.add(jlist_teamMembers);
		
		jcbx_users = new JComboBox<User>();
		jcbx_users.setBounds(318, 110, 189, 20);
		panel_Team.add(jcbx_users);
		
		JButton btn_deleteTeamMember = new JButton("Eliminar");
		btn_deleteTeamMember.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				removeTeamMember(jlist_teamMembers.getSelectedValue());
			}
		});
		btn_deleteTeamMember.setToolTipText("Eliminar el tipo de item del proyecto");
		btn_deleteTeamMember.setFont(new Font("Verdana", Font.PLAIN, 11));
		btn_deleteTeamMember.setBounds(256, 320, 101, 30);
		panel_Team.add(btn_deleteTeamMember);
		
		chckbxDefaultInCharge = new JCheckBox("Responsable por defecto");
		chckbxDefaultInCharge.setFont(new Font("Verdana", Font.PLAIN, 11));
		chckbxDefaultInCharge.setEnabled(false);
		chckbxDefaultInCharge.setBounds(252, 275, 226, 23);
		panel_Team.add(chckbxDefaultInCharge);
		
		JPanel panel_itemTypes = new JPanel();
		tabbedPane.addTab("Tipo de Item", null, panel_itemTypes, null);
		panel_itemTypes.setLayout(null);
		
		jlist_itemTypes = new JList<ItemType>();
		jlist_itemTypes.setFont(new Font("Verdana", Font.PLAIN, 11));
		jlist_itemTypes.setBorder(new LineBorder(new Color(0, 0, 0)));
		jlist_itemTypes.setBounds(10, 42, 236, 301);
		panel_itemTypes.add(jlist_itemTypes);
		
		JButton btn_deleteIItemType = new JButton("Eliminar");
		btn_deleteIItemType.setToolTipText("Eliminar el tipo de item del proyecto");
		btn_deleteIItemType.setFont(new Font("Verdana", Font.PLAIN, 11));
		btn_deleteIItemType.setBounds(256, 313, 101, 30);
		panel_itemTypes.add(btn_deleteIItemType);
		
		JPanel panel_items = new JPanel();
		tabbedPane.addTab("Items", null, panel_items, null);
		
		jlist_items = new JList<Item>();
		jlist_items.setBounds(10, 42, 157, 312);
		jlist_items.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Cargar el historial del item seleccionado
			}
		});
		panel_items.setLayout(null);
		jlist_items.setToolTipText("Clickee un item para ver su historial");
		jlist_items.setFont(new Font("Verdana", Font.PLAIN, 11));
		jlist_items.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_items.add(jlist_items);
		
		JButton btn_deleteItem = new JButton("Eliminar");
		btn_deleteItem.setBounds(237, 227, 101, 30);
		btn_deleteItem.setToolTipText("Eliminar item del proyecto");
		btn_deleteItem.setFont(new Font("Verdana", Font.PLAIN, 11));
		panel_items.add(btn_deleteItem);
		
		JLabel lblItemsDelProyecto = new JLabel("Items del proyecto");
		lblItemsDelProyecto.setBounds(10, 11, 140, 20);
		lblItemsDelProyecto.setFont(new Font("Verdana", Font.PLAIN, 13));
		lblItemsDelProyecto.setHorizontalAlignment(SwingConstants.CENTER);
		panel_items.add(lblItemsDelProyecto);
		
		jlist_history = new JList<State>();
		jlist_history.setBounds(413, 44, 160, 310);
		jlist_history.setFont(new Font("Verdana", Font.PLAIN, 11));
		jlist_history.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_items.add(jlist_history);
		
		JLabel lblHistorial = new JLabel("Historial");
		lblHistorial.setBounds(430, 11, 143, 20);
		lblHistorial.setFont(new Font("Verdana", Font.PLAIN, 13));
		lblHistorial.setHorizontalAlignment(SwingConstants.CENTER);
		panel_items.add(lblHistorial);
		
		JButton btn_stateChange = new JButton("Cambio de estado");
		btn_stateChange.setBounds(206, 176, 160, 30);
		btn_stateChange.setToolTipText("Cambiar de estado un item");
		btn_stateChange.setFont(new Font("Verdana", Font.PLAIN, 11));
		panel_items.add(btn_stateChange);
		
		JButton btn_userInCharge = new JButton("Cambio de usuario");
		btn_userInCharge.setBounds(206, 123, 160, 30);
		btn_userInCharge.setToolTipText("Cambiar el responsable del item");
		btn_userInCharge.setFont(new Font("Verdana", Font.PLAIN, 11));
		panel_items.add(btn_userInCharge);
		
		labelLogeadoComo2 = new JLabel("Logeado como:");
		labelLogeadoComo2.setFont(new Font("Verdana", Font.PLAIN, 13));
		labelLogeadoComo2.setBounds(10, 5, 225, 23);
		panel_project.add(labelLogeadoComo2);
		
		labelRol2 = new JLabel("Rol:");
		labelRol2.setFont(new Font("Verdana", Font.PLAIN, 13));
		labelRol2.setBounds(234, 5, 168, 23);
		panel_project.add(labelRol2);
		
		JLabel labelCerrarSesion2 = new JLabel("Cerrar sesion");
		labelCerrarSesion2.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent arg0) {
				Cursor cursor = new Cursor(Cursor.HAND_CURSOR);
		        labelCerrarSesion2.setCursor(cursor);
			}
		});
		labelCerrarSesion2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				logout();
			}
		});
		labelCerrarSesion2.setForeground(SystemColor.textHighlight);
		labelCerrarSesion2.setFont(new Font("Verdana", Font.PLAIN, 13));
		labelCerrarSesion2.setBackground(SystemColor.textHighlight);
		labelCerrarSesion2.setBounds(476, 5, 92, 23);
		panel_project.add(labelCerrarSesion2);
		
		JLabel lblInicio = new JLabel("Inicio");
		lblInicio.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				changePanel(HOME);
			}
		});
		lblInicio.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				Cursor cursor = new Cursor(Cursor.HAND_CURSOR);
		        lblInicio.setCursor(cursor);
			}
		});
		lblInicio.setForeground(SystemColor.textHighlight);
		lblInicio.setFont(new Font("Verdana", Font.PLAIN, 13));
		lblInicio.setBounds(420, 5, 46, 23);
		panel_project.add(lblInicio);
	}
	
	/* metodo para cambiar los paneles sin tener que hacer siempre el casteo en el metodo donde quiero cambiar el panel */
	public void changePanel(String panelName) {
		CardLayout c = (CardLayout)(contentPane).getLayout();
		c.show(contentPane, panelName);
		tf_username.setText("");
		pf_Password.setText("");
	}
	
	public void resetSignUp() {
		tf_surname.setText("");
		tf_name.setText("");
		tf_email.setText("");
		tf_signUpUsername.setText("");
		pf_SignUpPassword.setText("");
	}
	
	public void authenticate(String username, String password) {
		currentUser = controller.login(username, password);
		if (currentUser != null) {
			changePanel(HOME);
			loadProjects();
			loadItemTypes((Project)jcbx_project.getSelectedItem());
			lblLogeadoComo.setText("Logeado como: "+ currentUser.getUsername());
			lblRol.setText("Rol: " + ( currentUser.isDeveloper() ? "Desarrollador" : "Basico"));
			tabbedPane_home.setEnabledAt(2, currentUser.isDeveloper()); // solo desarrolladores pueden ver dentro del proyecto
			tabbedPane_home.setEnabledAt(3, currentUser.isAdmin()); // solo administradores pueden ver todos los usuarios y cambiar sus roles
			btn_addProject.setEnabled(currentUser.isDeveloper() );
			btn_deleteProject.setEnabled(currentUser.isDeveloper());
			btn_viewProject.setEnabled(currentUser.isDeveloper());
			if (currentUser.isAdmin()) {
				loadUsersTab();
			}

		}	
		else {
			JOptionPane.showMessageDialog(null, "Inicio de sesion fallido, usuario y/o contraseña incorrecto/a");
		}
		
	}



	
	/* GENERAL */
	
	public void loadProjects() {
		ArrayList<Project> projects = controller.getProjects();
		DefaultListModel<Project> dlm = new DefaultListModel<Project>();
		DefaultComboBoxModel<Project> dcbxm = new DefaultComboBoxModel<Project>();
		for (Project p : projects) {
			dlm.addElement(p);
			dcbxm.addElement(p);
		}
		jcbx_project.setModel(dcbxm);
		jlist_projects.setModel(dlm);
	}
	
	
	/* HOME -> USERS */
	private void loadUsersTab() {
		ArrayList<User> users = controller.getUsers();
		DefaultListModel<User> dlm = new DefaultListModel<User>();
		for (User u : users) {
			dlm.addElement(u);
		}
		if(dlm.contains(currentUser)) {
			dlm.removeElement(currentUser);
		}
		jlist_allUsers.setModel(dlm);
	}
	
	
	private void logout() {
		changePanel(SIGNIN);
	}
	
	
	public void deleteUser(User u) {
		if(u != null) {
			if (controller.deleteUser(u)) {
				JOptionPane.showMessageDialog(null, "Usuario eliminado exitosamente.");
				loadUsersTab();
			}
			else {
				JOptionPane.showMessageDialog(null, "Error al eliminar usuario.");
			}
		}
		else {
			JOptionPane.showMessageDialog(null, "No eligió ningun usuario.");
		}
	}
	
	public void adminValueChanged(User u) {
		if (u != null) {
			if(chckbxAdmin.isSelected())
				controller.giveAdmin(u, true);
			else 
				controller.giveAdmin(u, false);
		}	
		else {
			JOptionPane.showMessageDialog(null, "No eligió ningun usuario.");
		}
	}
	
	public void developerValueChanged(User u) {
		if (u != null) {
			if(chckbxDeveloper.isSelected())
				controller.makeDeveloper(u, true);
			else
				controller.makeDeveloper(u, false);
		}
		else {
			JOptionPane.showMessageDialog(null, "No eligió ningun usuario.");
		}
	}
	
	/* Home -> Project */
	public void addNewProject(String proj) {
		Project p = new Project(proj);
		if (controller.addProject(p)) {
			JOptionPane.showMessageDialog(null,  "Proyecto añadido exitosamente!");
			tf_newProject.setText("");
			loadProjects();

		}
		else {
			JOptionPane.showMessageDialog(null,  "Error al añadir proyecto.");
		}
	}
	
	public void removeProject(Project project) {
		if (controller.removeProject(project)) {
			JOptionPane.showMessageDialog(null,  "Proyecto eliminado.");
			loadProjects();
		}
		else {
			JOptionPane.showMessageDialog(null,  "Error al eliminar proyecto.");
		}
	}
	
	
	/*Home -> Item */
	/*Cuando cambia el valor de proyecto, trae sus tipos de item asociados al combobox*/
	public void loadItemTypes(Project p) {
		DefaultComboBoxModel<ItemType> dcbxm = new DefaultComboBoxModel<ItemType>();
		ArrayList<ItemType> ips = ((Project)jcbx_project.getSelectedItem()).getItemTypes();
		for(ItemType ip : ips) {
			dcbxm.addElement(ip);
		}
		jcbx_itemType.setModel(dcbxm);
	}
	
	/*Agrega el nuevo item a un proyecto*/
	public void addNewItem(Project p, String i) {
		Item item = new Item(i);
		
		if (p.addItem(item) ) {
			JOptionPane.showMessageDialog(null, "Item reportado exitosamente");
		}
		else {
			JOptionPane.showMessageDialog(null, "Error al reportar item");
		}
	}
	
	/* VISTA DE PROYECTO */
	
	public void viewProject(Project project) {
		currentProject = project;
		if (currentProject != null) {
			this.setTitle("BugTracker - Proyecto " + currentProject.getName());
			labelLogeadoComo2.setText("Logeado como: "+ currentUser.getUsername());
			labelRol2.setText("Rol: " + ( currentUser.isDeveloper() ? "Desarrollador" : "Basico"));
			changePanel(PROJECT);
			// metodos para cargar equipo, items y tipos de item del proyecto
			loadTeam();
			loadUsers();
			loadItemTypes();
			loadItems();
		}
		else {
			JOptionPane.showMessageDialog(null, "No se eligió ningun proyecto.");
		}
	}
	
	

	
	//carga el equipo en el jlist de equipo y en el combobox de empleados para el item
	// ya que comparten los mismos datos
	public void loadTeam(){
		ArrayList<User> team = currentProject.getTeam();
		DefaultListModel<User> dlm = new DefaultListModel<User>();
		DefaultComboBoxModel<User> dcbxm = new DefaultComboBoxModel<User>();
		for (User u : team) {
			dlm.addElement(u);
			dcbxm.addElement(u);
		}
		jlist_teamMembers.setModel(dlm);
		//jcbx_userInCharge.setModel(dcbxm);
	}
	
	public void loadUsers() {
		ArrayList<User> users = controller.getUsers();
		ArrayList<User> team = currentProject.getTeam();
		DefaultComboBoxModel<User> dcbxm = new DefaultComboBoxModel<User>();
		for (User u : users) {
			if(! (team.contains(u))) {
				dcbxm.addElement(u);
			}
		}
		jcbx_users.setModel(dcbxm);
	}
	
	
	public void addNewTeamMember(User user) {
		if(user != null)
		{
			if (currentProject.addTeamMember(user) ) {
				JOptionPane.showMessageDialog(null, "Usuario agregado exitosamente al equipo!");
				loadUsers();
				loadTeam();
			}
			else {
				JOptionPane.showMessageDialog(null, "Error al agregar usuario al equipo.");
			}
		}
		else {
			JOptionPane.showMessageDialog(null, "No se eligió ningun usuario");
		}
	}
	
	public void removeTeamMember(User user) {
		if(user != null) {
			if (currentProject.removeTeamMember(user) ) {
				JOptionPane.showMessageDialog(null, "Usuario eliminado del equipo.");
				loadUsers();
				loadTeam();
			}
			else {
				JOptionPane.showMessageDialog(null, "Error al eliminar usuario");
			}
		}
		else {
			JOptionPane.showMessageDialog(null, "No se seleccionó ningun usuario.");
		}
	}
	
	//Este metodo es para cuando se esta dentro de la pestaña del proyecto
	public void loadItemTypes() {
		DefaultComboBoxModel<ItemType> dcbxm = new DefaultComboBoxModel<ItemType>();
		DefaultListModel<ItemType> dlm = new DefaultListModel<ItemType>();
		
		for(ItemType ip : currentProject.getItemTypes()) {
			dlm.addElement(ip);
			dcbxm.addElement(ip);
		}
		jlist_itemTypes.setModel(dlm);
		jcbx_itemType.setModel(dcbxm);
	}
	
	public void loadItems() {
		DefaultListModel<Item> dlm = new DefaultListModel<Item>();
		for (Item i : currentProject.getItems()) {
			dlm.addElement(i);
		}
		jlist_items.setModel(dlm);
	}
}
