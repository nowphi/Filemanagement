package header;

public class class2 extends testString {
	
	@FilePosToMdlFieldRelation(filePos=6)
	String HeaderValue4 = "Value6";
	
	public static void main(String args[]) {
		class2 c = new class2();
		System.out.println(c.getFileHeader());
	}
}
