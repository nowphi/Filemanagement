package filemangement;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.logging.Logger;

public class ExportReaderCSV {

	private File TARGET_FILE;
	
	 final Logger log = Logger.getLogger( this.getClass().getName() );
	
	public ExportReaderCSV(File targetFile) {
		this.TARGET_FILE = targetFile;
		
		log.info("Exportfile: " + TARGET_FILE);
	}
	
	public void write(Collection<?> list, String header) {
		
		BufferedWriter out;
		try {
			out = new BufferedWriter(new FileWriter(TARGET_FILE, false));
			
			// Set header
			out.write(header);
			out.newLine();
			
			// Content
			for (Iterator<?> it = list.iterator(); it.hasNext();) {
				Object obj = it.next();
				out.write(obj.toString());
				out.newLine();
			}
			
			log.info("Exportfile: " + TARGET_FILE + " writen.");
			
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			log.info("Exportfile: " + TARGET_FILE + " cannot be writen. Exception " + e);
		}
	}
	
	public void write(Collection<?> list, boolean append ) {
		
		BufferedWriter out;
		try {
			out = new BufferedWriter(new FileWriter(TARGET_FILE,append));
				
			// Content
			for (Iterator<?> it = list.iterator(); it.hasNext();) {
				Object obj = it.next();
				System.out.println(obj.toString());
				out.write(obj.toString());
				out.newLine();
			}
			
			log.info("Exportfile: " + TARGET_FILE + " writen.");
			
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			log.info("Exportfile: " + TARGET_FILE + " cannot be writen. Exception " + e);
		}
	}
	
}
