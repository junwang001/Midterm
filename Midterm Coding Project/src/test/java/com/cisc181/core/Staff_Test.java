package com.cisc181.core;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Calendar;

import org.junit.BeforeClass;
import org.junit.Test;

import com.cisc181.eNums.eTitle;

public class Staff_Test {

	@BeforeClass
	public static void setup() {
	}
	
	@Test
	public void test() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(2017, 3, 17);
		Staff staff1 = new Staff("Tom", "", "", calendar.getTime(), "", "", "", "", 1, 20000, calendar.getTime(), eTitle.MR);
		Staff staff2 = new Staff("Sam", "", "", calendar.getTime(), "", "", "", "", 1, 80000, calendar.getTime(), eTitle.MRS);
		Staff staff3 = new Staff("Jack", "", "", calendar.getTime(), "", "", "", "", 1, 40000, calendar.getTime(), eTitle.MRS);
		Staff staff4 = new Staff("Lucy", "", "", calendar.getTime(), "", "", "", "", 1, 60000, calendar.getTime(), eTitle.MS);
		ArrayList<Staff> staffList = new ArrayList<Staff>();
		staffList.add(staff1);
		staffList.add(staff2);
		staffList.add(staff3);
		staffList.add(staff4);
		double sum = 0;
		for(Staff s : staffList) {
			sum += s.getSalary();
		}
		double average = sum / staffList.size();
		assertEquals(5000000, (int)(average * 100));
	}	
	
	@Test
	public void testException() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(2280, 12, 11);
		Staff staff1 = new Staff("Tom", "", "", calendar.getTime(), "", "12-12-111", "", "", 1, 20000, calendar.getTime(), eTitle.MR);
		try {
			staff1.verifyDOB();
		} catch (PersonException e) {
			System.out.println("invalid DOB");
		}
		try {
			staff1.verifyPhone();
		} catch (PersonException e) {
			System.out.println("invalid Phone");
		}
		
	}

}
