import javax.swing.JPanel;
import javax.swing.JButton;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ButtonPanel extends JPanel
{
	public static final long serialVersionUID = 1L;
	private static final int BUTTON_WIDTH = 200, BUTTON_HEIGHT = 80;
	private JButton[] btns;
	
	private SortButtonListener listener;
	private int number = 5;

	public ButtonPanel(SortButtonListener listener)
	{
		super();

		this.listener = listener;
		btns = new JButton[number];
		for (int i = 0; i < btns.length; i++) btns[i] = new JButton();

		initButtons(btns[0], "New Array", 0);
		initButtons(btns[1], "Bubble Sort", 1);
		initButtons(btns[2], "Selection Sort", 2);
		initButtons(btns[3], "Insertion Sort", 3);
		initButtons(btns[4], "Quick Sort", 4);

		// add button to the panel
		setLayout(null);
		for (int i = 0; i < btns.length; i++)
		{
			
			btns[i].setBounds(20, 40 + (BUTTON_HEIGHT + 20) * i, BUTTON_WIDTH, BUTTON_HEIGHT);
			add(btns[i]);
		}
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