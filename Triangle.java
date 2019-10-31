import Util.UnorderedTriple;
import Util.ArrayList;

public class Triangle implements TriangleInterface {

	public static boolean isValid(Point p1, Point p2, Point p3) {
		double a = Point.distance(p1, p2);
		double b = Point.distance(p2, p3);
		double c = Point.distance(p3, p1);
		if (a + b <= c) return false;
		if (b + c <= a) return false;
		if (c + a <= b) return false;
		return true;
	}

	public UnorderedTriple<Point> vertices;
	public UnorderedTriple<Edge> edges;
	public ArrayList<Triangle> faceNeighbors;

	public Triangle(Point p1, Point p2, Point p3, Edge e1, Edge e2, Edge e3) {
		vertices = new UnorderedTriple<Point>(p1, p2, p3);
		edges = new UnorderedTriple<Edge>(e1, e2, e3);
		faceNeighbors = new ArrayList<Triangle>();
	}

	public PointInterface[] triangle_coord() {
		PointInterface[] r = new PointInterface[3];
		for (int i = 0; i < 3; ++i) r[i] = vertices.get(i);
		return r;
	}
}