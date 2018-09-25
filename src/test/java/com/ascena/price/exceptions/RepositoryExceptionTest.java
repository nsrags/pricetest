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

public class RepositoryExceptionTest {
	
	@Test
	public void shouldRetainFormattedMessage() {
		assertThat(new RepositoryException("%s", "message").getMessage(), is("message"));
		assertThat(new RepositoryException("%s", "message").getCause(), is(nullValue()));
	}

	@Test
	public void shouldRetainFormattedMessageAndCause() {
		Throwable cause = new Throwable();
		assertThat(new RepositoryException(cause, "%s", "message").getMessage(), is("message"));
		assertThat(new RepositoryException(cause, "%s", "message").getCause(), is(cause));
	}

	@Test
	public void shouldNotFailedtoConstructWhenMessageIsAFormatStringWithNoArgs() {
		assertThat(new RepositoryException("Message with % in it").getMessage(), is("Message with % in it"));
		Throwable cause = new Throwable();
		assertThat(new RepositoryException(cause, "Message with % in it").getMessage(), is("Message with % in it"));
	}
}
