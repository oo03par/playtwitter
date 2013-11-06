package oauth;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Random;

import org.bouncycastle.util.encoders.Base64Encoder;

public class NonceGenerator {
	private Random randomiser;
	private Base64Encoder encoder;

	public NonceGenerator() {
		randomiser = new Random();
		encoder = new Base64Encoder();
	}
	
	public String generateNonce() throws IOException {
		byte[] bytes = new byte[32];
		randomiser.nextBytes(bytes);

		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		
		encoder.encode(bytes, 0, 32, stream);
		
		return stream.toString();
	}

}
