package filemangement;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * 
 * <p> ImportReaderCSV provides a quick and convenient way of producing a collection from text or csv file. </p>
 * 
 * @author nowphi
 * @version 1.0
 */
public class ImportReaderCSV {

	private File SOURCE_FILE;

	private String LINE_DELIMITER = "\n";

	private String SYMBOL_FILTER= "[\\n|\\r]";

	private String CSV_BUFFER = new String();

	 final Logger log = Logger.getLogger( this.getClass().getName() );
	
	/**
	 * @param sourceFile
	 */
	public ImportReaderCSV(File sourceFile) {
		this.SOURCE_FILE = sourceFile;
		log.info("Importfile: " + SOURCE_FILE + " line delimiter: " + LINE_DELIMITER.trim() + "\\n" + " symbol filter: "  + SYMBOL_FILTER);
	}

	/**
	 * @param sourceFile
	 * @param lineDelimiter
	 */
	public ImportReaderCSV(File sourceFile, String lineDelimiter) {
		this.LINE_DELIMITER = lineDelimiter;
		this.SOURCE_FILE = sourceFile;
		log.info("Importfile: " + SOURCE_FILE + " line delimiter: " + LINE_DELIMITER.trim() + "\\n" + " symbol filter: "  + SYMBOL_FILTER);
	}

	/**
	 * @param sourceFile
	 * @param lineDelimiter
	 * @param symbolFilter
	 */
	public ImportReaderCSV(File sourceFile, String lineDelimiter, String symbolFilter) {
		this.LINE_DELIMITER = lineDelimiter;
		this.SOURCE_FILE = sourceFile;
		this.SYMBOL_FILTER = symbolFilter;	
		log.info("Importfile: " + SOURCE_FILE + " line delimiter: " + LINE_DELIMITER.trim() + "\\n" + " symbol filter: "  + SYMBOL_FILTER);
	}

	/**
	 * @return the cSV_BUFFER
	 */
	public String getCSV_BUFFER() {
		return CSV_BUFFER;
	}
	
	/**
	 * @return the lINE_DELIMITER
	 */
	public String getLINE_DELIMITER() {
		return LINE_DELIMITER;
	}
	/**
	 * @return the sOURCE_FILE
	 */
	public File getSOURCE_FILE() {
		return SOURCE_FILE;
	}
	/**
	 * @return the sYMBOL_FILTER
	 */
	public String getSYMBOL_FILTER() {
		return SYMBOL_FILTER;
	}
	
	/** 
	 * <p> Reads the File (usually CSV) as Buffer in the ImportReader. Method has to invoke before transformation. </p>
	 * 
	 * @throws IOException
	 */
	public void read()  {
		
		StringBuilder sb = new StringBuilder();
		
		try 
		{
			int c;
			
			BufferedReader 	in = new BufferedReader(new FileReader(SOURCE_FILE));
			
			while ((c = in.read()) != -1) 
			{
				sb.append( (char) c);
			}
				
			in.close();
			
			log.info("Importfile: " + SOURCE_FILE + " readed.");
			
		} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			log.info("Importfile: " + SOURCE_FILE + " cannot be readed. Exception " + e);
		}
		
			this.CSV_BUFFER = sb.toString();
	}
	
	/**
	 * @param lINE_DELIMITER the lINE_DELIMITER to set
	 */
	public void setLINE_DELIMITER(String lINE_DELIMITER) {
		LINE_DELIMITER = lINE_DELIMITER;
	}
	
	/**
	 * @param sOURCE_FILE the sOURCE_FILE to set
	 */
	public void setSOURCE_FILE(File sOURCE_FILE) {
		SOURCE_FILE = sOURCE_FILE;
	}
		
	/**
	 * @param sYMBOL_FILTER the sYMBOL_FILTER to set
	 */
	public void setSYMBOL_FILTER(String sYMBOL_FILTER) {
		SYMBOL_FILTER = sYMBOL_FILTER;
	}
	
	/**
	 *<p> Transforms a readed buffer to a collection with the given specification. The method read() has to invoke before transformation or a empty string returns.
	 * Trims whitespaces and filters empty lines.</p> 
	 * Default configuration:
	 * <li> Line delimiter: \n </li> <li>  symbol filter (regex): [\\n|\\r] </li>
	 * 
	 * <pre> Usage example: 
	 * 		reader.<String>transformCSV( s -> new String(s)); 
	 * 		generates a collection with string listings 
	 * </pre>
	 * @param objectGenerationFunction the specification for object generation
	 * @return collection with the in function given specification or an empty string
	 */
	public <T> List<T> transformCSV(   Function <String, T> objectGenerationFunction) {
		return Arrays.stream(CSV_BUFFER.split(LINE_DELIMITER)) // Split with given delimiter
		 		.map(p -> p.trim().replaceAll(SYMBOL_FILTER, " "))
		 		.filter(p -> !p.isEmpty())
		 		.map(objectGenerationFunction )
		 		.collect(Collectors.toList());
	}
}
