package mainSrc;

import javax.swing.*;

public class GameMenu extends JComponent{

	public GameMenu() {
		//Menu Bar for Game

		// Creates a menubar for a JFrame
		JMenuBar menuBar = new JMenuBar();

		// Add the menubar to the frame
		//setJMenuBar(menuBar);

		// Define and add two drop down menu to the menubar
		JMenu aboutMenu = new JMenu("About");
		JMenu musicMenu = new JMenu("Music");
		menuBar.add(aboutMenu);
		menuBar.add(musicMenu);

		// Create and add simple menu item to one of the drop down menu
		JMenuItem nextSongAction = new JMenuItem("Next Song");
		JMenuItem previousSongAction = new JMenuItem("Previous Song");
		JMenuItem playSongAction = new JMenuItem("Play");
		JMenuItem stopSongAction = new JMenuItem("Stop");

		musicMenu.add(nextSongAction);
		musicMenu.add(previousSongAction);
		musicMenu.add(playSongAction);
		musicMenu.add(stopSongAction);

		/*
		nextSongAction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("You have clicked on the new action");
				testBoard.gameMusic.nextSong();
			}
		});
		*/

	}

}
