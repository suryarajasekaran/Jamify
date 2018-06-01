package jamify;


	import org.apache.tika.exception.TikaException;
	import org.apache.tika.metadata.Metadata;
	import org.apache.tika.parser.Parser;
	import org.apache.tika.parser.mp3.Mp3Parser;
	import org.apache.tika.parser.ParseContext;
	import org.xml.sax.ContentHandler;
	import org.xml.sax.SAXException;
	import org.xml.sax.helpers.DefaultHandler;


	import javax.imageio.ImageIO;
	import javax.swing.*;
	import java.awt.*;
	import java.awt.image.BufferedImage;
	import java.io.*;
	import java.util.ArrayList;


public class FancyStuff {

	public void fancy(String songPath) {
		String name = songPath;


		try {
			InputStream input = new FileInputStream(new File(name));
			ContentHandler handler = new DefaultHandler();
			Metadata metadata = new Metadata();
			Parser parser = new Mp3Parser();
			ParseContext parserCtx = new ParseContext();
			parser.parse(input, (ContentHandler) handler, metadata, parserCtx);
			input.close();

			DisplayImage(this.getSongImage(songPath));
			DisplayName(this.getSongName(songPath));

			System.out.println("Title: " + metadata.get("title"));
			System.out.println("Artists: " + metadata.get("xmpDM:artist"));
			System.out.println("Composer: " + metadata.get("xmpDM:composer"));
			System.out.println("Genre: " + metadata.get("xmpDM:genre"));
			System.out.println("Alnum: " + metadata.get("xmpDM:album"));
			//System.out.println("Alnum: "+metadata.get("xmpDM:image"));

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (TikaException e) {
			e.printStackTrace();
		}
	}

	public static void DisplayName(String songNameQ) throws IOException {

		JFrame frame = new JFrame();
		frame.setLayout(new FlowLayout());
		frame.setSize(400, 400);
		JLabel lbl = new JLabel(songNameQ);
		frame.add(lbl);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void DisplayImage(String songImageQ) throws IOException {

		BufferedImage img = ImageIO.read(new File(String.valueOf(songImageQ)));
		ImageIcon icon = new ImageIcon(img);
		JFrame frame = new JFrame();
		frame.setLayout(new FlowLayout());
		frame.setSize(200, 300);
		JLabel lbl = new JLabel();
		lbl.setIcon(icon);
		frame.add(lbl);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public String getSongName(String path) {
		String csvFile = "/Users/SuryaRajasekaran/Desktop/Jamify/src/jamify/AllSongs.csv";
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";
		try {
			br = new BufferedReader(new FileReader(csvFile));
			while ((line = br.readLine()) != null) {
				// use comma as separator
				String[] csvitems = line.split(cvsSplitBy);
				if (csvitems[2].equals(path)) {
					return csvitems[1];
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}

	public String getSongImage(String path) {
		String csvFile = "/Users/SuryaRajasekaran/Desktop/Jamify/src/jamify/AllSongs.csv";
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";
		try {
			br = new BufferedReader(new FileReader(csvFile));
			while ((line = br.readLine()) != null) {
				// use comma as separator
				String[] csvitems = line.split(cvsSplitBy);
				if (csvitems[2].equals(path)) {
					return csvitems[3];
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}


		return null;
	}
}


