import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.sql.*;

import javax.swing.*;

public class Gui implements ActionListener {
	

	private JFrame frame;
	private JTextField username;
	private JPasswordField password;
	private JButton newUser;
	private JButton login;
	private JButton cancel;
	private JRadioButton country;
	private JRadioButton region;
	private JRadioButton province;
	private JTextField search;
	private JButton searchButton;
	private JPanel panel;
	
	/**
	 * @param frame
	 */
	public Gui(JFrame frame) {
		this.frame = frame;
	}
	
	protected JComponent createLoginFields() {
		JPanel panel = new JPanel(new SpringLayout());
		
		panel.setPreferredSize(new Dimension(400,100));

		String[] labelStrings = {
			"Username: ",
	        "Password: ",
	    };

        JLabel[] labels = new JLabel[labelStrings.length];
        JComponent[] fields = new JComponent[labelStrings.length];
        int fieldNum = 0;

        //Create the text field and set it up.
        username  = new JTextField();
        username.setColumns(20);
        fields[fieldNum++] = username;

        password = new JPasswordField();
        password.setColumns(20);
        fields[fieldNum++] = password;
        
        password.setActionCommand("password");

        //Associate label/field pairs, add everything,
        //and lay it out.
        for (int i = 0; i < labelStrings.length; i++) {
            labels[i] = new JLabel(labelStrings[i],
                                   JLabel.TRAILING);
            labels[i].setLabelFor(fields[i]);
            panel.add(labels[i]);
            panel.add(fields[i]);

            //Add listeners to each field.
            JTextField tf = null;
            if (fields[i] instanceof JPasswordField) {
                tf = (JPasswordField)fields[i];
            } else {
                tf = (JTextField)fields[i];
            }
            tf.addActionListener(this);
            
        }
        
        makeCompactGrid(panel,labelStrings.length, 2, 10, 10, 10, 10);
        
        return panel;
    }
	
	protected JComponent searchField() {
		JPanel panel = new JPanel(new SpringLayout());
		
		panel.setPreferredSize(new Dimension(400,100));

        search = new JTextField();
        search.setColumns(20);

        JLabel searchLabel = new JLabel("Search: ", JLabel.TRAILING);

        searchLabel.setLabelFor(search);
        panel.add(searchLabel);
        panel.add(search);

        //Add listeners to each field.
        JTextField tf = (JTextField) search;

        tf.addActionListener(this);

        
        makeCompactGrid(panel, 1, 2, 10, 10, 10, 10);
        
        return panel;
    }
	
	protected JComponent loginButton() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.TRAILING));

        newUser = new JButton("Create New User");
		newUser.setVerticalTextPosition(AbstractButton.CENTER);
		newUser.setHorizontalTextPosition(AbstractButton.LEADING);
		newUser.setMnemonic(KeyEvent.VK_D);
		newUser.setActionCommand("createuser");

		newUser.addActionListener(this);
		newUser.setToolTipText("Click this button to create a new user.");
		
		panel.add(newUser);
		
		login = new JButton("Login");
		login.setVerticalTextPosition(AbstractButton.CENTER);
		login.setHorizontalTextPosition(AbstractButton.LEADING);
		login.setMnemonic(KeyEvent.VK_D);
		login.setActionCommand("login");

		login.addActionListener(this);
		login.setToolTipText("Click this button to login with the entered user name and password.");
		
		panel.add(login);
		
		cancel = new JButton("Cancel");
		cancel.setVerticalTextPosition(AbstractButton.CENTER);
		cancel.setHorizontalTextPosition(AbstractButton.LEADING);
		cancel.setMnemonic(KeyEvent.VK_D);
		cancel.setActionCommand("cancel");

		cancel.addActionListener(this);
		cancel.setToolTipText("Click this button to cancel/quit.");
		
		panel.add(cancel);
		
        panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        return panel;
    }
	
	protected JComponent searchButtons() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.TRAILING));

		
		searchButton = new JButton("Search");
        searchButton.setVerticalTextPosition(AbstractButton.CENTER);
		searchButton.setHorizontalTextPosition(AbstractButton.LEADING);
		searchButton.setMnemonic(KeyEvent.VK_D);
		searchButton.setActionCommand("search");

		searchButton.addActionListener(this);
		searchButton.setToolTipText("Click this button to search.");
		
		panel.add(searchButton);
		
		cancel = new JButton("Cancel");
		cancel.setVerticalTextPosition(AbstractButton.CENTER);
		cancel.setHorizontalTextPosition(AbstractButton.LEADING);
		cancel.setMnemonic(KeyEvent.VK_D);
		cancel.setActionCommand("cancel");

		cancel.addActionListener(this);
		cancel.setToolTipText("Click this button to cancel/quit.");
		
		panel.add(cancel);
		
        panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        return panel;
    }
	
	protected JComponent locationButtons() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.TRAILING));

        country = new JRadioButton("Country");
        country.setVerticalTextPosition(AbstractButton.CENTER);
		country.setHorizontalTextPosition(AbstractButton.LEADING);
		country.setMnemonic(KeyEvent.VK_D);
		country.setActionCommand("country");

		country.addActionListener(this);
		country.setToolTipText("Click this button to search wineries by country.");
		
		panel.add(country);
		
		region = new JRadioButton("Region");
        region.setVerticalTextPosition(AbstractButton.CENTER);
		region.setHorizontalTextPosition(AbstractButton.LEADING);
		region.setMnemonic(KeyEvent.VK_D);
		region.setActionCommand("region");

		region.addActionListener(this);
		region.setToolTipText("Click this button to search wineries by region.");
		
		panel.add(region);
		
		province = new JRadioButton("Province");
        province.setVerticalTextPosition(AbstractButton.CENTER);
		province.setHorizontalTextPosition(AbstractButton.LEADING);
		province.setMnemonic(KeyEvent.VK_D);
		province.setActionCommand("province");

		province.addActionListener(this);
		province.setToolTipText("Click this button to search wineries by province.");
		
		panel.add(province);
		
        panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        return panel;
    }
	
	 public void createAndShowGUI() {
	        //Create and set up the window.
	        
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	 
	        drawLogin(0);
	    }
	 
	 /**
		 * @param i 
		 * 
		 */
		public void drawSearch(int help) {
			
			panel = new JPanel();
			
			
			panel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
			
			JLabel reprompt = new JLabel();
			
			
			if (help == 1) {
				reprompt.setText("Select a location search type.");
				panel.add(reprompt,BorderLayout.CENTER);
				panel.add(new JSeparator(JSeparator.HORIZONTAL), BorderLayout.LINE_START);
			}
			else if (help == 2) {
				reprompt.setText("No results.");
				panel.add(reprompt,BorderLayout.CENTER);
				panel.add(new JSeparator(JSeparator.HORIZONTAL), BorderLayout.LINE_START);
			}
			
			panel.setPreferredSize(new Dimension(400,150));
			
			panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
			
			panel.add(locationButtons());
			panel.add(searchField());
			panel.add(searchButtons());
			
			frame.getContentPane().add(panel, BorderLayout.CENTER);
	 
			//Display the window.
			frame.pack();
			frame.setVisible(true);
		}

	/**
	 * @param i 
	 * 
	 */
	public void drawLogin(int subsequent) {
		
		panel = new JPanel();
		
		JLabel reprompt = new JLabel();
		
		panel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
		
		if (subsequent == 1) {
			reprompt.setText("Login failed. Check your username and password.");
			panel.add(reprompt,BorderLayout.CENTER);
			panel.add(new JSeparator(JSeparator.HORIZONTAL), BorderLayout.LINE_START);
		}
		
		
		panel.setPreferredSize(new Dimension(400,150));
		
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		
		panel.add(createLoginFields());
		panel.add(loginButton());
		
		frame.getContentPane().add(panel, BorderLayout.CENTER);
 
		//Display the window.
		frame.pack();
		frame.setVisible(true);
	}
	 
	/* Used by makeCompactGrid. */
	private static SpringLayout.Constraints getConstraintsForCell(int row, int col,Container parent,int cols) {
	    SpringLayout layout = (SpringLayout) parent.getLayout();
	    Component c = parent.getComponent(row * cols + col);
	    return layout.getConstraints(c);
	}
	
	/**
	 * Aligns the first <code>rows</code> * <code>cols</code>
	 * components of <code>parent</code> in
	 * a grid. Each component in a column is as wide as the maximum
	 * preferred width of the components in that column;
	 * height is similarly determined for each row.
	 * The parent is made just big enough to fit them all.
	 *
	 * @param rows number of rows
	 * @param cols number of columns
	 * @param initialX x location to start the grid at
	 * @param initialY y location to start the grid at
	 * @param xPad x padding between cells
	 * @param yPad y padding between cells
	 */
	public static void makeCompactGrid(Container parent, int rows, int cols, int initialX, int initialY, int xPad, int yPad) {
	    SpringLayout layout;
	    try {
	        layout = (SpringLayout)parent.getLayout();
	    } catch (ClassCastException exc) {
	        System.err.println("The first argument to makeCompactGrid must use SpringLayout.");
	        return;
	    }
	
	    //Align all cells in each column and make them the same width.
	    Spring x = Spring.constant(initialX);
	    for (int c = 0; c < cols; c++) {
	        Spring width = Spring.constant(0);
	        for (int r = 0; r < rows; r++) {
	            width = Spring.max(width,
	                               getConstraintsForCell(r, c, parent, cols).
	                                   getWidth());
	        }
	        for (int r = 0; r < rows; r++) {
	            SpringLayout.Constraints constraints =
	                    getConstraintsForCell(r, c, parent, cols);
	            constraints.setX(x);
	            constraints.setWidth(width);
	        }
	        x = Spring.sum(x, Spring.sum(width, Spring.constant(xPad)));
	    }
	
	    //Align all cells in each row and make them the same height.
	    Spring y = Spring.constant(initialY);
	    for (int r = 0; r < rows; r++) {
	        Spring height = Spring.constant(0);
	        for (int c = 0; c < cols; c++) {
	            height = Spring.max(height,
	                                getConstraintsForCell(r, c, parent, cols).
	                                    getHeight());
	        }
	        for (int c = 0; c < cols; c++) {
	            SpringLayout.Constraints constraints =
	                    getConstraintsForCell(r, c, parent, cols);
	            constraints.setY(y);
	            constraints.setHeight(height);
	        }
	        y = Spring.sum(y, Spring.sum(height, Spring.constant(yPad)));
	    }
	
	    //Set the parent's size.
	    SpringLayout.Constraints pCons = layout.getConstraints(parent);
	    pCons.setConstraint(SpringLayout.SOUTH, y);
	    pCons.setConstraint(SpringLayout.EAST, x);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			if ("createuser".equals(e.getActionCommand())) {
				try {
					UserFunctions.createUser(0, 0);
				} catch (SQLException e1) {
					
					e1.printStackTrace();
				}
			}
			else if ("cancel".equals(e.getActionCommand())) {
				System.exit(1);
			}
			else if("login".equals(e.getActionCommand())) {
				String usernameIn = username.getText();
				String passwordIn = new String(password.getPassword());
				

				if (UserFunctions.validateUser(usernameIn, passwordIn)) {
					frame.remove(panel);
					drawSearch(0);
				}
				else {
					frame.remove(panel);
					drawLogin(1);
				}
	
			}
			else if ("country".equals(e.getActionCommand())) {
				country.setSelected(true);
				region.setSelected(false);
				province.setSelected(false);
			}
			else if ("region".equals(e.getActionCommand())) {
				country.setSelected(false);
				region.setSelected(true);
				province.setSelected(false);
			}
			else if ("province".equals(e.getActionCommand())) {
				country.setSelected(false);
				region.setSelected(false);
				province.setSelected(true);
			}
			else if ("search".equals(e.getActionCommand())) {
				String searchText = search.getText();
				
				int ret = -1;
				
				if (country.isSelected()) {
					ret = LocationFunctions.locationSearch(1, searchText);
				}
				else if (region.isSelected()) {
					ret = LocationFunctions.locationSearch(2, searchText);
				}
				else if (province.isSelected()) {
					ret = LocationFunctions.locationSearch(3, searchText);
				}
				
				if (ret == -1) {
					frame.remove(panel);
					drawSearch(1);
				}
				else if (ret == 0) {
					frame.remove(panel);
					drawSearch(2);
				}

			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}



	public JFrame getFrame() {
		return this.frame;
	}



	public void setFrame(JFrame frame) {
		this.frame = frame;
	}


	
}
