import Util.UnorderedTriple;
import Util.ArrayList;

public class Triangle implements TriangleInterface, Comparable<Triangle> {

	public static boolean isValid(Point p1, Point p2, Point p3) {
		double a = Point.distance(p1, p2);
		double b = Point.distance(p2, p3);
		double c = Point.distance(p3, p1);
		if (a + b <= c) return false;
		if (b + c <= a) return false;
		if (c + a <= b) return false;
		return true;
	}

	static int timer = 0;
	public int insert_time;

	public Component component;

	public UnorderedTriple<Point> vertices;
	public ArrayList<Edge> edges;
	public ArrayList<Triangle> faceNeighbors;

	public Triangle(Point p1, Point p2, Point p3, Edge e1, Edge e2, Edge e3) {
		insert_time = ++timer;
		vertices = new UnorderedTriple<Point>(p1, p2, p3);
		edges = new ArrayList<Edge>();
		edges.add(e1);
		edges.add(e2);
		edges.add(e3);
		faceNeighbors = new ArrayList<Triangle>();
	}

	public String toString() {
		return "Triangle[ " + vertices.first + ", " + vertices.second + ", " + vertices.third + " ]";
	}

	public PointInterface[] triangle_coord() {
		PointInterface[] r = new PointInterface[3];
		for (int i = 0; i < 3; ++i) r[i] = vertices.get(i);
		return r;
	}

	public int compareTo(Triangle t) {
		return insert_time - t.insert_time;
	}
}