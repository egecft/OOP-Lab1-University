package university;

public class Student {
	private static final int MAX_ATTENDED_COURSE_COUNT = 25;
    private static final int ABSENT_GRADE = -1;


    private String studentFirstName;
    private String studentLastName;
    private int studentID;
    private int attendedCourseCount = 0;

    private Course[] attendedCourseList = new Course[MAX_ATTENDED_COURSE_COUNT];

    private int[] examGrades = new int[MAX_ATTENDED_COURSE_COUNT];

    public Student(String studentFirstName, String studentLastName, int studentID) {
        this.studentFirstName = studentFirstName;
        this.studentLastName = studentLastName;
        this.studentID = studentID;
        for (int i=0; i<examGrades.length; i++) {
            examGrades[i] = ABSENT_GRADE;
        }
    }

    public void recordExamGrade(Course course, int grade) {
        for (int i=0; i<attendedCourseCount; i++) {
            if (attendedCourseList[i] == course) {
                examGrades[i] = grade;
            }
        }
    }

    public void addCourse(Course c) {
        attendedCourseList[attendedCourseCount++] = c;
    }

    public double averageGrade() {
        int sum = 0;
        int count = 0;
        for (int i=0; i<attendedCourseCount; i++) {
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

    public double score() {
        int examsTaken = 0;
        for (int i=0; i<attendedCourseCount; i++) {
            if (examGrades[i] != ABSENT_GRADE) {
                examsTaken++;
            }
        }

        double avg = averageGrade();
        if (avg == ABSENT_GRADE) {
            return ABSENT_GRADE;
        }

        double bonus = ((double) (examsTaken) / attendedCourseCount) * 10;

        return avg + bonus;
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
