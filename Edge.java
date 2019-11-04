import Util.ArrayList;
import Util.UnorderedPair;

class Edge implements EdgeInterface, Comparable<Edge> {
	public UnorderedPair<Point> vertices;
	public ArrayList<Triangle> faceNeighbors;

	public Edge(Point p1, Point p2) {
		vertices = new UnorderedPair<Point>(p1, p2);
		faceNeighbors = new ArrayList<Triangle>();
	}

	public String toString() {
		return "Edge{ " + vertices.first.toString() + ", " + vertices.second.toString() + " }";
	}

	public PointInterface[] edgeEndPoints() {
		PointInterface[] r = new PointInterface[2];
		for (int i = 0; i < 2; ++i) r[i] = vertices.get(i);
		return r;
	}

	public double length() {
		return Point.distance(vertices.get(0), vertices.get(1));
	}

	public int compareTo(Edge e) {
		double l = length(), el = e.length();
		if (l > el) return 1;
		if (l < el) return -1;
		return 0;
	}
}