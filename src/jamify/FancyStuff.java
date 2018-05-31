package jamify;


	import org.apache.tika.exception.TikaException;
	import org.apache.tika.metadata.Metadata;
	import org.apache.tika.parser.Parser;
	import org.apache.tika.parser.mp3.Mp3Parser;
	import org.apache.tika.parser.ParseContext;
	import org.xml.sax.ContentHandler;
	import org.xml.sax.SAXException;
	import org.xml.sax.helpers.DefaultHandler;


	import java.io.*;


	public class FancyStuff {
	    public void fancy (String songPath) {
	        String name = songPath;
			System.out.println("path from Fancy" + name);
	        try {
	            InputStream input = new FileInputStream(new File(name));
	            ContentHandler handler = new DefaultHandler();
	            Metadata metadata = new Metadata();
	            Parser parser = new Mp3Parser();
	            ParseContext parserCtx = new ParseContext();
	            parser.parse(input, (ContentHandler) handler,metadata,parserCtx);
	            input.close();

	            System.out.println("Title: "+metadata.get("title"));
	            System.out.println("Artists: "+metadata.get("xmpDM:artist"));
	            System.out.println("Composer: "+metadata.get("xmpDM:composer"));
	            System.out.println("Genre: "+metadata.get("xmpDM:genre"));
	            System.out.println("Alnum: "+metadata.get("xmpDM:album"));

	        }catch (FileNotFoundException e){
	            e.printStackTrace();
	        }catch (IOException e){
	            e.printStackTrace();
	        }catch (SAXException e){
	            e.printStackTrace();
	        }catch (TikaException e){
	            e.printStackTrace();
	        }
	    }
	}


