package corelogic;

import java.util.LinkedList;

public class MoveTree {
    private corelogic.MoveNode root = null;

    public corelogic.MoveNode insert(corelogic.Move move) {
        if (this.root == null) {
            this.root = new corelogic.MoveNode(move);
            return this.root;
        }
        return this.insert(this.root, move);
    }

    public corelogic.MoveNode insert(corelogic.MoveNode parent, corelogic.Move move) {
        return parent.insert(move);
    }

    public boolean contains(corelogic.Move check) {
        for (corelogic.MoveNode n : this.getRoot().getChildNodes()) {
            if (!this.contains(check, n)) continue;
            return true;
        }
        return false;
    }

    private boolean contains(corelogic.Move check, corelogic.MoveNode r) {
        if (r.getMove().getFrom().equals(check.getFrom()) && r.getMove().getDestination().equals(check.getDestination()) || r.getMove().getFrom().equals(check.getDestination()) && r.getMove().getDestination().equals(check.getFrom())) {
            return true;
        }
        for (corelogic.MoveNode n : r.getChildNodes()) {
            if (!this.contains(check, n)) continue;
            return true;
        }
        return false;
    }

    public corelogic.MoveNode getRoot() {
        return this.root;
    }

    public LinkedList<corelogic.MoveNode> getLeaves() {
        LinkedList<corelogic.MoveNode> leaves = new LinkedList<corelogic.MoveNode>();
        this.getLeaves(leaves, this.getRoot());
        return leaves;
    }

    private void getLeaves(LinkedList<corelogic.MoveNode> leaves, corelogic.MoveNode root) {
        LinkedList<corelogic.MoveNode> nodes = root.getChildNodes();
        if (nodes.isEmpty()) {
            leaves.add(root);
            return;
        }
        for (corelogic.MoveNode m : nodes) {
            this.getLeaves(leaves, m);
        }
    }

    public String toString() {
        return "MoveTree{root=" + this.root + '}';
    }

    public static void main(String[] args) {
        MoveTree tree = new MoveTree();
        tree.insert(new corelogic.Move(1, 2, 3, 0));
        tree.insert(new corelogic.Move(3, 0, 1, 6));
        tree.insert(new corelogic.Move(3, 0, 5, 6));
        tree.insert(new corelogic.Move(5, 6, 7, 7));
        System.out.println(tree);
    }
}
