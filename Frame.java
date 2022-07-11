import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferStrategy;

public class Frame extends JFrame implements
		ChangeListener, Animation.SortedListener,
		Button.SortButtonListener, MakeCanvas.AnimationProvider {
	public static final long serialVersionUID = 10L;

	private static final int WIDTH = 1280, HEIGHT = 720;
	private static final int CAPACITY = 10, FPS = 100;
	private JPanel mainPanel, inputPanel, sliderPanel;
	private Button buttonPanel;
	private JLabel capacityLabel, fpsLabel;
	private JSlider fpsSlider;
	private MakeCanvas canvas;
	private Animation animation;
	@SuppressWarnings("rawtypes")
	private JComboBox combobox;

	public Frame() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setMaximumSize(new Dimension(WIDTH, HEIGHT + 200));
		this.setMinimumSize(new Dimension(WIDTH, HEIGHT + 20));
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT + 20));
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setBackground(new Color(62, 62, 62));
		this.setTitle("Group 4's Sorting animation");
		initialize();
		this.setVisible(true);
	}

	// ktao p tử
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void initialize() {
		mainPanel = new JPanel();
		mainPanel.setLayout(null);
		mainPanel.setBackground(new Color(62, 62, 62));
		add(mainPanel);

		// tạo button
		buttonPanel = new Button(this);
		buttonPanel.setBounds(0, 150, 250, HEIGHT);
		buttonPanel.setBackground(new Color(62, 62, 62));
		mainPanel.add(buttonPanel);

		// tạo canvas
		canvas = new MakeCanvas(this);
		int cWidth = WIDTH - 250 - 10;
		int cHeight = HEIGHT - 80;
		canvas.setFocusable(false);
		canvas.setMaximumSize(new Dimension(cWidth, cHeight));
		canvas.setMinimumSize(new Dimension(cWidth, cHeight));
		canvas.setPreferredSize(new Dimension(cWidth, cHeight));
		canvas.setBounds(250, 30, cWidth, cHeight);
		mainPanel.add(canvas);
		pack();


		animation = new Animation(CAPACITY, FPS, this);
		animation.createRandomArray(canvas.getWidth(), canvas.getHeight());

		capacityLabel = new JLabel("Capacity");
		capacityLabel.setForeground(new Color(232, 232, 232));
		capacityLabel.setFont(new Font(null, Font.BOLD, 15));
		Integer[] number = {10, 20, 30, 40, 50 };
		combobox = new JComboBox(number);
		combobox.setFont(new Font("Arial", Font.BOLD, 15));
		combobox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				animation.setCapacity((int) combobox.getSelectedItem());
				animation.createRandomArray(canvas.getWidth(), canvas.getHeight());
			}

		});
		// input panel
		inputPanel = new JPanel(new GridLayout(1, 0));
		inputPanel.add(capacityLabel);
		inputPanel.add(combobox);
		inputPanel.setBackground(new Color(62, 62, 62));
		inputPanel.setBounds(25, 20, 170, 30);
		mainPanel.add(inputPanel);

		// label
		fpsLabel = new JLabel("FPS");
		fpsLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		fpsLabel.setFont(new Font(null, Font.BOLD, 15));
		fpsLabel.setForeground(new Color(232, 232, 232));

		// slider
		fpsSlider = new JSlider(JSlider.HORIZONTAL, 1, 101, FPS);
		fpsSlider.setMajorTickSpacing(20);
		fpsSlider.setMinorTickSpacing(10);
		fpsSlider.setPaintTicks(true);
		fpsSlider.setPaintLabels(true);
		fpsSlider.setPaintTrack(true);
		fpsSlider.setBackground(new Color(62, 62, 62));
		fpsSlider.setForeground(new Color(232, 232, 232));
		fpsSlider.addChangeListener(this);

		sliderPanel = new JPanel();
		sliderPanel.setLayout(new BoxLayout(sliderPanel, BoxLayout.Y_AXIS));
		sliderPanel.setBackground(new Color(62, 62, 62));
		sliderPanel.add(fpsLabel);
		sliderPanel.add(fpsSlider);

		sliderPanel.setBounds(10, 80, 220, 100);
		mainPanel.add(sliderPanel);
	}

	public void stateChanged(ChangeEvent e) {
		if (!fpsSlider.getValueIsAdjusting()) {
			int value = (int) fpsSlider.getValue();
			animation.setFPS(value);
		}
	}

	// chọn
	public void sortButtonClicked(int id) {
		switch (id) {
			case 0:
				animation.createRandomArray(canvas.getWidth(), canvas.getHeight());
				break;
			case 1:
				animation.bubbleSort();
				break;
			case 2:
				animation.selectionSort();
				break;
			case 3:
				animation.heapSort();
				break;
			case 4:
				animation.quickSort();
				break;
		}
	}

	// vẽ arr
	public void onDrawArray() {
		if (animation != null)
			animation.drawArray();
	}

	public BufferStrategy getBufferStrategy() {
		BufferStrategy bs = canvas.getBufferStrategy();
		if (bs == null) {
			canvas.createBufferStrategy(2);
			bs = canvas.getBufferStrategy();
		}

		return bs;
	}

}