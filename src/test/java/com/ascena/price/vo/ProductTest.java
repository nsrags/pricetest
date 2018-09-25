package com.ascena.price.vo;

import java.time.LocalDateTime;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.meanbean.lang.Factory;
import org.meanbean.test.BeanTester;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ascena.price.app.PriceApplication;

/**
 * 
 * @author smeenavalli
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PriceApplication.class)

public class ProductTest {
	@Test
	public void getterAndSetterCorrectness() throws Exception {
		final BeanTester beanTester = new BeanTester();
		beanTester.getFactoryCollection().addFactory(LocalDateTime.class, new LocalDateTimeFactory());
		beanTester.testBean(Product.class);
	}

	/**
	 * Concrete Factory that creates a LocalDateTime.
	 */
	class LocalDateTimeFactory implements Factory<Object> {

		@Override
		public LocalDateTime create() {
			return LocalDateTime.now();
		}
	}
}
