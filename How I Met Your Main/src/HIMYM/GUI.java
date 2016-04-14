package HIMYM;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class GUI extends JFrame implements ActionListener{

	private Db db;
	private JFrame mainFrame;
	private JTextArea inputRequest;
	private JTextArea outputRequest;
	private JButton sendRequest;
	private JButton addWindowButton;
	private JLabel input;
	private JLabel output;
	private JScrollPane scrollInput;
	private JScrollPane scrollOutput;
	
	private JFrame addWindow;
	private String[] tables = { "Player", "Guild", "Place", "Time", "Fight" };
	private JComboBox list;
	private JPanel cb;
	private JButton download;
	
	private JButton addPlayer;
	private JButton addGuild;
	private JButton addPlace;
	private JButton addTime;
	private JButton addFight;
	
	private JPanel playerPanel;
	private JPanel guildPanel;
	private JPanel placePanel;
	private JPanel timePanel;
	private JPanel fightPanel;
	
	
	public GUI(Db db){
		
		this.db = db;
		
		this.mainFrame = new JFrame("How I Met Your Main");
		this.mainFrame.setSize(860, 480);
		this.mainFrame.setResizable(true);
		this.mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.inputRequest = new JTextArea("Input");
		this.inputRequest.setBorder(BorderFactory.createLineBorder(Color.BLUE));
		this.outputRequest = new JTextArea("Output");
		this.outputRequest.setEditable(false);
		this.outputRequest.setBorder(BorderFactory.createLineBorder(Color.RED));
		this.sendRequest = new JButton("Send SQL request !");
		this.sendRequest.setName("sendRequest");
		this.sendRequest.addActionListener((ActionListener) this);
		this.addWindowButton = new JButton("Add in database");
		this.addWindowButton.setName("addWindowButton");
		this.addWindowButton.addActionListener((ActionListener) this);
		this.input = new JLabel("Enter SQL Request (without ';')");
		this.output = new JLabel("Result of the SQL Request");
		this.download = new JButton("Download Database");
		this.download.setName("downloadDatabase");
		this.download.addActionListener((ActionListener) this);
		
		
		this.mainFrame.setLayout(new GridLayout(5,2));
		this.mainFrame.getContentPane().add(input);
		this.mainFrame.getContentPane().add(output);
		this.mainFrame.getContentPane().add(inputRequest);
		this.scrollInput = new JScrollPane(this.inputRequest, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED); 
		this.mainFrame.getContentPane().add(scrollInput);
		this.mainFrame.getContentPane().add(outputRequest);
		this.scrollOutput = new JScrollPane(this.outputRequest, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED); 
		this.mainFrame.getContentPane().add(scrollOutput);
		this.mainFrame.getContentPane().add(sendRequest);
		this.mainFrame.getContentPane().add(addWindowButton);
		this.mainFrame.getContentPane().add(download);
		
		this.mainFrame.setVisible(true);
		
		this.playerPanel = new JPanel(new FlowLayout());
		this.guildPanel = new JPanel(new FlowLayout());
		this.placePanel = new JPanel(new FlowLayout());
		this.timePanel = new JPanel(new FlowLayout());
		this.fightPanel = new JPanel(new FlowLayout());
		
		this.addPlayer = new JButton("Add Player !");
		this.addGuild = new JButton("Add Guild !");
		this.addPlace = new JButton("Add Place !");
		this.addTime = new JButton("Add Time !");
		this.addFight = new JButton("Add Fight !");
		
	}
	
	void toggleAddWindow(){
		
		addWindow = new JFrame("Add in database");
		addWindow.setSize(430, 240);
		addWindow.setResizable(true);
		addWindow.setLayout(new BorderLayout());
		
		list = new JComboBox(tables);
		list.setSelectedIndex(-1);
		cb = new JPanel();
		cb.add(list);
		addWindow.add(cb, BorderLayout.NORTH);
		list.addActionListener((ActionListener) this);
		
		addWindow.setVisible(true);
		
	}
	
	public void clearPanel(){
		
		addWindow.remove(playerPanel);
		addWindow.remove(guildPanel);
		addWindow.remove(placePanel);
		addWindow.remove(timePanel);
		addWindow.remove(fightPanel);
		addWindow.setVisible(false);
		
	}
	
	public void setPlayerPanel(){
		
		JTextField name = new JTextField("Name");
		JTextField guild = new JTextField("Guild");
		JTextField faction = new JTextField("Faction");
		JTextField classe = new JTextField("Classe");
		JTextField specialization = new JTextField("Specialization");
		JTextField nbgotkilled = new JTextField("NbGotKilled");
		JTextField nbkilled = new JTextField("NbKilled");
		JTextField hpleft = new JTextField("HP Left (%)");
		JTextField whisprage = new JTextField("Whisp Rage");
		JTextField skill = new JTextField("Skill (x/10)");
		JTextField backped = new JTextField("Backped (0/1)");
		JTextField skillcomment = new JTextField("Skill Comment");
		addPlayer = new JButton("Add Player in database");
		addPlayer.setName("addWindowButton");
		addPlayer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event2) {
            	
            	db.checkAddGuild(guild.getText(), 0, 0);
            	
            	db.requestString("INSERT INTO PLAYERS VALUES ('" + name.getText() + "','" + guild.getText() + "','" + faction.getText() + "','"  + classe.getText() + "','" + specialization.getText() + "'," + Integer.valueOf(nbgotkilled.getText()) + "," + Integer.valueOf(nbkilled.getText()) + "," + Integer.valueOf(hpleft.getText()) + ",'" + whisprage.getText() + "'," + Integer.valueOf(skill.getText()) + "," + Integer.valueOf(backped.getText()) + ",'" + skillcomment.getText() + "'"  + ")");
            	
            }
        });
		
		playerPanel.removeAll();
		
		playerPanel.add(name);
		playerPanel.add(guild);
		playerPanel.add(faction);
		playerPanel.add(classe);
		playerPanel.add(specialization);
		playerPanel.add(nbgotkilled);
		playerPanel.add(nbkilled);
		playerPanel.add(hpleft);
		playerPanel.add(whisprage);
		playerPanel.add(skill);
		playerPanel.add(backped);
		playerPanel.add(skillcomment);
		playerPanel.add(addPlayer);
		
		playerPanel.revalidate();
		
	}
	
	public void setGuildPanel(){
		
		JTextField name = new JTextField("Name");
		JTextField nbgotkilled = new JTextField("NbGotKilled");
		JTextField nbkilled = new JTextField("NbKilled");
			
		addGuild = new JButton("Add Guild in database");
		addGuild.setName("addWindowButtonG");
		addGuild.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event2) {

				db.checkAddGuild(name.getText(), Integer.valueOf(nbgotkilled.getText()), Integer.valueOf(nbkilled.getText()));
	            	
	        }
	    });
		
		guildPanel.removeAll();
			
		guildPanel.add(name);
		guildPanel.add(nbgotkilled);
		guildPanel.add(nbkilled);
		guildPanel.add(addGuild);
			
		guildPanel.revalidate();
			
	}
	
	public void setPlacePanel(){
		
		JTextField name = new JTextField("Name");
		JTextField x = new JTextField("x");
		JTextField y = new JTextField("y");
		
		addPlace = new JButton("Add Place in database");
		addPlace.setName("addWindowButtonP");
		addPlace.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event2) {

            	db.checkAddPlace(name.getText(), Integer.valueOf(x.getText()), Integer.valueOf(y.getText()));
            	
            }
        });
		
		placePanel.removeAll();
		
		placePanel.add(name);
		placePanel.add(x);
		placePanel.add(y);
		placePanel.add(addPlace);
		
		placePanel.revalidate();
		
	}
	
	public void setTimePanel(){
		
		JTextField year = new JTextField("Year");
		JTextField month = new JTextField("Month");
		JTextField day = new JTextField("Day");
		JTextField hour = new JTextField("Hour");
		JTextField minute = new JTextField("Minute");
		JTextField second = new JTextField("Second");
		
		addTime = new JButton("Add Time in database");
		addTime.setName("addWindowButtonT");
		addTime.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event2) {

            	db.checkAddTime(Integer.valueOf(year.getText()),Integer.valueOf(month.getText()), Integer.valueOf(day.getText()), Integer.valueOf(hour.getText()), Integer.valueOf(minute.getText()), Integer.valueOf(second.getText()));
            	
            }
        });
		
		timePanel.removeAll();
		
		timePanel.add(year);
		timePanel.add(month);
		timePanel.add(day);
		timePanel.add(hour);
		timePanel.add(minute);
		timePanel.add(second);
		timePanel.add(addTime);
		
		timePanel.revalidate();
		
	}
	
	public void setFightPanel(){
		
		JTextField a = new JTextField("Player A");
		JTextField b = new JTextField("Player B");
		JTextField winner = new JTextField("Winner");
		JTextField loser = new JTextField("Loser");
		JTextField place = new JTextField("Place : Name/X/Y");
		JTextField time = new JTextField("Date : AAAA-MO-DD HH:MI:SS");
		JTextField fight_comment = new JTextField("Fight Comment");
		JTextField fight_length = new JTextField("Fight length (s)");
		
		addFight = new JButton("Add Fight in database");
		addFight.setName("addWindowButtonF");
		addFight.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event2) {

            	// db.checkAddTime(Integer.valueOf(year.getText()),Integer.valueOf(month.getText()), Integer.valueOf(day.getText()), Integer.valueOf(hour.getText()), Integer.valueOf(minute.getText()), Integer.valueOf(second.getText()));
            	
            }
        });
		
		fightPanel.removeAll();
		
		fightPanel.add(a);
		fightPanel.add(b);
		fightPanel.add(winner);
		fightPanel.add(loser);
		fightPanel.add(place);
		fightPanel.add(time);
		fightPanel.add(fight_comment);
		fightPanel.add(fight_length);
		fightPanel.add(addFight);
		
		fightPanel.revalidate();
		
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		
		if(event.getSource() == sendRequest) outputRequest.setText(db.requestString(inputRequest.getText()));
		if(event.getSource() == addWindowButton) toggleAddWindow();
		if(event.getSource() == download) db.objectLists.loadEverything();
		
		if(event.getSource() == list && (String)list.getSelectedItem() == "Player"){
			
			clearPanel();
			setPlayerPanel();
			addWindow.add(playerPanel, BorderLayout.CENTER);
			addWindow.setVisible(true);
			
		}
		
		if(event.getSource() == list && (String)list.getSelectedItem() == "Guild"){
			
			clearPanel();
			setGuildPanel();
			addWindow.add(guildPanel, BorderLayout.CENTER);
			addWindow.setVisible(true);
		}
		
		if(event.getSource() == list && (String)list.getSelectedItem() == "Place"){
			
			clearPanel();
			setPlacePanel();
			addWindow.add(placePanel, BorderLayout.CENTER);
			addWindow.setVisible(true);
		}
		
		if(event.getSource() == list && (String)list.getSelectedItem() == "Time"){
			
			clearPanel();
			setTimePanel();
			addWindow.add(timePanel, BorderLayout.CENTER);
			addWindow.setVisible(true);
		}
		
		if(event.getSource() == list && (String)list.getSelectedItem() == "Fight"){
			
			clearPanel();
			setFightPanel();
			addWindow.add(fightPanel, BorderLayout.CENTER);
			addWindow.setVisible(true);
		}
	
	}
	
}