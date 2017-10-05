package TreeCore;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

public class Node {
    //region Fields

    private List<Node> _children;

    private List<Attribute> _attributes;

    private Node _parent;

    private final static String DEFAULT_NAME = "Undefined";

    private Tree _tree;

    private String _name;

    private String _value;


    //endregion

    //region Getters-Setters

    public Tree getTree() {
        return _tree;
    }

    public void setTree(Tree _tree) {
        this._tree = _tree;
    }

    public String getName() {
        return _name;
    }

    public void setName(String _name) {
        this._name = _name;
    }

    public String getValue() {
        return _value;
    }

    public void setValue(String _value) {
        this._value = _value;
    }

    public List<Node> getChildren() {
        return _children;
    }

    public List<Attribute> getAttributes() {
        return _attributes;
    }

    public Node getParent() {
        return _parent;
    }


    //endregion

    //region Ctor
    public Node(String name) {
        _children = new ArrayList<Node>();
        this._name = name;
    }

    public Node() {
        this(DEFAULT_NAME);
    }
    //endregion

    /**
     * Проверка: есть ли заданный атрибут в списке
     *
     * @param name имя искомого атрибута
     * @return найденный атрибут
     */
    public String getAttributeValue(String name) {
        if (_attributes == null)
            return null;
        for (Attribute att : _attributes) {
            if (att.getName().toLowerCase().equals(name.toLowerCase()))
                return att.getValue();
        }
        return null;
    }

    //region AddOperations

    /**
     * Добавление нового атрибута в список
     *
     * @param name  имя нового атрибута
     * @param value значение нового атрибута
     */
    public void addAttribute(String name, String value) {
        if (_attributes == null)
            _attributes = new ArrayList<>();
        Attribute attribute = new Attribute(name, value);
        _attributes.add(attribute);
    }

    /**
     * Добавление дочернего элемента по имени
     *
     * @param name имя дочернего элемента
     * @return добавленный элемент
     */
    public Node addChild(String name) {
        Node node = new Node(name);
        return addChild(node);
    }

    /**
     * Добавление дочернего элемента по узлу
     *
     * @param child узел для добавления
     * @return добавленный узел
     */
    public Node addChild(Node child) throws Exception {
        setConcreteTree(child);
        child.setParent(this);
        return child;
    }
    //endregion

    //region ParentOperations

    /**
     * Задание родителя
     *
     * @param parent родительский узел
     * @throws Exception
     */
    private void setParent(Node parent) throws Exception {
        if (parent != null) {
            if (_parent == null) {
                _parent = parent;
                _parent.getChildren().add(this);
            } else
                throw new Exception("To set a new parent, you need to remove it first");
        } else
            throw new Exception("Parent cannot be null. Use the remove parent method");
    }

    /**
     * Удаление родителя
     */
    public void removeParent() throws Exception {
        searchNodeAction(this, nodesRemoveAction());
        _tree = null;
        removeFromParent(this);
    }

    /**
     * Удаление текущего узла из списка дочерних элементов родителя
     *
     * @param child узел, который удаляем
     */
    private void removeFromParent(Node child) throws Exception {
        if (_parent == null)
            throw new Exception("This node hasnt a parent");
        _parent.getChildren().remove(child);
        _parent = null;
    }
    //endregion

    //region Actions

    /**
     * Удаление из nodes
     *
     * @return функциональный интерфейс Сonsumer с действием
     */
    private Consumer<Node> nodesRemoveAction() {
        return (currentNode) ->
        {
            if (_tree != null) {
                _tree.getNodes().remove(currentNode);
                if (currentNode != this)
                    currentNode.setTree(null);
            }
        };
    }

    /**
     * проверка на соответствие имеющегося узла с текущим по ссылке
     * @param secondCurrent второй узел
     * @return
     */
    private Consumer<Node> referenceEqualsAction(Node secondCurrent)
    {
        return (currentNode) ->
        {
            if(currentNode == secondCurrent)
                throw new IllegalArgumentException("This node exists in the tree");
        };
    }

    /**
     * Добавление в Nodes
     *
     * @return
     */
    private Consumer<Node> nodesAddAction()
    {
        return currentNode -> {
            if (_tree.getNodes().contains(currentNode))
                throw new IllegalArgumentException("This node exists in the tree");
            currentNode.setTree(this.getTree());
            _tree.getNodes().add(currentNode);
        };
    }

    //endregion
}
