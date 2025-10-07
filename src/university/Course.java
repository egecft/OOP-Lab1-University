package university;

public class Course {
	public static final int MAX_ATTENDEE_COUNT = 100;

    private String courseTitle;
    private String teacherName;
    private int courseID;
    private int attendeeCount = 0;

    private Student[] attendeeList = new Student[MAX_ATTENDEE_COUNT];

    public Course(String courseTitle, String teacherName, int courseID) {
        this.courseTitle = courseTitle;
        this.teacherName = teacherName;
        this.courseID = courseID;
    }

    public void addStudent(Student s) {
        attendeeList[attendeeCount++] = s;
    }

    public Student[] getAttendeeList() {
        return attendeeList;
    }

    public int getAttendeeCount() {
        return attendeeCount;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public int getCourseID() {
        return courseID;
    }

    public String getCourseInfo() {
        return String.valueOf(courseID) + "," + courseTitle + "," + teacherName;
    }
}
