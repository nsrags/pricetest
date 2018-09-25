package com.ascena.price.exceptions;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ascena.price.app.PriceApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = PriceApplication.class)
public class BaseExceptionTest {

	@Test
	public void shouldHaveDefaultCtorForLazyPeople() throws InstantiationException, IllegalAccessException {
		assertThat(BaseException.class.newInstance(), is(notNullValue()));
		assertThat(BaseException.class.newInstance().getMessage(), is(nullValue()));
		assertThat(BaseException.class.newInstance().getCause(), is(nullValue()));
	}

	@Test
	public void shouldRetainFormattedMessage() {
		assertThat(new BaseException("%s", "message").getMessage(), is("message"));
		assertThat(new BaseException("%s", "message").getCause(), is(nullValue()));
	}

	@Test
	public void shouldRetainFormattedMessageAndCause() {
		Throwable cause = new Throwable();
		assertThat(new BaseException(cause, "%s", "message").getMessage(), is("message"));
		assertThat(new BaseException(cause, "%s", "message").getCause(), is(cause));
	}

	@Test
	public void shouldRetainCause() {
		Throwable cause = new Throwable();
		assertThat(new BaseException(cause).getCause(), is(cause));
		assertThat(new BaseException(cause).getMessage(), is("java.lang.Throwable"));
	}

	@Test
	public void shouldNotFailedtoConstructWhenMessageIsAFormatStringWithNoArgs() {
		assertThat(new BaseException("Message with % in it").getMessage(), is("Message with % in it"));
		Throwable cause = new Throwable();
		assertThat(new BaseException(cause, "Message with % in it").getMessage(), is("Message with % in it"));
	}
}
