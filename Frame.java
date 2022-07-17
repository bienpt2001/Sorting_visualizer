import javax.swing.JFrame;

public class Frame extends JFrame {
	public Frame(){
		this.setSize(1280,720);
		this.setTitle("Visual Sort");
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.getContentPane().setLayout(null);
	}
}