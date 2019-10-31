import Util.ArrayList;

class Point implements PointInterface, Comparable<Point> {
	public float x, y, z;
	public ArrayList

	public Point(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
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
		if (x == p.x && y == p.y) {
			return comp(z, p.z);
		} else if (x == p.x) {
			return comp(y, p.y);
		} else {
			return comp(x, p.x);
		}
	}
}