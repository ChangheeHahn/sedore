import java.security.NoSuchAlgorithmException;
import it.unisa.dia.gas.jpbc.*;
import it.unisa.dia.gas.plaf.jpbc.pairing.PairingFactory;

public class Main {
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws NoSuchAlgorithmException {
		int messageLength = 8; //The maximum bit-length
		BitConverter converter = new BitConverter();
				
		int[] m1 = converter.intToBinary(100, messageLength); //Message for u
		int[] m2 = converter.intToBinary(86, messageLength); //Message for v
		System.out.print("Binary repr. of m1= ");
		for (int i = 0; i < messageLength; i++)
			System.out.print(m1[i]);
		System.out.println();
		System.out.print("Binary repr. of m2= ");
		for (int i = 0; i < messageLength; i++)
			System.out.print(m2[i]);
		System.out.println();
		System.out.println();
		
		//Setup
		Pairing pairing = PairingFactory.getPairing("d224.properties");
		Field<Element> Zr = pairing.getZr();
		Field<Element> G1 = pairing.getG1();
		Field<Element> G2 = pairing.getG2();
		Field<Element> GT = pairing.getGT();
		Element e1 = G1.newRandomElement();
		Element e2 = G2.newRandomElement();
		//Element initialization
		InitElement init = new InitElement();
		
		
		//Key generation
		KeyGen keygen = new KeyGen();
		Element[] sk_u = new Element[2];
		init.initDimensionOne_Zr(Zr, sk_u, 2);
		Element[] pk_u = new Element[1];
		init.initDimensionOne_G2(G2, pk_u, 1);
		keygen.keygen(Zr, G2, sk_u, pk_u, e2);
		Element[] sk_v = new Element[2];
		init.initDimensionOne_Zr(Zr, sk_v, 2);
		Element[] pk_v = new Element[1];
		init.initDimensionOne_G2(G2, pk_v, 1);
		keygen.keygen(Zr, G2, sk_v, pk_v, e2);
		
		
		//Authorization token generation
		TokGen authorize = new TokGen();
		Element[] tk_v_to_u = new Element[2];
		init.initDimensionOne_G2(G2, tk_v_to_u, 2);
		authorize.authorize(Zr, G2, tk_v_to_u, sk_u, pk_v);
		Element[] tk_u_to_v = new Element[2];
		init.initDimensionOne_G2(G2, tk_u_to_v, 2);
		authorize.authorize(Zr, G2, tk_u_to_v, sk_v, pk_u);
		
		
		//Message hash for encryption
		MessageHash messageHash = new MessageHash();
		Element[][] m1_hash = new Element[messageLength][2];
		init.initDimensionTwo_G1(G1, m1_hash, messageLength, 2);
		messageHash.hash(m1_hash, m1, messageLength);
		Element[][] m2_hash = new Element[messageLength][2];
		init.initDimensionTwo_G1(G1, m2_hash, messageLength, 2);
		messageHash.hash(m2_hash, m2, messageLength);
		
		
		//hash encryption
		Enc enc = new Enc();
		Element[][] ct_u = new Element[messageLength][4];
		init.initDimensionTwo_G1(G1, ct_u, messageLength, 4);
		enc.encrypt(Zr, G1, ct_u, m1_hash, messageLength, sk_u, e1);
		Element[][] ct_v = new Element[messageLength][4];
		init.initDimensionTwo_G1(G1, ct_v, messageLength, 4);
		enc.encrypt(Zr, G1, ct_v, m2_hash, messageLength, sk_v, e1);
		
		
		//Test to reveal order
		Test test = new Test();		
		int result = test.test(GT, ct_u, ct_v, tk_v_to_u, tk_u_to_v, messageLength);
		
		if (result == 0)
			System.out.println("Test result: m1 = m2");
		else if (result == 1)
			System.out.println("Test result: m1 > m2");
		else
			System.out.println("Test result: m1 < m2");

	}
}
