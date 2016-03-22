import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

import Entities.Customer;
import support.RefSortedList;

public class PhoneBookApp {
	private static File textFile;
	
	public PhoneBookApp(){
		
	}
	public static RefSortedList<Customer> importPhoneBook(String filePath) throws FileNotFoundException{
		RefSortedList<Customer> result = new RefSortedList<Customer>();
		textFile = new File(filePath);
		Scanner scanner = new Scanner(textFile);
		String firstName, lastName, phoneNumber, line;
		String secondSegment;
		while(scanner.hasNextLine()){
			line = scanner.nextLine();
			lastName = line.substring(0, line.indexOf(" "));
			secondSegment = line.substring(line.indexOf(" ")+1);
			firstName = secondSegment.substring(0, secondSegment.indexOf(" "));
			phoneNumber = secondSegment.substring(secondSegment.indexOf(" ")+1);
			Customer newCustomer = new Customer(lastName, firstName, phoneNumber);
			result.add(newCustomer);
		}
		scanner.close();
		return result;
	}
	public static boolean findPhoneNumber(String phoneNumber, RefSortedList<Customer> list){
		list.reset();

		Customer itemInList = list.getNext();
		
		while(itemInList != null){
			if(itemInList.getPhoneNumber().equals(phoneNumber)){
				return true;
			}
			else{
				itemInList = list.getNext();
				if(itemInList.getPhoneNumber().equals(phoneNumber)){
					return false;
				}
			}
		}
		return false;
	}
	
	public static void insert(String lastName, String firstName, String phoneNumber, RefSortedList<Customer> list){
		list.reset();
		if(!findPhoneNumber(phoneNumber, list)){
			Customer input = new Customer(lastName, firstName, phoneNumber);
			list.add(input);
		}
		else{
			System.out.println("Phone number already exists");
		}
	}
	
	public static void delete(String lastName, String firstName, String phoneNumber, RefSortedList<Customer> list){
		if(findPhoneNumber(phoneNumber, list)){
			Customer input = new Customer(lastName, firstName, phoneNumber);
			list.remove(input);
		}
	}
	
	public static void search(String firstName, String lastName, RefSortedList<Customer> list){
		list.reset();
		Customer input = new Customer(lastName, firstName, null);
		Customer itemInList = list.getNext();
		Customer firstCustomer = itemInList;
		boolean hasWildcard = (lastName.contains("*") || firstName.contains("*"));
		while(itemInList != null){
			if(itemInList.equals(input, hasWildcard)){
				System.out.println(itemInList.toString());
			}
			itemInList = list.getNext();
				if(itemInList.equals(firstCustomer)){
					break;
				}			
		}
	}
	
	public static void print(RefSortedList<Customer> list){
		list.reset();
		Customer itemInList = list.getNext();
		Customer firstCustomer = itemInList;
		while(itemInList != null){
			
			System.out.println(itemInList.toString());
			
			itemInList = list.getNext();
			if(itemInList.equals(firstCustomer)){
				break;
			}			
		}
	}
	
	public static void quit(RefSortedList<Customer> list) throws IOException{
		list.reset();
		FileOutputStream outputStream = new FileOutputStream(textFile, false);
		Customer itemInList = list.getNext();
		Customer firstCustomer = itemInList;
		while(itemInList != null){
			
			outputStream.write((itemInList.toString()+"\n").getBytes());
			itemInList = list.getNext();
			if(itemInList.equals(firstCustomer)){
				break;
			}			
		}
		outputStream.close();
	}
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Customer a = new Customer("Han", "Noble", "7034736751");
		Customer b = new Customer("Han", "Jack", "7034736750");
		//System.out.println("Compare to: " + a.compareTo(b));
		RefSortedList<Customer> test = importPhoneBook("C:\\Users\\Andre\\Desktop\\testPhoneBook.txt");
		insert("Han","Noble","7034736750", test);
		insert("Han","Noble","7034736751", test);
		System.out.println("Test: " + test.size());
		//print(test);
		System.out.println(findPhoneNumber("7034736750", test));
		//insert("Han", "Andrew", "7034736751", test);
		//insert("Han", "Marie", "7034736750",test);
		search("*", "H*", test);
		print(test);
		//quit(test);
	}

}
