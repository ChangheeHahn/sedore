import it.unisa.dia.gas.jpbc.*;
import it.unisa.dia.gas.plaf.jpbc.pairing.PairingFactory;

public class Query {
	Pairing pairing = PairingFactory.getPairing("d224.properties");
	//Pairing pairing = PairingFactory.getPairing("d159.properties");
	public void query (Field Zr, Field G2, Element[][] token, Element[] uk, Element e2, int[] message) {
		Element r;
		Element m;
		
		for (int i = 0; i < message.length; i++) {
			r = Zr.newRandomElement();
			m = Zr.newElement(message[i]);
			
			
			Element temp0 = G2.newRandomElement();
			Element temp1 = G2.newRandomElement();
			Element temp2 = G2.newRandomElement();
			
			temp0.set(uk[0]);
			temp1.set(uk[1]);
			temp2.set(uk[1]);
			
			temp0.powZn(r);
			temp1.powZn(r);
			temp2.powZn(r);
			
			temp2.powZn(m);
			temp0.mul(temp2);
			
			token[i][0].set(temp0);
			token[i][1].set(temp1);
		}	
		
	}
}
