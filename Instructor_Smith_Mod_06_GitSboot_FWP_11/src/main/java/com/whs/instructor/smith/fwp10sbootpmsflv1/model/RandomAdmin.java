package com.whs.instructor.smith.fwp10sbootpmsflv1.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomAdmin {
	public static List<Admin> getRandAdmin(int next20) {
		List<Admin> people = new ArrayList<>();
		for (int i = 0; i < next20; i++) {
			Admin admin = randomAdmin();
			people.add(admin);
		}
		return people;
	} 
	public static Admin randomAdmin() {
		Long id = new Random().nextLong(333388821, 339988821);
		String user = randomUser();
		String pass = randomPass(); 
		Admin admin = new Admin(id, user, pass);
		return admin;
	}
	public static String randomPass() {
		String[] p = { "ty9read", "huh6!lokl", "yel3hom", "el8fee", "loo3vgu", "poo7que", "hub9mo" };
		String f=p[new Random().nextInt(0, p.length)];
		char nu =(char) new Random().nextInt(96, 121);
		
		return f+""+nu;
	}
	public static String randomUser() {
		String[] p = { "Code", "Talent", "Warrior", "Domino", "Tiger", "Rambo", "Zeus" };
		String f=p[new Random().nextInt(0, p.length)];
		int nu = new Random().nextInt(11, 99);
		
		return f+""+nu;
	} 

	public static String randomFirst() {
		String[] n = { "Sue", "Mike", "Nick", "Julie", "Janice", "Josh", "Fred" };
		return n[new Random().nextInt(0, n.length)];
	}

	public static String randomLast() {
		String[] n = { "William", "Stone", "Yale", "Brown", "Gomez", "Reyes", "Zho" };
		return n[new Random().nextInt(0, n.length)];
	}

	public static String randomStret() {
		String[] n = { "Main St.", "Wall Blvd.", "West Ln.", "Dulles Rd.", "Griggs Rd.", "Lone St.", "2nd St." };
		return new Random().nextInt(1254, 9562) + " " + n[new Random().nextInt(0, n.length)];
	}

	public static String randomCity() {
		String[] n = { "Houston", "SugaLand", "Bellaire", "West U", "SoHo", "Kemah", "Conroe" };
		return n[new Random().nextInt(0, n.length)];
	}
}