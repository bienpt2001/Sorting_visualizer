import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;

public class Main {
	
	private MyColor myColor = new MyColor();
	
	private Frame mainFrame;
	JLabel bestCase = new JLabel("");
	JLabel worstCase = new JLabel("");
	JLabel averageCase = new JLabel("");
	JLabel space = new JLabel("");
	JPanel inforPanel = new JPanel();
	
	private int length = 50;
	private int curAlg = 0;
	private int spd = 50;
	private int compare = 0;
	private int swap = 0;
	JLabel capacLabel = new JLabel("Capacity : ");
	JPanel capacPanel = new JPanel();

	private int[] list = new int[length];
	private String[] types = {"Bar Graph", "Dot Plot"};
	private String[] algorithms = {"Bubble Sort", 
								   "Insertion Sort",
								   "Quick Sort", 
								   "Heap Sort", 
								   "Merge Sort", };
	JRadioButton bubbleSortBtn,insertionSortBtn,quickSortBtn,heapSortBtn,mergeSortBtn;
	private Information infor = new Information() ;
	JPanel delayPanel = new JPanel();
	private Integer[] capacityList = {50,40,30,20,10};
	JComboBox capacityCombobox = new JComboBox(capacityList);
	private boolean sorting = false;
	private boolean shuffled = true;
	
	SortingAlgorithms algorithm;
	Random r = new Random();
	
	JPanel tools = new JPanel();
	JPanel sliding = new JPanel();
	private MyCanvas myCanvas;
	
	JLabel groupTile = new JLabel("Group 4");
	JLabel delayL = new JLabel("Delay :");
	JLabel msL = new JLabel(spd+" ms");
	JLabel sizeL = new JLabel("Size :");
	JLabel lenL = new JLabel(length+"");
	JLabel compareL = new JLabel("Comparisons : " + compare);
	JLabel accessL = new JLabel("Swap Number : " + swap);
	JLabel algorithmL = new JLabel("Algorithms");

	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	JComboBox alg = new JComboBox(algorithms);
	@SuppressWarnings({ "rawtypes", "unchecked" })
	JComboBox graph = new JComboBox(types);

	
	JButton stopBtn = new JButton("Stop");
	
	
	JPanel test = new JPanel();
	
	JButton sort = new JButton("Sort");
	JButton shuffle = new JButton("New Array");
	JButton credit = new JButton("Informaion");
	
	JSlider size = new JSlider(JSlider.HORIZONTAL,1,6,1);
	JSlider speed = new JSlider(JSlider.HORIZONTAL,0,1000,spd);
	
	Border loweredetched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
	
	public Main() {
		createList();	
		initialize();	
	}
	
	public void createList() {	
		
		Random rand = new Random();
		for(int i = 0; i < length; i++) {	
			list[i] = rand.nextInt(400)+50;
		}  
		
		sorting = false;
		shuffled = true;
	}
	

	
	JPanel mainPanel;
	
	public void initialize() {
		
		
	
		mainFrame = new Frame();
		
		mainPanel = new JPanel();
		mainPanel.setBounds(0,0,mainFrame.getWidth(),mainFrame.getHeight());
		mainPanel.setBackground(myColor.backgroundColor);
		mainPanel.setLayout(null);
		
		tools.setLayout(null);
		tools.setBounds(20,70,250,600);
		tools.setBackground(myColor.backgroundColor);
		
		sliding.setLayout(null);
		sliding.setBackground(myColor.backgroundColor);
		sliding.setBounds(400,10,800,50);

		groupTile.setForeground(myColor.textColor);
		groupTile.setFont(new Font("Arial",Font.BOLD,30));
		groupTile.setBounds(60,20,200,50);
		mainFrame.add(groupTile);
		
		ButtonGroup btnGrp = new ButtonGroup();
		bubbleSortBtn = new JRadioButton();
		mergeSortBtn = new JRadioButton();
		heapSortBtn = new JRadioButton();
		insertionSortBtn = new JRadioButton();
		quickSortBtn = new JRadioButton();
		initButton(bubbleSortBtn,"Bubble Sort",0);
		initButton(mergeSortBtn,"Merge Sort",4);
		initButton(heapSortBtn,"Heap Sort",3);
		initButton(insertionSortBtn,"Insertion Sort",1);
		initButton(quickSortBtn,"Quick Sort",2);
		
		btnGrp.add(bubbleSortBtn);
		btnGrp.add(insertionSortBtn);
		btnGrp.add(quickSortBtn);
		btnGrp.add(heapSortBtn);
		btnGrp.add(mergeSortBtn);
		
		
		test.add(bubbleSortBtn);
		test.add(insertionSortBtn);
		test.add(quickSortBtn);
		test.add(heapSortBtn);
		test.add(mergeSortBtn);
		test.setBounds(40,100,150,200);
		test.setBackground(myColor.backgroundColor);
		test.setLayout(new FlowLayout(FlowLayout.LEFT,0,10));

		tools.add(test);
		
		
		
		stopBtn.setBounds(40,440,120,30);
		stopBtn.setFont(new Font("Arial",Font.BOLD,15));
		stopBtn.setFocusable(false);
		tools.add(stopBtn);
		
		
		sort.setBounds(40,490,120,30);
		sort.setFont(new Font("Arial",Font.BOLD,15));
		sort.setFocusable(false);
		tools.add(sort);
		
		
		shuffle.setBounds(40,550,120,30);
		shuffle.setFont(new Font("Arial",Font.BOLD,15));
		shuffle.setFocusable(false);
		tools.add(shuffle);
		
		
		delayL.setHorizontalAlignment(JLabel.LEFT);
		delayL.setBounds(50,10,50,25);
		delayL.setForeground(myColor.textColor);
		sliding.add(delayL);
		
		
		msL.setHorizontalAlignment(JLabel.LEFT);
		msL.setBounds(250,10,50,25);
		msL.setForeground(myColor.textColor);
		sliding.add(msL);

		
		speed.setMajorTickSpacing(100);
		speed.setBounds(90,12,150,25);
		speed.setPaintTicks(true);
		speed.setBackground(myColor.backgroundColor);
		sliding.add(speed);

		capacPanel.setLayout(null);
		capacPanel.setBounds(10,20,200,50);
		capacLabel.setBounds(30,17,150,25);
		capacLabel.setFont(new Font("Arial",Font.BOLD,17));
		capacLabel.setForeground(myColor.textColor);
		capacityCombobox.setBounds(120,15,50,30);
		capacityCombobox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				length = (int)capacityCombobox.getSelectedItem();
				Update();
			}
			
		}
		);
		
	
		
		capacityCombobox.setFocusable(false);
		capacPanel.add(capacLabel);
		capacPanel.add(capacityCombobox);
		capacPanel.setBackground(myColor.backgroundColor);
		tools.add(capacPanel);
		
		
	
		compareL.setHorizontalAlignment(JLabel.LEFT);
		compareL.setBounds(400,10,200,25);
		compareL.setForeground(myColor.textColor);
		sliding.add(compareL);
		
		
		accessL.setHorizontalAlignment(JLabel.LEFT);
		accessL.setBounds(600,10,200,25);
		accessL.setForeground(myColor.textColor);
		sliding.add(accessL);
		
		
		bestCase.setFont(new Font("Arial",Font.PLAIN,14));
		bestCase.setForeground(myColor.textColor);
		worstCase.setFont(new Font("Arial",Font.PLAIN,14));
		worstCase.setForeground(myColor.textColor);
		averageCase.setFont(new Font("Arial",Font.PLAIN,14));
		averageCase.setForeground(myColor.textColor);
		space.setFont(new Font("Arial",Font.PLAIN,14));
		space.setForeground(myColor.textColor);
		inforPanel.add(bestCase);

		inforPanel.add(worstCase);
		inforPanel.add(averageCase);
		inforPanel.add(space);
		inforPanel.setBounds(60,360,160,120);
		inforPanel.setLayout(new FlowLayout(FlowLayout.LEFT,0,10));
		inforPanel.setBackground(myColor.backgroundColor);
		mainPanel.add(inforPanel);
		
		
		
		
		algorithm = new SortingAlgorithms();
		
		myCanvas = new MyCanvas(list);
		myCanvas.setBounds(320,150,900,620);

		mainPanel.add(sliding);
		mainPanel.add(tools);
		mainPanel.add(myCanvas);
	
		
		alg.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				curAlg = alg.getSelectedIndex();
			}	
		});

		
		
		stopBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(sorting) 
					{
						stopBtn.setText("Continue");
						sorting = false;
					}

				else 
					{
						stopBtn.setText("Stop");
						sorting = true;
					}
				
			}
			
		});
		
		
		sort.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(shuffled) {
					if(sorting == false) {
						compare = 0;
						swap = 0;
					}
					sorting = true;
				}
				
			}
		});
		shuffle.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				createList();
				reset();
				compare = 0;
				swap = 0;
				Update();
			}
		});
		speed.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent arg0) {
				spd = (int)speed.getValue();
				msL.setText(spd+" ms");
			}
		});
		mainFrame.add(mainPanel);
		mainFrame.setVisible(true);
		sorting();
	}
	
	
	
	public void updateBtn() {
		switch(curAlg) {
		case 0:{
			bubbleSortBtn.setForeground(myColor.selectedColor);
			mergeSortBtn.setForeground(Color.white);
			heapSortBtn.setForeground(Color.white);
			insertionSortBtn.setForeground(Color.white);
			quickSortBtn.setForeground(Color.white);
			break;
		}
		case 1:{
			bubbleSortBtn.setForeground(Color.white);
			mergeSortBtn.setForeground(Color.white);
			heapSortBtn.setForeground(Color.white);
			insertionSortBtn.setForeground(myColor.selectedColor);
			quickSortBtn.setForeground(Color.white);
			break;
		}
		case 2:{
			bubbleSortBtn.setForeground(Color.white);
			mergeSortBtn.setForeground(Color.white);
			heapSortBtn.setForeground(Color.white);
			insertionSortBtn.setForeground(Color.white);
			quickSortBtn.setForeground(myColor.selectedColor);
			break;
		}
		case 3:{
			bubbleSortBtn.setForeground(Color.white);
			mergeSortBtn.setForeground(Color.white);
			heapSortBtn.setForeground(myColor.selectedColor);
			insertionSortBtn.setForeground(Color.white);
			quickSortBtn.setForeground(Color.white);
			break;
		}case 4:{
			bubbleSortBtn.setForeground(Color.white);
			mergeSortBtn.setForeground(myColor.selectedColor);
			heapSortBtn.setForeground(Color.white);
			insertionSortBtn.setForeground(Color.white);
			quickSortBtn.setForeground(Color.white);
			break;
		}
		
		}
		updateInfor();
	}
	
	public void initButton(JRadioButton btn,String name,int type ) {
		
		btn.setText(name);
		btn.setForeground(myColor.textColor);
		btn.setFocusable(false);
		btn.setBackground(myColor.backgroundColor);
		btn.setForeground(myColor.textColor);
		btn.setFont(new Font("Arial", Font.BOLD, 15));
		btn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				curAlg = type;
				updateBtn();
			}
			
		});
		btn.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				
				
			}

			@Override
			public void mousePressed(MouseEvent e) {
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				
			
			}

			@Override
			public void mouseExited(MouseEvent e) {
				
			}
			
		});
		
		
	}
	
	

	public void sorting() {
		if(sorting == true) stopBtn.setText("Stop");
		else stopBtn.setText("Continue");
		if(sorting) {
			try {
				switch(curAlg) {	
					case 0:
						algorithm.bubbleSort();
						break;
					case 1:
						algorithm.insertionSort(0, length);
						break;
					case 2:
						algorithm.quickSort(0,length-1);
						break;
					case 3:
						algorithm.heapSort();
						break;
					case 4:
						algorithm.mergeSort(0,length-1);
						break;
			
				
					default:
		
						break;
				}
			} catch(IndexOutOfBoundsException e) {}	
		}
		reset();	
		pause();	
	}
	
	
	public void pause() {
		int i = 0;
		while(!sorting) {	
			i++;
			if(i > 100)
				i = 0;
			try {
				Thread.sleep(1);
			} catch(Exception e) {}
		}
		sorting();	
	}
	
	
	public void reset() {
		sorting = false;
		myCanvas.setCurrent(-1);
		myCanvas.setCheck(-1);
		myCanvas.setSecondCheck(-1);
		if(sorting==false) stopBtn.setText("Continue");
		if(!algorithm.checkSorted()) Update();
		else {
			myCanvas.setcapacity(length);
			myCanvas.setSort(algorithm.checkSorted());
			compareL.setText("Comparisons : " + compare);
			accessL.setText("Swaps : " + swap);
		}
	}
	
	public void delay() {
		try {
			Thread.sleep(spd);
		} catch(Exception e) {}
	}
	
	public void Update() {	
		myCanvas.setcapacity(length);
		myCanvas.setSort(algorithm.checkSorted());
		myCanvas.repaint();
		compareL.setText("Comparisons : " + compare);
		accessL.setText("Swaps : " + swap);
	}
	
	public static void main(String[] args) {
		new Main();
	}

	
	public void updateInfor() {
		bestCase.setText("Best Case: "+ infor.getBestTimeComplexity(curAlg));
		worstCase.setText("Worst Case: "+ infor.getWorstTimeComplexity(curAlg));
		averageCase.setText("Average Case: " + infor.getAverageTimeComplexity(curAlg));
		space.setText("Space: " + infor.getSpaceComplexity(curAlg));
	}
	

	
	class SortingAlgorithms {
		
		
		public void insertionSort(int start, int end) {
			int j=0;
			for(int i = start+1; i <= end; i++) {
				
				myCanvas.setCurrent(i);
				j = i;
				while(list[j] < list[j-1] && sorting ) {
					swap(j,j-1);
					compare++;
					if(j > start+1) j--;
					myCanvas.setCheck(j);
					Update();
					delay();
					if(j == start) break;
				}
				compare+=j-start;
			}
		}
		
		
		public void bubbleSort() {
			int j;
			int c = 0;
			while(c<length && sorting) {
					j=0;
					while(j<length-c-1 && sorting) {
						myCanvas.setCurrent(j);
						myCanvas.setCheck(j+1);
						if(list[j]>list[j+1]) {
							swap(j,j+1);
							
						}
						compare++;
						Update();
						delay();
						j++;
					}
					c++;
			}
		}
	
		
		
		public void heapSort() {
			heapify(length);
			int end = length-1;
			while(end > 0 && sorting) {
				myCanvas.setCurrent(end);
				myCanvas.setCheck(0);
				swap(end,0);
				end--;
				siftDown(0,end);
				Update();
				delay();
			}
		}
		
		public void heapify(int n) {
			int start = iParent(n-1);
			while(start >= 0 && sorting) {
				siftDown(start, n-1);
				start--;
				Update();
				delay();
			}
		}
		
		public void siftDown(int start, int end) {
			int root = start;
			while(iLeftChild(root) <= end && sorting) {
				int child = iLeftChild(root);
				int swap = root;
				
				if(list[swap] < list[child]) {
					myCanvas.setCheck(swap);
					myCanvas.setSecondCheck(child);
					swap = child;
				} 
		
				if(child+1 <= end && list[swap] < list[child+1]) {
					myCanvas.setCheck(swap); 
					myCanvas.setSecondCheck(child+1); 
					swap = child+1;
				} 
			
				if(swap == root) {
					myCanvas.setCheck(swap);
					myCanvas.setSecondCheck(root);
					return;
				} else {
					myCanvas.setCheck(swap);
					myCanvas.setSecondCheck(root);
					swap(root,swap);	
					root = swap;
				}
				compare+=3;	
				Update();
				delay();
			}
		}
		
		public int iParent(int i) { return ((i-1)/2); }
		public int iLeftChild(int index) { 
			return 2*index + 1; 
		}
		
		public void quickSort(int lo, int hi) {
			if(!sorting)
				return;
		
			if(lo < hi) {
				int p = partition(lo,hi);
				quickSort(lo,p-1);
				quickSort(p+1, hi);
			}
		}
		
		public int partition(int lo, int hi) {
			int pivot = list[hi];
			myCanvas.setCurrent(hi);
			int i = lo - 1;
			for(int j = lo; j < hi; j++) {
				myCanvas.setCheck(j);
				if(!sorting)
					break;
				if(list[j] < pivot) {
					i++;
					myCanvas.setSecondCheck(i);
					swap(i,j);	
				}
				compare++;
				Update();
				delay();
			}
			swap(i+1,hi);
			Update();
			delay();
			return i+1;
		}
		
		void merge(int l, int m, int r)
	    {
	        int n1 = m - l + 1;
	        int n2 = r - m;
	 
	        int L[] = new int [n1];
	        int R[] = new int [n2];
	 
	        for (int i=0; i<n1; i++) {
	            L[i] = list[l + i];	
	        }
	        for (int j=0; j<n2; j++) {
	            R[j] = list[m + 1+ j];	
	        }
	        int i = 0, j = 0;

	        int k = l;
	        while (i < n1 && j < n2 && sorting) {
	        	
	            if (L[i] <= R[j]) {
	                list[k] = L[i];	
	                i++;
	            } else {
	                list[k] = R[j];	
	                j++;
	            }
	            compare++;
	            Update();
	            delay();
	            k++;
	        }

	        while (i < n1 && sorting) {
	            list[k] = L[i];	
	            i++;
	            k++;
	            compare++;
	            Update();
	            delay();
	        }

	        while (j < n2 && sorting) {
	            list[k] = R[j];	
	            j++;
	            k++;
	            compare++;
	            Update();
	            delay();
	        }
	    }

		
		
		
	    public void mergeSort(int l, int r) {
	        if (l < r) {
	        	compare++;
	            int m = (l+r)/2;
	            mergeSort(l, m);
	            mergeSort(m+1, r);
	            merge(l, m, r);
	        }
	    }

	    public int getMax(int n) {
	    	int mx = list[0];
	    	for(int i = 1; i < n; i++) {
	    		if(list[i] > mx)
	    			mx = list[i];
	    		compare++;	
	    	}
	    	return mx;
	    }

		public void swap(int i1, int i2) {
			int temp = list[i1];	
			list[i1] = list[i2];	
			list[i2] = temp;	
			swap++;
		}
		
		public boolean checkSorted() {
			for(int i = 0; i < length-1; i++) {
				if(list[i] > list[i+1]) {	
					return false;
				}
			}
			return true;
		}
	}
	
}