import java.util.Objects;

public class Student implements Comparable<Student> {

    private static final String INVALID_ARG = "Invalid argument";

    private final String firstName;
    private final String lastName;
    private final String PID;

    public Student(String firstName, String lastName, String PID) {

        if (firstName == null || lastName == null || PID == null) {
            throw new IllegalArgumentException(INVALID_ARG);
        }

        this.firstName = firstName;
        this.lastName = lastName;
        this.PID = PID;
    }

    public String getFirstName() {

        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getPID() {
        return this.PID;
    }

    /**
     * Returns true if o is 1) not null,
     * 2) a Student object,
     * 3) all the instance variables of o equal the
     * corresponding instance variables of the current Student object.
     * Otherwise, return false.
     */
    @Override
    public boolean equals(Object o) {

        if (o != null) {
            if (o instanceof Student) {

                Student student = (Student) o;

                if ((student.firstName.equals(this.firstName)) &&
                        ((student.lastName).equals(this.lastName)) &&
                        ((student.PID).equals(this.PID))) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Return the hash value generated using the built-in Objects.hash function.
     * The hash function should generate a hash value in the order of
     * the student’s firstName, lastName, and PID.
     * 
     */
    @Override
    public int hashCode() {
        return Objects.hash(this.firstName, this.lastName, this.PID);

    }

    public int compareTo(Student o) {

        if (o == null) {
            throw new NullPointerException("Argument cannot be null.");
        }

        if (this.lastName.compareTo(o.lastName) == 0) {

            if (this.firstName.compareTo(o.firstName) == 0) {

                return this.PID.compareTo(o.PID);

            } else {

                return this.firstName.compareTo(o.firstName);
            }
        } else {

            return this.lastName.compareTo(o.lastName);
        }
    }
}
