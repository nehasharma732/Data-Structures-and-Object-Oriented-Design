import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;

import static org.junit.Assert.*;

import org.junit.*;

public class CustomTester {

    Student studentTester1, studentTester2;
    Course courseTester1;
    Sanctuary sanctuaryTester1, sanctuaryTester2;

    @Before
    public void setup() throws Exception {

        studentTester1 = new Student("Neha", "Sharma", "A16632613");
        studentTester2 = new Student("Neha", "Sharma", "A11111111");

        courseTester1 = new Course("CSE", "CSE 12", "data structures", 1);

        sanctuaryTester1 = new Sanctuary(4, 2);
        sanctuaryTester2 = new Sanctuary(4, 2);

    }

    @Test
    public void testStudentEquals() {

        assertThrows(IllegalArgumentException.class, () -> new Student(null, null, null));

        assertSame(false, studentTester1.equals("hello"));
    }

    @Test
    public void testStudentCompareTo() {

        assertTrue((studentTester2.compareTo(studentTester1) < 0));

    }

    @Test
    public void testCourseDrop() {

        courseTester1.enroll(studentTester1); // add one student

        assertTrue((courseTester1.drop(studentTester2) == false)); // drop nonexistent student

        assertThrows(IllegalArgumentException.class, () -> courseTester1.drop(null));

        assertSame(1, courseTester1.getEnrolledCount());

        assertSame(true, courseTester1.drop(studentTester1));

        assertSame(0, courseTester1.getEnrolledCount());
    }

    @Test
    public void testCourseEnroll() {

        courseTester1.enroll(studentTester1); // add one student

        assertTrue((courseTester1.enroll(studentTester2) == false));

        assertThrows(IllegalArgumentException.class, () -> courseTester1.enroll(null));

        assertSame(1, courseTester1.getEnrolledCount());

        courseTester1.drop(studentTester1);

        assertSame(0, courseTester1.getEnrolledCount());
    }

    @Test
    public void testCourseGetRoster() {

        ArrayList<Student> expectedRoster = new ArrayList<>();

        // Generate and enroll 100 students
        for (int i = 1; i <= 100; i++) {

            Student student = new Student("FirstName" + i, "LastName" + i, "A" + String.format("%08d", i));
            courseTester1.enroll(student);
            expectedRoster.add(student);
        }

        Collections.sort(expectedRoster);

        // ArrayList<Student> actualRoster = courseTester1.getRoster();

        // assertEquals("The expected roster should match the actual roster",
        // expectedRoster, actualRoster);
    }

    @Test
    public void testSanctConstructor() {

        assertThrows(IllegalArgumentException.class, () -> new Sanctuary(-20, 10));

    }

    @Test
    public void testSanctRescuePartial() {

        sanctuaryTester1.rescue("moose", 3);

        assertSame(4, sanctuaryTester1.rescue("moose", 5)); // returns num rejected

    }

    @Test
    public void testSanctRescueMaxSpecies() {

        sanctuaryTester1.rescue("moose", 1);
        sanctuaryTester1.rescue("tiger", 2);

        int rejected = sanctuaryTester1.rescue("monkey", 1);

        assertSame(1, rejected);

        int mooseCount = sanctuaryTester1.countForSpecies("moose");
        int tigerCount = sanctuaryTester1.countForSpecies("tiger");
        int totalAnimals = sanctuaryTester1.getTotalAnimals();
        int totalSpecies = sanctuaryTester1.getTotalSpecies();

        assertSame(1, mooseCount);
        assertSame(2, tigerCount);
        assertSame(3, totalAnimals);
        assertSame(2, totalSpecies);

        // Verify no entry for "monkey" since its rescue was rejected
        int monkeyCount = sanctuaryTester1.countForSpecies("monkey");
        Assert.assertEquals("Monkey should not have been added", 0, monkeyCount);

    }

    @Test
    public void testSanctReleasePartial() {

        sanctuaryTester1.rescue("moose", 1);
        sanctuaryTester1.rescue("tiger", 3);
        sanctuaryTester1.release("tiger", 2);

        assertSame(1, sanctuaryTester1.countForSpecies("tiger"));

    }

    @Test
    public void testSanctReleaseTooMany() {

        sanctuaryTester1.rescue("moose", 1);
        sanctuaryTester1.rescue("tiger", 3);

        assertThrows(IllegalArgumentException.class,
                () -> sanctuaryTester1.release("tiger", 5));

    }

    @Test
    public void testSanctHelpClosingSanctuaryPartial() {

        sanctuaryTester1.rescue("moose", 2);
        sanctuaryTester1.rescue("tiger", 1);

        sanctuaryTester2.rescue("moose", 2);
        sanctuaryTester2.rescue("tiger", 1);

        assertSame(1, sanctuaryTester1.helpClosingSanctuary(sanctuaryTester2));

    }
}
