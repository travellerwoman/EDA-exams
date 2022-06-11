package examen.sumlevels;

import material.Position;
import material.tree.binarytree.BinaryTree;
import material.tree.binarytree.LinkedBinaryTree;

import java.util.*;

public class SumLevels {

    BinaryTree<Integer> tree;


    public SumLevels() {
        tree = new LinkedBinaryTree<>();
    }

    public SumLevels(BinaryTree<Integer> tree) {
        this.tree = tree;
    }

    public int sumLevels(Position<Integer> position, Set<Integer> levels, int level) {
        if (!levels.isEmpty()) {
            int levelSum = 0;
            boolean isSameLevel = levels.contains(level);
            if (isSameLevel) {
                levelSum += position.getElement();
            }
            if (tree.hasLeft(position)){
                levelSum += sumLevels(tree.left(position), levels, level + 1);
            } if (tree.hasRight(position)){
                levelSum += sumLevels(tree.right(position), levels, level + 1);
            }
            return levelSum;
        } return 0;
	}

    public int sumLevels(int[] levels) {
        if (levels != null) {
            Set<Integer> list1 = new HashSet<>();
            for (int element : levels) {
                list1.add(element);
            }
            return sumLevels(tree.root(), list1, 0);
        }
        return 0;
    }

    
	
}










