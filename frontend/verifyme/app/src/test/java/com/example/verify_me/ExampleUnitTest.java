import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.sql.SQLException;

public class ExampleUnitTest {

    private Attendance attendance = null;
    private Employee employee = null;
    private Incident incident = null;
    private Learner learner = null;
    private Student student = null;

    @BeforeEach
    void setUp() {
        initializeTestData();
    }

    @Test
    void testAttendance() throws SQLException {
        initializeAttendance();
        attendance.insert("S001", "John Doe", "2023-09-15", true);
        assertNotNull(attendance.getAttendanceRecord("S001"), "Attendance record should be created");
    }

    @Test
    void testEmployee() throws SQLException {
        initializeEmployee();
        employee.insert("E001", "Jane Smith", "Marketing");
        assertEquals("Marketing", employee.getDepartment("E001"), "Department should match");
    }

    @Test
    void testIncident() throws SQLException {
        initializeIncident();
        incident.report("I001", "Security breach", "High");
        assertTrue(incident.getSeverity("I001").equals("High"), "Incident severity should be high");
    }

    @Test
    void testLearner() throws SQLException {
        initializeLearner();
        learner.enroll("L001", "Mathematics");
        assertNotNull(learner.getCourse("L001"), "Course enrollment should be recorded");
    }

    private void initializeTestData() {
        // Initialize all test objects
        initializeAttendance();
        initializeEmployee();
        initializeIncident();
        initializeLearner();
    }

    private void initializeAttendance() {
        attendance = new Attendance();
    }

    private void initializeEmployee() {
        employee = new Employee();
    }

    private void initializeIncident() {
        incident = new Incident();
    }

    private void initializeLearner() {
        learner = new Learner();
    }
}