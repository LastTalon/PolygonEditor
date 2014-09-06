import java.awt.Polygon;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;


public class PSPolygon {
	public static int POLY_VERTEX = 0;
	public static int POLY_EDGE = 1;
	public static int POLY_FACE = 2;
	public static int POLY_NONE;
	
	public List<Point2D> points;
	public int width;
	
	public PSPolygon() {
		points = new ArrayList<Point2D>();
		width = 0;
	}
	
	public PSPolygon(int[] xpoints, int[] ypoints) {
		points = new ArrayList<Point2D>();
		width = 0;
		int npoints = xpoints.length;
		if (npoints == ypoints.length)
			for (int i = 0; i < npoints; i++)
				points.add(new Point2D.Float(xpoints[i], ypoints[i]));
	}
	
	public PSPolygon(int w) {
		points = new ArrayList<Point2D>();
		width = w;
	}
	
	public PSPolygon(int[] xpoints, int[] ypoints, int w) {
		width = w;
		int npoints = xpoints.length;
		if (npoints == ypoints.length)
			for (int i = 0; i < npoints; i++)
				points.add(new Point2D.Float(xpoints[i], ypoints[i]));
	}
	
	public int intersect(Point2D p) {
		PathIterator itr = getPathIterator(null);
		double[] coords = null;
	)	while (!itr.isDone()) {
			itr.currentSegment(coords);
			if (p.distance(coords[0], coords[1]) <= width)
				return POLY_VERTEX;
			itr.next();
		}
		
		itr = getPathIterator(null);
		coords = null;
		double[] original = null;
		itr.currentSegment(original);
		double[] lastCoords = null;
		int type;
		while(!itr.isDone()) {
			lastCoords[0] = coords[0];
			lastCoords[1] = coords[1];
			type = itr.currentSegment(coords);
			if (type == PathIterator.SEG_LINETO && Line2D.ptLineDist(lastCoords[0], lastCoords[1], coords[0], coords[1], p.getX(), p.getY()) <= width)
				return POLY_EDGE;
			itr.next();
		}
		
		if (contains(p))
			return POLY_FACE;
		
		return POLY_NONE;
	}
}
