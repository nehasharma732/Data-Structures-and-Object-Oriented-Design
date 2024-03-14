import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;

public class Course {

    private static final String NULL_ARGS = "Null arguments not allowed.";
    private static final String INVALID_ARGS = "Argument is invalid.";

    HashSet<Student> enrolled;
    private final int capacity;
    private final String department;
    private final String number;
    private final String description;

    public Course(String department, String number, String description, int capacity) {

        if (department == null || number == null || description == null) {
            throw new IllegalArgumentException(INVALID_ARGS);
        }
        if (capacity <= 0) {
            throw new IllegalArgumentException(INVALID_ARGS);
        }

        this.department = department;
        this.number = number;
        this.description = description;
        this.capacity = capacity;
        this.enrolled = new HashSet<Student>(0);
    }

    /*
     * Return the department name.
     */
    public String getDepartment() {

        return this.department;
    }

    /*
     * Return the course number.
     */
    public String getNumber() {

        return this.number;
    }

    /*
     * Return the description of the course.
     */
    public String getDescription() {

        return this.description;
    }

    /*
     * Return the capacity of the course.
     */
    public int getCapacity() {

        return this.capacity;
    }

    public boolean enroll(Student student) {

        if (student == null) {
            throw new IllegalArgumentException(INVALID_ARGS);
        }

        if ((this.enrolled.size() < this.capacity)
                && (this.enrolled.contains(student) == false)) {

            enrolled.add(student);
            return true;

        } else {

            return false;
        }
    }

    public boolean drop(Student student) {

        if (student == null) {
            throw new IllegalArgumentException(INVALID_ARGS);
        }

        if (this.enrolled.contains(student)) {

            this.enrolled.remove(student);
            return true;

        } else {
            return false;
        }
    }

    public void cancel() {

        this.enrolled.clear();
    }

    public boolean isFull() {

        if (this.enrolled.size() == this.capacity) {
            return true;
        } else {
            return false;
        }
    }

    public int getEnrolledCount() {

        return this.enrolled.size();
    }

    public int getAvailableSeats() {

        return this.capacity - this.enrolled.size();
    }

    public HashSet<Student> getStudents() {

        HashSet<Student> studentsCopy = (HashSet<Student>) this.enrolled.clone(); // ???

        return studentsCopy;
    }

    public ArrayList<Student> getRoster() {

        ArrayList<Student> studentArray = new ArrayList<Student>();
        Iterator<Student> studentIterator = this.enrolled.iterator();

        while (studentIterator.hasNext()) {

            studentArray.add(studentIterator.next());
        }

        Collections.sort(studentArray);
        return studentArray;
    }

    @Override
    public String toString() {

        return String.format("%s %s [%d] %s",
                this.department, this.number,
                this.capacity, this.description);
    }
}
