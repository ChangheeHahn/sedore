import it.unisa.dia.gas.jpbc.*;
import it.unisa.dia.gas.plaf.jpbc.pairing.PairingFactory;

public class Test {
	Pairing pairing = PairingFactory.getPairing("d224.properties");
	
	public int test(Field<Element> GT, Element[][] ct_u, Element[][] ct_v, Element[] tk_v_to_u, Element[] tk_u_to_v, int messageLength) {
		// Return 0 if m = m', 1 if m < m', 2 if m > m'
		Element T0, T1, T2, T3, d0, d1;
		
		
		for (int i = 0; i < messageLength; i++) {
			//Test-1 (if m_u > m_v)
			T0 = GT.newElement();
			T0.set(pairing.pairing(ct_u[i][0], tk_v_to_u[0]));
			T1 = GT.newElement();
			T1.set(pairing.pairing(ct_u[i][1], tk_v_to_u[1]));
			d0 = GT.newElement();
			d0.set(T0.div(T1));
			
			T2 = GT.newElement();
			T2.set(pairing.pairing(ct_v[i][2], tk_u_to_v[0]));
			T3 = GT.newElement();
			T3.set(pairing.pairing(ct_v[i][3], tk_u_to_v[1]));
			d1 = GT.newElement();
			d1.set(T2.div(T3));
		
			if (d0.isEqual(d1))
				return 1;
			
			//Test-2 (if m_u < m_v)
			T0 = GT.newElement();
			T0.set(pairing.pairing(ct_u[i][2], tk_v_to_u[0]));
			T1 = GT.newElement();
			T1.set(pairing.pairing(ct_u[i][3], tk_v_to_u[1]));
			d0 = GT.newElement();
			d0.set(T0.div(T1));
			
			T2 = GT.newElement();
			T2.set(pairing.pairing(ct_v[i][0], tk_u_to_v[0]));
			T3 = GT.newElement();
			T3.set(pairing.pairing(ct_v[i][1], tk_u_to_v[1]));
			d1 = GT.newElement();
			d1.set(T2.div(T3));
		
			if (d0.isEqual(d1))
				return 2;
		}
		
		//Test-3 (if m_u == m_v)
		return 0;
	}
}
