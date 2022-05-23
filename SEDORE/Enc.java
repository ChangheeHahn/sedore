import it.unisa.dia.gas.jpbc.*;
import it.unisa.dia.gas.plaf.jpbc.pairing.PairingFactory;

public class Enc {
	Pairing pairing = PairingFactory.getPairing("d224.properties");
	//Pairing pairing = PairingFactory.getPairing("d159.properties");
	
	public void encrypt (Field<Element> Zr, Field<Element> G1, Element[][] ciphertext, Element[][] message_hash, int messageLength, Element[] sk, Element e1) {
		
		for (int i = 0; i < messageLength; i++) {
			Element temp0, temp1, temp2, temp3;
			temp0 = G1.newElement();
			temp1 = G1.newElement();
			temp2 = G1.newElement();
			temp3 = G1.newElement();
			Element r0, r1;
			r0 = Zr.newRandomElement();
			r1 = Zr.newRandomElement();
			
			//Compute c_{i,0}
			temp0.set(e1);
			temp0.powZn(Zr.newOneElement().mulZn(r0).mulZn(sk[1]));
			temp0.mul(message_hash[i][0]);
			temp0.powZn(sk[0]);
			
			temp1.set(e1);
			temp1.powZn(r0);
			
			//Compute c_{i,1}
			temp2.set(e1);
			temp2.powZn(Zr.newOneElement().mulZn(r1).mulZn(sk[1]));
			temp2.mul(message_hash[i][1]);
			temp2.powZn(sk[0]);
			
			temp3.set(e1);
			temp3.powZn(r1);
			
			//Return c_{i,0} and c_{i,1}
			ciphertext[i][0].set(temp0);
			ciphertext[i][1].set(temp1);
			ciphertext[i][2].set(temp2);
			ciphertext[i][3].set(temp3);
		}
		
	}

}
