import java.applet.Applet;
import java.awt.Frame;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;


public class PolygonSystem extends Applet {
	private static final long serialVersionUID = 4682884691362790753L;
	private List<PSPolygon> polys;
	
	public PolygonSystem() {
		polys = new ArrayList<PSPolygon>();
	}
	
	public PolygonSystem(List<PSPolygon> list) {
		polys = list;
	}
	
	@Override
	public void init() {
		PMouseListener mListener = new PMouseListener(this);
		addMouseListener(mListener);
		setIgnoreRepaint(true);
		
		// TODO: Add temporary starting polygon until ability to add new polys.
	}
	
	@Override
	public void start() {
		// TODO: Main loop.
	}
	
	@Override
	public void stop() {
		// TODO: Break the loop.
	}
	
	@Override
	public void destroy() {
		// TODO: Decide what needs to be broken down.
	}
	
	@Override
	public void paint(Graphics g) {
		// TODO: Draw polygons. Will need update method later for double buffer.
	}

	public static void main(String[] args) {
		Frame frame = new Frame("Polygon Editor");
		PolygonSystem panel = new PolygonSystem();
		PWindowListener wListener = new PWindowListener(panel);
		
		frame.add(panel);
		frame.addWindowListener(wListener);
		
		frame.setSize(800, 600);
		frame.setLocationByPlatform(true);
		frame.setVisible(true);
		frame.setEnabled(true);
		
		panel.init();
	}

}
