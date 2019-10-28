package Util;

class SetNode<T> {
	T key;
	SetNode<T> left;
	SetNode<T> right;
	SetNode<T> parent;
	Color color;
	boolean isLeft;

	public SetNode(T key, SetNode<T> parent, boolean isLeft) {
		this.key = key;
		if (key == null) {
			this.left = null;
			this.right = null;
		} else {
			this.left = new SetNode<T>(null, this, true);
			this.left.color = Color.BLACK;
			this.right = new SetNode<T>(null, this, false);
			this.right.color = Color.BLACK;
		}
		this.parent = parent;
		this.isLeft = isLeft;
	}
}

public class Set<T extends Comparable> {
	private SetNode<T> root;

	private void rightRotate(SetNode<T> g) {
		SetNode<T> p = g.left, z = p.left;
		boolean l = g.isLeft;
		SetNode<T> t = p.right, y = g.parent;
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

	private void leftRotate(SetNode<T> g) {
		SetNode<T> p = g.right, z = p.right;
		boolean l = g.isLeft;
		SetNode<T> t = p.left, y = g.parent;
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

    public void insert(T key) {
		if (this.root == null) {
			this.root = new SetNode<T>(key, null, false);
			this.root.color = Color.BLACK;
			return;
		}

		SetNode<T> z = this.root;
		while (z.key != null) {
			int comp = z.key.compareTo(key);
			if (comp > 0) z = z.left;
			else if (comp < 0) z = z.right;
			else {
				return;
			}
		}

		z = new SetNode<T>(key, z.parent, z.isLeft);
		if (z.isLeft) z.parent.left = z;
		else z.parent.right = z;

		z.color = Color.RED;

		SetNode<T> p = null, u = null, g = null;
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

    private void inOrderTraverse(SetNode<T> node, ArrayList<T> res) {
    	if (node.key == null) return;
    	inOrderTraverse(node.left, res);
    	res.add(node.key);
    	inOrderTraverse(node.right, res);
    }

    public ArrayList<T> getElements() {
    	ArrayList<T> res = new ArrayList<T>();
    	inOrderTraverse(this.root, res);
    	return res;
    }
}