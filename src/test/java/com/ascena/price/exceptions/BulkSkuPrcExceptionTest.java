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
public class BulkSkuPrcExceptionTest {
	@Test
	public void shouldRetainFormattedErrorCode() {
		assertThat(new BulkSkuPrcException("errorcode").getMessage(), is(nullValue()));
		assertThat(new BulkSkuPrcException("errorcode").getCause(), is(nullValue()));
	}
	
	@Test
	public void shouldRetainFormattedMessage() {
		assertThat(new BulkSkuPrcException("message", "errorcode").getMessage(), is("message"));
		assertThat(new BulkSkuPrcException("message", "errorcode").getCause(), is(nullValue()));
	}

	@Test
	public void shouldRetainFormattedMessageAndCause() {
		Throwable cause = new Throwable();
		assertThat(new BulkSkuPrcException("message",cause, "errorcode").getMessage(), is("message"));
		assertThat(new BulkSkuPrcException("message",cause, "errorcode").getCause(), is(cause));
	}

	@Test
	public void shouldNotFailedtoConstructWhenMessageIsAFormatStringWithNoArgs() {
		assertThat(new BulkSkuPrcException("Message with % in it").getCode(), is("Message with % in it"));
		Throwable cause = new Throwable();		
		assertThat(new BulkSkuPrcException(cause, "Message with % in it").getCode(), is("Message with % in it"));
	}
}


