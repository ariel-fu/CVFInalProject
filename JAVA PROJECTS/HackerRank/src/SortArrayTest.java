import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SortArrayTest {
  SortArray test;

  @BeforeEach
  void setUp() throws Exception {
    test = new SortArray();
  }

  @AfterEach
  void tearDown() throws Exception {
  }

  @Test
  void test() {
    int[] nums = new int[] { 2, 1, 5, 7, 3, 4, 10, 9 };
    int[] result = new int[] { 1, 2, 3, 4, 5, 7, 9, 10 };
    nums = test.sortArrayInsertion(nums);
    for (int i = 0; i < result.length; i++) {
      assertEquals(nums[i], result[i]);
    }
  }

}
