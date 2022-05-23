import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import it.unisa.dia.gas.jpbc.*;
import it.unisa.dia.gas.plaf.jpbc.pairing.PairingFactory;

public class TokGen {
	Pairing pairing = PairingFactory.getPairing("d224.properties");
	
	public void authorize (Field<Element> Zr, Field<Element> G2, Element[] tk_v_to_u, Element[] sk_u, Element[] pk_v) throws NoSuchAlgorithmException {
		MessageDigest hasher = MessageDigest.getInstance("SHA-256");
		Element temp0 = G2.newElement();
		temp0.set(pk_v[0]);
		temp0.powZn(sk_u[0]); //g_2^{alpha_v * alpha_u}
		byte[] g2Bytes = hasher.digest(temp0.toCanonicalRepresentation());
		
		Element temp1 = G2.newElement();
		temp1 = pairing.getG2().newElementFromHash(g2Bytes, 0, g2Bytes.length); //F(g_2^{alpha_v * alpha_u})
		
		Element temp2_0 = G2.newElement();
		Element temp2_1 = G2.newElement();
		temp2_0.set(temp1);
		temp2_1.set(temp1);
		
		Element temp3 = Zr.newElement();
		temp3.set(sk_u[0]);
		temp3.invert();
		
		tk_v_to_u[0].set(temp2_0.powZn(temp3));
		tk_v_to_u[1].set(temp2_1.powZn(sk_u[1]));
	}
}
