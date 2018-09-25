package com.ascena.price.app;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Inventory Application Start Tests
 * @author smeenavalli
 *
 */
@RunWith(SpringRunner.class)
public class PriceApplicationStartTests {
	 @Test
	  public void applicationStarts() {
	    PriceApplication.main(new String[] {});
	  }
}
