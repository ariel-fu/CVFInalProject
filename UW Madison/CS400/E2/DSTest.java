import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DSTest {
  private DS ds;

  @BeforeEach
  void setUp() throws Exception {
    ds = new DS();
  }

  @AfterEach
  void tearDown() throws Exception {
  }

  @Test
  void testDS() {
    fail("Not yet implemented");
  }

//  void testAdd() {
//    DailyStateDataEntry dataEntry = new DailyStateDataEntry();
//    dataEntry = dataEntry.parse("jwelw,Alaska,10,200");
//    ds.add(dataEntry);
//    assertTrue(ds.hashtable.size() == 1);
//    List<DailyStateDataEntry> expectedlist = ds.hashtable.get("Alaska");
//    assertTrue(expectedlist.contains(dataEntry));
  // }

  @Test
  void testGetStateSummaryFor() {
    DailyStateDataEntry dataEntry = new DailyStateDataEntry();
    List<DailyStateDataEntry> recordsList = new ArrayList<>();
    dataEntry = dataEntry.parse("EFWE,Wisconsin,23,436");
    ds.add(dataEntry);
    recordsList.add(dataEntry);
    dataEntry = dataEntry.parse("EFWE,Wisconsin,2233,4236");
    ds.add(dataEntry);
    recordsList.add(dataEntry);
    dataEntry = dataEntry.parse("EFWE,Wisconsin,3,46");
    ds.add(dataEntry);
    recordsList.add(dataEntry);
    dataEntry = dataEntry.parse("EFWE,Wisconsin,213,4356");
    ds.add(dataEntry);
    recordsList.add(dataEntry);
    dataEntry = dataEntry.parse("EFWE,Wisconsin,233,4636");
    ds.add(dataEntry);
    recordsList.add(dataEntry);
    dataEntry = dataEntry.parse("EFWE,Wisconsin,213,4365");
    ds.add(dataEntry);
    recordsList.add(dataEntry);

    StateSummary expected = new StateSummary("Wisconsin", recordsList);
    StateSummary results = ds.getStateSummaryFor("Wisconsin");
    assertTrue(expected.getTotalCases() == results.getTotalCases());
    assertTrue(expected.getTotalDeaths() == results.getTotalDeaths());

  }

  @Test
  void testGetStateNamesInSortedOrder() {
    ds.add(new DailyStateDataEntry().parse("ewlf,Wisconsin,513,82"));
    ds.add(new DailyStateDataEntry().parse("ewlf,Alaska,513,82"));
    ds.add(new DailyStateDataEntry().parse("ewlf,Alabama,513,82"));
    ds.add(new DailyStateDataEntry().parse("ewlf,Aekds,513,82"));
    String[] lsit = ds.getStateNamesInSortedOrder();
    for (int i = 0; i < lsit.length; i++) {
      System.out.println(lsit[i]);
    }
  }

  @Test
  void testMain() {
    fail("Not yet implemented");
  }

}
