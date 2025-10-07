package university;

public class Student {
	public static final int MAX_ATTENDED_COURSE_COUNT = 25;

    private String studentFirstName;
    private String studentLastName;
    private int studentID;
    private int attendedCourseCount = 0;

    private Course[] attendedCourseList = new Course[MAX_ATTENDED_COURSE_COUNT];

    public Student(String studentFirstName, String studentLastName, int studentID) {
        this.studentFirstName = studentFirstName;
        this.studentLastName = studentLastName;
        this.studentID = studentID;
    }

    public void addCourse(Course c) {
        attendedCourseList[attendedCourseCount++] = c;
    }

    public Course[] getAttendedCourseList() {
        return attendedCourseList;
    }

    public int getAttendedCourseCount() {
        return attendedCourseCount;
    }

    public int getStudentID() { 
        return studentID;
    }

    public String getFirstName() {
        return studentFirstName;
    }

    public String getLastName() {
        return studentLastName;
    }

    public String getStudentInfo() {
        return String.valueOf(studentID) + " " + studentFirstName + " " + studentLastName;
    }
}
