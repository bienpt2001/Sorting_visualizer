import javax.swing.JOptionPane;

import javafx.scene.chart.PieChart.Data;

import java.awt.image.BufferStrategy;
import java.awt.Color;
import java.awt.Graphics;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Animation {
	private static final int padding = 20;
	private int spadding = 10;
	private static final int maxHeight = 550, minHeight = 30;
	private Integer[] arr;
	private int capac, speed;
	private Bar[] bars;
	private boolean isArray;

	private Color src, swp, cmp;

	private BufferStrategy bs;
	private Graphics g;

	private SortedListener listener;

	public Animation(int capac, int fps, SortedListener listener) {
		this.capac = capac;
		this.speed = (int) (1000.0 / fps);
		this.listener = listener;

		src = new Color(211, 221, 228);// trắng
		cmp = new Color(96, 96, 96);// đen
		swp = new Color(153, 0, 76);// hồng tím

		bs = listener.getBufferStrategy();

		isArray = false;
	}

	// tạo arr
	public void createRandomArray(int canvasWidth, int canvasHeight) {
		arr = new Integer[capac];
		bars = new Bar[capac];
		isArray = true;

		// khởi tạo vị trí
		double x = padding;
		int y = canvasHeight;

		// rộng các bar
		double width = (double) (canvasWidth - padding * 2) / capac;

		// set g
		g = bs.getDrawGraphics();
		g.setColor(new Color(48, 48, 48));
		g.fillRect(0, 0, canvasWidth, canvasHeight);

		Random rand = new Random();
		int value;
		Bar bar;
		for (int i = 0; i < arr.length; i++) {
			value = rand.nextInt(maxHeight - minHeight) + minHeight;
			arr[i] = value;

			bar = new Bar((int) x, y+spadding, (int) width-spadding, value, src);
			bar.draw(g);
			bars[i] = bar;

			// chuyển bar
			x += width;
		}

		bs.show();
		g.dispose();
	}
	public void setSpadding(int value){
		this.spadding = value;
	}
	private Color getBarColor(int value) {

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
		return new Color(198, 21, 233);	//purple
	}
	/* BUBBLE SORT */
	public void bubbleSort() {
		if (!isCreated())
			return;

		g = bs.getDrawGraphics();
		int count = 0;
		for (int i = arr.length - 1; i >= 0; i--) {
			count = 0;
			for (int j = 0; j < i; j++) {
				colorPair(j, j + 1, cmp);

				if (arr[j] > arr[j + 1]) {
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

		for (int i = arr.length - 1; i >= 0; i--) {
			// find the max
			int max = arr[i], index = i;
			for (int j = 0; j <= i; j++) {
				if (max < arr[j]) {
					max = arr[j];
					index = j;
				}

				colorPair(index, j, cmp);
			}

			swap(i, index);
			bars[i].setColor(getBarColor(i));
			bars[i].draw(g);
			bs.show();
		}

		finishAnimation();

		g.dispose();
	}

	// /* INSERTION SORT */
	// public void insertionSort() {
	// 	if (!isCreated())
	// 		return;

	// 	g = bs.getDrawGraphics();

	// 	Bar bar;
	// 	for (int i = 1; i < array.length; i++) {
	// 		bars[i].setColor(getBarColor(i));

	// 		// tìm vị trí chèn
	// 		int index = i - 1, element = array[i];
	// 		while (index >= 0 && element < array[index]) {
	// 			array[index + 1] = array[index];

	// 			bar = bars[index + 1];
	// 			bar.clear(g);
	// 			bar.setValue(bars[index].getValue());
	// 			colorBar(index + 1, swappingColor);

	// 			index--;
	// 		}

	// 		index++;

	// 		// thêm ptu
	// 		array[index] = element;

	// 		bar = bars[index];
	// 		bar.clear(g);
	// 		bar.setValue(element);
	// 		bar.setColor(getBarColor(index));
	// 		bar.draw(g);

	// 		bs.show();
	// 	}

	// 	finishAnimation();

	// 	g.dispose();
	// }

	/*Heap sort */

	public void heapify(int n, int i){
		int max = i;    
		int l = i * 2 + 1;   
		int r = l + 1;
		if(l < n&& arr[l] > arr[max]){
			max = l;
		}

		if(r < n && arr[r] > arr[max]){
			max = r;
		}

		if(max != i){
			swap(i, max);
			heapify(n, max);
		}
	}

	public void heapSort(){
		if (!isCreated())
			return;
		int n = arr.length;
		g = bs.getDrawGraphics();
		for (int i = n/2 - 1; i >= 0; i--) {
			heapify(n, i);
		}
		for (int j = n - 1 ; j > 0; j--) {
			swap(0, j);
			bars[j].setColor(getBarColor(j));
			bars[j].draw(g);
			bs.show();
			heapify(j, 0);
		}
		finishAnimation();
	}

	/* QUICK SORT */
	public void quickSort() {
		if (!isCreated())
			return;

		g = bs.getDrawGraphics();

		quickSort(0, arr.length - 1);

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
		int pivot = arr[end];

		// đánh dấu pivot
		Bar bar = bars[end];
		Color oldColor = bar.getColor();
		bar.setColor(cmp);
		bar.draw(g);
		bs.show();

		int index = start - 1;
		for (int i = start; i < end; i++) {
			if (arr[i] < pivot) {
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
		int temp = arr[j];
		arr[j] = arr[i];
		arr[i] = temp;

		// clear bar
		bars[i].clear(g);
		bars[j].clear(g);

		// swap hiệu ứng
		bars[j].setValue(bars[i].getValue());
		bars[i].setValue(temp);

		colorPair(i, j, swp);
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
			colorBar(i, cmp);
			bars[i].setColor(getBarColor(i));
			bars[i].draw(g);
			bs.show();
		}
	}

	public void drawArray() {
		if (!isArray)
			return;

		g = bs.getDrawGraphics();

		for (int i = 0; i < bars.length; i++) {
			bars[i].draw(g);
		}

		bs.show();
		g.dispose();
	}

	private boolean isCreated() {
		if (!isArray)
			JOptionPane.showMessageDialog(null, "You need to create an array!", "No Array Created Error",
					JOptionPane.ERROR_MESSAGE);
		return isArray;
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