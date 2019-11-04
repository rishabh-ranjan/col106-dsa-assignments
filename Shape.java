import Util.OrderedTriple;
import Util.UnorderedPair;
import Util.UnorderedTriple;
import Util.Map;
import Util.Pair;
import Util.ArrayList;

public class Shape implements ShapeInterface {
	Map<OrderedTriple<Float>, Point> vertexMap;
	Map<UnorderedPair<Point>, Edge> edgeMap;
	Map<UnorderedTriple<Point>, Triangle> faceMap;

	public Shape() {
		vertexMap = new Map<OrderedTriple<Float>, Point>();
		edgeMap = new Map<UnorderedPair<Point>, Edge>();
		faceMap = new Map<UnorderedTriple<Point>, Triangle>();
	}

	public boolean ADD_TRIANGLE(float[] triangle_coord) {
		float[] c = triangle_coord;
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
		System.out.println("Trying to insert edges:");
		System.out.println(e1);
		System.out.println(e2);
		System.out.println(e3);
		
		Pair<Edge, Boolean> pe1 = edgeMap.insert(e1.vertices, e1);
		Pair<Edge, Boolean> pe2 = edgeMap.insert(e2.vertices, e2);
		Pair<Edge, Boolean> pe3 = edgeMap.insert(e3.vertices, e3);
		System.out.println("After inserting edges:");
		ArrayList<Edge> edges = edgeMap.getValues();
		for (int i = 0; i < edges.size(); ++i) {
			System.out.println(edges.get(i));
		}
		e1 = pe1.first;
		e2 = pe2.first;
		e3 = pe3.first;
		boolean ne1 = pe1.second;
		boolean ne2 = pe2.second;
		boolean ne3 = pe3.second;
		System.out.println(p1 + " " + p2 + " " + p3);
		System.out.println(ne1 + " " + ne2 + " " + ne3);

		Triangle t = new Triangle(p1, p2, p3, e1, e2, e3);
		Pair<Triangle, Boolean> pt = faceMap.insert(t.vertices, t);
		t = pt.first;
		boolean nt = pt.second;

		System.err.println("nt = " + nt);
		if (nt) {
			for (int j = 0; j < e1.faceNeighbors.size(); ++j) {
				t.faceNeighbors.add(e1.faceNeighbors.get(j));
			}
			for (int j = 0; j < e2.faceNeighbors.size(); ++j) {
				t.faceNeighbors.add(e2.faceNeighbors.get(j));
			}
			for (int j = 0; j < e3.faceNeighbors.size(); ++j) {
				t.faceNeighbors.add(e3.faceNeighbors.get(j));
			}
			p1.faceNeighbors.add(t);
			p2.faceNeighbors.add(t);
			p3.faceNeighbors.add(t);
			e1.faceNeighbors.add(t);
			e2.faceNeighbors.add(t);
			e3.faceNeighbors.add(t);
		}

		if (ne1) {
			p1.vertexNeighbors.add(p2);
			p2.vertexNeighbors.add(p1);
			p1.edgeNeighbors.add(e1);
			p2.edgeNeighbors.add(e1);
		}

		if (ne2) {
			p2.vertexNeighbors.add(p3);
			p3.vertexNeighbors.add(p2);
			p2.edgeNeighbors.add(e2);
			p3.edgeNeighbors.add(e2);
		}

		if (ne3) {
			p1.vertexNeighbors.add(p3);
			p3.vertexNeighbors.add(p1);
			p1.edgeNeighbors.add(e3);
			p3.edgeNeighbors.add(e3);
		}

		return true;
	}
}