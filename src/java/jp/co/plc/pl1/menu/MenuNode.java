package jp.co.plc.pl1.menu;

import java.util.ArrayList;
import java.util.List;
/**
 * tree nodes model
 * @author chenht
 *
 */
public class MenuNode {
	
    private String nodeId;    //tree nodeIdÅCfenkai qubie shujuku zhong de id
    private String pid;
    private String text;   //jiedian mingzi
    private String icon;
    private String href;   //dianji chu fa de lianjie 
    private List<MenuNode> nodes;    //zi jie dian
	
    public MenuNode() { 
        this.nodes = new ArrayList<MenuNode>();
    }
    public MenuNode(String nodeId,String pId) {
        this.nodeId = nodeId;
        this.pid = pId;
        this.nodes = new ArrayList<MenuNode>();
    }
    /**
     * create nodes
     * @param nodeId
     * @param pId
     * @param text
     * @param icon
     * @param href
     */
    public MenuNode(String nodeId, String pId, String text, String icon, String href) {
        super();
        this.nodeId = nodeId;
        this.pid = pId;
        this.text = text;
        this.icon = icon;
        this.href = href;
        this.nodes = new ArrayList<MenuNode>();
    }


    
    public String getNodeId() {
        return nodeId;
    }
    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }
    
    public String getPid() {
        return pid;
    }
    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }
    
    public String getIcon() {
        return icon;
    }
    public void setIcon(String icon) {
        this.icon = icon;
    }
    
    public String getHref() {
        return href;
    }
    public void setHref(String href) {
        this.href = href;
    }
    
    public List<MenuNode> getNodes() {
        return nodes;
    }
    public void setNodes(List<MenuNode> nodes) {
        this.nodes = nodes;
    }
    
    /**
     * shengcheng yi gen duo cha shu gen jiedian wei root
     * @param Nodes shengcheng duo cha shu de gen jiedian jihe 
     * @return root
     *//*
    public MenuNode createTree(List<Node> Nodes) {
        if (Nodes == null || Nodes.size() < 0)
            return null;
        Node root = new Node("root","0");//gen jie dian zidingyi dan shi yao gen pid duiyinghao
        // jiang suoyou jiedian tian jia dao duocha shu zhong 
        for (Node node : Nodes) {
            if (node.getPid().equals("0") || node.getPid().equals("root")) {//gen jie dian zidingyi dan shi yao gen pid duiyinghao
                // xiang gen tianjia yige jie dian 
                root.getNodes().add(node);
            } else {
                addChild(root, node);
            }
        }
        return root;
    }

    *//**
     * xiang zhiding duochashu tianjia jiedian 
     * @param Node duocha shu jiedian 
     * @param child jiedian
     *//*
    public void addChild(MenuNode Node, MenuNode child) {
        for (Node item : Node.getNodes()) {
            if (item.getNodeId().equals(child.getPid())) {
                // zhaodao duiyin de fuqin 
                item.getNodes().add(child);
                break;
            } else {
                if (item.getNodes() != null && item.getNodes().size() > 0) {
                    addChild(item, child);
                }
            }
        }
    }

    *//**
     * bianli duo cha shu
     * @param Node duocha shu jiedian
     * @return
     *//*
    public String iteratorTree(Node Node) {
        StringBuilder buffer = new StringBuilder();
        buffer.append("\n");
        if (Node != null) {
            for (Node index : Node.getNodes()) {
                buffer.append(index.getNodeId() + ",");
                if (index.getNodes() != null && index.getNodes().size() > 0) {
                    buffer.append(iteratorTree(index));
                }
            }
        }
        buffer.append("\n");
        return buffer.toString();
    }*/


     
}