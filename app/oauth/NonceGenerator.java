package oauth;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Random;

import org.bouncycastle.util.encoders.Base64Encoder;

public class NonceGenerator {
	private static final int LENGTH_OF_RANDOM_BYTES = 32;
	private Random randomiser;
	private Base64Encoder encoder;

	public NonceGenerator() {
		randomiser = new Random();
		encoder = new Base64Encoder();
	}
	
	public String generateNonce() throws IOException {
		return convertToBase64String(generateRandomBytes());
	}

	private String convertToBase64String(byte[] bytes) throws IOException {
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		
		encoder.encode(bytes, 0, LENGTH_OF_RANDOM_BYTES, stream);
		
		String string = stream.toString();
		return string;
	}

	private byte[] generateRandomBytes() {
		byte[] bytes = new byte[LENGTH_OF_RANDOM_BYTES];
		randomiser.nextBytes(bytes);
		return bytes;
	}
}
