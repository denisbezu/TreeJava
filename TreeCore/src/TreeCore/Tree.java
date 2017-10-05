package TreeCore;

import TreeCore.Node;

import java.util.ArrayList;
import java.util.List;

public class Tree
{
    //region Fields
    private Node _root;

    private String _name;

    private List<Node> _nodes;
    //endregion

    //region Getters-Setters
    public String getName() {
        return _name;
    }

    public void setName(String _name) {
        this._name = _name;
    }

    public Node getRoot() {
        return _root;
    }

    public List<Node> getNodes() {
        return _nodes;
    }
    //endregion


    public Tree() throws Exception {
        this(new Node());
    }

    public Tree(Node root) throws Exception {
        SetRoot(root);
    }

    /**
     * Установка корня дерева
     * @param root корень
     */
    private void SetRoot(Node root) throws Exception {
        if(getRoot() != null)
            throw new Exception("The root item is already set, you cannot change it");
        if(root.getTree() != null)
            throw new Exception("This node has already a tree");
        _root = root;
        root.setTree(this);
        _name = root.getName();
        _nodes = new ArrayList<>();
        _nodes.add(root);
    }

    @Override
    public String toString() {
        return _name;
    }
}
