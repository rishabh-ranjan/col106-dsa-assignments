import Util.ArrayList;

public class Component {
	public ArrayList<Triangle> faceList;
	public int index;

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
}