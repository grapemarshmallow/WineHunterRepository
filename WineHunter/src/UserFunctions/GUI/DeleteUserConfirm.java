package UserFunctions.GUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import WineObjects.User;

import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.event.AncestorListener;

import Core.WineHunterApplication;
import UserFunctions.Logic.UserUpdate;

import javax.swing.event.AncestorEvent;

public class DeleteUserConfirm extends JDialog {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	
	
	/**
	 * Create the dialog.
	 */
	public DeleteUserConfirm(User user) {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			JLabel lblAreYouSure = new JLabel("Are you sure you would like to delete " + user.getId() + " (" + user.getUsername() + ")?");
			contentPanel.add(lblAreYouSure);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						UserUpdate userUpdate = new UserUpdate();
						try {
							userUpdate.deleteUser(user.getId());
						} catch (SQLException e1) {
							WineHunterApplication.viewUserProfile(user, 1);
							e1.printStackTrace();
						}
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						WineHunterApplication.viewUserProfile(user, 0);
					}
				});
				cancelButton.addAncestorListener(new AncestorListener() {
					public void ancestorAdded(AncestorEvent event) {
					}
					public void ancestorMoved(AncestorEvent event) {
					}
					public void ancestorRemoved(AncestorEvent event) {
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	
}
