package jamify;

import java.awt.BorderLayout;


import java.awt.Color;
import java.awt.*;
import java .awt.event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.*;


public class MusicPlayerUI extends JFrame implements ActionListener{
	public static String commonPath = "/Users/SuryaRajasekaran/Desktop/jamify_may30/src/jamify/";

//	public static String songPath = "/Users/satishrambhatla/Documents/workspace/Jamify/src/jamify/Allsongs.csv";
//	public static String tempFilePath = "/Users/satishrambhatla/Documents/workspace/Jamify/src/jamify/tempFile.txt";
	JFrame window = new JFrame ("Something Working");
	JPanel display = new JPanel();
	static JPanel jp = new JPanel();
    static JPanel playlist = new JPanel();
    static JPanel showqueue = new JPanel();
    JButton allSongsButton = new JButton("All Songs");
    static JButton play = new JButton("Play");
    static JButton pause = new JButton("Pause");
   static  JButton stop = new JButton("Stop");
   static   JButton rewind = new JButton("Rewind");
    static JButton forward= new JButton("Forward");
   static JButton repeat = new JButton("Repeat");
   static JButton next = new JButton("Next");
    static JButton previous = new JButton("Previous");
    static JButton createPlaylistButton = new JButton("Create Playlist");
	static JLabel label = new JLabel("Jamify");
	static JButton shuffle = new JButton("Shuffle Songs");
	
	private static int y;
	MusicPlayerUI()
	{
		AllSongs allSongs = new AllSongs();
		display.setBackground(Color.WHITE);
		playlist.setBackground(Color.black);
		showqueue.setBackground(Color.white);
		window.setSize(1000,500);
		//window.setResizable(false);
		display.setPreferredSize(new Dimension(400,200));
		playlist.setPreferredSize(new Dimension(200,100));
		showqueue.setPreferredSize(new Dimension(200,200));
		playlist.add(allSongsButton);
		 ActionListener Action1 = (ActionEvent e) -> {
           try {
			   String path = "/Users/SuryaRajasekaran/Desktop/JamifySongs/AllSongs.csv";
         JPanel allSongsPanel = allSongs.displaySongsOnThePanel(new File(path), true);

         allSongsPanel.setPreferredSize(new Dimension(400,400));
           allSongsPanel.setLocation(200,100);
           
        //           allSongsPanel.setBounds(200, 100, 400, 400);
           	window.getContentPane().add(allSongsPanel);
           } catch (Exception p){
			   System.out.println("Error");
               System.out.println(p);
           }

      };
		allSongsButton.addActionListener(Action1);
		playlist.add(createPlaylistButton);
		createPlaylistButton.addActionListener(new CreatePlayListActionListener());
//		allSongsButton.addActionListener(new showAllSongs);
		window.add(display);
		display.add(label);
//		window.add(jp);
		window.setLayout(new BorderLayout(3,3));
		display.setLayout(new FlowLayout());
		display.add(play);
		display.add(stop);
		display.add(pause);
		display.add(rewind);
		display.add(forward);
		display.add(repeat);
		display.add(shuffle);
		display.add(next);
		display.add(previous);
		window.setLocation(100,100);
		window.add(playlist,BorderLayout.WEST);
		window.add(showqueue,BorderLayout.EAST);
		window.add(display,BorderLayout.SOUTH);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);
//		display.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0){
		
	}
	


	public static void main(String [] args) 
	{

	
		SavePlaylists savePlaylists = new SavePlaylists();
		savePlaylists.retrieve();

		/** Previously if there were any playlist created it'll be shown on the UI. On Right click of the play list button,
		 * DELETE Option is provided */

		if (AllSongs.playlistNamePathsList.size() > 0) {
			for (int i = 0; i < AllSongs.playlistNamePathsList.size(); i++) {
				JButton playlistButton = new JButton(AllSongs.playlistNamePathsList.get(i).getPlayListName());
				int finalI1 = i;
				playlistButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						super.mouseClicked(e);
						if (e.getModifiers() == MouseEvent.BUTTON3_MASK)
						{
							System.out.println("You right clicked on the button");

							JPopupMenu rightClickMenu = new JPopupMenu();
							JMenuItem delete = new JMenuItem("Delete");
							rightClickMenu.add(delete);
							rightClickMenu.show(e.getComponent(), e.getX(), e.getY());

							delete.addActionListener(new ActionListener() {
								@Override
								public void actionPerformed(ActionEvent e) {
									System.out.println("clicked delete ");
									MusicPlayerUI.playlist.remove(playlistButton);
									AllSongs.playlistNamePathsList.remove(finalI1);
									MusicPlayerUI.playlist.repaint();
								}
							});

						}
					}
				});


				playlistButton.setBounds(20, y, 100, 30);
				y = y + 30;
				MusicPlayerUI.playlist.add(playlistButton);

				int finalI = i;

				/** On click of Play list button, the songs added in that play list is shown */
				playlistButton.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						//JFrame jFrame = new JFrame();
						AllSongs allSongs = new AllSongs();
						JPanel jp = new JPanel();
						//AllSongs allSongs = new AllSongs();
						jp = allSongs.displaySongsOnThePanel(new File(AllSongs.playlistNamePathsList.get(finalI).getPlayListPath()), false);
						GroupLayout allSongsLayout = new GroupLayout(jp);
						jp.setLayout(allSongsLayout);
						allSongsLayout.setHorizontalGroup(allSongsLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0, 510, Short.MAX_VALUE)
						);
						allSongsLayout.setVerticalGroup(allSongsLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0, 420, Short.MAX_VALUE));
						JFrame frame3 = new JFrame();
						frame3.setSize(500, 500);
						frame3.setLocation(300, 200);
						frame3.add(jp);
						frame3.setVisible(true);

					}
				});
			}
		}


		MusicPlayerUI  mp = new MusicPlayerUI();
		
		mp.window.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				savePlaylists.save();
			}
		});

		
			}
}
