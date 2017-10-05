import TreeCore.Attribute;
import TreeCore.Node;

public class Main {
    public static void  main(String args[])
    {
        Node node = new Node("denys");
        node.addAttribute("denys1", "best1");
        node.addAttribute("denys2", "best2");
        node.addAttribute("denys3", "best3");

        System.out.println(node.getName());
        for (Attribute attribute : node.getAttributes())
            System.out.println(attribute);
        //System.out.println(node.getAttributeValue("dENdsays2"));
    }
}
