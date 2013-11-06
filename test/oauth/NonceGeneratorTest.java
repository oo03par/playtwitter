package oauth;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.*;
import static matchers.RegexpMatcher.matches;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Test;

public class NonceGeneratorTest {
	private static final String BASE64_REGEXP = "^([A-Za-z0-9+/]{4})*([A-Za-z0-9+/]{4}|[A-Za-z0-9+/]{3}=|[A-Za-z0-9+/]{2}==)$";
	private NonceGenerator unit;

	@Before
	public void setupTest() throws Exception {
		unit = new NonceGenerator();
	}
	
	@Test
	public void shouldGenerateAValidNonce() throws Exception {
		String nonce = unit.generateNonce();
		
		assertThat(nonce, is(not(nullValue())));
		assertThat(nonce.length(), is(44));
		assertThat(nonce, matches(BASE64_REGEXP));
	}
	
	@Test
	public void shouldGenerateDifferentNonceEachTime() throws Exception {
		int totalRuns = 10000;
		Set<String> nonces = new HashSet<String>();
		for (int i = 0; i < totalRuns; i++) {
			nonces.add(unit.generateNonce());
		}
		assertThat(nonces.size(), is(totalRuns));
	}
}
