package com.cisc181.core;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.UUID;

import org.junit.BeforeClass;
import org.junit.Test;

import com.cisc181.eNums.eMajor;

public class Student_Test {

	private static ArrayList<Course> courses = new ArrayList<Course>();
	private static ArrayList<Semester> semesters = new ArrayList<Semester>();
	private static ArrayList<Section> sections = new ArrayList<Section>();
	private static ArrayList<Student> students = new ArrayList<Student>();
	private static ArrayList<Enrollment> enrollments = new ArrayList<Enrollment>();

	@BeforeClass
	public static void setup() {
		Course course1 = new Course();
		course1.setCourseID(UUID.randomUUID());
		course1.setCourseName("A");
		course1.setGradePoints(3);
		course1.setMajor("a");
		Course course2 = new Course();
		course2.setCourseID(UUID.randomUUID());
		course2.setCourseName("B");
		course2.setGradePoints(3);
		course2.setMajor("b");
		Course course3 = new Course();
		course3.setCourseID(UUID.randomUUID());
		course3.setCourseName("C");
		course3.setGradePoints(3);
		course3.setMajor("c");
		courses.add(course1);
		courses.add(course2);
		courses.add(course3);
		Semester semester1 = new Semester();
		semester1.setSemesterID(UUID.randomUUID());
		Calendar calendar = Calendar.getInstance();
		calendar.set(2017, 9, 1);
		semester1.setStartDate(calendar.getTime());
		calendar.set(2017, 12, 31);
		semester1.setEndDate(calendar.getTime());
		Semester semester2 = new Semester();
		semester2.setSemesterID(UUID.randomUUID());
		calendar.set(2017, 9, 1);
		semester2.setStartDate(calendar.getTime());
		calendar.set(2017, 12, 31);
		semester2.setEndDate(calendar.getTime());
		semesters.add(semester1);
		semesters.add(semester2);
		for (Course c : courses) {
			for (Semester s : semesters) {
				Section section = new Section();
				section.setCourseID(c.getCourseID());
				section.setSemesterID(s.getSemesterID());
				section.setSectionID(UUID.randomUUID());
				sections.add(section);
			}
		}
		Student student1 = new Student("Tom1", "", "", calendar.getTime(),
				eMajor.BUSINESS, "", "", "");
		Student student2 = new Student("Tom2", "", "", calendar.getTime(),
				eMajor.CHEM, "", "", "");
		Student student3 = new Student("Tom3", "", "", calendar.getTime(),
				eMajor.COMPSI, "", "", "");
		Student student4 = new Student("Tom4", "", "", calendar.getTime(),
				eMajor.NURSING, "", "", "");
		Student student5 = new Student("Tom5", "", "", calendar.getTime(),
				eMajor.PHYSICS, "", "", "");
		Student student6 = new Student("Tom6", "", "", calendar.getTime(),
				eMajor.BUSINESS, "", "", "");
		Student student7 = new Student("Tom7", "", "", calendar.getTime(),
				eMajor.CHEM, "", "", "");
		Student student8 = new Student("Tom8", "", "", calendar.getTime(),
				eMajor.COMPSI, "", "", "");
		Student student9 = new Student("Tom9", "", "", calendar.getTime(),
				eMajor.PHYSICS, "", "", "");
		Student student0 = new Student("Tom0", "", "", calendar.getTime(),
				eMajor.BUSINESS, "", "", "");
		students.add(student0);
		students.add(student1);
		students.add(student2);
		students.add(student3);
		students.add(student4);
		students.add(student5);
		students.add(student6);
		students.add(student7);
		students.add(student8);
		students.add(student9);
		for (Section c : sections) {
			for (Student s : students) {
				Enrollment enrollment = new Enrollment();
				enrollment.setSectionID(c.getSectionID());
				enrollment.setStudentID(s.getStudentID());
				enrollment.setGrade(180);
				enrollments.add(enrollment);
			}
		}
	}

	@Test
	public void testGPA() {
		int GPA = 0;
		for (Student student : students) {
			for (Enrollment enrollment : enrollments) {
				if (student.getStudentID().toString()
						.equals((enrollment.getStudentID().toString()))) {
					for (Section section : sections) {
						if (section.getSectionID().toString()
								.equals((enrollment.getSectionID().toString()))) {
							for (Course course : courses) {
								if (course
										.getCourseID()
										.toString()
										.equals((section.getCourseID()
												.toString()))) {
									GPA += course.getGradePoints();
								}
							}
						}
					}
				}
			}
		}
		assertEquals(180, GPA);
	}

	@Test
	public void testAverageGrade() {
		int Grade = 0;
		for (Course course : courses) {
			for (Section section : sections) {
				if (section.getCourseID().toString()
						.equals(course.getCourseID().toString())) {
					for (Enrollment enrollment : enrollments) {
						if (section.getSectionID().toString()
								.equals(enrollment.getSectionID().toString())) {
							for (Student student : students) {
								if (student
										.getStudentID()
										.toString()
										.equals(enrollment.getStudentID()
												.toString())) {
									Grade += enrollment.getGrade();
								}
							}
						}
					}
				}
			}
		}
		int AverageGrade = Grade / (students.size() * sections.size());
		System.out.println(AverageGrade);
		assertEquals(180, AverageGrade);
	}

	@Test
	public void testChangeMajor() {
		UUID studentID = students.get(0).getStudentID();
		for (Enrollment enrollment : enrollments) {
			if (studentID.toString().equals((enrollment.getStudentID().toString()))) {
				for (Section section : sections) {
					if (section.getSectionID().toString()
							.equals((enrollment.getSectionID().toString()))) {
						for (Course course : courses) {
							if (course.getCourseID().toString()
									.equals((section.getCourseID().toString()))) {
								course.setMajor("change major!");
							}
						}
					}
				}
			}
		}
	}
}