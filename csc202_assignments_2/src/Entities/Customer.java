package Entities;

public class Customer implements Comparable<Customer>{
	private String lastName;
	private String firstName;
	private String phoneNumber;

	public Customer(String lastName, String firstName, String phoneNumber){
		this.lastName = lastName;
		this.firstName = firstName;
		this.phoneNumber = phoneNumber;
	}
	public Customer(){
		this.lastName = null;
		this.firstName = null;
		this.phoneNumber = null;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public String toString(){
		String result = this.lastName + " " + this.firstName + " " + this.phoneNumber;
		
		return result;
	}
	
//	public int compareTo(Customer customer){
//		int comparator = 0;
//		int lastNameComparator;
//		int firstNameComparator;
//		int phoneNumberComparator;
//		if(this.getPhoneNumber().equals(customer.getPhoneNumber())){
//			comparator = 0;
//		}
//		else{
//			lastNameComparator = this.getLastName().compareTo(customer.getLastName());
//			if(lastNameComparator != 0){
//				comparator = lastNameComparator;
//			}
//			else{
//				firstNameComparator = this.getFirstName().compareTo(customer.getFirstName());
//				if(firstNameComparator != 0){
//					comparator = firstNameComparator;
//				}
//				else{
//					phoneNumberComparator = this.getPhoneNumber().compareTo(customer.getPhoneNumber());
//					if(phoneNumberComparator != 0){
//						comparator = phoneNumberComparator;
//					}
//				}
//			}
//		}
//		
//		
//	}
	@Override
	public int compareTo(Customer customer) {
		// TODO Auto-generated method stub
		int comparator = 0;
		int lastNameComparator;
		int firstNameComparator;
		int phoneNumberComparator;
		
		if(this.getPhoneNumber().equals(customer.getPhoneNumber())){
			return 0;
		}
		if (this.getLastName() != null && customer.getLastName() != null){
		lastNameComparator = this.getLastName().compareTo(customer.getLastName());
		if(lastNameComparator != 0){
			return lastNameComparator;
		}
		}
		if (this.getFirstName() != null && customer.getFirstName() != null){
		firstNameComparator = this.getFirstName().compareTo(customer.getFirstName());
		if(firstNameComparator != 0){
			return firstNameComparator;
		}
		}
		phoneNumberComparator = this.getPhoneNumber().compareTo(customer.getPhoneNumber());
		if(phoneNumberComparator != 0){
			return phoneNumberComparator;
		}
		return comparator;
	}
	
	public boolean equals(Customer customer){
		if(this.getPhoneNumber().equals(customer.getPhoneNumber())){
			return true;
		}
		else{
			return false;
		}
	}
	
	public boolean noWildcardEquals(String lastName, String firstName){
		if(this.lastName.equals(lastName) && this.firstName.equals(firstName)){
			return true;
		}
		else{
			return false;
		}
	}
	
	public boolean wildcardEquals(String lastName, String firstName){
		if(lastName.indexOf("*") != -1 || lastName != null){
			
		}
		return false;
	}
	public boolean firstNameWildcardComparison(String firstName){
		if(firstName.indexOf("*") != -1 && firstName != null){
			if(firstName.startsWith("*")){
				return true;
			}
			else{
				if(this.firstName != null && this.firstName.startsWith(firstName.substring(0, firstName.indexOf("*")))){
					return true;
				}
				else{
					return false;
				}
			}
		}
		else{
			if(firstName.equals(this.firstName) && firstName != null && this.firstName != null){
				return true;
			}
			else{
				return false;
			}
		}
	}

	public boolean equals(Customer customer, boolean hasWildCards) {
		// Case where there are no wildcards
		if (!hasWildCards) {
			return noWildcardEquals(customer.getLastName(), customer.getFirstName());
		}
		// Case where wild cards can be present
		else {
			if ((customer.getLastName() != null && this.lastName != null)) {
				if (customer.getLastName().indexOf("*") != -1) {
					if (customer.getLastName().startsWith("*")) {
						return firstNameWildcardComparison(customer.getFirstName());
					} else {
						if (this.lastName.startsWith(customer.getLastName().substring(0, customer.getLastName().indexOf("*")))) {
							return firstNameWildcardComparison(customer.getFirstName());
						} else {
							return false;
						}
					}
				} else {
					if(this.lastName.equals(customer.getLastName())){
						return firstNameWildcardComparison(customer.getFirstName());
					}
				}
			}
			return false;
		}
	}
	
}
