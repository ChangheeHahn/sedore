import it.unisa.dia.gas.jpbc.*;
import it.unisa.dia.gas.plaf.jpbc.pairing.PairingFactory;

public class KeyGen {
	Pairing pairing = PairingFactory.getPairing("d224.properties");
	
	public void keygen (Field<Element> Zr, Field<Element> G2, Element[] sk, Element[] pk, Element e2) {
		sk[0].set(Zr.newRandomElement());
		sk[1].set(Zr.newRandomElement());
		
		Element temp = G2.newElement();
		temp.set(e2);
		
		pk[0].set(temp.powZn(sk[0]));
	}
}
