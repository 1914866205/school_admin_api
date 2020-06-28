package com.soft1851.smart.campus.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description TODO
 * @Author 涛涛
 * @Date 2020/4/24 9:48
 * @Version 1.0
 **/
public class FleaTreeBuilder {

    /**
     * 两层循环实现建树
     *
     * @param treeNodes 传入的树节点列表
     * @return
     */
    public static List<FleaTreeNode> buildTreeByLoop(List<FleaTreeNode> treeNodes) {

        List<FleaTreeNode> trees = new ArrayList<>();
        for (FleaTreeNode treeNode : treeNodes) {
            if (treeNode.getParentId() == 0) {
                trees.add(treeNode);
            }
            for (FleaTreeNode it : treeNodes) {
                if (it.getParentId().equals(treeNode.getPkFleaTypeId())) {
                    if (treeNode.getSubTypes() == null) {
                        treeNode.setSubTypes(new ArrayList<>());
                    }
                    treeNode.getSubTypes().add(it);
                }
            }
        }
        return trees;
    }




    /**
     * 使用递归方法建树
     * @param treeNodes
     * @return
     */
    public static List<FleaTreeNode> buildTreeByRecursive(List<FleaTreeNode> treeNodes) {
        List<FleaTreeNode> trees = new ArrayList<>();
        for (FleaTreeNode treeNode : treeNodes) {
            if (treeNode.getParentId() == 0) {
                trees.add(findChildren(treeNode, treeNodes));
            }
        }
        return trees;
    }
    //1.除非你没有子目录，你自己就是叶子结点
    // 如果有，那么你就是集合容器，
    // 2.再遍历所有结点，
    //找你的子节点
    //3. 如果它是叶子结点，就添加到你的容器
    //如果不是，重复1步骤
    /**
     * 递归查找子节点
     *
     * @param treeNodes
     * @return
     */
    public static FleaTreeNode findChildren(FleaTreeNode treeNode, List<FleaTreeNode> treeNodes) {
        for (FleaTreeNode it : treeNodes) {
            if (treeNode.getPkFleaTypeId().equals(it.getParentId())) {
                if (treeNode.getSubTypes() == null) {
                    treeNode.setSubTypes(new ArrayList<>());
                }
                treeNode.getSubTypes().add(findChildren(it, treeNodes));
            }
        }
        return treeNode;
    }
}
