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

	boolean isLeft() {
		return this.parent == null || this.parent.left == this;
	}

	public MapNode(T key, E value, MapNode<T, E> parent) {
		this.key = key;
		if (key == null) {
			this.left = null;
			this.right = null;
			this.value = null;
		} else {
			this.left = new MapNode<T, E>(null, null, this);
			this.left.color = Color.BLACK;
			this.right = new MapNode<T, E>(null, null, this);
			this.right.color = Color.BLACK;
			this.value = value;
		}
		this.parent = parent;
	}
}

public class Map<T extends Comparable<T>, E> {
	private MapNode<T, E> root;

	private void rightRotate(MapNode<T, E> g) {
		MapNode<T, E> p = g.left, z = p.left;
		boolean l = g.isLeft();
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
		boolean l = g.isLeft();
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

	// returns older value without overwriting if key already exists
	// else returns 'value'
	// true if new insertion, else false
    public Pair<E, Boolean> insert(T key, E value) {
		if (this.root == null) {
			this.root = new MapNode<T, E>(key, value, null);
			this.root.color = Color.BLACK;
			return new Pair<E, Boolean>(value, true);
		}

		MapNode<T, E> z = this.root;
		while (z.key != null) {
			int comp = z.key.compareTo(key);
			if (comp > 0) z = z.left;
			else if (comp < 0) z = z.right;
			else {
				return new Pair<E, Boolean>(z.value, false);
			}
		}

		boolean zl = z.isLeft();
		z = new MapNode<T, E>(key, value, z.parent);
		if (zl) z.parent.left = z;
		else z.parent.right = z;

		z.color = Color.RED;

		MapNode<T, E> p = null, u = null, g = null;
		while (z != this.root) {
			p = z.parent;
			g = z.parent;
			if (p.color == Color.BLACK) return new Pair<E, Boolean>(value, true);
			if (p.isLeft()) u = g.right;
			else u = g.left;
			if (u.color == Color.BLACK) break;
			p.color = Color.BLACK;
			u.color = Color.BLACK;
			g.color = Color.RED;
			z = g;
		}

		if (z == this.root) {
			z.color = Color.BLACK;
			return new Pair<E, Boolean>(value, true);
		}

		if (z.isLeft() && p.isLeft()) {
			rightRotate(g);
			p.color = Color.BLACK;
			g.color = Color.RED;
		} else if (!z.isLeft() && p.isLeft()) {
			leftRotate(p);
			rightRotate(g);
			z.color = Color.BLACK;
			g.color = Color.RED;
		} else if (!z.isLeft() && !p.isLeft()) {
			leftRotate(g);
			p.color = Color.BLACK;
			g.color = Color.RED;
		} else {
			rightRotate(p);
			leftRotate(g);
			z.color = Color.BLACK;
			g.color = Color.RED;
		}

		return new Pair<E, Boolean>(value, true);
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

    private void inOrderTraverse(MapNode<T, E> node, ArrayList<E> res) {
    	if (node.key == null) return;
    	inOrderTraverse(node.left, res);
    	res.add(node.value);
    	inOrderTraverse(node.right, res);
    }

    public ArrayList<E> getValues() {
    	ArrayList<E> res = new ArrayList<E>();
    	inOrderTraverse(this.root, res);
    	return res;
    }
}