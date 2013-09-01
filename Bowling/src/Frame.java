import java.util.ArrayList;

public class Frame {

	ArrayList<Integer> tumbling = new ArrayList<Integer>();

	public int getTumblingPins() {
		int sum = 0;
		for (int i : tumbling) {
			sum += i;
		}
		return sum;
	}

	public void tumbleDownPin(int n) {
		tumbling.add(n);		
	}

}
