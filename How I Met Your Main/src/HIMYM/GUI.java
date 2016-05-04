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

	private JFrame startFrame;
	private JTextArea address;
	private JTextArea port;
	private JTextArea username;
	private JTextArea password;
	private JButton connect;
	
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
	private String[] tables = { "Player", "Guild", "Fight" };
	private JComboBox list;
	private JPanel cb;
	private JButton download;
	
	private JButton addPlayer;
	private JButton addGuild;
	private JButton addFight;
	
	private JPanel playerPanel;
	private JPanel guildPanel;
	private JPanel fightPanel;
	
	private JFrame fightExplorer;
	private String[] fightid;
	int ife;
	
	public GUI(){
		
		
		this.startFrame = new JFrame("How I Met Your Main");
		this.startFrame.setSize(430, 240);
		this.startFrame.setResizable(true);
		this.startFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.startFrame.setLayout(new GridLayout(5,1));
		
		this.address = new JTextArea("Server's address");
		this.address.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		this.port = new JTextArea("Port");
		this.port.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		this.username = new JTextArea("Username");
		this.username.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		this.password = new JTextArea("Password");
		this.password.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		this.connect = new JButton("Connect");
		this.connect.setName("ConnectButton");
		this.connect.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event2) {
            	
            	connect();

            }
        });
		
		this.startFrame.getContentPane().add(address);
		this.startFrame.getContentPane().add(port);
		this.startFrame.getContentPane().add(username);
		this.startFrame.getContentPane().add(password);
		this.startFrame.getContentPane().add(connect);
		
		this.startFrame.setVisible(true);
		
	}
	
	public void connectionError(){
		
		startFrame.setTitle("How I Met Your Main - CONNECTION FAILED");
		
	}
	
	public void closeStartFrame(){
		
		startFrame.setVisible(false);
		
	}
	
	public void connect(){
		
		try {
			this.db = new Db(address.getText(), port.getText(), username.getText(), password.getText(), this);
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		}
		
	}
	
	public void start(){
		
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
		
		this.exportToTxt = new JButton("Export DB to .csv");
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
		this.fightPanel = new JPanel(new FlowLayout());
		
		this.addPlayer = new JButton("Add Player !");
		this.addGuild = new JButton("Add Guild !");
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
		fightExplorer.setSize(1000, 480);
		fightExplorer.setResizable(true);
		fightExplorer.setLayout(new BorderLayout());
		JPanel res = new JPanel(new BorderLayout());
		JTextArea resultBox = new JTextArea("");
		JTextField toSearch = new JTextField("Enter a name (guild, player, class, specialization, place, region, continent, faction, race, server) or a date YYYY-MM-DD and set the type above.");
		String[] types = { "PLAYERS", "GUILDS", "PLACES", "TIMES", "CLASSES", "SPECIALIZATIONS", "FACTIONS", "RACES", "REGIONS", "CONTINENTS", "SERVERS" };
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
            		
            		ife = 0;
            		String id = db.getPlayerIDFromName(toSearch.getText());
            		if(id.matches("") == false) fightid = db.requestString("select fight_id from fights where playera = " + id + " or playerb = " + id).split("\n");
            		resultFE(ife, resultBox);
            		
            	}
            	
            	if(type.getSelectedItem() == "GUILDS"){
            		
            		ife = 0;
            		String id = db.getGuildIDFromName(toSearch.getText());
            		if(id.matches("") == false) fightid = db.requestString("select distinct f.fight_id from players p, fights f where (f.playera = p.player_id or f.playerb = p.player_id) and p.guild =" + id).split("\n");
            		resultFE(ife, resultBox);
            		
            	}
            	
            	if(type.getSelectedItem() == "PLACES"){
            		
            		ife = 0;
            		fightid = db.requestString("select place_id from places where name = '" + toSearch.getText() + "'").split("\n");
            		if(db.requestString("select place_id from places where name = '" + toSearch.getText() + "'").matches("") == false) resultFE(ife, resultBox);
            		
            	}
            	
            	if(type.getSelectedItem() == "TIMES"){
            		
            		ife = 0;
            		fightid = db.requestString("select fight_id from fights where to_char(time, 'YYYY-MM-DD') = '" + toSearch.getText() + "'").split("\n");
            		if(db.requestString("select fight_id from fights where to_char(time, 'YYYY-MM-DD') = '" + toSearch.getText() + "'").matches("") == false) resultFE(ife, resultBox);
            		
            	}
            	
            	if(type.getSelectedItem() == "CLASSES"){
            		
            		ife = 0;
            		fightid = db.requestString("select distinct f.fight_id from players p, fights f where (f.playera = p.player_id or f.playerb = p.player_id) and p.classe = '" + toSearch.getText() + "'").split("\n");
            		if(db.requestString("select distinct f.fight_id from players p, fights f where (f.playera = p.player_id or f.playerb = p.player_id) and p.classe = '" + toSearch.getText() + "'").matches("") == false) resultFE(ife, resultBox);
            		
            	}
            	
            	if(type.getSelectedItem() == "SPECIALIZATIONS"){
            		
            		ife = 0;
            		fightid = db.requestString("select distinct f.fight_id from players p, fights f where (f.playera = p.player_id or f.playerb = p.player_id) and p.specialization = '" + toSearch.getText() + "'").split("\n");
            		if(db.requestString("select distinct f.fight_id from players p, fights f where (f.playera = p.player_id or f.playerb = p.player_id) and p.specialization = '" + toSearch.getText() + "'").matches("") == false) resultFE(ife, resultBox);
            		
            	}
            	
            	if(type.getSelectedItem() == "FACTIONS"){
            		
            		ife = 0;
            		fightid = db.requestString("select distinct f.fight_id from players p, fights f where (f.playera = p.player_id or f.playerb = p.player_id) and p.faction = '" + toSearch.getText() + "'").split("\n");
            		if(db.requestString("select distinct f.fight_id from players p, fights f where (f.playera = p.player_id or f.playerb = p.player_id) and p.faction = '" + toSearch.getText() + "'").matches("") == false) resultFE(ife, resultBox);
            		
            	}
            	
            	if(type.getSelectedItem() == "RACES"){
            		
            		ife = 0;
            		fightid = db.requestString("select distinct f.fight_id from players p, fights f where (f.playera = p.player_id or f.playerb = p.player_id) and p.race = '" + toSearch.getText() + "'").split("\n");
            		if(db.requestString("select distinct f.fight_id from players p, fights f where (f.playera = p.player_id or f.playerb = p.player_id) and p.race = '" + toSearch.getText() + "'").matches("") == false) resultFE(ife, resultBox);
            		
            	}
            	
            	if(type.getSelectedItem() == "REGIONS"){
            		
            		ife = 0;
            		fightid = db.requestString("select place_id from places where region = '" + toSearch.getText() + "'").split("\n");
            		if(db.requestString("select place_id from places where region = '" + toSearch.getText() + "'").matches("") == false) resultFE(ife, resultBox);
            		
            	}
            	
            	if(type.getSelectedItem() == "CONTINENTS"){
            		
            		ife = 0;
            		fightid = db.requestString("select p.place_id from places p, regions r where p.region = r.name and r.continent = '" + toSearch.getText() + "'").split("\n");
            		if(db.requestString("select p.place_id from places p, regions r where p.region = r.name and r.continent = '" + toSearch.getText() + "'").matches("") == false) resultFE(ife, resultBox);
            		
            	}
            	
            	if(type.getSelectedItem() == "SERVERS"){
            		
            		ife = 0;
            		fightid = db.requestString("select distinct f.fight_id from players p, fights f where (f.playera = p.player_id or f.playerb = p.player_id) and p.server = '" + toSearch.getText() + "'").split("\n");
            		if(db.requestString("select distinct f.fight_id from players p, fights f where (f.playera = p.player_id or f.playerb = p.player_id) and p.server = '" + toSearch.getText() + "'").matches("") == false) resultFE(ife, resultBox);
            		
            	}
            }
        });
		
		previous.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event2) {
            	
            		
           		if(ife > 0){
           			
           			ife --;
           			resultFE(ife, resultBox);
           			
        		}
            }
        });
		
		next.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event2) {
            		
           		if(ife < fightid.length - 1){
           			
           			ife ++;
           			resultFE(ife, resultBox);
 
        		}
            }
        });
		
		fightExplorer.setVisible(true);
		
	}
	
	public void resultFE(int ife, JTextArea resultBox){
		
		resultBox.setText("");
		
		if(fightid != null && fightid.length > 0){
			
			if(Integer.valueOf(db.getFightHpleftaFromID(fightid[ife])) >= Integer.valueOf(db.getFightHpleftbFromID(fightid[ife]))){
				
				resultBox.append("Name : " + db.getNameFromPlayerA(fightid[ife]) + " Guild : " + db.getGuildNameFromPlayerA(fightid[ife]) + " from Server : " + db.getServerFromPlayerA(fightid[ife]) +  " (" + db.getFightHpleftaFromID(fightid[ife]) + "% HP left)\n");
				resultBox.append(db.getRaceFromPlayerA(fightid[ife]) + "(" + db.getFactionFromPlayerA(fightid[ife]) + "), " + db.getClassFromPlayerA(fightid[ife]) + " " + db.getSpecializationFromPlayerA(fightid[ife]) + ", level : " + db.getLevelFromPlayerA(fightid[ife]) + "\n\n");
				resultBox.append("won a fight against\n\n");
				resultBox.append("Name : " + db.getNameFromPlayerB(fightid[ife]) + " Guild : " + db.getGuildNameFromPlayerB(fightid[ife]) + " from Server : " + db.getServerFromPlayerB(fightid[ife]) +  " (" + db.getFightHpleftbFromID(fightid[ife]) + "% HP left)\n");
				resultBox.append(db.getRaceFromPlayerB(fightid[ife]) + "(" + db.getFactionFromPlayerB(fightid[ife]) + "), " + db.getClassFromPlayerB(fightid[ife]) + " " + db.getSpecializationFromPlayerB(fightid[ife]) + ", level : " + db.getLevelFromPlayerB(fightid[ife]) + "\n\n");
			
			}
			else{
				
				resultBox.append("Name : " + db.getNameFromPlayerB(fightid[ife]) + " Guild : " + db.getGuildNameFromPlayerB(fightid[ife]) + " from Server : " + db.getServerFromPlayerB(fightid[ife]) +  " (" + db.getFightHpleftbFromID(fightid[ife]) + "% HP left)\n");
				resultBox.append(db.getRaceFromPlayerB(fightid[ife]) + "(" + db.getFactionFromPlayerB(fightid[ife]) + "), " + db.getClassFromPlayerB(fightid[ife]) + " " + db.getSpecializationFromPlayerB(fightid[ife]) + ", level : " + db.getLevelFromPlayerB(fightid[ife]) + "\n\n");
				resultBox.append("won a fight against\n\n");
				resultBox.append("Name : " + db.getNameFromPlayerA(fightid[ife]) + " Guild : " + db.getGuildNameFromPlayerA(fightid[ife]) + " from Server : " + db.getServerFromPlayerA(fightid[ife]) +  " (" + db.getFightHpleftaFromID(fightid[ife]) + "% HP left)\n");
				resultBox.append(db.getRaceFromPlayerA(fightid[ife]) + "(" + db.getFactionFromPlayerA(fightid[ife]) + "), " + db.getClassFromPlayerA(fightid[ife]) + " " + db.getSpecializationFromPlayerA(fightid[ife]) + ", level : " + db.getLevelFromPlayerA(fightid[ife]) + "\n\n");
				
			}
			resultBox.append("At " + db.getPlaceNameFromID(fightid[ife]) + " X : " + db.getPlaceXFromID(fightid[ife]) + " Y : " + db.getPlaceYFromID(fightid[ife]) + " in " + db.getPlaceRegionFromID(fightid[ife]) + "(" + db.getContinentFromPlaceID(fightid[ife]) + ")\n");
			resultBox.append("On the " + db.getTimeFromFight(fightid[ife]) + " for a " + db.getLengthFromFight(fightid[ife]) + " seconds fight !\n");
			resultBox.append("" + db.getCommentFromFight(fightid[ife]));
			
			
		}
		
	}
	
	public void clearPanel(){
		
		addWindow.remove(playerPanel);
		addWindow.remove(guildPanel);
		addWindow.remove(fightPanel);
		addWindow.setVisible(false);
		
	}
	
	public void setPlayerPanel(){
		
		JTextField name = new JTextField("Name");
		JTextField guild = new JTextField("Guild");
		JTextField server = new JTextField("Server (EU/NA-Name)");
		JTextField faction = new JTextField("Faction");
		JTextField race = new JTextField("Race");
		JTextField classe = new JTextField("Classe");
		JTextField specialization = new JTextField("Specialization");
		JTextField level = new JTextField("Level");
		JTextField nbdeaths = new JTextField("NB Deaths");
		JTextField nbkills = new JTextField("NB Kills");
		JTextField whisprage = new JTextField("Whisp Rage");
		JTextField skill = new JTextField("Skill (x/10)");
		JTextField backped = new JTextField("Backped (0/1)");
		JTextField skillcomment = new JTextField("Skill Comment");
		addPlayer = new JButton("Add Player in database");
		addPlayer.setName("addWindowButton");
		addPlayer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event2) {
            	
            	if(guild.getText().matches("")) guild.setText("Unguilded");
            	
            	db.addGuild(guild.getText());
            	
            	db.addPlayer(name.getText(), level.getText(), guild.getText(), server.getText(), faction.getText(), race.getText(), classe.getText(), specialization.getText(), nbkills.getText(), nbdeaths.getText(), whisprage.getText(), skill.getText(), backped.getText(), skillcomment.getText());
            	
            }
        });
		
		playerPanel.removeAll();
		
		playerPanel.add(name);
		playerPanel.add(guild);
		playerPanel.add(server);
		playerPanel.add(faction);
		playerPanel.add(race);
		playerPanel.add(classe);
		playerPanel.add(specialization);
		playerPanel.add(level);
		playerPanel.add(nbdeaths);
		playerPanel.add(nbkills);
		playerPanel.add(whisprage);
		playerPanel.add(skill);
		playerPanel.add(backped);
		playerPanel.add(skillcomment);
		playerPanel.add(addPlayer);
		
		playerPanel.revalidate();
		
	}
	
	public void setGuildPanel(){
		
		JTextField name = new JTextField("Name");
			
		addGuild = new JButton("Add Guild in database");
		addGuild.setName("addWindowButtonG");
		addGuild.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event2) {

				db.addGuild(name.getText());
	            	
	        }
	    });
		
		guildPanel.removeAll();
			
		guildPanel.add(name);
		guildPanel.add(addGuild);
			
		guildPanel.revalidate();
			
	}
	
	public void setFightPanel(){
		
		JTextField a = new JTextField("Player A");
		JTextField b = new JTextField("Player B");
		JTextField winner = new JTextField("Winner");
		JTextField loser = new JTextField("Loser");
		JTextField place = new JTextField("Place : Name/X/Y/Region/Continent");
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
            	if(a.getText().matches(loser.getText()) == false && b.getText().matches(loser.getText()) == false && loser.getText().matches("") == false){

            		error.append("Unknown Player assigned to Loser\n");

            	}
            	
            	if(error.getText().isEmpty() == true){
            		
            		String[] tempo = place.getText().split("/");
            		String placename = tempo[0];
            		String x = tempo[1];
            		String y = tempo[2];
            		String region = tempo[3];
            		String continent = tempo[4];

                	db.addFight(a.getText(), b.getText(), hplefta.getText(), hpleftb.getText(), winner.getText(), loser.getText(), time.getText(), fight_comment.getText(), fight_length.getText(), continent, region, placename, x, y);
                	
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
		fightPanel.add(fight_length);
		fightPanel.add(fight_comment);
		fightPanel.add(addFight);
		fightPanel.add(error);
		
		fightPanel.revalidate();
		
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		
		if(event.getSource() == sendRequest) outputRequest.setText(db.requestString(inputRequest.getText()));
		if(event.getSource() == addWindowButton) toggleAddWindow();
		//if(event.getSource() == download) db.objectLists.loadEverything();
		/*if(event.getSource() == exportToTxt)
			try {
				//db.objectLists.export();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
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
		
		if(event.getSource() == list && (String)list.getSelectedItem() == "Fight"){
			
			clearPanel();
			setFightPanel();
			addWindow.add(fightPanel, BorderLayout.CENTER);
			addWindow.setVisible(true);
		}
	
	}
	
}