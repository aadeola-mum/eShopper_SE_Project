package cs425.team4.eshopper;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({TestOrderDetailService.class, TestOrderService.class, TestUserService.class,
	TestProductService.class})
public class TestSuite {

}
