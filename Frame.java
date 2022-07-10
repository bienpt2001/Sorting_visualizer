import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferStrategy;


public class Frame extends JFrame implements
	   ChangeListener, Visualizer.SortedListener,
	   ButtonPanel.SortButtonListener, MyCanvas.VisualizerProvider
{
	public static final long serialVersionUID = 10L;


	private static final int WIDTH = 1280, HEIGHT = 720;
	private static final int CAPACITY = 10, FPS = 100;
	private JPanel mainPanel, inputPanel, sliderPanel;
	private ButtonPanel buttonPanel;
	private JLabel capacityLabel, fpsLabel;
	private JSlider fpsSlider;
	private MyCanvas canvas;
	private Visualizer visualizer;
	@SuppressWarnings("rawtypes")
	private JComboBox  combobox;
	public Frame()
	{
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setMaximumSize(new Dimension(WIDTH, HEIGHT + 200));
		this.setMinimumSize(new Dimension(WIDTH, HEIGHT + 20));
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT + 20));
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setBackground(ColorManager.BACKGROUND);
		this.setTitle("Group 4's Sorting Visualizer");
		initialize();
		this.setVisible(true);
	}


	// initialize components
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void initialize()
	{
		mainPanel = new JPanel();
		mainPanel.setLayout(null);
		mainPanel.setBackground(ColorManager.BACKGROUND);
		add(mainPanel);

		// add buttons panel
		buttonPanel = new ButtonPanel(this);
		buttonPanel.setBounds(0, 150, 250, HEIGHT);
		buttonPanel.setBackground(ColorManager.BACKGROUND);
		mainPanel.add(buttonPanel);

		// add canvas
		canvas = new MyCanvas(this);
		int cWidth = WIDTH - 250 - 10;
		int cHeight = HEIGHT - 80;
		canvas.setFocusable(false);
		canvas.setMaximumSize(new Dimension(cWidth, cHeight));
		canvas.setMinimumSize(new Dimension(cWidth, cHeight));
		canvas.setPreferredSize(new Dimension(cWidth, cHeight));
		canvas.setBounds(250, 30, cWidth, cHeight);
		mainPanel.add(canvas);
		pack();

		visualizer = new Visualizer(CAPACITY, FPS, this);
		visualizer.createRandomArray(canvas.getWidth(), canvas.getHeight());

		capacityLabel = new JLabel("Capacity");
		capacityLabel.setForeground(ColorManager.TEXT);
		capacityLabel.setFont(new Font(null, Font.BOLD, 15));
		Integer[] number = { 10,20,30,40,50};
		combobox = new JComboBox(number);
		combobox.setFont(new Font("Arial",Font.BOLD,15));
		combobox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				visualizer.setCapacity((int)combobox.getSelectedItem());
			}
			
		});
		// input panel
		inputPanel = new JPanel(new GridLayout(1, 0));
		inputPanel.add(capacityLabel);
		inputPanel.add(combobox);
		inputPanel.setBackground(ColorManager.BACKGROUND);
		inputPanel.setBounds(25, 20, 170, 30);
		mainPanel.add(inputPanel);


		// create slider for fps
		// label
		fpsLabel = new JLabel("Frames Per Second");
		fpsLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		fpsLabel.setFont(new Font(null, Font.BOLD, 15));
		fpsLabel.setForeground(ColorManager.TEXT);

		// slider
		fpsSlider = new JSlider(JSlider.HORIZONTAL, 10, 310, FPS);
		fpsSlider.setMajorTickSpacing(100);
		fpsSlider.setMinorTickSpacing(20);
		fpsSlider.setPaintTicks(true);
		fpsSlider.setPaintLabels(true);
		fpsSlider.setPaintTrack(true);
		fpsSlider.setBackground(ColorManager.BACKGROUND);
		fpsSlider.setForeground(ColorManager.TEXT);
		fpsSlider.addChangeListener(this);


		sliderPanel = new JPanel();
		sliderPanel.setLayout(new BoxLayout(sliderPanel, BoxLayout.Y_AXIS));
		sliderPanel.setBackground(ColorManager.BACKGROUND);
		sliderPanel.add(fpsLabel);
		sliderPanel.add(fpsSlider);

		sliderPanel.setBounds(10, 80, 220, 100);
		mainPanel.add(sliderPanel);
	}
	public void stateChanged(ChangeEvent e)
	{
		if (!fpsSlider.getValueIsAdjusting())
		{
			int value = (int) fpsSlider.getValue();
			visualizer.setFPS(value);
		}
	}


	// button clicked
	public void sortButtonClicked(int id)
	{
		switch (id)
		{
			case 0:  // create button
				visualizer.createRandomArray(canvas.getWidth(), canvas.getHeight());
				break;
			case 1:  // bubble button
				visualizer.bubbleSort();
				break;
			case 2:  // selection button
				visualizer.selectionSort();
				break;
			case 3:  // insertion button
				visualizer.insertionSort();
				break;
			case 4:  // quick button
				visualizer.quickSort();
				break;
		}
	}


	// draw the array
	public void onDrawArray()
	{
		if (visualizer != null)
			visualizer.drawArray();
	}


	// showing statistics when sorting is completed
	@Override


	// return the graphics for drawing
	public BufferStrategy getBufferStrategy()
	{
		BufferStrategy bs = canvas.getBufferStrategy();
		if (bs == null)
		{
			canvas.createBufferStrategy(2);
			bs = canvas.getBufferStrategy();
		}

		return bs;
	}


	@Override
	public void onArraySorted(long elapsedTime, int comparison, int swapping) {
		// TODO Auto-generated method stub
		
	}


	// @Override
	// public void onArraySorted(int comparison, int swapping) {
	// 	// TODO Auto-generated method stub
		
	// }
}