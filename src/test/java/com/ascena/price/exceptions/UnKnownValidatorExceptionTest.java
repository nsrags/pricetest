package com.ascena.price.exceptions;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ascena.price.app.PriceApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = PriceApplication.class)
public class UnKnownValidatorExceptionTest {

	@Test
	public void shouldRetainFormattedMessage() {
		assertThat(new UnKnownValidatorException("message").getMessage(), is("message"));
		assertThat(new UnKnownValidatorException("message").getCause(), is(nullValue()));
	}

	
}
