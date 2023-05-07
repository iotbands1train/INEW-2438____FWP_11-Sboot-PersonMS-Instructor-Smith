package com.whs.instructor.smith.fwp10sbootpmsflv1.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class RandomPerson {
	private static List<Person> getRandPerson(int next20) {
		List<Person> people = new ArrayList<>();
		for (int i = 0; i < next20; i++) {
			Person person = randomPerson();
			people.add(person);
		}
		return people;
	}

	public static Person randomPerson() {
		Long id = new Random().nextLong(333388821, 339988821);
		String fname = randomFirst();
		String lname = randomLast();
		String street = randomStret();
		String city = randomCity();
		String state = "Tx";
		String zip = "77" + new Random().nextInt(199, 998) + "";
		String phone = new Random().nextInt(199, 998) + "-" + new Random().nextInt(199, 998) + "-"
				+ new Random().nextInt(1991, 9989);
		Person person = new Person(id, fname, lname, street, city, state, zip, phone);
		return person;
	}

	private static String randomFirst() {
		String[] n = { "Sue", "Mike", "Nick", "Julie", "Janice", "Josh", "Fred" };
		return n[new Random().nextInt(0, n.length)];
	}

	private static String randomLast() {
		String[] n = { "William", "Stone", "Yale", "Brown", "Gomez", "Reyes", "Zho" };
		return n[new Random().nextInt(0, n.length)];
	}

	private static String randomStret() {
		String[] n = { "Main St.", "Wall Blvd.", "West Ln.", "Dulles Rd.", "Griggs Rd.", "Lone St.", "2nd St." };
		return new Random().nextInt(1254, 9562) + " " + n[new Random().nextInt(0, n.length)];
	}

	private static String randomCity() {
		String[] n = { "Houston", "SugaLand", "Bellaire", "West U", "SoHo", "Kemah", "Conroe" };
		return n[new Random().nextInt(0, n.length)];
	}

	public static List<Person> randomPersonList(int x) {
		List<Person> l = new ArrayList<>();
		for (int i = 0; i < x; i++) {
			l.add(randomPerson());
		}
		return l;
	}
}