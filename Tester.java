import java.util.ArrayList;
import java.util.Arrays;
import java.io.*;

public class Tester {
	private static void print(Object obj) {
		if (obj instanceof PointInterface) {
			print((PointInterface)obj);
		} else if (obj instanceof EdgeInterface) {
			print((EdgeInterface)obj);
		} else if (obj instanceof TriangleInterface) {
			print((TriangleInterface)obj);
		} else {
			System.out.print(obj);
		}
	}
	private static void println(Object obj) {
		print(obj);
		println();
	}
	private static void print(Object[] obj) {
		if (obj == null) {
			print("null");
			return;
		}

		print("[");
		for (int i = 0; i < obj.length; ++i) {
			print(obj[i]);
			if (i < obj.length - 1) print(", ");
		}
		print("]");
	}
	private static void println(Object[] obj) {
		print(obj);
		println();
	}
	private static void println() {
		System.out.println();
	}
	private static void print(PointInterface p) {
		print("(" + p.getX() + ", " + p.getY() + ", " + p.getZ() + ")");
	}
	private static void print(EdgeInterface e) {
		print(e.edgeEndPoints());
	}
	private static void print(TriangleInterface t) {
		print(t.triangle_coord());
	}
	public static void main(String[] args) throws FileNotFoundException, IOException {
		BufferedReader br = new BufferedReader(new FileReader(args[0]));
		ShapeInterface shape = new Shape();
		String st;
		while ((st = br.readLine()) != null) {
			String[] cmd = st.split(" ");
			// println("cmd : " + Arrays.toString(cmd));
			ArrayList<Float> inp = new ArrayList<>();
			for (int i = 1; i < cmd.length; ++i) {
				inp.add(Float.parseFloat(cmd[i]));
			}
			float[] input = new float[inp.size()];
			for (int i = 0; i < inp.size(); ++i) {
				input[i] = inp.get(i);
			}
			switch (cmd[0]) {
				case "ADD_TRIANGLE":
					println(shape.ADD_TRIANGLE(input));
                    break;
                case "TYPE_MESH":
					println(shape.TYPE_MESH());
                    break;
                case "COUNT_CONNECTED_COMPONENTS":
					println(shape.COUNT_CONNECTED_COMPONENTS());
                    break;
                case "BOUNDARY_EDGES":		
					println(shape.BOUNDARY_EDGES());
                    break;
                case "IS_CONNECTED":
					float [] triangle1 = new float[9]; 
					float [] triangle2 = new float[9]; 
					for (int i = 0; i < 9; ++i) {
						triangle1[i] = input[i];
					}
					for (int i = 9; i < 18; ++i) {
						triangle2[i-9]=input[i];
					}
					println(shape.IS_CONNECTED(triangle1, triangle2));		
                    break;
            	case "NEIGHBORS_OF_POINT":
					println(shape.NEIGHBORS_OF_POINT(input));
					break;
                case "NEIGHBORS_OF_TRIANGLE":
					println(shape.NEIGHBORS_OF_TRIANGLE(input));
					break;
                case "INCIDENT_TRIANGLES":
					println(shape.INCIDENT_TRIANGLES(input));
					break;
                case "VERTEX_NEIGHBOR_TRIANGLE":
					println(shape.VERTEX_NEIGHBOR_TRIANGLE(input));
	               	break;
                case "EXTENDED_NEIGHBOR_TRIANGLE":
					println(shape.EXTENDED_NEIGHBOR_TRIANGLE(input));
					break;
		        case "MAXIMUM_DIAMETER":
					println(shape.MAXIMUM_DIAMETER());
                    break;
                case "EDGE_NEIGHBOR_TRIANGLE":
					println(shape.EDGE_NEIGHBOR_TRIANGLE(input));
                    break;
                case "FACE_NEIGHBORS_OF_POINT":
					println(shape.FACE_NEIGHBORS_OF_POINT(input));
                    break;
                case "EDGE_NEIGHBORS_OF_POINT":
					println(shape.EDGE_NEIGHBORS_OF_POINT(input));
                    break;
                case "TRIANGLE_NEIGHBOR_OF_EDGE":
					println(shape.TRIANGLE_NEIGHBOR_OF_EDGE(input));
	          	case "CENTROID":
					println(shape.CENTROID());
                    break;
                case "CENTROID_OF_COMPONENT":
					println(shape.CENTROID_OF_COMPONENT(input));
                    break;
                case "CLOSEST_COMPONENTS":
				    println(shape.CLOSEST_COMPONENTS());
					break;
			}
		}
	}
}