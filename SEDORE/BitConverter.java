
public class BitConverter {
	int[] intToBinary (int a, int messageLength) {
		String temp = Integer.toBinaryString(a);
		int[] result = new int[messageLength];
		while (temp.length() != messageLength) {
			temp = "0" + temp;
		}
		
		for (int i = 0; i < messageLength; i++) {
			result[i] = Character.getNumericValue(temp.charAt(i));
		}
		
		return result;
	}
}
