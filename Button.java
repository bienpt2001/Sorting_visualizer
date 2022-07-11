import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.plaf.ColorUIResource;

import javafx.scene.paint.Color;


public class Button extends JPanel
{
	public static final long serialVersionUID = 1L;
	private static final int BUTTON_WIDTH = 200, BUTTON_HEIGHT = 80;
	private JButton[] btns;
	
	private SortButtonListener listener;
	private int number = 5;

	public Button(SortButtonListener listener)
	{
		super();
		this.listener = listener;
		btns = new JButton[number];
		for (int i = 0; i < btns.length; i++) btns[i] = new JButton();

		initButtons(btns[0], "Random", 0);
		initButtons(btns[1], "Bubble Sort", 1);
		initButtons(btns[2], "Selection Sort", 2);
		initButtons(btns[3], "Heap Sort", 3);
		initButtons(btns[4], "Quick Sort", 4);

		// add button to the panel
		setLayout(null);
		for (int i = 0; i < btns.length; i++)
		{
			
			btns[i].setBounds(20, 40 + (BUTTON_HEIGHT + 20) * i, BUTTON_WIDTH, BUTTON_HEIGHT);
			add(btns[i]);
			btns[i].setBackground(ColorUIResource.DARK_GRAY);
		}
		btns[0].setForeground(ColorUIResource.RED);
		btns[1].setForeground(ColorUIResource.ORANGE);
		btns[2].setForeground(ColorUIResource.YELLOW);
		btns[3].setForeground(ColorUIResource.magenta);
		btns[4].setForeground(ColorUIResource.green);
		
	}
	


	private void initButtons(JButton button, String name, int id)
	{
		button.setText(name);
		button.setFocusable(false);
		button.setFont(new Font("Arial",Font.BOLD,20));
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				listener.sortButtonClicked(id);
			}
			
		});
		
	}


	public interface SortButtonListener
	{
		void sortButtonClicked(int id);
	}
}