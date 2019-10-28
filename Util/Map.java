package Util;

enum Color {
	RED, BLACK;
}

class MapNode<T, E> {
	T key;
	E value;
	MapNode<T, E> left;
	MapNode<T, E> right;
	MapNode<T, E> parent;
	Color color;
	boolean isLeft;

	public MapNode(T key, E value, MapNode<T, E> parent, boolean isLeft) {
		this.key = key;
		if (key == null) {
			this.left = null;
			this.right = null;
			this.value = null;
		} else {
			this.left = new MapNode<T, E>(null, null, this, true);
			this.left.color = Color.BLACK;
			this.right = new MapNode<T, E>(null, null, this, false);
			this.right.color = Color.BLACK;
			this.value = value;
		}
		this.parent = parent;
		this.isLeft = isLeft;
	}
}

public class Map<T extends Comparable, E> {
	private MapNode<T, E> root;

	private void rightRotate(MapNode<T, E> g) {
		MapNode<T, E> p = g.left, z = p.left;
		boolean l = g.isLeft;
		MapNode<T, E> t = p.right, y = g.parent;
		p.right = g; g.parent = p;
		g.left = t; t.parent = g;
		if (y != null) {
			p.parent = y;
			if (l) y.left = p;
			else y.right = p;
		} else {
			p.parent = null;
			this.root = p;
		}
	}

	private void leftRotate(MapNode<T, E> g) {
		MapNode<T, E> p = g.right, z = p.right;
		boolean l = g.isLeft;
		MapNode<T, E> t = p.left, y = g.parent;
		p.left = g; g.parent = p;
		g.right = t; t.parent = g;
		if (y != null) {
			p.parent = y;
			if (l) y.left = p;
			else y.right = p;
		} else {
			p.parent = null;
			this.root = p;
		}
	}

    public void insert(T key, E value) {
		if (this.root == null) {
			this.root = new MapNode<T, E>(key, value, null, false);
			this.root.color = Color.BLACK;
			return;
		}

		MapNode<T, E> z = this.root;
		while (z.key != null) {
			int comp = z.key.compareTo(key);
			if (comp > 0) z = z.left;
			else if (comp < 0) z = z.right;
			else {
				return;
			}
		}

		z = new MapNode<T, E>(key, value, z.parent, z.isLeft);
		if (z.isLeft) z.parent.left = z;
		else z.parent.right = z;

		z.color = Color.RED;

		MapNode<T, E> p = null, u = null, g = null;
		while (z != this.root) {
			p = z.parent;
			g = z.parent;
			if (p.color == Color.BLACK) return;
			if (p.isLeft) u = g.right;
			else u = g.left;
			if (u.color == Color.BLACK) break;
			p.color = Color.BLACK;
			u.color = Color.BLACK;
			g.color = Color.RED;
			z = g;
		}

		if (z == this.root) {
			z.color = Color.BLACK;
			return;
		}

		if (z.isLeft && p.isLeft) {
			rightRotate(g);
			p.color = Color.BLACK;
			g.color = Color.RED;
		} else if (!z.isLeft && p.isLeft) {
			leftRotate(p);
			rightRotate(g);
			z.color = Color.BLACK;
			g.color = Color.RED;
		} else if (!z.isLeft && !p.isLeft) {
			leftRotate(g);
			p.color = Color.BLACK;
			g.color = Color.RED;
		} else {
			rightRotate(p);
			leftRotate(g);
			z.color = Color.BLACK;
			g.color = Color.RED;
		}
    }

    public E get(T key) {
    	if (this.root == null) return null;
    	MapNode<T, E> z = this.root;
    	while (z.key != null) {
    		int comp = z.key.compareTo(key);
    		if (comp > 0) z = z.left;
    		else if (comp < 0) z = z.right;
    		else break;
    	}
    	return z.value;
    }
}