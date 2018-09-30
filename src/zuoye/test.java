package zuoye;

import javax.xml.stream.events.Namespace;

public class test {
	
	public String nameString;
	public test() {
		nameString="<exercisefile>.txt";
		System.out.println(nameString.indexOf("-"));
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		test test = new test();
		test.nameString = test.nameString.replaceAll("<", "");
		test.nameString = test.nameString.replaceAll(">", "");
		// ½âÎö
	    System.out.println("tttt"+test.nameString);;
	}

}
