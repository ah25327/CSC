package Collections;

import support.LLNode;
import support.RefSortedList;


import Entities.Customer;

public class SimplePhoneBook extends RefSortedList<Customer> {
	protected LLNode<Customer> location;
	protected LLNode<Customer> previous;
	protected LLNode<Customer> list;

	public SimplePhoneBook() {
		super();
	}

	public void insert(Customer customer) {
		this.add(customer);
	}

	public void delete(Customer customer) {
		this.remove(customer);
	}

	public RefSortedList<Customer> search(String lastName, String firstName) {
		RefSortedList<Customer> result = new RefSortedList<Customer>();
			location = list;
			while(location != null){
				result.add(find(lastName, firstName));
			}
		return result;

	}

	public Customer find(String lastName, String firstName) {
		location = list;
		Customer result = null;
		String lastNameSearch = "";
		String firstNameSearch = "";
		boolean containsWildCard = false;
		if (lastName.contains("*") || firstName.contains(".")) {
			containsWildCard = true;
			if (lastName.contains("*")) {
				lastNameSearch = lastName.substring(0, lastName.indexOf('*'));
			}
			if (firstName.contains("*")) {
				firstNameSearch = firstName.substring(0, lastName.indexOf('*'));
			}
		}
		if (containsWildCard) {
			while (location != null) {
				if (location.getInfo().getLastName().substring(0, lastNameSearch.length()).equals(lastNameSearch)
						&& location.getInfo().getFirstName().substring(0, firstNameSearch.length())
								.equals(firstNameSearch)) {
					result = location.getInfo();
				} else {
					previous = location;
					location = location.getLink();
				}
				
			}
		} else {
			while (location != null) {
				if (location.getInfo().getLastName().equals(lastName)
						&& location.getInfo().getFirstName().equals(firstName)) {
					result = location.getInfo();
				} else {
					previous = location;
					location = location.getLink();
				}
			}
		}
		return result;
	}

}
