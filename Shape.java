import Util.OrderedTriple;
import Util.UnorderedPair;
import Util.UnorderedTriple;
import Util.Map;
import Util.Pair;
import Util.ArrayList;
import Util.Sort;

public class Shape implements ShapeInterface {
	Map<OrderedTriple<Float>, Point> vertexMap;
	Map<UnorderedPair<Point>, Edge> edgeMap;
	Map<UnorderedTriple<Point>, Triangle> faceMap;
	ArrayList<Edge> edgeList;
	ArrayList<Component> componentList;

	public Shape() {
		vertexMap = new Map<OrderedTriple<Float>, Point>();
		edgeMap = new Map<UnorderedPair<Point>, Edge>();
		faceMap = new Map<UnorderedTriple<Point>, Triangle>();
		edgeList = new ArrayList<Edge>();
		componentList = new ArrayList<Component>();
	}

	public boolean ADD_TRIANGLE(float[] c) {
		Point p1 = new Point(c[0], c[1], c[2]);
		Point p2 = new Point(c[3], c[4], c[5]);
		Point p3 = new Point(c[6], c[7], c[8]);

		if (!Triangle.isValid(p1, p2, p3)) return false;

		OrderedTriple<Float> t1 = new OrderedTriple<Float>(c[0], c[1], c[2]);
		OrderedTriple<Float> t2 = new OrderedTriple<Float>(c[3], c[4], c[5]);
		OrderedTriple<Float> t3 = new OrderedTriple<Float>(c[6], c[7], c[8]);
		Pair<Point, Boolean> pp1 = vertexMap.insert(t1, p1);
		Pair<Point, Boolean> pp2 = vertexMap.insert(t2, p2);
		Pair<Point, Boolean> pp3 = vertexMap.insert(t3, p3);
		p1 = pp1.first;
		p2 = pp2.first;
		p3 = pp3.first;
		boolean np1 = pp1.second;
		boolean np2 = pp2.second;
		boolean np3 = pp3.second;

		Edge e1 = new Edge(p1, p2);
		Edge e2 = new Edge(p2, p3);
		Edge e3 = new Edge(p3, p1);
		Pair<Edge, Boolean> pe1 = edgeMap.insert(e1.vertices, e1);
		Pair<Edge, Boolean> pe2 = edgeMap.insert(e2.vertices, e2);
		Pair<Edge, Boolean> pe3 = edgeMap.insert(e3.vertices, e3);
		e1 = pe1.first;
		e2 = pe2.first;
		e3 = pe3.first;
		boolean ne1 = pe1.second;
		boolean ne2 = pe2.second;
		boolean ne3 = pe3.second;

		Triangle t = new Triangle(p1, p2, p3, e1, e2, e3);
		Pair<Triangle, Boolean> pt = faceMap.insert(t.vertices, t);
		t = pt.first;
		boolean nt = pt.second;

		if (nt) {
			for (int j = 0; j < e1.faceNeighbors.size(); ++j) {
				Triangle f = e1.faceNeighbors.get(j);
				t.faceNeighbors.add(f);
				f.faceNeighbors.add(t);
			}
			for (int j = 0; j < e2.faceNeighbors.size(); ++j) {
				Triangle f = e2.faceNeighbors.get(j);
				t.faceNeighbors.add(f);
				f.faceNeighbors.add(t);
			}
			for (int j = 0; j < e3.faceNeighbors.size(); ++j) {
				Triangle f = e3.faceNeighbors.get(j);
				t.faceNeighbors.add(f);
				f.faceNeighbors.add(t);
			}
			p1.faceNeighbors.add(t);
			p2.faceNeighbors.add(t);
			p3.faceNeighbors.add(t);
			e1.faceNeighbors.add(t);
			e2.faceNeighbors.add(t);
			e3.faceNeighbors.add(t);
		}

		if (ne1) {
			edgeList.add(e1);
			p1.vertexNeighbors.add(p2);
			p2.vertexNeighbors.add(p1);
			p1.edgeNeighbors.add(e1);
			p2.edgeNeighbors.add(e1);
		}

		if (ne2) {
			edgeList.add(e2);
			p2.vertexNeighbors.add(p3);
			p3.vertexNeighbors.add(p2);
			p2.edgeNeighbors.add(e2);
			p3.edgeNeighbors.add(e2);
		}

		if (ne3) {
			edgeList.add(e3);
			p1.vertexNeighbors.add(p3);
			p3.vertexNeighbors.add(p1);
			p1.edgeNeighbors.add(e3);
			p3.edgeNeighbors.add(e3);
		}

		if (nt) {
			if (ne1 && ne2 && ne3) {
				t.component = new Component(componentList.size());
				t.component.add(t);
				componentList.add(t.component);
			} else {
				Component cmp = null;
				if (!ne1) {
					cmp = e1.faceNeighbors.get(0).component;
				}
				if (!ne2) {
					Component alt = e2.faceNeighbors.get(0).component;
					if (cmp == null) {
						cmp = alt;
					} else {
						if (cmp != alt) {
							if (alt.size() > cmp.size()) {
								Component tmp = alt;
								alt = cmp;
								cmp = tmp;
							}
							for (int i = 0; i < alt.size(); ++i) {
								Triangle at = alt.get(i);
								at.component = cmp;
								cmp.add(at);
							}
							componentList.set(alt.index, componentList.get(componentList.size() - 1));
							componentList.get(alt.index).index = alt.index;
							componentList.pop();
						}
					}
				}
				if (!ne3) {
					Component alt = e3.faceNeighbors.get(0).component;
					if (cmp == null) {
						cmp = alt;
					} else {
						if (cmp != alt) {
							if (alt.size() > cmp.size()) {
								Component tmp = alt;
								alt = cmp;
								cmp = tmp;
							}
							for (int i = 0; i < alt.size(); ++i) {
								Triangle at = alt.get(i);
								at.component = cmp;
								cmp.add(at);
							}
							componentList.set(alt.index, componentList.get(componentList.size() - 1));
							componentList.get(alt.index).index = alt.index;
							componentList.pop();
						}
					}
				}
				t.component = cmp;
				cmp.add(t);
			}
		}

		return true;
	}

	private Triangle findFace(float[] c) {
		Point p1 = new Point(c[0], c[1], c[2]);
		Point p2 = new Point(c[3], c[4], c[5]);
		Point p3 = new Point(c[6], c[7], c[8]);
		UnorderedTriple<Point> ut = new UnorderedTriple<Point>(p1, p2, p3);
		return faceMap.get(ut);
	}

	public TriangleInterface[] NEIGHBORS_OF_TRIANGLE(float[] c) {
		Triangle t = findFace(c);
		if (t == null) return null;
		Sort.sort(t.faceNeighbors);
		TriangleInterface[] r = new TriangleInterface[t.faceNeighbors.size()];
		for (int i = 0; i < r.length; ++i) {
			r[i] = t.faceNeighbors.get(i);
		}
		return r;
	}

	public EdgeInterface[] EDGE_NEIGHBOR_TRIANGLE(float[] c) {
		Triangle t = findFace(c);
		if (t == null) return null;
		EdgeInterface[] r = new EdgeInterface[3];
		for (int i = 0; i < r.length; ++i) {
			r[i] = t.edges.get(i);
		}
		return r;
	}

	public PointInterface[] VERTEX_NEIGHBOR_TRIANGLE(float[] c) {
		Triangle t = findFace(c);
		if (t == null) return null;
		PointInterface[] r = new PointInterface[3];
		for (int i = 0; i < r.length; ++i) {
			r[i] = t.vertices.get(i);
		}
		return r;
	}

	public TriangleInterface[] EXTENDED_NEIGHBOR_TRIANGLE(float[] c) {
		Triangle t = findFace(c);
		if (t == null) return null;
		ArrayList<Triangle> a = new ArrayList<Triangle>();
		for (int i = 0; i < 3; ++i) {
			Point p = t.vertices.get(i);
			for (int j = 0; j < p.faceNeighbors.size(); ++j) {
				Triangle f = p.faceNeighbors.get(j);
				if (!t.isProperNeighbor(f)) {
					a.add(f);
				}
			}
		}
		for (int i = 0; i < 3; ++i) {
			Edge e = t.edges.get(i);
			for (int j = 0; j < e.faceNeighbors.size(); ++j) {
				Triangle f = e.faceNeighbors.get(j);
				if (t != f) {
					a.add(f);
				}
			}
		}
		if (a.size() == 0) return null;
		Sort.sort(a);
		TriangleInterface[] r = new TriangleInterface[a.size()];
		for (int i = 0; i < r.length; ++i) {
			r[i] = a.get(i);
		}
		return r;
	}

	private Point findVertex(float[] c) {
		OrderedTriple<Float> ot = new OrderedTriple<Float>(c[0], c[1], c[2]);
		return vertexMap.get(ot);
	}

	public TriangleInterface[] INCIDENT_TRIANGLES(float[] c) {
		Point p = findVertex(c);
		if (p == null) return null;
		// Sort.sort(p.faceNeighbors);
		TriangleInterface[] r = new TriangleInterface[p.faceNeighbors.size()];
		for (int i = 0; i < r.length; ++i) {
			r[i] = p.faceNeighbors.get(i);
		}
		return r;
	}

	public PointInterface[] NEIGHBORS_OF_POINT(float[] c) {
		Point p = findVertex(c);
		if (p == null) return null;
		PointInterface[] r = new PointInterface[p.vertexNeighbors.size()];
		for (int i = 0; i < r.length; ++i) {
			r[i] = p.vertexNeighbors.get(i);
		}
		return r;
	}

	public EdgeInterface[] EDGE_NEIGHBORS_OF_POINT(float[] c) {
		Point p = findVertex(c);
		if (p == null) return null;
		EdgeInterface[] r = new EdgeInterface[p.edgeNeighbors.size()];
		for (int i = 0; i < r.length; ++i) {
			r[i] = p.edgeNeighbors.get(i);
		}
		return r;
	}

	public TriangleInterface[] FACE_NEIGHBORS_OF_POINT(float[] c) {
		Point p = findVertex(c);
		if (p == null) return null;
		// Sort.sort(p.faceNeighbors);
		TriangleInterface[] r = new TriangleInterface[p.faceNeighbors.size()];
		for (int i = 0; i < r.length; ++i) {
			r[i] = p.faceNeighbors.get(i);
		}
		return r;
	}

	private Edge findEdge(float[] c) {
		float[] c1 = new float[3];
		float[] c2 = new float[3];
		c1[0] = c[0];
		c1[1] = c[1];
		c1[2] = c[2];
		c2[0] = c[3];
		c2[1] = c[4];
		c2[2] = c[5];
		Point p1 = findVertex(c1);
		if (p1 == null) return null;
		Point p2 = findVertex(c2);
		if (p2 == null) return null;
		UnorderedPair<Point> up = new UnorderedPair<Point>(p1, p2);
		return edgeMap.get(up);
	}

	public TriangleInterface[] TRIANGLE_NEIGHBOR_OF_EDGE(float[] c) {
		Edge e = findEdge(c);
		if (e == null) return null;
		// Sort.sort(e.faceNeighbors);
		TriangleInterface[] r = new TriangleInterface[e.faceNeighbors.size()];
		for (int i = 0; i < r.length; ++i) {
			r[i] = e.faceNeighbors.get(i);
		}
		return r;
	}

	public int TYPE_MESH() {
		boolean semi = false;
		for (int i = 0; i < edgeList.size(); ++i) {
			int s = edgeList.get(i).faceNeighbors.size();
			if (s > 2) return 3;
			if (s == 1) semi = true;
		}
		if (semi) return 2;
		return 1;
	}

	public int COUNT_CONNECTED_COMPONENTS() {
		return componentList.size();
	}

	public boolean IS_CONNECTED(float[] c1, float[] c2) {
		Triangle t1 = findFace(c1);
		if (t1 == null) return false;
		Triangle t2 = findFace(c2);
		if (t2 == null) return false;
		return t1.component == t2.component;
	}

	public EdgeInterface[] BOUNDARY_EDGES() {
		ArrayList<Edge> a = new ArrayList<Edge>();
		for (int i = 0; i < edgeList.size(); ++i) {
			Edge e = edgeList.get(i);
			if (e.faceNeighbors.size() == 1) {
				a.add(e);
			}
		}
		if (a.size() == 0) return null;
		Sort.sort(a);
		EdgeInterface[] r = new EdgeInterface[a.size()];
		for (int i = 0; i < r.length; ++i) {
			r[i] = a.get(i);
		}
		return r;
	}

	public int MAXIMUM_DIAMETER() {
		Component cmp = null;
		int mx = -1;
		for (int i = 0; i < componentList.size(); ++i) {
			Component c = componentList.get(i);
			if (c.size() > mx) {
				mx = c.size();
				cmp = c;
			}
		}
		mx = 0;
		for (int i = 0; i < cmp.size(); ++i) {
			for (int j = 0; j < cmp.size(); ++j) {
				cmp.get(j).discovered = false;
			}
			Triangle s = cmp.get(i);
			ArrayList<Triangle> q = new ArrayList<Triangle>();
			q.add(s);
			s.discovered = true;
			q.add(null);
			int f = 0;
			int d = 0;
			while (f < q.size()) {
				Triangle t = q.get(f++);
				if (t == null) {
					q.add(null);
					if (q.get(f) == null) break;
					d++;
					if (d > mx) mx = d;
					continue;
				}
				for (int j = 0; j < t.faceNeighbors.size(); ++j) {
					Triangle ft = t.faceNeighbors.get(j);
					if (!ft.discovered) {
						q.add(ft);
						ft.discovered = true;
					}
				}
			}
		}
		return mx;
	}

	public PointInterface[] CENTROID() {
		ArrayList<Point> a = new ArrayList<Point>();
		for (int i = 0; i < componentList.size(); ++i) {
			a.add(componentList.get(i).centroid());
		}
		Sort.sort(a);
		PointInterface[] r = new PointInterface[a.size()];
		for (int i = 0; i < r.length; ++i) {
			r[i] = a.get(i);
		}
		return r;
	}

	public PointInterface CENTROID_OF_COMPONENT(float[] c) {
		Point p = findVertex(c);
		if (p == null) return null;
		PointInterface r = p.faceNeighbors.get(0).component.centroid();
		return r;
	}

	public PointInterface[] CLOSEST_COMPONENTS() {
		if (componentList.size() == 1) return null;
		for (int i = 0; i < componentList.size(); ++i) {
			componentList.get(i).fillVertexList();
		}
		double mn = Double.MAX_VALUE;
		Point pa = null, pb = null;
		for (int i = 0; i < componentList.size(); ++i) {
			Component a = componentList.get(i);
			ArrayList<Point> va = a.vertexList;
			for (int j = i+1; j < componentList.size(); ++j) {
				Component b = componentList.get(j);
				ArrayList<Point> vb = b.vertexList;
				for (int k = 0; k < va.size(); ++k) {
					for (int l = 0; l < vb.size(); ++l) {
						double d = Point.distance(va.get(k), vb.get(l));
						if (d < mn) {
							mn = d;
							pa = va.get(k);
							pb = vb.get(l);
						}
					}
				}
			}
		}
		PointInterface[] r = new PointInterface[2];
		r[0] = pa;
		r[1] = pb;
		return r;
	}
}