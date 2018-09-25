package com.ascena.price.exceptions;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.ascena.price.app.PriceApplication;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = PriceApplication.class)

public class SysExceptionTest {
	@Test
	public void shouldRetainFormattedErrorCode() {
		assertThat(new SysException("errorcode").getMessage(), is(nullValue()));
		assertThat(new SysException("errorcode").getCause(), is(nullValue()));
	}
	
	@Test
	public void shouldRetainFormattedMessage() {
		assertThat(new SysException("message", "errorcode").getMessage(), is("message"));
		assertThat(new SysException("message", "errorcode").getCause(), is(nullValue()));
	}

	@Test
	public void shouldRetainFormattedMessageAndCause() {
		Throwable cause = new Throwable();
		assertThat(new SysException("message",cause, "errorcode").getMessage(), is("message"));
		assertThat(new SysException("message",cause, "errorcode").getCause(), is(cause));
	}

	@Test
	public void shouldNotFailedtoConstructWhenMessageIsAFormatStringWithNoArgs() {
		assertThat(new SysException("Message with % in it").getCode(), is("Message with % in it"));
		Throwable cause = new Throwable();		
		assertThat(new SysException(cause, "Message with % in it").getCode(), is("Message with % in it"));
	}
}


