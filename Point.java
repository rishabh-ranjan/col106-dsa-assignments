import Util.ArrayList;

class Point implements PointInterface, Comparable<Point> {

	public static double distance(Point a, Point b) {
		return Math.sqrt((a.x-b.x)*(a.x-b.x) + (a.y-b.y)*(a.y-b.y) + (a.z-b.z)*(a.z-b.z));
	}

	public float x, y, z;
	public ArrayList<Point> vertexNeighbors;
	public ArrayList<Edge> edgeNeighbors;
	public ArrayList<Triangle> faceNeighbors;

	public Point(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
		vertexNeighbors = new ArrayList<Point>();
		edgeNeighbors = new ArrayList<Edge>();
		faceNeighbors = new ArrayList<Triangle>();
	}

	public String toString() {
		// for (int i = 0; i < vertexNeighbors.size(); ++i) {
		// 	System.out.println(vertexNeighbors.get(i).x + " "
		// 		+ vertexNeighbors.get(i).y + " " + vertexNeighbors.get(i).z);
		// }
		return "Point( " + x + ", " + y + ", " + z + ", " + vertexNeighbors.size() + ", " + edgeNeighbors.size() + ", " + faceNeighbors.size() + " )";
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public float getZ() {
		return z;
	}

	public float[] getXYZcoordinate() {
		float[] ret = {x, y, z};
		return ret;
	}

	private int comp(float a, float b) {
		if (a > b) return 1;
		if (a < b) return -1;
		return 0;
	}

	public int compareTo(Point p) {
		if (this == p) return 0;
		if (x == p.x && y == p.y) return comp(z, p.z);
		if (x == p.x) return comp(y, p.y);
		return comp(x, p.x);
	}
}