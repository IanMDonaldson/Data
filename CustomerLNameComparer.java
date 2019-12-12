package com.iandonaldson.data;

import java.util.Comparator;
public class CustomerLNameComparer implements Comparable<Customer>{

	public Comparator<Customer> lastNameComparator = new Comparator<Customer>() {
		public int compare(Customer customer1, Customer customer2) {
			return customer1.getLastName().compareTo(customer2.getLastName());
		}
	};

	@Override
	public int compareTo(Customer arg0) {
		// TODO Auto-generated method stub
		return 0;
	}
}
