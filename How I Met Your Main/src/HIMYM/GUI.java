package HIMYM;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

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
	private JButton exportToTxt;
	private JButton fightExplorerWindow;
	private JButton statsWindow;
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
	
	private JFrame fightExplorer;
	private String[] afe, bfe, wfe, lfe, hpLeftAfe, hpLeftBfe, placefe, xfe, yfe, timefe, fight_commentfe, fight_lengthfe;
	int ife;
	
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
		this.exportToTxt = new JButton("Export DB to .txt");
		this.exportToTxt.setName("exportToTxt");
		this.exportToTxt.addActionListener((ActionListener) this);
		this.fightExplorerWindow = new JButton("Fights Explorer");
		this.fightExplorerWindow.setName("fightExplorerWindow");
		this.fightExplorerWindow.addActionListener((ActionListener) this);
		this.statsWindow = new JButton("Statistics");
		this.statsWindow.setName("statsWindow");
		this.statsWindow.addActionListener((ActionListener) this);
		
		
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
		this.mainFrame.getContentPane().add(exportToTxt);
		this.mainFrame.getContentPane().add(fightExplorerWindow);
		this.mainFrame.getContentPane().add(statsWindow);
		
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
		
		this.ife = 0;
		
	}
	
	public void toggleAddWindow(){
		
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
	
	public void toggleFightExplorer(){
		
		fightExplorer = new JFrame("Fight Explorer");
		fightExplorer.setSize(820, 240);
		fightExplorer.setResizable(true);
		fightExplorer.setLayout(new BorderLayout());
		JPanel res = new JPanel(new BorderLayout());
		JTextArea resultBox = new JTextArea("");
		JTextField toSearch = new JTextField("Enter a name (guild, player, class, specialization, place, faction) or a date AAAA-MO-DD and set the type above.");
		String[] types = { "PLAYERS", "GUILDS", "PLACES", "TIMES", "FIGHTS", "CLASSES", "SPECIALIZATIONS", "FACTIONS" };
		JComboBox type = new JComboBox(types);
		JButton previous = new JButton("Previous");
		JButton next = new JButton("Next");
		type.setSelectedIndex(-1);
		resultBox.setEditable(false);
		ife = 0;
		
		res.add(previous, BorderLayout.WEST);
		res.add(next, BorderLayout.EAST);
		res.add(resultBox, BorderLayout.CENTER);
		fightExplorer.getContentPane().add(res, BorderLayout.SOUTH);
		fightExplorer.getContentPane().add(toSearch, BorderLayout.CENTER);
		fightExplorer.getContentPane().add(type, BorderLayout.NORTH);
		
		JButton search = new JButton("Search in database");
		search.setName("searchButton");
		fightExplorer.getContentPane().add(search, BorderLayout.EAST);
		
		search.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event2) {
            	
            	if(type.getSelectedItem() == "PLAYERS"){
            		
            		resultBox.setText("");
            		ife = 0;
            		
            		//afe = db.requestString("select playera from fights where playera = '" + toSearch.getText() + "' or playerb = '"  + toSearch.getText() + "'").split("\n");
            		//bfe = db.requestString("select playerb from fights where playera = '" + toSearch.getText() + "' or playerb = '"  + toSearch.getText() + "'").split("\n");
            		wfe = db.requestString("select winner from fights where playera = '" + toSearch.getText() + "' or playerb = '"  + toSearch.getText() + "'").split("\n");
            		lfe = db.requestString("select loser from fights where playera = '" + toSearch.getText() + "' or playerb = '"  + toSearch.getText() + "'").split("\n");
            		hpLeftAfe = db.requestString("select hplefta from fights where playera = '" + toSearch.getText() + "' or playerb = '"  + toSearch.getText() + "'").split("\n");
            		hpLeftBfe = db.requestString("select hpleftb from fights where playera = '" + toSearch.getText() + "' or playerb = '"  + toSearch.getText() + "'").split("\n");
            		placefe = db.requestString("select place from fights where playera = '" + toSearch.getText() + "' or playerb = '"  + toSearch.getText() + "'").split("\n");
            		xfe = db.requestString("select x from fights where playera = '" + toSearch.getText() + "' or playerb = '"  + toSearch.getText() + "'").split("\n");
            		yfe = db.requestString("select y from fights where playera = '" + toSearch.getText() + "' or playerb = '"  + toSearch.getText() + "'").split("\n");
            		timefe = db.requestString("select time from fights where playera = '" + toSearch.getText() + "' or playerb = '"  + toSearch.getText() + "'").split("\n");
            		fight_commentfe = db.requestString("select fightcomment from fights where playera = '" + toSearch.getText() + "' or playerb = '"  + toSearch.getText() + "'").split("\n");
            		fight_lengthfe = db.requestString("select fightlength from fights where playera = '" + toSearch.getText() + "' or playerb = '"  + toSearch.getText() + "'").split("\n");
            		
            		//System.out.println(a + " " + b + " " + w + " " + l + " " + hpLeftA + " " + hpLeftB + " " + place + " " + x + " " + y + " " + time + " " + fight_comment + " " + fight_length);
            		
            		if(wfe.length > 0){
            			
            			resultBox.append(wfe[ife] + " (" + hpLeftAfe[ife] + "% HP left) won a fight against " + lfe[ife] + " (" + hpLeftBfe[ife] +"% HP left)\n");
            			resultBox.append("At " + placefe[ife] + " X : " + xfe[ife] + " Y : " + yfe[ife] + "\n");
            			resultBox.append("On the " + timefe[ife] + " for a " + fight_lengthfe[ife] + " seconds fight !\n");
            			resultBox.append("" + fight_commentfe[ife]);
            			
            			
            		}
            	}
            }
        });
		
		previous.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event2) {
            	
            	if(type.getSelectedItem() == "PLAYERS"){
            		
            		if(ife > 0){
            			
            			ife --;
            			
            			resultBox.setText("");
            			
            			resultBox.append(wfe[ife] + " (" + hpLeftAfe[ife] + "% HP left) won a fight against " + lfe[ife] + " (" + hpLeftBfe[ife] +"% HP left)\n");
            			resultBox.append("At " + placefe[ife] + " X : " + xfe[ife] + " Y : " + yfe[ife] + "\n");
            			resultBox.append("On the " + timefe[ife] + " for a " + fight_lengthfe[ife] + " seconds fight !\n");
            			resultBox.append("" + fight_commentfe[ife]);
            			res.revalidate();
            			
            		}
            	}
            }
        });
		
		next.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event2) {
            	
            	if(type.getSelectedItem() == "PLAYERS"){
            		
            		if(ife < wfe.length - 1){
            			
            			ife ++;
            			
            			resultBox.setText("");
            			
            			resultBox.append(wfe[ife] + " (" + hpLeftAfe[ife] + "% HP left) won a fight against " + lfe[ife] + " (" + hpLeftBfe[ife] +"% HP left)\n");
            			resultBox.append("At " + placefe[ife] + " X : " + xfe[ife] + " Y : " + yfe[ife] + "\n");
            			resultBox.append("On the " + timefe[ife] + " for a " + fight_lengthfe[ife] + " seconds fight !\n");
            			resultBox.append("" + fight_commentfe[ife]);
            			res.revalidate();
            			
            		}
            	}
            }
        });
		
		fightExplorer.setVisible(true);
		
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
		JTextField whisprage = new JTextField("Whisp Rage");
		JTextField skill = new JTextField("Skill (x/10)");
		JTextField backped = new JTextField("Backped (0/1)");
		JTextField skillcomment = new JTextField("Skill Comment");
		addPlayer = new JButton("Add Player in database");
		addPlayer.setName("addWindowButton");
		addPlayer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event2) {
            	
            	db.checkAddGuild(guild.getText(), 0, 0);
            	
            	db.checkAddPlayer(name.getText(), guild.getText(), faction.getText(), classe.getText(), specialization.getText(), skillcomment.getText(), Integer.valueOf(skill.getText()), Integer.valueOf(backped.getText()), Integer.valueOf(nbgotkilled.getText()), Integer.valueOf(nbkilled.getText()), whisprage.getText());
            	db.requestString("update CLASSES set CLASSES.NBGOTKILLED = CLASSES.NBGOTKILLED + " + Integer.valueOf(nbgotkilled.getText()) + " where CLASSES.NAME = '" + classe.getText() + "'");
            	db.requestString("update CLASSES set CLASSES.NBKILLED = CLASSES.NBKILLED + " + Integer.valueOf(nbkilled.getText()) + " where CLASSES.NAME = '" + classe.getText() + "'");
            	db.requestString("update SPECIALIZATIONS set SPECIALIZATIONS.NBGOTKILLED = SPECIALIZATIONS.NBGOTKILLED + " + Integer.valueOf(nbgotkilled.getText()) + " where SPECIALIZATIONS.NAME = '" + specialization.getText() + "'");
            	db.requestString("update SPECIALIZATIONS set SPECIALIZATIONS.NBKILLED = SPECIALIZATIONS.NBKILLED + " + Integer.valueOf(nbkilled.getText()) + " where SPECIALIZATIONS.NAME = '" + specialization.getText() + "'");
            	db.requestString("update GUILDS set GUILDS.NBGOTKILLED = GUILDS.NBGOTKILLED + " + Integer.valueOf(nbgotkilled.getText()) + " where GUILDS.NAME = '" + guild.getText() + "'");
            	db.requestString("update GUILDS set GUILDS.NBKILLED = GUILDS.NBKILLED + " + Integer.valueOf(nbkilled.getText()) + " where GUILDS.NAME = '" + guild.getText() + "'");
            	db.requestString("update FACTIONS set FACTIONS.NBGOTKILLED = FACTIONS.NBGOTKILLED + " + Integer.valueOf(nbgotkilled.getText()) + " where FACTIONS.NAME = '" + faction.getText() + "'");
            	db.requestString("update FACTIONS set FACTIONS.NBKILLED = FACTIONS.NBKILLED + " + Integer.valueOf(nbkilled.getText()) + " where FACTIONS.NAME = '" + faction.getText() + "'");
            	
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
		JTextField hplefta = new JTextField("HP Left Player A");
		JTextField hpleftb = new JTextField("HP Left Player B");
		JTextArea error = new JTextArea("");
		
		addFight = new JButton("Add Fight in database");
		addFight.setName("addWindowButtonF");
		addFight.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event2) {

            	error.setText("");
            	
            	if(db.requestString("select name from players where name = '" + a.getText() + "'") == ""){

            		error.append("You should add Player A before adding the fight !\n");

            	}
            	if(db.requestString("select name from players where name = '" + b.getText() + "'") == ""){

            		error.append("You should add Player B before adding the fight !\n");

            	}
            	if(a.getText().matches(b.getText())){

            		error.append("Player A should be different from Player B\n");

            	}
            	if(winner.getText().matches(loser.getText())){

            		error.append("Winner should be different from Loser\n");

            	}
            	if(a.getText().matches(winner.getText()) == false && b.getText().matches(winner.getText()) == false && winner.getText().matches("") == false){

            		error.append("Unknown Player assigned to Winner\n");

            	}
            	if(a.getText().matches(loser.getText()) == false && b.getText().matches(loser.getText()) == false && loser.getText().matches("") == false){

            		error.append("Unknown Player assigned to Loser\n");

            	}
            	
            	//System.out.println("Error : " + error.getText());
            	
            	if(error.getText().isEmpty() == true){
            		
            		//System.out.println("OKKKKKK");
            		
            		String[] tempo = place.getText().split("/");
            		int x = Integer.valueOf(tempo[1]);
            		int y = Integer.valueOf(tempo[2]);
            		
            		String[] tempa = time.getText().split("-|\\:|\\ ");
            		System.out.println(tempo[0]);
            		
            		
            		db.checkAddPlace(tempo[0], x, y);
            		db.checkAddTime(Integer.valueOf(tempa[0]), Integer.valueOf(tempa[1]), Integer.valueOf(tempa[2]), Integer.valueOf(tempa[3]), Integer.valueOf(tempa[4]), Integer.valueOf(tempa[5]));
            		db.checkPlayerDB(a.getText());
            		db.checkPlayerDB(b.getText());
            		
            		db.checkAddFight(a.getText(), b.getText(), winner.getText(), loser.getText(), time.getText(), tempo[0], x, y, fight_comment.getText(), Integer.valueOf(fight_length.getText()), Integer.valueOf(hplefta.getText()), Integer.valueOf(hpleftb.getText()));
            		
            		db.requestString("update CLASSES set CLASSES.NBGOTKILLED = CLASSES.NBGOTKILLED + 1 where CLASSES.NAME = '" + db.objectLists.findPlayer(loser.getText()).getClasse().getName() + "'");
                	db.requestString("update CLASSES set CLASSES.NBKILLED = CLASSES.NBKILLED + 1 where CLASSES.NAME = '" + db.objectLists.findPlayer(winner.getText()).getClasse().getName() + "'");
                	db.requestString("update SPECIALIZATIONS set SPECIALIZATIONS.NBGOTKILLED = SPECIALIZATIONS.NBGOTKILLED + 1 where SPECIALIZATIONS.NAME = '" + db.objectLists.findPlayer(loser.getText()).getSpecialization().getName() + "'");
                	db.requestString("update SPECIALIZATIONS set SPECIALIZATIONS.NBKILLED = SPECIALIZATIONS.NBKILLED + 1 where SPECIALIZATIONS.NAME = '" + db.objectLists.findPlayer(winner.getText()).getSpecialization().getName() + "'");
                	db.requestString("update GUILDS set GUILDS.NBGOTKILLED = GUILDS.NBGOTKILLED + 1 where GUILDS.NAME = '" + db.objectLists.findPlayer(loser.getText()).getGuild().getName() + "'");
                	db.requestString("update GUILDS set GUILDS.NBKILLED = GUILDS.NBKILLED + 1 where GUILDS.NAME = '" + db.objectLists.findPlayer(winner.getText()).getGuild().getName() + "'");
                	db.requestString("update PLAYERS set PLAYERS.NBGOTKILLED = PLAYERS.NBGOTKILLED + 1 where PLAYERS.NAME = '" + loser.getText() + "'");
                	db.requestString("update PLAYERS set PLAYERS.NBKILLED = PLAYERS.NBKILLED + 1 where PLAYERS.NAME = '" + winner.getText() + "'");
                	db.requestString("update FACTIONS set FACTIONS.NBGOTKILLED = FACTIONS.NBGOTKILLED + 1 where FACTIONS.NAME = '" + db.objectLists.findPlayer(loser.getText()).getFaction().getName() + "'");
                	db.requestString("update FACTIONS set FACTIONS.NBKILLED = FACTIONS.NBKILLED + 1 where FACTIONS.NAME = '" + db.objectLists.findPlayer(winner.getText()).getFaction().getName() + "'");
                	
            	}
            	
            }
        });
		
		fightPanel.removeAll();
		
		fightPanel.add(a);
		fightPanel.add(b);
		fightPanel.add(hplefta);
		fightPanel.add(hpleftb);
		fightPanel.add(winner);
		fightPanel.add(loser);
		fightPanel.add(place);
		fightPanel.add(time);
		fightPanel.add(fight_comment);
		fightPanel.add(fight_length);
		fightPanel.add(addFight);
		fightPanel.add(error);
		
		fightPanel.revalidate();
		
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		
		if(event.getSource() == sendRequest) outputRequest.setText(db.requestString(inputRequest.getText()));
		if(event.getSource() == addWindowButton) toggleAddWindow();
		if(event.getSource() == download) db.objectLists.loadEverything();
		if(event.getSource() == exportToTxt)
			try {
				db.objectLists.export();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		if(event.getSource() == fightExplorerWindow) toggleFightExplorer();	
		
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