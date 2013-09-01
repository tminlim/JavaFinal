import java.util.ArrayList;

public class Bowling {

	static int numberFrame = 0;
	static int tumblingPin = 0;
	static int scores[] = new int[10];

	static ArrayList<Frame> frames = new ArrayList<Frame>(10);

	public static void next() {
		if (numberFrame >= 9) {
			System.out.println("Game over");
			return;
		}

		numberFrame++;
		tumblingPin = 0;

		System.out.println("Next Frame :" + (numberFrame + 1));
	}

	public static void throwPin(int n) {

		System.out.println("Frame :" + (numberFrame + 1) + "  number"
				+ (tumblingPin + 1) + "  tumblingPins " + n);

		Frame f = frames.get(numberFrame);

		f.tumbleDownPin(n);
		tumblingPin++;

		if (numberFrame == 9) {// 10 Frame
			if (f.tumbling.get(0) != 10 && tumblingPin == 2) {
				next();
			} else if (f.tumbling.get(0) == 10 && tumblingPin == 3) {
				next();
			}
		} else if (f.getTumblingPins() == 10) {// strike
			next();
		} else if (tumblingPin >= 2) {
			next();
		}
	}

	public static void score() {

		for (int i = 0; i < frames.size(); i++) {
			int bonus = 0;

			if (i != 0)
				scores[i] = scores[i - 1] + frames.get(i).getTumblingPins();
			else
				scores[0] = frames.get(0).getTumblingPins();

			if (!frames.get(i).tumbling.isEmpty()
					&& frames.get(i).tumbling.get(0) == 10)// strike
				bonus = 2;
			else if (frames.get(i).getTumblingPins() == 10)// spare
				bonus = 1;

			// bonus
			if (bonus != 0 && i != 9) {
				int j = i + 1;

				while (bonus > 0) {
					for (int k = 0; k < frames.get(j).tumbling.size(); k++) {
						scores[i] += frames.get(j).tumbling.get(k);
						bonus--;

						if (bonus == 0)
							break;
					}
					j++;
				}

			}

		}
	}

	public static void showScore() {

		for (int i = 0; i < frames.size(); i++) {
			System.out.printf("%8d", i + 1);
		}
		System.out.println();
		for (int i = 0; i < frames.size(); i++) {
			System.out.print("  ");

			if (frames.get(i).tumbling.size() == 1) {

				if (frames.get(i).tumbling.get(0) == 10)
					System.out.print("  X   ");
				else if (frames.get(i).tumbling.get(0) == 0)
					System.out.printf("  -   ");
				else
					System.out.printf("%3d   ", frames.get(i).tumbling.get(0));

			} else if (frames.get(i).tumbling.size() == 2) {

				if (frames.get(i).tumbling.get(0) == 10)
					System.out.print("  X");
				else if (frames.get(i).tumbling.get(0) == 0)
					System.out.printf("  -");
				else
					System.out.printf("%3d", frames.get(i).tumbling.get(0));

				if (frames.get(i).getTumblingPins() == 10)
					System.out.print("  /");
				else if (frames.get(i).tumbling.get(1) == 0)
					System.out.printf("  -");
				else
					System.out.printf("%3d", frames.get(i).tumbling.get(1));

			} else if (frames.get(i).tumbling.size() == 3) {
				if (frames.get(i).tumbling.get(0) == 10)
					System.out.print("  X");
				else if (frames.get(i).tumbling.get(0) == 0)
					System.out.printf("  -");
				else
					System.out.printf("%3d", frames.get(i).tumbling.get(0));

				if (frames.get(i).tumbling.get(1) == 10)
					System.out.print("  X");
				else if (frames.get(i).tumbling.get(1) == 0)
					System.out.printf("  -");
				else
					System.out.printf("%3d", frames.get(i).tumbling.get(1));

				if (frames.get(i).tumbling.get(2) == 10)
					System.out.print("  X");
				else if (frames.get(i).tumbling.get(2) == 0)
					System.out.printf("  -");
				else if (frames.get(i).tumbling.get(1)
						+ frames.get(i).tumbling.get(2) == 10)
					System.out.printf("  /");
				else
					System.out.printf("%3d", frames.get(i).tumbling.get(2));
			}
		}
		System.out.println();

		for (int i : scores) {
			System.out.printf("%8d", i);
		}
	}

	public static void main(String[] args) {
		numberFrame = 0;
		for (int i = 0; i < 10; i++) {
			frames.add(new Frame());
		}

		throwPin(9);
		throwPin(1);// Spare

		throwPin(8);
		throwPin(0);

		throwPin(10);

		throwPin(10);

		throwPin(8);
		throwPin(0);

		throwPin(10);

		throwPin(8);
		throwPin(1);

		throwPin(9);
		throwPin(1);

		throwPin(8);
		throwPin(1);

		throwPin(10);
		throwPin(1);
		throwPin(9);

		score();
		showScore();
	}

}
