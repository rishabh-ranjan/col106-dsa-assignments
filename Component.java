import Util.ArrayList;

public class Component {
	public ArrayList<Triangle> faceList;
	public int index;
	public ArrayList<Point> vertexList;

	public Component(int ind) {
		faceList = new ArrayList<Triangle>();
		index = ind;
	}

	public void add(Triangle t) {
		faceList.add(t);
	}

	public int size() {
		return faceList.size();
	}

	public Triangle get(int i) {
		return faceList.get(i);
	}

	public Point centroid() {
		double ax = 0, ay = 0, az = 0;
		double ctr = 0;
		for (int j = 0; j < this.size(); ++j) {
			this.get(j).discovered = false;
		}
		Triangle s = this.get(0);
		ArrayList<Triangle> q = new ArrayList<Triangle>();
		q.add(s);
		s.discovered = true;
		int f = 0;
		while (f < q.size()) {
			Triangle t = q.get(f++);
			for (int j = 0; j < 3; ++j) {
				Point p = t.vertices.get(j);
				int d = p.faceNeighbors.size();
				ax += p.x/d;
				ay += p.y/d;
				az += p.z/d;
				ctr += 1.0/d;
			}
			for (int j = 0; j < t.faceNeighbors.size(); ++j) {
				Triangle ft = t.faceNeighbors.get(j);
				if (!ft.discovered) {
					q.add(ft);
					ft.discovered = true;
				}
			}
		}
		return new Point((float)(ax/ctr), (float)(ay/ctr), (float)(az/ctr));
	}

	public void fillVertexList() {
		vertexList = new ArrayList<Point>();
		for (int i = 0; i < faceList.size(); ++i) {
			Triangle t = faceList.get(i);
			for (int j = 0; j < 3; ++j) {
				Point p = t.vertices.get(j);
				if (p.faceNeighbors.get(0) == t) {
					vertexList.add(p);
				}
			}
		}
	}
}