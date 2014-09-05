import java.awt.Polygon;
import java.awt.geom.Line2D;
import java.awt.geom.PathIterator;
import java.awt.geom.Point2D;


public class PSPolygon extends Polygon {
	public static int POLY_VERTEX = 0;
	public static int POLY_EDGE = 1;
	public static int POLY_FACE = 2;
	public static int POLY_NONE;
	public int width;
	
	public PSPolygon() {
		super();
		width = 0;
	}
	
	public PSPolygon(int[] xpoints, int[] ypoints, int npoints) {
		super(xpoints, ypoints, npoints);
		width = 0;
	}
	
	public PSPolygon(int w) {
		super();
		width = w;
	}
	
	public PSPolygon(int[] xpoints, int[] ypoints, int npoints, int w) {
		super(xpoints, ypoints, npoints);
		width = w;
	}
	
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
}
