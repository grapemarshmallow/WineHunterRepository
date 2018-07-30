package UserFunctions.GUI;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import Core.WineHunterApplication;
import Search.Logic.LoadVariousLists;
import UserFunctions.Logic.UserProfile;
import WineObjects.Keyword;
import WineObjects.User;
import WineObjects.Variety;

import java.awt.Insets;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EditTasterProfile extends JPanel {
	
	//fields
	private static final long serialVersionUID = -7938844906983194519L;
	private JComboBox<Variety> grapeLikeOne;
	private JComboBox<Variety> grapeLikeTwo;
	private JComboBox<Variety> grapeLikeThree;
	private JComboBox<Variety> grapeLikeFour;
	private JComboBox<Variety> grapeLikeFive;
	private JComboBox<Variety> grapeDislikeOne;
	private JComboBox<Variety> grapeDislikeTwo;
	private JComboBox<Variety> grapeDislikeThree;
	private JComboBox<Variety> grapeDislikeFour;
	private JComboBox<Variety> grapeDislikeFive;
	private JComboBox<Keyword> keywordLikeOne;
	private JComboBox<Keyword> keywordLikeTwo;
	private JComboBox<Keyword> keywordLikeThree;
	private JComboBox<Keyword> keywordLikeFour;
	private JComboBox<Keyword> keywordLikeFive;
	private JComboBox<Keyword> keywordDislikeOne;
	private JComboBox<Keyword> keywordDislikeTwo;
	private JComboBox<Keyword> keywordDislikeThree;
	private JComboBox<Keyword> keywordDislikeFour;
	private JComboBox<Keyword> keywordDislikeFive;
	private User user;

	/**
	 * Create the panel for editing the taster profile
	 * @param the taster profile user
	 */
	public EditTasterProfile(User user) {
		
		UserProfile userProfile = new UserProfile();
		
		try {
			userProfile.getTasterProfile(user);
		} catch (SQLException e) {
			WineHunterApplication.splash(2);

			e.printStackTrace();
			return;
		}
		
		this.setUser(user);
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 1.0};
		gridBagLayout.columnWeights = new double[]{1.0};
		setLayout(gridBagLayout);
		
		JPanel titlePanel = new JPanel();
		titlePanel.setName("titlePanel");
		GridBagConstraints gbc_titlePanel = new GridBagConstraints();
		gbc_titlePanel.weightx = 1.0;
		gbc_titlePanel.anchor = GridBagConstraints.NORTH;
		gbc_titlePanel.insets = new Insets(15, 15, 15, 15);
		gbc_titlePanel.fill = GridBagConstraints.BOTH;
		gbc_titlePanel.gridx = 0;
		gbc_titlePanel.gridy = 0;
		add(titlePanel, gbc_titlePanel);
		GridBagLayout gbl_titlePanel = new GridBagLayout();
		titlePanel.setLayout(gbl_titlePanel);
		
		JLabel titleLabel = new JLabel("Edit Taster Profile");
		titleLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		titleLabel.setName("titleLabel");
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setFont(WineHunterApplication.format.getHeadingFont());
		GridBagConstraints gbc_titleLabel = new GridBagConstraints();
		gbc_titleLabel.weightx = 1.0;
		gbc_titleLabel.anchor = GridBagConstraints.NORTH;
		gbc_titleLabel.fill = GridBagConstraints.BOTH;
		gbc_titleLabel.gridx = 0;
		gbc_titleLabel.gridy = 0;
		titlePanel.add(titleLabel, gbc_titleLabel);
		
		grapeVarietyPanel();
		keywordPanel();
		
		JPanel acceptPanel = new JPanel();
		GridBagConstraints gbc_acceptPanel = new GridBagConstraints();
		gbc_acceptPanel.fill = GridBagConstraints.BOTH;
		gbc_acceptPanel.gridx = 0;
		gbc_acceptPanel.gridy = 3;
		add(acceptPanel, gbc_acceptPanel);
		
		JButton accept = new JButton("Accept");
		accept.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<Keyword> keywordLike = new ArrayList<Keyword>();
				ArrayList<Keyword> keywordDislike = new ArrayList<Keyword>();
				ArrayList<Variety> varietyLike = new ArrayList<Variety>();
				ArrayList<Variety> varietyDislike = new ArrayList<Variety>();
				if (grapeLikeOne.getSelectedItem() != null) {
					varietyLike.add((Variety) grapeLikeOne.getSelectedItem());
				}
				if (grapeLikeTwo.getSelectedItem() != null) {
					varietyLike.add((Variety) grapeLikeTwo.getSelectedItem());
				}
				if (grapeLikeThree.getSelectedItem() != null) {
					varietyLike.add((Variety) grapeLikeThree.getSelectedItem());
				}
				if (grapeLikeFour.getSelectedItem() != null) {
					varietyLike.add((Variety) grapeLikeFour.getSelectedItem());
				}
				if (grapeLikeFive.getSelectedItem() != null) {
					varietyLike.add((Variety) grapeLikeFive.getSelectedItem());
				}
				if (grapeDislikeOne.getSelectedItem() != null) {
					varietyDislike.add((Variety) grapeDislikeOne.getSelectedItem());
				}
				if (grapeDislikeTwo.getSelectedItem() != null) {
					varietyDislike.add((Variety) grapeDislikeTwo.getSelectedItem());
				}
				if (grapeDislikeThree.getSelectedItem() != null) {
					varietyDislike.add((Variety) grapeDislikeThree.getSelectedItem());
				}
				if (grapeDislikeFour.getSelectedItem() != null) {
					varietyDislike.add((Variety) grapeDislikeFour.getSelectedItem());
				}
				if (grapeDislikeFive.getSelectedItem() != null) {
					varietyDislike.add((Variety) grapeDislikeFive.getSelectedItem());
				}
				if (keywordLikeOne.getSelectedItem() != null) {
					keywordLike.add((Keyword) keywordLikeOne.getSelectedItem());
				}
				if (keywordLikeTwo.getSelectedItem() != null) {
					keywordLike.add((Keyword) keywordLikeTwo.getSelectedItem());
				}
				if (keywordLikeThree.getSelectedItem() != null) {
					keywordLike.add((Keyword) keywordLikeThree.getSelectedItem());
				}
				if (keywordLikeFour.getSelectedItem() != null) {
					keywordLike.add((Keyword) keywordLikeFour.getSelectedItem());
				}
				if (keywordLikeFive.getSelectedItem() != null) {
					keywordLike.add((Keyword) keywordLikeFive.getSelectedItem());
				}
				if (keywordDislikeOne.getSelectedItem() != null) {
					keywordDislike.add((Keyword) keywordDislikeOne.getSelectedItem());
				}
				if (keywordDislikeTwo.getSelectedItem() != null) {
					keywordDislike.add((Keyword) keywordDislikeTwo.getSelectedItem());
				}
				if (keywordDislikeThree.getSelectedItem() != null) {
					keywordDislike.add((Keyword) keywordDislikeThree.getSelectedItem());
				}
				if (keywordDislikeFour.getSelectedItem() != null) {
					keywordDislike.add((Keyword) keywordDislikeFour.getSelectedItem());
				}
				if (keywordDislikeFive.getSelectedItem() != null) {
					keywordDislike.add((Keyword) keywordDislikeFive.getSelectedItem());
				}
				
				user.setUserDislikeKeywordList(keywordDislike);
				user.setUserLikeKeywordList(keywordLike);
				user.setUserDislikeVarietyList(varietyDislike);
				user.setUserLikeVarietyList(varietyLike);
				
				int result = 0;
				try {
					result = userProfile.setTasterProfile(user);
					
					
				} catch (SQLException e1) {
					
					e1.printStackTrace();
				}
				
				if (result == 1) {
					WineHunterApplication.viewUserProfile(user, 3, 0);
				}
				else if (result == -1) {
					WineHunterApplication.viewUserProfile(user, 4, 0);
				}
				else {
					WineHunterApplication.viewUserProfile(user, 2, 0);
				}
				
			}
		});
		acceptPanel.add(accept);
	}

	/**
	 * sets up grape variety panel
	 */
	public void grapeVarietyPanel() {
		JPanel GrapeVariety = new JPanel();
		GrapeVariety.setName("GrapeVariety");
		GridBagConstraints gbc_GrapeVariety = new GridBagConstraints();
		gbc_GrapeVariety.insets = new Insets(0, 0, 5, 0);
		gbc_GrapeVariety.weightx = 1.0;
		gbc_GrapeVariety.fill = GridBagConstraints.BOTH;
		gbc_GrapeVariety.gridx = 0;
		gbc_GrapeVariety.gridy = 1;
		add(GrapeVariety, gbc_GrapeVariety);
		GridBagLayout gbl_GrapeVariety = new GridBagLayout();
		GrapeVariety.setLayout(gbl_GrapeVariety);
		
		JLabel labelGrapeVariety = new JLabel("Grape Variety Preferences");
		labelGrapeVariety.setName("labelGrapeVariety");
		labelGrapeVariety.setHorizontalAlignment(SwingConstants.CENTER);
		labelGrapeVariety.setVerticalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_labelGrapeVariety = new GridBagConstraints();
		gbc_labelGrapeVariety.weightx = 1.0;
		gbc_labelGrapeVariety.insets = new Insets(0, 0, 5, 0);
		gbc_labelGrapeVariety.fill = GridBagConstraints.HORIZONTAL;
		gbc_labelGrapeVariety.gridx = 0;
		gbc_labelGrapeVariety.gridy = 0;
		GrapeVariety.add(labelGrapeVariety, gbc_labelGrapeVariety);
		
		JPanel grapeSplit = new JPanel();
		grapeSplit.setName("grapeSplit");
		GridBagConstraints gbc_grapeSplit = new GridBagConstraints();
		gbc_grapeSplit.weightx = 1.0;
		gbc_grapeSplit.insets = new Insets(0, 0, 5, 0);
		gbc_grapeSplit.fill = GridBagConstraints.BOTH;
		gbc_grapeSplit.gridx = 0;
		gbc_grapeSplit.gridy = 1;
		GrapeVariety.add(grapeSplit, gbc_grapeSplit);
		GridBagLayout gbl_grapeSplit = new GridBagLayout();
		gbl_grapeSplit.columnWidths = new int[]{0};
		gbl_grapeSplit.rowHeights = new int[]{0};
		gbl_grapeSplit.columnWeights = new double[]{0.5};
		grapeSplit.setLayout(gbl_grapeSplit);
		
		// set up our panel for liking grape varieties
		grapeLikePanelSet(grapeSplit);
		
		// set up our panel for disliking grape varieties
		grapeDislikePanelSet(grapeSplit);
	}

	/**
	 * set up panel for editing liked grape varieties
	 * @param grapeSplit parent panel for grape stuff
	 */
	public void grapeLikePanelSet(JPanel grapeSplit) {
		JPanel grapeLikePanel = new JPanel();
		grapeLikePanel.setName("grapeLikePanel");
		GridBagConstraints gbc_grapeLikePanel = new GridBagConstraints();
		gbc_grapeLikePanel.weightx = 0.5;
		gbc_grapeLikePanel.insets = new Insets(0, 0, 5, 0);
		gbc_grapeLikePanel.fill = GridBagConstraints.BOTH;
		gbc_grapeLikePanel.gridx = 0;
		gbc_grapeLikePanel.gridy = 0;
		gbc_grapeLikePanel.weightx = 1;
		grapeSplit.add(grapeLikePanel, gbc_grapeLikePanel);
		GridBagLayout gbl_grapeLikePanel = new GridBagLayout();
		grapeLikePanel.setLayout(gbl_grapeLikePanel);
		
		JLabel grapeLIke = new JLabel("Likes");
		grapeLIke.setHorizontalAlignment(SwingConstants.CENTER);
		grapeLIke.setHorizontalTextPosition(SwingConstants.CENTER);
		grapeLIke.setName("grapeLIkeLabel");
		GridBagConstraints gbc_grapeLIke = new GridBagConstraints();
		gbc_grapeLIke.insets = new Insets(0, 0, 5, 0);
		gbc_grapeLIke.fill = GridBagConstraints.BOTH;
		gbc_grapeLIke.gridx = 0;
		gbc_grapeLIke.gridy = 0;
		gbc_grapeLIke.weightx = 1;
		grapeLikePanel.add(grapeLIke, gbc_grapeLIke);
		
		JPanel setGrapeLikePanel = new JPanel();
		setGrapeLikePanel.setName("setGrapeLikePanel");
		GridBagConstraints gbc_setGrapeLikePanel = new GridBagConstraints();
		gbc_setGrapeLikePanel.insets = new Insets(0, 0, 5, 0);
		gbc_setGrapeLikePanel.fill = GridBagConstraints.BOTH;
		gbc_setGrapeLikePanel.gridx = 0;
		gbc_setGrapeLikePanel.gridy = 1;
		grapeLikePanel.add(setGrapeLikePanel, gbc_setGrapeLikePanel);
		GridBagLayout gbl_setGrapeLIkePanel = new GridBagLayout();
		gbl_setGrapeLIkePanel.columnWidths = new int[]{0};
		gbl_setGrapeLIkePanel.rowHeights = new int[]{0};
		gbl_setGrapeLIkePanel.columnWeights = new double[]{Double.MIN_VALUE, 1.0};
		gbl_setGrapeLIkePanel.rowWeights = new double[]{Double.MIN_VALUE};
		setGrapeLikePanel.setLayout(gbl_setGrapeLIkePanel);
		
		JLabel grapeLikeOneLabel = new JLabel("1.");
		grapeLikeOneLabel.setName("grapeLikeOneLabel");
		GridBagConstraints gbc_grapeLikeOneLabel = new GridBagConstraints();
		gbc_grapeLikeOneLabel.weightx = 1.0;
		gbc_grapeLikeOneLabel.insets = new Insets(0, 0, 5, 0);
		gbc_grapeLikeOneLabel.anchor = GridBagConstraints.EAST;
		gbc_grapeLikeOneLabel.gridx = 0;
		gbc_grapeLikeOneLabel.gridy = 0;
		setGrapeLikePanel.add(grapeLikeOneLabel, gbc_grapeLikeOneLabel);
		
		JComboBox<Variety> grapeLikeOne = new JComboBox<Variety>();
		grapeLikeOne.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				itemSelectedVariety(e);
			}
		});
		grapeLikeOne.setName("grapeLikeOne");
		DefaultComboBoxModel<Variety> varietyLike = new DefaultComboBoxModel<Variety>();
		try {
			LoadVariousLists.loadAllVarieties(varietyLike);
		} catch (SQLException e) {
			WineHunterApplication.splash(2);
			e.printStackTrace();
		}
		grapeLikeOne.setModel(varietyLike);

		GridBagConstraints gbc_grapeLikeOne = new GridBagConstraints();
		gbc_grapeLikeOne.fill = GridBagConstraints.HORIZONTAL;
		gbc_grapeLikeOne.gridx = 1;
		gbc_grapeLikeOne.gridy = 0;
		gbc_grapeLikeOne.weightx = 1;
		setGrapeLikePanel.add(grapeLikeOne, gbc_grapeLikeOne);
		
		JLabel grapeLikeTwoLabel = new JLabel("2.");
		grapeLikeTwoLabel.setName("grapeLikeTwoLabel");
		GridBagConstraints gbc_grapeLikeTwoLabel = new GridBagConstraints();
		gbc_grapeLikeTwoLabel.insets = new Insets(0, 0, 5, 0);
		gbc_grapeLikeTwoLabel.anchor = GridBagConstraints.EAST;
		gbc_grapeLikeTwoLabel.gridx = 0;
		gbc_grapeLikeTwoLabel.gridy = 1;
		setGrapeLikePanel.add(grapeLikeTwoLabel, gbc_grapeLikeTwoLabel);
		
		JComboBox<Variety> grapeLikeTwo = new JComboBox<Variety>();
		grapeLikeTwo.setName("grapeLikeTwo");
		DefaultComboBoxModel<Variety> varietyLike2 = new DefaultComboBoxModel<Variety>();
		try {
			LoadVariousLists.loadAllVarieties(varietyLike2);
		} catch (SQLException e) {
			WineHunterApplication.splash(2);
			e.printStackTrace();
		}
		grapeLikeTwo.setModel(varietyLike);

		grapeLikeTwo.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				itemSelectedVariety(e);
			}
		});
		GridBagConstraints gbc_grapeLikeTwo = new GridBagConstraints();
		gbc_grapeLikeTwo.fill = GridBagConstraints.HORIZONTAL;
		gbc_grapeLikeTwo.gridx = 1;
		gbc_grapeLikeTwo.gridy = 1;
		gbc_grapeLikeTwo.weightx =1;
		setGrapeLikePanel.add(grapeLikeTwo, gbc_grapeLikeTwo);
		
		JLabel grapeLikeThreeLabel = new JLabel("3.");
		grapeLikeThreeLabel.setName("grapeLikeThreeLabel");
		GridBagConstraints gbc_grapeLikeThreeLabel = new GridBagConstraints();
		gbc_grapeLikeThreeLabel.insets = new Insets(0, 0, 5, 0);
		gbc_grapeLikeThreeLabel.anchor = GridBagConstraints.EAST;
		gbc_grapeLikeThreeLabel.gridx = 0;
		gbc_grapeLikeThreeLabel.gridy = 2;
		setGrapeLikePanel.add(grapeLikeThreeLabel, gbc_grapeLikeThreeLabel);
		
		JComboBox<Variety> grapeLikeThree = new JComboBox<Variety>();
		grapeLikeThree.setName("grapeLikeThree");
		DefaultComboBoxModel<Variety> varietyLike3 = new DefaultComboBoxModel<Variety>();
		try {
			LoadVariousLists.loadAllVarieties(varietyLike3);
		} catch (SQLException e) {
			WineHunterApplication.splash(2);
			e.printStackTrace();
		}
		grapeLikeThree.setModel(varietyLike);
		grapeLikeThree.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				itemSelectedVariety(e);
			}
		});
		GridBagConstraints gbc_grapeLikeThree = new GridBagConstraints();
		gbc_grapeLikeThree.fill = GridBagConstraints.HORIZONTAL;
		gbc_grapeLikeThree.gridx = 1;
		gbc_grapeLikeThree.gridy = 2;
		setGrapeLikePanel.add(grapeLikeThree, gbc_grapeLikeThree);
		
		JLabel grapeLikeFourLabel = new JLabel("4.");
		grapeLikeFourLabel.setName("grapeLikeFourLabel");
		GridBagConstraints gbc_grapeLikeFourLabel = new GridBagConstraints();
		gbc_grapeLikeFourLabel.insets = new Insets(0, 0, 5, 0);
		gbc_grapeLikeFourLabel.anchor = GridBagConstraints.EAST;
		gbc_grapeLikeFourLabel.gridx = 0;
		gbc_grapeLikeFourLabel.gridy = 3;
		setGrapeLikePanel.add(grapeLikeFourLabel, gbc_grapeLikeFourLabel);
		
		JComboBox<Variety> grapeLikeFour = new JComboBox<Variety>();
		grapeLikeFour.setName("grapeLikeFour");
		DefaultComboBoxModel<Variety> varietyLike4 = new DefaultComboBoxModel<Variety>();
		try {
			LoadVariousLists.loadAllVarieties(varietyLike4);
		} catch (SQLException e) {
			WineHunterApplication.splash(2);
			e.printStackTrace();
		}
		grapeLikeFour.setModel(varietyLike);
		grapeLikeFour.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				itemSelectedVariety(e);
			}
		});
		GridBagConstraints gbc_grapeLikeFour = new GridBagConstraints();
		gbc_grapeLikeFour.fill = GridBagConstraints.HORIZONTAL;
		gbc_grapeLikeFour.gridx = 1;
		gbc_grapeLikeFour.gridy = 3;
		setGrapeLikePanel.add(grapeLikeFour, gbc_grapeLikeFour);
		
		JLabel grapeLikeFiveLabel = new JLabel("5.");
		grapeLikeFiveLabel.setName("grapeLikeFiveLabel");
		GridBagConstraints gbc_grapeLikeFiveLabel = new GridBagConstraints();
		gbc_grapeLikeFiveLabel.insets = new Insets(0, 0, 5, 0);
		gbc_grapeLikeFiveLabel.anchor = GridBagConstraints.EAST;
		gbc_grapeLikeFiveLabel.gridx = 0;
		gbc_grapeLikeFiveLabel.gridy = 4;
		setGrapeLikePanel.add(grapeLikeFiveLabel, gbc_grapeLikeFiveLabel);
		
		JComboBox<Variety> grapeLikeFive = new JComboBox<Variety>();
		grapeLikeFive.setName("grapeLikeFive");
		DefaultComboBoxModel<Variety> varietyLike5 = new DefaultComboBoxModel<Variety>();
		try {
			LoadVariousLists.loadAllVarieties(varietyLike5);
		} catch (SQLException e) {
			WineHunterApplication.splash(2);
			e.printStackTrace();
		}
		grapeLikeFive.setModel(varietyLike);
		grapeLikeFive.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				itemSelectedVariety(e);
			}
		});
		GridBagConstraints gbc_grapeLikeFive = new GridBagConstraints();
		gbc_grapeLikeFive.fill = GridBagConstraints.HORIZONTAL;
		gbc_grapeLikeFive.gridx = 1;
		gbc_grapeLikeFive.gridy = 4;
		setGrapeLikePanel.add(grapeLikeFive, gbc_grapeLikeFive);
	}
	
	/**
	 * set up panel for editing disliked grape varieties
	 * @param grapeSplit parent panel for grape stuff
	 */
	public void grapeDislikePanelSet(JPanel grapeSplit) {
		JPanel grapeDislikePanel = new JPanel();
		grapeDislikePanel.setName("grapeDislikePanel");
		GridBagConstraints gbc_grapeDislikePanel = new GridBagConstraints();
		gbc_grapeDislikePanel.weightx = 0.5;
		gbc_grapeDislikePanel.insets = new Insets(0, 0, 5, 0);
		gbc_grapeDislikePanel.fill = GridBagConstraints.BOTH;
		gbc_grapeDislikePanel.gridx = 1;
		gbc_grapeDislikePanel.gridy = 0;
		gbc_grapeDislikePanel.weightx = 1;
		grapeSplit.add(grapeDislikePanel, gbc_grapeDislikePanel);
		GridBagLayout gbl_grapeDislikePanel = new GridBagLayout();
		gbl_grapeDislikePanel.columnWeights = new double[]{Double.MIN_VALUE, 1.0};
		grapeDislikePanel.setLayout(gbl_grapeDislikePanel);
		
		JLabel grapeLIke = new JLabel("Dislikes");
		grapeLIke.setHorizontalAlignment(SwingConstants.CENTER);
		grapeLIke.setHorizontalTextPosition(SwingConstants.CENTER);
		grapeLIke.setName("grapeLIkeLabel");
		GridBagConstraints gbc_grapeLIke = new GridBagConstraints();
		gbc_grapeLIke.insets = new Insets(0, 0, 5, 0);
		gbc_grapeLIke.fill = GridBagConstraints.BOTH;
		gbc_grapeLIke.gridx = 0;
		gbc_grapeLIke.gridy = 0;
		gbc_grapeLIke.weightx = 1;
		grapeDislikePanel.add(grapeLIke, gbc_grapeLIke);
		
		JPanel setGrapeDislikePanel = new JPanel();
		setGrapeDislikePanel.setName("setGrapeDislikePanel");
		GridBagConstraints gbc_setGrapeDislikePanel = new GridBagConstraints();
		gbc_setGrapeDislikePanel.insets = new Insets(0, 0, 5, 0);
		gbc_setGrapeDislikePanel.fill = GridBagConstraints.BOTH;
		gbc_setGrapeDislikePanel.gridx = 0;
		gbc_setGrapeDislikePanel.gridy = 1;
		grapeDislikePanel.add(setGrapeDislikePanel, gbc_setGrapeDislikePanel);
		GridBagLayout gbl_setGrapeLIkePanel = new GridBagLayout();
		gbl_setGrapeLIkePanel.columnWidths = new int[]{0};
		gbl_setGrapeLIkePanel.rowHeights = new int[]{0};
		gbl_setGrapeLIkePanel.columnWeights = new double[]{Double.MIN_VALUE};
		gbl_setGrapeLIkePanel.rowWeights = new double[]{Double.MIN_VALUE};
		setGrapeDislikePanel.setLayout(gbl_setGrapeLIkePanel);
		
		JLabel grapeDislikeOneLabel = new JLabel("1.");
		grapeDislikeOneLabel.setName("grapeDislikeOneLabel");
		GridBagConstraints gbc_grapeDislikeOneLabel = new GridBagConstraints();
		gbc_grapeDislikeOneLabel.insets = new Insets(0, 0, 5, 0);
		gbc_grapeDislikeOneLabel.anchor = GridBagConstraints.EAST;
		gbc_grapeDislikeOneLabel.gridx = 0;
		gbc_grapeDislikeOneLabel.gridy = 0;
		setGrapeDislikePanel.add(grapeDislikeOneLabel, gbc_grapeDislikeOneLabel);
		
		JComboBox<Variety> grapeDislikeOne = new JComboBox<Variety>();
		grapeDislikeOne.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				itemSelectedVariety(e);
			}
		});
		grapeDislikeOne.setName("grapeDislikeOne");
		DefaultComboBoxModel<Variety> varietyDislike = new DefaultComboBoxModel<Variety>();
		try {
			LoadVariousLists.loadAllVarieties(varietyDislike);
		} catch (SQLException e) {
			WineHunterApplication.splash(2);
			e.printStackTrace();
		}
		grapeDislikeOne.setModel(varietyDislike);
		GridBagConstraints gbc_grapeDislikeOne = new GridBagConstraints();
		gbc_grapeDislikeOne.fill = GridBagConstraints.HORIZONTAL;
		gbc_grapeDislikeOne.gridx = 1;
		gbc_grapeDislikeOne.gridy = 0;
		setGrapeDislikePanel.add(grapeDislikeOne, gbc_grapeDislikeOne);
		
		JLabel grapeDislikeTwoLabel = new JLabel("2.");
		grapeDislikeTwoLabel.setName("grapeDislikeTwoLabel");
		GridBagConstraints gbc_grapeDislikeTwoLabel = new GridBagConstraints();
		gbc_grapeDislikeTwoLabel.insets = new Insets(0, 0, 5, 0);
		gbc_grapeDislikeTwoLabel.anchor = GridBagConstraints.EAST;
		gbc_grapeDislikeTwoLabel.gridx = 0;
		gbc_grapeDislikeTwoLabel.gridy = 1;
		setGrapeDislikePanel.add(grapeDislikeTwoLabel, gbc_grapeDislikeTwoLabel);
		
		JComboBox<Variety> grapeDislikeTwo = new JComboBox<Variety>();
		grapeDislikeTwo.setName("grapeDislikeTwo");
		DefaultComboBoxModel<Variety> varietyDislike2 = new DefaultComboBoxModel<Variety>();
		try {
			LoadVariousLists.loadAllVarieties(varietyDislike2);
		} catch (SQLException e) {
			WineHunterApplication.splash(2);
			e.printStackTrace();
		}
		grapeDislikeTwo.setModel(varietyDislike);
		grapeDislikeTwo.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				itemSelectedVariety(e);
			}
		});
		GridBagConstraints gbc_grapeDislikeTwo = new GridBagConstraints();
		gbc_grapeDislikeTwo.fill = GridBagConstraints.HORIZONTAL;
		gbc_grapeDislikeTwo.gridx = 1;
		gbc_grapeDislikeTwo.gridy = 1;
		setGrapeDislikePanel.add(grapeDislikeTwo, gbc_grapeDislikeTwo);
		
		JLabel grapeDislikeThreeLabel = new JLabel("3.");
		grapeDislikeThreeLabel.setName("grapeDislikeThreeLabel");
		GridBagConstraints gbc_grapeDislikeThreeLabel = new GridBagConstraints();
		gbc_grapeDislikeThreeLabel.insets = new Insets(0, 0, 5, 0);
		gbc_grapeDislikeThreeLabel.anchor = GridBagConstraints.EAST;
		gbc_grapeDislikeThreeLabel.gridx = 0;
		gbc_grapeDislikeThreeLabel.gridy = 2;
		setGrapeDislikePanel.add(grapeDislikeThreeLabel, gbc_grapeDislikeThreeLabel);
		
		JComboBox<Variety> grapeDislikeThree = new JComboBox<Variety>();
		grapeDislikeThree.setName("grapeDislikeThree");
		DefaultComboBoxModel<Variety> varietyDislike3 = new DefaultComboBoxModel<Variety>();
		try {
			LoadVariousLists.loadAllVarieties(varietyDislike3);
		} catch (SQLException e) {
			WineHunterApplication.splash(2);
			e.printStackTrace();
		}
		grapeDislikeThree.setModel(varietyDislike);
		grapeDislikeThree.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				itemSelectedVariety(e);
			}
		});
		GridBagConstraints gbc_grapeDislikeThree = new GridBagConstraints();
		gbc_grapeDislikeThree.fill = GridBagConstraints.HORIZONTAL;
		gbc_grapeDislikeThree.gridx = 1;
		gbc_grapeDislikeThree.gridy = 2;
		setGrapeDislikePanel.add(grapeDislikeThree, gbc_grapeDislikeThree);
		
		JLabel grapeDislikeFourLabel = new JLabel("4.");
		grapeDislikeFourLabel.setName("grapeDislikeFourLabel");
		GridBagConstraints gbc_grapeDislikeFourLabel = new GridBagConstraints();
		gbc_grapeDislikeFourLabel.insets = new Insets(0, 0, 5, 0);
		gbc_grapeDislikeFourLabel.anchor = GridBagConstraints.EAST;
		gbc_grapeDislikeFourLabel.gridx = 0;
		gbc_grapeDislikeFourLabel.gridy = 3;
		setGrapeDislikePanel.add(grapeDislikeFourLabel, gbc_grapeDislikeFourLabel);
		
		JComboBox<Variety> grapeDislikeFour = new JComboBox<Variety>();
		grapeDislikeFour.setName("grapeDislikeFour");
		DefaultComboBoxModel<Variety> varietyDislike4 = new DefaultComboBoxModel<Variety>();
		try {
			LoadVariousLists.loadAllVarieties(varietyDislike4);
		} catch (SQLException e) {
			WineHunterApplication.splash(2);
			e.printStackTrace();
		}
		grapeDislikeFour.setModel(varietyDislike);
		grapeDislikeFour.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				itemSelectedVariety(e);
			}
		});
		GridBagConstraints gbc_grapeDislikeFour = new GridBagConstraints();
		gbc_grapeDislikeFour.fill = GridBagConstraints.HORIZONTAL;
		gbc_grapeDislikeFour.gridx = 1;
		gbc_grapeDislikeFour.gridy = 3;
		setGrapeDislikePanel.add(grapeDislikeFour, gbc_grapeDislikeFour);
		
		JLabel grapeDislikeFiveLabel = new JLabel("5.");
		grapeDislikeFiveLabel.setName("grapeDislikeFiveLabel");
		GridBagConstraints gbc_grapeDislikeFiveLabel = new GridBagConstraints();
		gbc_grapeDislikeFiveLabel.insets = new Insets(0, 0, 5, 0);
		gbc_grapeDislikeFiveLabel.anchor = GridBagConstraints.EAST;
		gbc_grapeDislikeFiveLabel.gridx = 0;
		gbc_grapeDislikeFiveLabel.gridy = 4;
		setGrapeDislikePanel.add(grapeDislikeFiveLabel, gbc_grapeDislikeFiveLabel);
		
		JComboBox<Variety> grapeDislikeFive = new JComboBox<Variety>();
		grapeDislikeFive.setName("grapeDislikeFive");
		DefaultComboBoxModel<Variety> varietyDislike5 = new DefaultComboBoxModel<Variety>();
		try {
			LoadVariousLists.loadAllVarieties(varietyDislike5);
		} catch (SQLException e) {
			WineHunterApplication.splash(2);
			e.printStackTrace();
		}
		grapeDislikeFive.setModel(varietyDislike);
		grapeDislikeFive.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				itemSelectedVariety(e);
			}
		});
		GridBagConstraints gbc_grapeDislikeFive = new GridBagConstraints();
		gbc_grapeDislikeFive.fill = GridBagConstraints.HORIZONTAL;
		gbc_grapeDislikeFive.gridx = 1;
		gbc_grapeDislikeFive.gridy = 4;
		setGrapeDislikePanel.add(grapeDislikeFive, gbc_grapeDislikeFive);
	}
	
	/**
	 * sets up keyword keyword panel
	 */
	public void keywordPanel() {
		JPanel keyword = new JPanel();
		keyword.setName("Keyword");
		GridBagConstraints gbc_Keyword = new GridBagConstraints();
		gbc_Keyword.insets = new Insets(0, 0, 5, 0);
		gbc_Keyword.weightx = 1.0;
		gbc_Keyword.fill = GridBagConstraints.BOTH;
		gbc_Keyword.gridx = 0;
		gbc_Keyword.gridy = 2;
		add(keyword, gbc_Keyword);
		GridBagLayout gbl_Keyword = new GridBagLayout();
		keyword.setLayout(gbl_Keyword);
		
		JLabel labelKeyword = new JLabel("Keyword Preferences");
		labelKeyword.setName("labelKeyword");
		labelKeyword.setHorizontalAlignment(SwingConstants.CENTER);
		labelKeyword.setVerticalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_labelKeyword = new GridBagConstraints();
		gbc_labelKeyword.weightx = 1.0;
		gbc_labelKeyword.insets = new Insets(0, 0, 5, 0);
		gbc_labelKeyword.fill = GridBagConstraints.HORIZONTAL;
		gbc_labelKeyword.gridx = 0;
		gbc_labelKeyword.gridy = 0;
		keyword.add(labelKeyword, gbc_labelKeyword);
		
		JPanel keywordSplit = new JPanel();
		keywordSplit.setName("keywordSplit");
		GridBagConstraints gbc_keywordSplit = new GridBagConstraints();
		gbc_keywordSplit.weightx = 1.0;
		gbc_keywordSplit.insets = new Insets(0, 0, 5, 0);
		gbc_keywordSplit.fill = GridBagConstraints.BOTH;
		gbc_keywordSplit.gridx = 0;
		gbc_keywordSplit.gridy = 1;
		keyword.add(keywordSplit, gbc_keywordSplit);
		GridBagLayout gbl_keywordSplit = new GridBagLayout();
		gbl_keywordSplit.columnWidths = new int[]{0};
		gbl_keywordSplit.rowHeights = new int[]{0};
		gbl_keywordSplit.columnWeights = new double[]{0.5};
		keywordSplit.setLayout(gbl_keywordSplit);
		
		// set up our panel for liking keyword keywords
		keywordLikePanelSet(keywordSplit);
		
		// set up our panel for disliking keyword keywords
		keywordDislikePanelSet(keywordSplit);
		
		
	}

	/**
	 * set up panel for editing liked keyword keywords
	 * @param keywordSplit parent panel for keyword stuff
	 */
	public void keywordLikePanelSet(JPanel keywordSplit) {
		JPanel keywordLikePanel = new JPanel();
		keywordLikePanel.setName("keywordLikePanel");
		GridBagConstraints gbc_keywordLikePanel = new GridBagConstraints();
		gbc_keywordLikePanel.weightx = 0.5;
		gbc_keywordLikePanel.insets = new Insets(0, 0, 5, 0);
		gbc_keywordLikePanel.fill = GridBagConstraints.BOTH;
		gbc_keywordLikePanel.gridx = 0;
		gbc_keywordLikePanel.gridy = 0;
		gbc_keywordLikePanel.weightx = 1;
		keywordSplit.add(keywordLikePanel, gbc_keywordLikePanel);
		GridBagLayout gbl_keywordLikePanel = new GridBagLayout();
		keywordLikePanel.setLayout(gbl_keywordLikePanel);
		
		JLabel keywordLIke = new JLabel("Likes");
		keywordLIke.setHorizontalAlignment(SwingConstants.CENTER);
		keywordLIke.setHorizontalTextPosition(SwingConstants.CENTER);
		keywordLIke.setName("keywordLIkeLabel");
		GridBagConstraints gbc_keywordLIke = new GridBagConstraints();
		gbc_keywordLIke.insets = new Insets(0, 0, 5, 0);
		gbc_keywordLIke.fill = GridBagConstraints.BOTH;
		gbc_keywordLIke.gridx = 0;
		gbc_keywordLIke.gridy = 0;
		gbc_keywordLIke.weightx = 1;
		keywordLikePanel.add(keywordLIke, gbc_keywordLIke);
		
		JPanel setKeywordLikePanel = new JPanel();
		setKeywordLikePanel.setName("setKeywordLikePanel");
		GridBagConstraints gbc_setKeywordLikePanel = new GridBagConstraints();
		gbc_setKeywordLikePanel.insets = new Insets(0, 0, 5, 0);
		gbc_setKeywordLikePanel.fill = GridBagConstraints.BOTH;
		gbc_setKeywordLikePanel.gridx = 0;
		gbc_setKeywordLikePanel.gridy = 1;
		keywordLikePanel.add(setKeywordLikePanel, gbc_setKeywordLikePanel);
		GridBagLayout gbl_setKeywordLIkePanel = new GridBagLayout();
		gbl_setKeywordLIkePanel.columnWidths = new int[]{0};
		gbl_setKeywordLIkePanel.rowHeights = new int[]{0};

		gbl_setKeywordLIkePanel.columnWeights = new double[]{Double.MIN_VALUE, 1.0};
		gbl_setKeywordLIkePanel.rowWeights = new double[]{Double.MIN_VALUE};
		setKeywordLikePanel.setLayout(gbl_setKeywordLIkePanel);
		
		JLabel keywordLikeOneLabel = new JLabel("1.");
		keywordLikeOneLabel.setName("keywordLikeOneLabel");
		GridBagConstraints gbc_keywordLikeOneLabel = new GridBagConstraints();
		gbc_keywordLikeOneLabel.insets = new Insets(0, 0, 5, 0);
		gbc_keywordLikeOneLabel.anchor = GridBagConstraints.EAST;
		gbc_keywordLikeOneLabel.gridx = 0;
		gbc_keywordLikeOneLabel.gridy = 0;
		setKeywordLikePanel.add(keywordLikeOneLabel, gbc_keywordLikeOneLabel);
		
		JComboBox<Keyword> keywordLikeOne = new JComboBox<Keyword>();
		keywordLikeOne.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				itemSelectedKeyword(e);
			}
		});
		keywordLikeOne.setName("keywordLikeOne");
		DefaultComboBoxModel<Keyword> keywordLike = new DefaultComboBoxModel<Keyword>();
		try {
			LoadVariousLists.loadAllKeywords(keywordLike);
		} catch (SQLException e) {
			WineHunterApplication.splash(2);
			e.printStackTrace();
		}
		keywordLikeOne.setModel(keywordLike);
		GridBagConstraints gbc_keywordLikeOne = new GridBagConstraints();
		gbc_keywordLikeOne.fill = GridBagConstraints.HORIZONTAL;
		gbc_keywordLikeOne.gridx = 1;
		gbc_keywordLikeOne.gridy = 0;
		setKeywordLikePanel.add(keywordLikeOne, gbc_keywordLikeOne);
		
		JLabel keywordLikeTwoLabel = new JLabel("2.");
		keywordLikeTwoLabel.setName("keywordLikeTwoLabel");
		GridBagConstraints gbc_keywordLikeTwoLabel = new GridBagConstraints();
		gbc_keywordLikeTwoLabel.insets = new Insets(0, 0, 5, 0);
		gbc_keywordLikeTwoLabel.anchor = GridBagConstraints.EAST;
		gbc_keywordLikeTwoLabel.gridx = 0;
		gbc_keywordLikeTwoLabel.gridy = 1;
		setKeywordLikePanel.add(keywordLikeTwoLabel, gbc_keywordLikeTwoLabel);
		
		JComboBox<Keyword> keywordLikeTwo = new JComboBox<Keyword>();
		keywordLikeTwo.setName("keywordLikeTwo");
		DefaultComboBoxModel<Keyword> keywordLike2 = new DefaultComboBoxModel<Keyword>();
		try {
			LoadVariousLists.loadAllKeywords(keywordLike2);
		} catch (SQLException e) {
			WineHunterApplication.splash(2);
			e.printStackTrace();
		}
		keywordLikeTwo.setModel(keywordLike);
		keywordLikeTwo.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				itemSelectedKeyword(e);
			}
		});
		GridBagConstraints gbc_keywordLikeTwo = new GridBagConstraints();
		gbc_keywordLikeTwo.fill = GridBagConstraints.HORIZONTAL;
		gbc_keywordLikeTwo.gridx = 1;
		gbc_keywordLikeTwo.gridy = 1;
		setKeywordLikePanel.add(keywordLikeTwo, gbc_keywordLikeTwo);
		
		JLabel keywordLikeThreeLabel = new JLabel("3.");
		keywordLikeThreeLabel.setName("keywordLikeThreeLabel");
		GridBagConstraints gbc_keywordLikeThreeLabel = new GridBagConstraints();
		gbc_keywordLikeThreeLabel.insets = new Insets(0, 0, 5, 0);
		gbc_keywordLikeThreeLabel.anchor = GridBagConstraints.EAST;
		gbc_keywordLikeThreeLabel.gridx = 0;
		gbc_keywordLikeThreeLabel.gridy = 2;
		setKeywordLikePanel.add(keywordLikeThreeLabel, gbc_keywordLikeThreeLabel);
		
		JComboBox<Keyword> keywordLikeThree = new JComboBox<Keyword>();
		keywordLikeThree.setName("keywordLikeThree");
		DefaultComboBoxModel<Keyword> keywordLike3 = new DefaultComboBoxModel<Keyword>();
		try {
			LoadVariousLists.loadAllKeywords(keywordLike3);
		} catch (SQLException e) {
			WineHunterApplication.splash(2);
			e.printStackTrace();
		}
		keywordLikeThree.setModel(keywordLike);
		keywordLikeThree.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				itemSelectedKeyword(e);
			}
		});
		GridBagConstraints gbc_keywordLikeThree = new GridBagConstraints();
		gbc_keywordLikeThree.fill = GridBagConstraints.HORIZONTAL;
		gbc_keywordLikeThree.gridx = 1;
		gbc_keywordLikeThree.gridy = 2;
		setKeywordLikePanel.add(keywordLikeThree, gbc_keywordLikeThree);
		
		JLabel keywordLikeFourLabel = new JLabel("4.");
		keywordLikeFourLabel.setName("keywordLikeFourLabel");
		GridBagConstraints gbc_keywordLikeFourLabel = new GridBagConstraints();
		gbc_keywordLikeFourLabel.insets = new Insets(0, 0, 5, 0);
		gbc_keywordLikeFourLabel.anchor = GridBagConstraints.EAST;
		gbc_keywordLikeFourLabel.gridx = 0;
		gbc_keywordLikeFourLabel.gridy = 3;
		setKeywordLikePanel.add(keywordLikeFourLabel, gbc_keywordLikeFourLabel);
		
		JComboBox<Keyword> keywordLikeFour = new JComboBox<Keyword>();
		keywordLikeFour.setName("keywordLikeFour");
		DefaultComboBoxModel<Keyword> keywordLike4 = new DefaultComboBoxModel<Keyword>();
		try {
			LoadVariousLists.loadAllKeywords(keywordLike4);
		} catch (SQLException e) {
			WineHunterApplication.splash(2);
			e.printStackTrace();
		}
		keywordLikeFour.setModel(keywordLike);
		keywordLikeFour.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				itemSelectedKeyword(e);
			}
		});
		GridBagConstraints gbc_keywordLikeFour = new GridBagConstraints();
		gbc_keywordLikeFour.fill = GridBagConstraints.HORIZONTAL;
		gbc_keywordLikeFour.gridx = 1;
		gbc_keywordLikeFour.gridy = 3;
		setKeywordLikePanel.add(keywordLikeFour, gbc_keywordLikeFour);
		
		JLabel keywordLikeFiveLabel = new JLabel("5.");
		keywordLikeFiveLabel.setName("keywordLikeFiveLabel");
		GridBagConstraints gbc_keywordLikeFiveLabel = new GridBagConstraints();
		gbc_keywordLikeFiveLabel.insets = new Insets(0, 0, 5, 0);
		gbc_keywordLikeFiveLabel.anchor = GridBagConstraints.EAST;
		gbc_keywordLikeFiveLabel.gridx = 0;
		gbc_keywordLikeFiveLabel.gridy = 4;
		setKeywordLikePanel.add(keywordLikeFiveLabel, gbc_keywordLikeFiveLabel);
		
		JComboBox<Keyword> keywordLikeFive = new JComboBox<Keyword>();
		keywordLikeFive.setName("keywordLikeFive");
		DefaultComboBoxModel<Keyword> keywordLike5 = new DefaultComboBoxModel<Keyword>();
		try {
			LoadVariousLists.loadAllKeywords(keywordLike5);
		} catch (SQLException e) {
			WineHunterApplication.splash(2);
			e.printStackTrace();
		}
		keywordLikeFive.setModel(keywordLike);
		keywordLikeFive.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				itemSelectedKeyword(e);
			}
		});
		GridBagConstraints gbc_keywordLikeFive = new GridBagConstraints();
		gbc_keywordLikeFive.fill = GridBagConstraints.HORIZONTAL;
		gbc_keywordLikeFive.gridx = 1;
		gbc_keywordLikeFive.gridy = 4;
		setKeywordLikePanel.add(keywordLikeFive, gbc_keywordLikeFive);
	}
	
	/**
	 * set up panel for editing disliked keyword keywords
	 * @param keywordSplit parent panel for keyword stuff
	 */
	public void keywordDislikePanelSet(JPanel keywordSplit) {
		JPanel keywordDislikePanel = new JPanel();
		keywordDislikePanel.setName("keywordDislikePanel");
		GridBagConstraints gbc_keywordDislikePanel = new GridBagConstraints();
		gbc_keywordDislikePanel.weightx = 0.5;
		gbc_keywordDislikePanel.insets = new Insets(0, 0, 5, 0);
		gbc_keywordDislikePanel.fill = GridBagConstraints.BOTH;
		gbc_keywordDislikePanel.gridx = 1;
		gbc_keywordDislikePanel.gridy = 0;
		gbc_keywordDislikePanel.weightx = 1;
		keywordSplit.add(keywordDislikePanel, gbc_keywordDislikePanel);
		GridBagLayout gbl_keywordDislikePanel = new GridBagLayout();
		gbl_keywordDislikePanel.columnWeights = new double[]{Double.MIN_VALUE, 1.0};
		keywordDislikePanel.setLayout(gbl_keywordDislikePanel);
		
		JLabel keywordLIke = new JLabel("Dislikes");
		keywordLIke.setHorizontalAlignment(SwingConstants.CENTER);
		keywordLIke.setHorizontalTextPosition(SwingConstants.CENTER);
		keywordLIke.setName("keywordLIkeLabel");
		GridBagConstraints gbc_keywordLIke = new GridBagConstraints();
		gbc_keywordLIke.insets = new Insets(0, 0, 5, 0);
		gbc_keywordLIke.fill = GridBagConstraints.BOTH;
		gbc_keywordLIke.gridx = 0;
		gbc_keywordLIke.gridy = 0;
		gbc_keywordLIke.weightx = 1;
		keywordDislikePanel.add(keywordLIke, gbc_keywordLIke);
		
		JPanel setKeywordDislikePanel = new JPanel();
		setKeywordDislikePanel.setName("setKeywordDislikePanel");
		GridBagConstraints gbc_setKeywordDislikePanel = new GridBagConstraints();
		gbc_setKeywordDislikePanel.insets = new Insets(0, 0, 5, 0);
		gbc_setKeywordDislikePanel.fill = GridBagConstraints.BOTH;
		gbc_setKeywordDislikePanel.gridx = 0;
		gbc_setKeywordDislikePanel.gridy = 1;
		keywordDislikePanel.add(setKeywordDislikePanel, gbc_setKeywordDislikePanel);
		GridBagLayout gbl_setKeywordLIkePanel = new GridBagLayout();
		gbl_setKeywordLIkePanel.columnWidths = new int[]{0};
		gbl_setKeywordLIkePanel.rowHeights = new int[]{0};
		gbl_setKeywordLIkePanel.columnWeights = new double[]{Double.MIN_VALUE};
		gbl_setKeywordLIkePanel.rowWeights = new double[]{Double.MIN_VALUE};
		setKeywordDislikePanel.setLayout(gbl_setKeywordLIkePanel);
		
		JLabel keywordDislikeOneLabel = new JLabel("1.");
		keywordDislikeOneLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		keywordDislikeOneLabel.setName("keywordDislikeOneLabel");
		GridBagConstraints gbc_keywordDislikeOneLabel = new GridBagConstraints();
		gbc_keywordDislikeOneLabel.insets = new Insets(0, 0, 5, 0);
		gbc_keywordDislikeOneLabel.anchor = GridBagConstraints.EAST;
		gbc_keywordDislikeOneLabel.gridx = 0;
		gbc_keywordDislikeOneLabel.gridy = 0;
		setKeywordDislikePanel.add(keywordDislikeOneLabel, gbc_keywordDislikeOneLabel);
		
		JComboBox<Keyword> keywordDislikeOne = new JComboBox<Keyword>();
		keywordDislikeOne.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				itemSelectedKeyword(e);
			}
		});
		keywordDislikeOne.setName("keywordDislikeOne");
		DefaultComboBoxModel<Keyword> keywordDislike = new DefaultComboBoxModel<Keyword>();
		try {
			LoadVariousLists.loadAllKeywords(keywordDislike);

		} catch (SQLException e) {
			WineHunterApplication.splash(2);
			e.printStackTrace();
		}
		keywordDislikeOne.setModel(keywordDislike);
		GridBagConstraints gbc_keywordDislikeOne = new GridBagConstraints();
		gbc_keywordDislikeOne.fill = GridBagConstraints.HORIZONTAL;
		gbc_keywordDislikeOne.gridx = 1;
		gbc_keywordDislikeOne.gridy = 0;
		setKeywordDislikePanel.add(keywordDislikeOne, gbc_keywordDislikeOne);
		
		JLabel keywordDislikeTwoLabel = new JLabel("2.");
		keywordDislikeTwoLabel.setName("keywordDislikeTwoLabel");
		GridBagConstraints gbc_keywordDislikeTwoLabel = new GridBagConstraints();
		gbc_keywordDislikeTwoLabel.insets = new Insets(0, 0, 5, 0);
		gbc_keywordDislikeTwoLabel.anchor = GridBagConstraints.EAST;
		gbc_keywordDislikeTwoLabel.gridx = 0;
		gbc_keywordDislikeTwoLabel.gridy = 1;
		setKeywordDislikePanel.add(keywordDislikeTwoLabel, gbc_keywordDislikeTwoLabel);
		
		JComboBox<Keyword> keywordDislikeTwo = new JComboBox<Keyword>();
		keywordDislikeTwo.setName("keywordDislikeTwo");
		DefaultComboBoxModel<Keyword> keywordDislike2 = new DefaultComboBoxModel<Keyword>();
		try {
			LoadVariousLists.loadAllKeywords(keywordDislike2);
		} catch (SQLException e) {
			WineHunterApplication.splash(2);
			e.printStackTrace();
		}
		keywordDislikeTwo.setModel(keywordDislike);
		keywordDislikeTwo.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				itemSelectedKeyword(e);
			}
		});
		GridBagConstraints gbc_keywordDislikeTwo = new GridBagConstraints();
		gbc_keywordDislikeTwo.fill = GridBagConstraints.HORIZONTAL;
		gbc_keywordDislikeTwo.gridx = 1;
		gbc_keywordDislikeTwo.gridy = 1;
		setKeywordDislikePanel.add(keywordDislikeTwo, gbc_keywordDislikeTwo);
		
		JLabel keywordDislikeThreeLabel = new JLabel("3.");
		keywordDislikeThreeLabel.setName("keywordDislikeThreeLabel");
		GridBagConstraints gbc_keywordDislikeThreeLabel = new GridBagConstraints();
		gbc_keywordDislikeThreeLabel.insets = new Insets(0, 0, 5, 0);
		gbc_keywordDislikeThreeLabel.anchor = GridBagConstraints.EAST;
		gbc_keywordDislikeThreeLabel.gridx = 0;
		gbc_keywordDislikeThreeLabel.gridy = 2;
		setKeywordDislikePanel.add(keywordDislikeThreeLabel, gbc_keywordDislikeThreeLabel);
		
		JComboBox<Keyword> keywordDislikeThree = new JComboBox<Keyword>();
		keywordDislikeThree.setName("keywordDislikeThree");
		DefaultComboBoxModel<Keyword> keywordDislike3 = new DefaultComboBoxModel<Keyword>();
		try {
			LoadVariousLists.loadAllKeywords(keywordDislike3);
		} catch (SQLException e) {
			WineHunterApplication.splash(2);
			e.printStackTrace();
		}
		keywordDislikeThree.setModel(keywordDislike);
		keywordDislikeThree.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				itemSelectedKeyword(e);
			}
		});
		GridBagConstraints gbc_keywordDislikeThree = new GridBagConstraints();
		gbc_keywordDislikeThree.fill = GridBagConstraints.HORIZONTAL;
		gbc_keywordDislikeThree.gridx = 1;
		gbc_keywordDislikeThree.gridy = 2;
		setKeywordDislikePanel.add(keywordDislikeThree, gbc_keywordDislikeThree);
		
		JLabel keywordDislikeFourLabel = new JLabel("4.");
		keywordDislikeFourLabel.setName("keywordDislikeFourLabel");
		GridBagConstraints gbc_keywordDislikeFourLabel = new GridBagConstraints();
		gbc_keywordDislikeFourLabel.insets = new Insets(0, 0, 5, 0);
		gbc_keywordDislikeFourLabel.anchor = GridBagConstraints.EAST;
		gbc_keywordDislikeFourLabel.gridx = 0;
		gbc_keywordDislikeFourLabel.gridy = 3;
		setKeywordDislikePanel.add(keywordDislikeFourLabel, gbc_keywordDislikeFourLabel);
		
		JComboBox<Keyword> keywordDislikeFour = new JComboBox<Keyword>();
		keywordDislikeFour.setName("keywordDislikeFour");
		DefaultComboBoxModel<Keyword> keywordDislike4 = new DefaultComboBoxModel<Keyword>();
		try {
			LoadVariousLists.loadAllKeywords(keywordDislike4);
		} catch (SQLException e) {
			WineHunterApplication.splash(2);
			e.printStackTrace();
		}
		keywordDislikeFour.setModel(keywordDislike);
		keywordDislikeFour.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				itemSelectedKeyword(e);
			}
		});
		GridBagConstraints gbc_keywordDislikeFour = new GridBagConstraints();
		gbc_keywordDislikeFour.fill = GridBagConstraints.HORIZONTAL;
		gbc_keywordDislikeFour.gridx = 1;
		gbc_keywordDislikeFour.gridy = 3;
		setKeywordDislikePanel.add(keywordDislikeFour, gbc_keywordDislikeFour);
		
		JLabel keywordDislikeFiveLabel = new JLabel("5.");
		keywordDislikeFiveLabel.setName("keywordDislikeFiveLabel");
		GridBagConstraints gbc_keywordDislikeFiveLabel = new GridBagConstraints();
		gbc_keywordDislikeFiveLabel.insets = new Insets(0, 0, 5, 0);
		gbc_keywordDislikeFiveLabel.anchor = GridBagConstraints.EAST;
		gbc_keywordDislikeFiveLabel.gridx = 0;
		gbc_keywordDislikeFiveLabel.gridy = 4;
		setKeywordDislikePanel.add(keywordDislikeFiveLabel, gbc_keywordDislikeFiveLabel);
		
		JComboBox<Keyword> keywordDislikeFive = new JComboBox<Keyword>();
		keywordDislikeFive.setName("keywordDislikeFive");
		DefaultComboBoxModel<Keyword> keywordDislike5 = new DefaultComboBoxModel<Keyword>();
		try {
			LoadVariousLists.loadAllKeywords(keywordDislike5);
		} catch (SQLException e) {
			WineHunterApplication.splash(2);
			e.printStackTrace();
		}
		keywordDislikeFive.setModel(keywordDislike);
		keywordDislikeFive.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				itemSelectedKeyword(e);
			}
		});
		GridBagConstraints gbc_keywordDislikeFive = new GridBagConstraints();
		gbc_keywordDislikeFive.fill = GridBagConstraints.HORIZONTAL;
		gbc_keywordDislikeFive.gridx = 1;
		gbc_keywordDislikeFive.gridy = 4;
		setKeywordDislikePanel.add(keywordDislikeFive, gbc_keywordDislikeFive);
	}
	
	/**
	 * helper to deal with multiple selections in combo boxes for grape varieties
	 * @param e even handler
	 */
	private void itemSelectedVariety(ItemEvent e) {
		if (!e.getSource().equals(grapeLikeOne)) {
			if(e.getStateChange() == ItemEvent.SELECTED){
				grapeLikeOne.removeItem(e.getItem());
			}else{
				grapeLikeOne.addItem((Variety) e.getItem());
			}
		}
		if (!e.getSource().equals(grapeLikeTwo)) {
			if(e.getStateChange() == ItemEvent.SELECTED){
				grapeLikeTwo.removeItem(e.getItem());
			}else{
				grapeLikeTwo.addItem((Variety) e.getItem());
			}
		}
		if (!e.getSource().equals(grapeLikeThree)) {
			if(e.getStateChange() == ItemEvent.SELECTED){
				grapeLikeThree.removeItem(e.getItem());
			}else{
				grapeLikeThree.addItem((Variety) e.getItem());
			}
		}
		if (!e.getSource().equals(grapeLikeFour)) {
			if(e.getStateChange() == ItemEvent.SELECTED){
				grapeLikeFour.removeItem(e.getItem());
			}else{
				grapeLikeFour.addItem((Variety) e.getItem());
			}
		}
		if (!e.getSource().equals(grapeLikeFive)) {
			if(e.getStateChange() == ItemEvent.SELECTED){
				grapeLikeFive.removeItem(e.getItem());
			}else{
				grapeLikeFive.addItem((Variety) e.getItem());
			}
		}
		if (!e.getSource().equals(grapeDislikeOne)) {
			if(e.getStateChange() == ItemEvent.SELECTED){
				grapeDislikeOne.removeItem(e.getItem());
			}else{
				grapeDislikeOne.addItem((Variety) e.getItem());
			}
		}
		if (!e.getSource().equals(grapeDislikeTwo)) {
			if(e.getStateChange() == ItemEvent.SELECTED){
				grapeDislikeTwo.removeItem(e.getItem());
			}else{
				grapeDislikeTwo.addItem((Variety) e.getItem());
			}
		}
		if (!e.getSource().equals(grapeDislikeThree)) {
			if(e.getStateChange() == ItemEvent.SELECTED){
				grapeDislikeThree.removeItem(e.getItem());
			}else{
				grapeDislikeThree.addItem((Variety) e.getItem());
			}
		}
		if (!e.getSource().equals(grapeDislikeFour)) {
			if(e.getStateChange() == ItemEvent.SELECTED){
				grapeDislikeFour.removeItem(e.getItem());
			}else{
				grapeDislikeFour.addItem((Variety) e.getItem());
			}
		}
		if (!e.getSource().equals(grapeDislikeFive)) {
			if(e.getStateChange() == ItemEvent.SELECTED){
				grapeDislikeFive.removeItem(e.getItem());
			}else{
				grapeDislikeFive.addItem((Variety) e.getItem());
			}
		}
		
		
	}
	
	/**
	 * helper to deal with multiple selections in combo boxes for keywords
	 * @param e even handler
	 */
	private void itemSelectedKeyword(ItemEvent e) {
		if (!e.getSource().equals(keywordLikeOne)) {
			if(e.getStateChange() == ItemEvent.SELECTED){
				keywordLikeOne.removeItem(e.getItem());
			}else{
				keywordLikeOne.addItem((Keyword) e.getItem());
			}
		}
		if (!e.getSource().equals(keywordLikeTwo)) {
			if(e.getStateChange() == ItemEvent.SELECTED){
				keywordLikeTwo.removeItem(e.getItem());
			}else{
				keywordLikeTwo.addItem((Keyword) e.getItem());
			}
		}
		if (!e.getSource().equals(keywordLikeThree)) {
			if(e.getStateChange() == ItemEvent.SELECTED){
				keywordLikeThree.removeItem(e.getItem());
			}else{
				keywordLikeThree.addItem((Keyword) e.getItem());
			}
		}
		if (!e.getSource().equals(keywordLikeFour)) {
			if(e.getStateChange() == ItemEvent.SELECTED){
				keywordLikeFour.removeItem(e.getItem());
			}else{
				keywordLikeFour.addItem((Keyword) e.getItem());
			}
		}
		if (!e.getSource().equals(keywordLikeFive)) {
			if(e.getStateChange() == ItemEvent.SELECTED){
				keywordLikeFive.removeItem(e.getItem());
			}else{
				keywordLikeFive.addItem((Keyword) e.getItem());
			}
		}
		if (!e.getSource().equals(keywordDislikeOne)) {
			if(e.getStateChange() == ItemEvent.SELECTED){
				keywordDislikeOne.removeItem(e.getItem());
			}else{
				keywordDislikeOne.addItem((Keyword) e.getItem());
			}
		}
		if (!e.getSource().equals(keywordDislikeTwo)) {
			if(e.getStateChange() == ItemEvent.SELECTED){
				keywordDislikeTwo.removeItem(e.getItem());
			}else{
				keywordDislikeTwo.addItem((Keyword) e.getItem());
			}
		}
		if (!e.getSource().equals(keywordDislikeThree)) {
			if(e.getStateChange() == ItemEvent.SELECTED){
				keywordDislikeThree.removeItem(e.getItem());
			}else{
				keywordDislikeThree.addItem((Keyword) e.getItem());
			}
		}
		if (!e.getSource().equals(keywordDislikeFour)) {
			if(e.getStateChange() == ItemEvent.SELECTED){
				keywordDislikeFour.removeItem(e.getItem());
			}else{
				keywordDislikeFour.addItem((Keyword) e.getItem());
			}
		}
		if (!e.getSource().equals(keywordDislikeFive)) {
			if(e.getStateChange() == ItemEvent.SELECTED){
				keywordDislikeFive.removeItem(e.getItem());
			}else{
				keywordDislikeFive.addItem((Keyword) e.getItem());
			}
		}
		
		
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}
