public class Triangle implements TriangleInterface {
	public Point[] vertices;

	public Triangle(Point p1, Point p2, Point p3) {
		vertices = new Point[3];
		vertices[0] = p1;
		vertices[1] = p2;
		vertices[2] = p3;
	}

	public PointInterface[] triangle_coord() {
		PointInterface[] r = new PointInterface[3];
		r[0] = vertices[0];
		r[1] = vertices[1];
		r[2] = vertices[2];
		return r;
	}
}