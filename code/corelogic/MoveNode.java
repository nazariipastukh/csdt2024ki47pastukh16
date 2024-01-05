package corelogic;

import java.util.LinkedList;

public class MoveNode {
    corelogic.Move data;
    LinkedList<MoveNode> childNodes;

    MoveNode(corelogic.Move move) {
        this.data = move;
        this.childNodes = new LinkedList();
    }

    public corelogic.Move getMove() {
        return this.data;
    }

    public MoveNode insert(corelogic.Move move) {
        MoveNode newM = new MoveNode(move);
        this.childNodes.add(newM);
        return newM;
    }

    public LinkedList<MoveNode> getChildNodes() {
        return this.childNodes;
    }

    public String toString() {
        String rtn = "MoveNode{" + this.data;
        for (MoveNode moveNode : this.childNodes) {
            rtn = rtn + ", " + moveNode;
        }
        return rtn + "}";
    }
}
