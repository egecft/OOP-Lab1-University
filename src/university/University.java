package university;
import java.util.logging.Logger;

/**
 * This class represents a university education system.
 * 
 * It manages students and courses.
 *
 */
public class University {
	private static final int MAX_STUDENT_COUNT = 1000;
	private static final int MAX_COURSE_COUNT = 50;
    private static final int FIRST_STUDENT_ID = 10000;
	private static final int FIRST_COURSE_ID = 10;
	private static final int ABSENT_GRADE = -1;

	private String universityName;
	private String rectorFirstName;
	private String rectorLastName;
	private int studentCount = 0;
	private int courseCount = 0;

	private Student[] students = new Student[MAX_STUDENT_COUNT];
	private Course[] courses = new Course[MAX_COURSE_COUNT];

// R1
	/**
	 * Constructor
	 * @param name name of the university
	 */
	public University(String name){
		universityName = name;
	}
	
	/**
	 * Getter for the name of the university
	 * 
	 * @return name of university
	 */
	public String getName(){
		return universityName;
	}
	
	/**
	 * Defines the rector for the university
	 * 
	 * @param first first name of the rector
	 * @param last	last name of the rector
	 */
	public void setRector(String first, String last){
		rectorFirstName = first;
		rectorLastName = last;
	}
	
	/**
	 * Retrieves the rector of the university with the format "First Last"
	 * 
	 * @return name of the rector
	 */
	public String getRector(){
		return rectorFirstName + " " + rectorLastName;
	}
	
// R2
	/**
	 * Enrol a student in the university
	 * The university assigns ID numbers 
	 * progressively from number 10000.
	 * 
	 * @param first first name of the student
	 * @param last last name of the student
	 * 
	 * @return unique ID of the newly enrolled student
	 */
	public int enroll(String first, String last) {
		int studentID = FIRST_STUDENT_ID + studentCount;
		students[studentCount++] = new Student(first, last, studentID);

		logger.info("New student enrolled: " + studentID + ", " + first + " " + last);

		return studentID;
	}
	
	/**
	 * Retrieves the information for a given student.
	 * The university assigns IDs progressively starting from 10000
	 * 
	 * @param id the ID of the student
	 * 
	 * @return information about the student
	 */
	public String student(int studentID) {
		Student currentStudent = students[studentID - FIRST_STUDENT_ID];
		return currentStudent.getStudentInfo();
	}
	
// R3
	/**
	 * Activates a new course with the given teacher
	 * Course codes are assigned progressively starting from 10.
	 * 
	 * @param title title of the course
	 * @param teacher name of the teacher
	 * 
	 * @return the unique code assigned to the course
	 */
	public int activate(String title, String teacher){
		int courseID = FIRST_COURSE_ID + courseCount;
		courses[courseCount++] = new Course(title, teacher, courseID);

		logger.info("New course activated: " + courseID + " " + title + " " + teacher);

		return courseID;
	}
	
	/**
	 * Retrieve the information for a given course.
	 * 
	 * The course information is formatted as a string containing 
	 * code, title, and teacher separated by commas, 
	 * e.g., {@code "10,Object Oriented Programming,James Gosling"}.
	 * 
	 * @param code unique code of the course
	 * 
	 * @return information about the course
	 */
	public String course(int code){
		Course currentCourse = courses[code - FIRST_COURSE_ID];
		return currentCourse.getCourseInfo();
	}
	
// R4
	/**
	 * Register a student to attend a course
	 * @param studentID id of the student
	 * @param courseCode id of the course
	 */
	public void register(int studentID, int courseCode){
		Student currentStudent = students[studentID - FIRST_STUDENT_ID];
		Course currentCourse = courses[courseCode - FIRST_COURSE_ID];
		currentStudent.addCourse(currentCourse);
		currentCourse.addStudent(currentStudent);

		logger.info("Student " + studentID + " signed up for course " + courseCode);
	}
	
	/**
	 * Retrieve a list of attendees.
	 * 
	 * The students appear one per row (rows end with `'\n'`) 
	 * and each row is formatted as describe in in method {@link #student}
	 * 
	 * @param courseCode unique id of the course
	 * @return list of attendees separated by "\n"
	 */
	public String listAttendees(int courseCode){
		Course currentCourse = courses[courseCode - FIRST_COURSE_ID];
		String attendeeListPrint = "";
		for (int i=0; i<currentCourse.getAttendeeCount(); i++) {
			attendeeListPrint += currentCourse.getAttendeeList()[i].getStudentInfo() + "\n";
		}
		return attendeeListPrint;
	}

	/**
	 * Retrieves the study plan for a student.
	 * 
	 * The study plan is reported as a string having
	 * one course per line (i.e. separated by '\n').
	 * The courses are formatted as describe in method {@link #course}
	 * 
	 * @param studentID id of the student
	 * 
	 * @return the list of courses the student is registered for
	 */
	public String studyPlan(int studentID){
		Student currentStudent = students[studentID - FIRST_STUDENT_ID];
		String attendedCoursesPrint = "";
		for (int i=0; i<currentStudent.getAttendedCourseCount(); i++) {
			attendedCoursesPrint += currentStudent.getAttendedCourseList()[i].getCourseInfo() + "\n";
		}
		return attendedCoursesPrint;
	}

// R5
	/**
	 * records the grade (integer 0-30) for an exam can 
	 * 
	 * @param studentId the ID of the student
	 * @param courseID	course code 
	 * @param grade		grade ( 0-30)
	 */
	public void exam(int studentId, int courseID, int grade) {
		if (grade < 0 || grade > 30) {
			return; //didn't learn how to handle errors the best way yet
		}
		Student currentStudent = students[studentId - FIRST_STUDENT_ID];
		Course currentCourse = courses[courseID - FIRST_COURSE_ID];
		currentStudent.recordExamGrade(currentCourse, grade);
		currentCourse.recordExamGrade(currentStudent, grade);

		logger.info("Student " + studentId + " took an exam in course " + courseID + " with grade " + grade);
	}

	/**
	 * Computes the average grade for a student and formats it as a string
	 * using the following format 
	 * 
	 * {@code "Student STUDENT_ID : AVG_GRADE"}. 
	 * 
	 * If the student has no exam recorded the method
	 * returns {@code "Student STUDENT_ID hasn't taken any exams"}.
	 * 
	 * @param studentId the ID of the student
	 * @return the average grade formatted as a string.
	 */
	public String studentAvg(int studentId) {
		Student currentStudent = students[studentId - FIRST_STUDENT_ID];
		double avg = currentStudent.averageGrade();

		if (avg == ABSENT_GRADE) {
			return "Student " + String.valueOf(studentId) + " hasn't taken any exams";
		}
		return "Student " + String.valueOf(studentId) + " : " + String.valueOf(avg);
	}
	
	/**
	 * Computes the average grades of all students that took the exam for a given course.
	 * 
	 * The format is the following: 
	 * {@code "The average for the course COURSE_TITLE is: COURSE_AVG"}.
	 * 
	 * If no student took the exam for that course it returns {@code "No student has taken the exam in COURSE_TITLE"}.
	 * 
	 * @param courseId	course code 
	 * @return the course average formatted as a string
	 */
	public String courseAvg(int courseId) {
		Course currentCourse = courses[courseId - FIRST_COURSE_ID];
		double avg = currentCourse.averageGrade();

		if (avg == ABSENT_GRADE) {
			return "No student has taken the exam in " + currentCourse.getCourseTitle();
		}
		return "The average for the course " + currentCourse.getCourseTitle() + " is: " + String.valueOf(avg);
	}
	

// R6
	/**
	 * Retrieve information for the best students to award a price.
	 * 
	 * The students' score is evaluated as the average grade of the exams they've taken. 
	 * To take into account the number of exams taken and not only the grades, 
	 * a special bonus is assigned on top of the average grade: 
	 * the number of taken exams divided by the number of courses the student is enrolled to, multiplied by 10.
	 * The bonus is added to the exam average to compute the student score.
	 * 
	 * The method returns a string with the information about the three students with the highest score. 
	 * The students appear one per row (rows are terminated by a new-line character {@code '\n'}) 
	 * and each one of them is formatted as: {@code "STUDENT_FIRSTNAME STUDENT_LASTNAME : SCORE"}.
	 * 
	 * @return info on the best three students.
	 */
	public String topThreeStudents() {
		// to store top three students indexes
		int first = -1;
		int second = -1;
		int third = -1;

		double[] scores = new double[studentCount];

		for (int i=0; i<studentCount; i++) {
			scores[i] = students[i].score();
		}
		// assuming there are no ties
		for (int i=0; i<studentCount; i++) {
			if (first == -1 || scores[i] > scores[first]) {
				third = second;
				second = first;
				first = i;
			}
			else if (second == -1 || scores[i] > scores[second]) {
				third = second;
				second = i;

			}
			else if (third == -1 || scores[i] > scores[third]) {
				third = i;
			}
			
		}
		String topStudentsPrint = "";
		if (first != -1) {
			topStudentsPrint += students[first].getFirstName() + " " + students[first].getLastName() + " : " + scores[first] + "\n";
		}
		if (second != -1) {
			topStudentsPrint += students[second].getFirstName() + " " + students[second].getLastName() + " : " + scores[second] + "\n";
		}
		if (third != -1) {
			topStudentsPrint += students[third].getFirstName() + " " + students[third].getLastName() + " : " + scores[third] + "\n";
		}

		return topStudentsPrint;
	}

// R7
    /**
     * This field points to the logger for the class that can be used
     * throughout the methods to log the activities.
     */
    public static final Logger logger = Logger.getLogger("University");

}
