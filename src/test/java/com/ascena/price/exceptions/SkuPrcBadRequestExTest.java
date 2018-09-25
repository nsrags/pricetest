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

public class SkuPrcBadRequestExTest {
	@Test
	public void shouldRetainFormattedErrorCode() {
		assertThat(new SkuPrcBadRequestEx("errorcode").getMessage(), is(nullValue()));
		assertThat(new SkuPrcBadRequestEx("errorcode").getCause(), is(nullValue()));
	}
	
	@Test
	public void shouldRetainFormattedMessage() {
		assertThat(new SkuPrcBadRequestEx("message", "errorcode").getMessage(), is("message"));
		assertThat(new SkuPrcBadRequestEx("message", "errorcode").getCause(), is(nullValue()));
	}

	@Test
	public void shouldRetainFormattedMessageAndCause() {
		Throwable cause = new Throwable();
		assertThat(new SkuPrcBadRequestEx("message",cause, "errorcode").getMessage(), is("message"));
		assertThat(new SkuPrcBadRequestEx("message",cause, "errorcode").getCause(), is(cause));
	}

	@Test
	public void shouldNotFailedtoConstructWhenMessageIsAFormatStringWithNoArgs() {
		assertThat(new SkuPrcBadRequestEx("Message with % in it").getCode(), is("Message with % in it"));
		Throwable cause = new Throwable();		
		assertThat(new SkuPrcBadRequestEx(cause, "Message with % in it").getCode(), is("Message with % in it"));
	}
}


