class Edge implements EdgeInterface {
	public Point[] endPoints;

	public Edge(Point p1, Point p2) {
		endPoints = new Point[2];
		endPoints[0] = p1;
		endPoints[1] = p2;
	}

	public PointInterface[] edgeEndPoints() {
		PointInterface[] r = new PointInterface[2];
		r[0] = endPoints[0];
		r[1] = endPoints[1];
		return r;
	}
}