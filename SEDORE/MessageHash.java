import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import it.unisa.dia.gas.jpbc.*;
import it.unisa.dia.gas.plaf.jpbc.pairing.PairingFactory;

public class MessageHash {
	Pairing pairing = PairingFactory.getPairing("d224.properties");
	
	public void hash (Element[][] message_hash, int[] message, int messageLength) throws NoSuchAlgorithmException {
		MessageDigest hasher = MessageDigest.getInstance("SHA-512");
		String temp0;
		String temp1;
		byte[] temp0Bytes;
		byte[] temp1Bytes;
		for (int i = 0; i < messageLength; i++) {
			String temp = new String("");
			
			if (message[i] == 0) {
				for (int j = 0; j < i; j++) {
					temp += Integer.toString(message[j]); //m_1 m_2 ... m_i
				}
				temp0 = new String(Integer.toString(i) + temp +  "0");
				temp1 = new String(Integer.toString(i) + temp +  "1");
				temp0Bytes = hasher.digest(temp0.getBytes());
				temp1Bytes = hasher.digest(temp1.getBytes());
			}
			else { //message[i] == 1
				for (int j = 0; j < i; j++) {
					temp += Integer.toString(message[j]); //m_1 m_2 ... m_i
				}
				temp0 = new String(Integer.toString(i) + temp +  "1");
				temp1 = new String(Integer.toString(i) + temp +  "2");
				temp0Bytes = hasher.digest(temp0.getBytes());
				temp1Bytes = hasher.digest(temp1.getBytes());
			}
			message_hash[i][0] = pairing.getG1().newElementFromHash(temp0Bytes, 0, temp0Bytes.length);
			message_hash[i][1] = pairing.getG1().newElementFromHash(temp1Bytes, 0, temp1Bytes.length);
		}
	}
}
