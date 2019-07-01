package header;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import filemangement.ExportReaderCSV;
import filemangement.ImportReaderCSV;

public class testString {

	@FilePosToMdlFieldRelation(filePos=5)
	String HeaderValue1 = "Value1";
	@FilePosToMdlFieldRelation(filePos=1)
	String HeaderValue2 = "Value2";
	@FilePosToMdlFieldRelation(filePos=2)
	String HeaderValue3 = "Value3";
	@FilePosToMdlFieldRelation(filePos=3)
	String HeaderValue4 = "Value4";
	
	String delimiter = ";";
	
	public String getFileHeader(){
		
		HashMap  <Integer, String > header = new HashMap<Integer,  String >();
		
		for(Class<?> c = testString.class ; !c.equals(Object.class); c = c.getSuperclass()) 
		{
			for(Field f : c.getDeclaredFields()) 
			{
				if(f.isAnnotationPresent(FilePosToMdlFieldRelation.class)) 
				{
					FilePosToMdlFieldRelation a = f.getAnnotation(FilePosToMdlFieldRelation.class);
					int key = a.filePos(); 
					header.put(key,  f.getName()  );
				}
			}
		}
		
		return header.entrySet()
                .stream()
                .map(k -> k.getValue().toString())
                .collect(Collectors.joining(delimiter));
		
	}
	
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {

		HashMap  <Integer, String > value = new HashMap<Integer,  String >();
			
		for(Class<?> c = testString.class ; !c.equals(Object.class); c = c.getSuperclass()) 
		{
			for(Field f : c.getDeclaredFields()) 
			{
				if(f.isAnnotationPresent(FilePosToMdlFieldRelation.class)) 
				{
					FilePosToMdlFieldRelation a = f.getAnnotation(FilePosToMdlFieldRelation.class);
					int key = a.filePos(); 

					try 
					{
						value.put(key,  f.get(this).toString()  );
					} 
					catch (IllegalArgumentException e) 
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					} 
					catch (IllegalAccessException e) 
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		
		return value.entrySet()
                .stream()
                .map(k -> k.getValue().toString())
                .collect(Collectors.joining(delimiter));
					
		//return String.join(";", map.entrySet().stream().map(e -> e.getKey()+"="+e.getValue().collect(Collectors.toList())));
		
		//.getDeclaredFields()
		//this.getClass().getDeclaredFields()
		
	//	return String.join(";", String.valueOf(Value1) , String.valueOf(Value2), String.valueOf(Value3), String.valueOf(Value4));
	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//toString
		
		 final Logger log = Logger.getLogger( testString.class.getName() );
		
		String str = new testString().toString();
		
		//System.out.println(str.toString());
		
		List<testString> list = new ArrayList<testString>();
		
		for(int i = 0; i<100;i++) {
			list.add(new testString());
		}
		
		File file = new File("C:\\Users\\user\\Desktop\\export\\list.txt");
		File file2 = new File("C:\\Users\\user\\Desktop\\export\\list.txt");
		
		ExportReaderCSV exporter = new ExportReaderCSV(file);
		exporter.write(list, list.get(0).getFileHeader());
	
		
		ImportReaderCSV importer = new ImportReaderCSV(file2, "\r\n");
		
		importer.read();
		
		List<String> list2 = importer.<String>transformCSV(s -> new String(s) );
		//list2.forEach(System.out::println);

	}

	
	
	
}
