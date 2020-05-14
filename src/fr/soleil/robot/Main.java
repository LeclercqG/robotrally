package fr.soleil.robot;

public class Main {
	
	public static void main(String[] args) {
		Plateau p = new Plateau(7, 7);
		Robot r = new Robot(p,0,0,Orientation.EAST);
		new Mur(p, 3,3, Orientation.EAST);
		System.out.println(p.afficherPlateau());
		r.simulate("FFFR");
		r.simulate("F");
		System.out.println(p.afficherPlateau());
	}

}
