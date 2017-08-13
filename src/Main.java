import Lists.LinkedList;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		LinkedList<String> list = new LinkedList<String>();
		
		System.out.println(list);
		
		list.addFirst("Amir");
		list.addFirst("Hooman");
		list.addFirst("Shahnaz");
		
		list.remove("Amir");
		list.remove("Hooman");
		list.remove("Shahnaz");
		
		System.out.println(list);
		System.out.println(list.first());
		System.out.println(list.last());
	}

}
