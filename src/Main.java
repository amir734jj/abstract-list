import Lists.*;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		AbstractList<String> list = new ArrayList<String>();

		System.out.println(list);

		list.addFirst("Amir");
		list.addFirst("Hooman");
		list.addFirst("Shahnaz");

		System.out.println(list.toString());

	}

}
