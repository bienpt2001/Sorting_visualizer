import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;

public class MakeCanvas extends Canvas {
	public static final long serialVersionUID = 2L;

	private VisualizerProvider listener;

	public MakeCanvas(VisualizerProvider listener) {
		super();
		this.listener = listener;
	}

	public void paint(Graphics g) {
		super.paint(g);
		clear(g);

		listener.onDrawArray();
	}

	public void clear(Graphics g) {
		g.setColor(new Color(62, 62, 62));
		g.fillRect(0, 0, getWidth(), getHeight());
	}

	public interface VisualizerProvider {
		void onDrawArray();
	}
}