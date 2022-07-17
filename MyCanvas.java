import java.awt.*;

import javax.swing.JPanel;
import java.util.Arrays;
@SuppressWarnings("serial")
public class MyCanvas extends JPanel {
	
	private static final int cWidth = 900,cHeight = 500;
	private int current= -1,check = -1,secondcheck = -1;
	private int[] arr,tempArr;
	private int width,height,capac=10;
	private boolean isSort = false;
	private MyColor myColor = new MyColor();
	MyCanvas(int[] arr){
		this.setBackground(new Color(62,62,62));
		this.arr = arr;
	}
	
	public void setSecondCheck(int value) {
		this.secondcheck = value;
	}
	
	
	public void setCapac(int value) {
		this.capac = value;
	}

	public void setCurrent(int value) {
		this.current = value;
	}
	
	public int getCheck() {
		return this.check;
	}
	
	public void setCheck(int value) {
		this.check = value;
	}
	
	public int getCurrent() {
		return this.current;
	}
	
	public void setSort(boolean check) {
		this.isSort = check;
	}
	
	public void paintComponent(Graphics g){
		width = cWidth/capac;
		
		super.paintComponent(g);
		if(isSort) {
			for(int i=0;i<capac;i++) {
				height = arr[i];
				g.setColor(myColor.textColor);
				g.drawString(String.valueOf(arr[i]), i*(width) + (int)(width - 10)/2 - 2, 500-height - 10);
				g.setFont(new Font("Arial",Font.PLAIN,10));
				g.setColor(myColor.rainbowColor[i%7]);
				g.fillRect(i*(width)+5, cHeight-height, width-10, height);
				g.drawRect(i*(width)+5, cHeight-height, width-10, height);
			}
		}
		else {
			for(int i=0;i<capac;i++) {
				height = arr[i];
				g.setColor(myColor.textColor);
				g.drawString(String.valueOf(arr[i]),i*(width) + (int)(width - 10)/2 - 2, 500-height - 10);
				g.setFont(new Font("Arial",Font.PLAIN,10));
				g.setColor(myColor.barColor);
				if(i==current) g.setColor(myColor.currentBarColor);
				else if(i==check) g.setColor(Color.red);
				else if(i == secondcheck) g.setColor(Color.blue);
				g.fillRect(i*(width)+5, cHeight-height, width-10, height);
				g.setColor(myColor.barColor);
				g.drawRect(i*(width)+5, cHeight-height, width-10, height);	
			}
		}
	}
	
	
}
