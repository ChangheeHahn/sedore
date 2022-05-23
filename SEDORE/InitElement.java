import it.unisa.dia.gas.jpbc.*;
import it.unisa.dia.gas.plaf.jpbc.pairing.PairingFactory;

public class InitElement {
	Pairing pairing = PairingFactory.getPairing("d224.properties");
	
	public void initDimensionOne_Zr (Field<Element> Zr, Element[] target, int messageLength) {
		for (int i = 0; i < messageLength; i++) {
			target[i] = Zr.newElement();
		}

	}
	public void initDimensionOne_G1 (Field<Element> G1, Element[] target, int messageLength) {
		for (int i = 0; i < messageLength; i++) {
			target[i] = G1.newElement();
		}

	}
	public void initDimensionOne_G2 (Field<Element> G2, Element[] target, int messageLength) {
		for (int i = 0; i < messageLength; i++) {
			target[i] = G2.newElement();
		}

	}
	public void initDimensionTwo_Zr (Field<Element> Zr, Element[][] target, int messageLength, int targetLength) {
		for (int i = 0; i < messageLength; i++) {
			for (int j = 0; j < targetLength; j++) 
				target[i][j] = Zr.newElement();
		}
	}
	public void initDimensionTwo_G1 (Field<Element> G1, Element[][] target, int messageLength, int targetLength) {
		for (int i = 0; i < messageLength; i++) {
			for (int j = 0; j < targetLength; j++) 
				target[i][j] = G1.newElement();
		}
	}
	public void initDimensionTwo_G2 (Field<Element> G2, Element[][] target, int messageLength, int targetLength) {
		for (int i = 0; i < messageLength; i++) {
			for (int j = 0; j < targetLength; j++) 
				target[i][j] = G2.newElement();
		}
	}
}
