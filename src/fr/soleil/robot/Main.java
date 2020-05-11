package fr.soleil.robot;

public class Main {
	
	public static void main(String[] args) {
		Plateau p = new Plateau(20, 7);
		Robot r = new Robot(p,3,2,Orientation.NORTH);
		System.out.println(p.afficherPlateau());
		System.out.println(r.x+ "/" +r.y);
		r.simulate("FF");
		System.out.println(r.x+ "/" +r.y);
		System.out.println(p.afficherPlateau());
	}

}
