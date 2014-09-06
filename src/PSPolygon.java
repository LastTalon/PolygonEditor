import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class PSPolygon {
	public static final int POLY_NONE = 0;
	public static final int POLY_VERTEX = 1;
	public static final int POLY_EDGE = 2;
	public static final int POLY_FACE = 3;
	
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
	
	private boolean intersectPolygon(Point2D p) {
		return false;
	}
	
	private Line2D intersectLine(Point2D p) {
		return null;
	}
	
	private Point2D intersectPoint(Point2D p) {
		Iterator<Point2D> itr = points.iterator();
		Point2D temp;
		while (itr.hasNext()) {
			temp = itr.next();
			if (p.distance(temp) <= width)
				return temp;
		}
		return null;
	}
	
	public int intersects(Point2D p) {
		if (intersectPoint(p) != null)
			return POLY_VERTEX;
		if (intersectLine(p) != null)
			return POLY_EDGE;
		if (intersectPolygon(p))
			return POLY_FACE;
		return POLY_NONE;
	}
	
	/*
	public int intersect(Point2D p) {
		PathIterator itr = getPathIterator(null);
		double[] coords = null;
		while (!itr.isDone()) {
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
	*/
}
