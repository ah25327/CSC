package Tests;

import static org.junit.Assert.*;

import org.junit.Test;

import Entities.Customer;
import support.RefSortedList;

public class FirstNameWildcardComparisonTest {

	@Test
	public void test() {
		Customer b = new Customer("Han", "Andrew", "7034736750");
		
		
		assertTrue(b.firstNameWildcardComparison("And*"));
		assertTrue(b.firstNameWildcardComparison("*"));
		assertFalse(b.firstNameWildcardComparison("And"));
	}

}
