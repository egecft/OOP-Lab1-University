package university;

public class Course {
	private static final int MAX_ATTENDEE_COUNT = 100;
    private static final int ABSENT_GRADE = -1;


    private String courseTitle;
    private String teacherName;
    private int courseID;
    private int attendeeCount = 0;

    private Student[] attendeeList = new Student[MAX_ATTENDEE_COUNT];

    private int[] examGrades = new int[MAX_ATTENDEE_COUNT];

    public Course(String courseTitle, String teacherName, int courseID) {
        this.courseTitle = courseTitle;
        this.teacherName = teacherName;
        this.courseID = courseID;
        for (int i=0; i<examGrades.length; i++) {
            examGrades[i] = ABSENT_GRADE;
        }
    }

    public void recordExamGrade(Student student, int grade) {
        for (int i=0; i<attendeeCount; i++) {
            if (attendeeList[i] == student) {
                examGrades[i] = grade;
            }
        }
    }

    public void addStudent(Student s) {
        attendeeList[attendeeCount++] = s;
    }

    public double averageGrade() {
        int sum = 0;
        int count = 0;
        for (int i=0; i<attendeeCount; i++) {
            if (examGrades[i] != ABSENT_GRADE) {
                sum += examGrades[i];
                count++;
            }
        }
        if (count == 0) {
            return ABSENT_GRADE;
        }
        return (double) sum / count; //since sum is float, result will be float
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
