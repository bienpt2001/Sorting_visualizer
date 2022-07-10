import javax.swing.JOptionPane;
import java.awt.image.BufferStrategy;
import java.awt.Color;
import java.awt.Graphics;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Visualizer {
	private static final int PADDING = 20;
	private static final int MAX_BAR_HEIGHT = 550, MIN_BAR_HEIGHT = 30;
	private Integer[] array;
	private int capac, speed;
	private Bar[] bars;
	private boolean hasArray;

	private Color originalColor, swappingColor, comparingColor;

	private BufferStrategy bs;
	private Graphics g;

	private SortedListener listener;

	public Visualizer(int capac, int fps, SortedListener listener) {
		this.capac = capac;
		this.speed = (int) (1000.0 / fps);
		this.listener = listener;

		originalColor = new Color(211, 221, 228);// trắng
		comparingColor = new Color(244, 186, 7);// vàng
		swappingColor = new Color(229, 77, 79);// đỏ

		bs = listener.getBufferStrategy();

		hasArray = false;
	}

	// tạo arr
	public void createRandomArray(int canvasWidth, int canvasHeight) {
		array = new Integer[capac];
		bars = new Bar[capac];
		hasArray = true;

		// khởi tạo vị trí
		double x = PADDING;
		int y = canvasHeight;

		// rộng các bar
		double width = (double) (canvasWidth - PADDING * 2) / capac;

		// set g
		g = bs.getDrawGraphics();
		g.setColor(new Color(48, 48, 48));
		g.fillRect(0, 0, canvasWidth, canvasHeight);

		Random rand = new Random();
		int value;
		Bar bar;
		for (int i = 0; i < array.length; i++) {
			value = rand.nextInt(MAX_BAR_HEIGHT - MIN_BAR_HEIGHT) + MIN_BAR_HEIGHT;
			array[i] = value;

			bar = new Bar((int) x, y, (int) width, value, originalColor);
			bar.draw(g);
			bars[i] = bar;

			// chuyển bar
			x += width;
		}

		bs.show();
		g.dispose();
	}

	private Color getBarColor(int value) {
		// int interval = (int) (array.length / 5.0);
		if (value % 7 == 0)
			return new Color(221, 77, 77); //red
		else if (value % 7 == 1)
			return new Color(214, 131, 79); //orange
		else if (value % 7 == 2)
			return new Color(244, 183, 7); //yellow
		else if (value % 7 == 3)
			return new Color(135, 170, 102); //green
		else if (value % 7 == 4)
			return new Color(105, 198, 255); //blue
		else if (value % 7 == 5)
			return new Color(120, 21, 233); //Indigo Blue
		return new Color(198, 21, 233);	//purpil
	}
	/* BUBBLE SORT */
	public void bubbleSort() {
		if (!isCreated())
			return;

		g = bs.getDrawGraphics();

		int count = 0;
		for (int i = array.length - 1; i >= 0; i--) {
			count = 0;
			for (int j = 0; j < i; j++) {
				colorPair(j, j + 1, comparingColor);

				if (array[j] > array[j + 1]) {
					swap(j, j + 1);
					count++;
				}

			}

			bars[i].setColor(getBarColor(i));
			bars[i].draw(g);
			bs.show();

			if (count == 0)
				break;
		}

		finishAnimation();

		g.dispose();
	}

	/* SELECTION SORT */
	public void selectionSort() {
		if (!isCreated())
			return;

		g = bs.getDrawGraphics();

		for (int i = array.length - 1; i >= 0; i--) {
			// find the max
			int max = array[i], index = i;
			for (int j = 0; j <= i; j++) {
				if (max < array[j]) {
					max = array[j];
					index = j;
				}

				colorPair(index, j, comparingColor);
			}

			swap(i, index);
			bars[i].setColor(getBarColor(i));
			bars[i].draw(g);
			bs.show();
		}

		finishAnimation();

		g.dispose();
	}

	/* INSERTION SORT */
	public void insertionSort() {
		if (!isCreated())
			return;

		g = bs.getDrawGraphics();

		Bar bar;
		for (int i = 1; i < array.length; i++) {
			bars[i].setColor(getBarColor(i));

			// tìm vị trí chèn
			int index = i - 1, element = array[i];
			while (index >= 0 && element < array[index]) {
				array[index + 1] = array[index];

				bar = bars[index + 1];
				bar.clear(g);
				bar.setValue(bars[index].getValue());
				colorBar(index + 1, swappingColor);

				index--;
			}

			index++;

			// thêm ptu
			array[index] = element;

			bar = bars[index];
			bar.clear(g);
			bar.setValue(element);
			bar.setColor(getBarColor(index));
			bar.draw(g);

			bs.show();
		}

		finishAnimation();

		g.dispose();
	}

	/* QUICK SORT */
	public void quickSort() {
		if (!isCreated())
			return;

		g = bs.getDrawGraphics();

		quickSort(0, array.length - 1);

		finishAnimation();
		g.dispose();
	}

	private void quickSort(int start, int end) {
		if (start < end) {
			// chọn pivot
			int pivot = partition(start, end);

			bars[pivot].setColor(getBarColor(pivot));
			bars[pivot].draw(g);
			bs.show();

			// sort bên phải
			quickSort(start, pivot - 1);

			// sort bên trái
			quickSort(pivot + 1, end);
		}
	}

	private int partition(int start, int end) {
		// pivot ở cuối
		int pivot = array[end];

		// đánh dấu pivot
		Bar bar = bars[end];
		Color oldColor = bar.getColor();
		bar.setColor(comparingColor);
		bar.draw(g);
		bs.show();

		int index = start - 1;
		for (int i = start; i < end; i++) {
			if (array[i] < pivot) {
				index++;
				swap(index, i);

			}
		}

		bar.setColor(oldColor);
		bar.draw(g);
		bs.show();

		// chuyển pivot về cuối
		index++;
		swap(index, end);
		return index;
	}

	// swap
	private void swap(int i, int j) {
		// swap
		int temp = array[j];
		array[j] = array[i];
		array[i] = temp;

		// clear bar
		bars[i].clear(g);
		bars[j].clear(g);

		// swap hiệu ứng
		bars[j].setValue(bars[i].getValue());
		bars[i].setValue(temp);

		colorPair(i, j, swappingColor);
	}

	private void colorPair(int i, int j, Color color) {
		Color color1 = bars[i].getColor(), color2 = bars[j].getColor();

		bars[i].setColor(color);
		bars[i].draw(g);

		bars[j].setColor(color);
		bars[j].draw(g);

		bs.show();

		try {
			TimeUnit.MILLISECONDS.sleep(speed);
		} catch (Exception ex) {
		}

		// chuyển màu cũ
		bars[i].setColor(color1);
		bars[i].draw(g);

		bars[j].setColor(color2);
		bars[j].draw(g);

		bs.show();
	}

	// back to its original color
	private void colorBar(int index, Color color) {
		Bar bar = bars[index];
		Color oldColor = bar.getColor();

		bar.setColor(color);
		bar.draw(g);
		bs.show();

		try {
			TimeUnit.MILLISECONDS.sleep(speed);
		} catch (Exception ex) {
		}

		bar.setColor(oldColor);
		bar.draw(g);

		bs.show();
	}

	// hiệu ứng kết thúc
	private void finishAnimation() {
		for (int i = 0; i < bars.length; i++) {
			colorBar(i, comparingColor);
			bars[i].setColor(getBarColor(i));
			bars[i].draw(g);
			bs.show();
		}
	}

	public void drawArray() {
		if (!hasArray)
			return;

		g = bs.getDrawGraphics();

		for (int i = 0; i < bars.length; i++) {
			bars[i].draw(g);
		}

		bs.show();
		g.dispose();
	}

	private boolean isCreated() {
		if (!hasArray)
			JOptionPane.showMessageDialog(null, "You need to create an array!", "No Array Created Error",
					JOptionPane.ERROR_MESSAGE);
		return hasArray;
	}

	public void setCapacity(int capac) {
		this.capac = capac;
	}

	// FPS
	public void setFPS(int fps) {
		this.speed = (int) (1000.0 / fps);
	}

	public interface SortedListener {
		BufferStrategy getBufferStrategy();
	}
}