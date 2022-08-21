import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DailyStateDataEntryTest {

  @BeforeEach
  void setUp() throws Exception {
  }

  @AfterEach
  void tearDown() throws Exception {
  }

  @Test
  void testParse() {
    String name = "Test";
    String id = "ehwflwe";
    int totalCases = -12;
    int totalDeaths = 42;
    DailyStateDataEntry data = new DailyStateDataEntry();
    data = data.parse("ehwflwe,Test,-12,42");
    assertTrue(data.getRecordID().equals(id));
    assertTrue(data.getCases() == -12);
    assertTrue(data.getDeaths() == 42);
    assertTrue(data.getStateName().equals(name));
  }
}
