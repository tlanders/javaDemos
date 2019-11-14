package algorithms.trees;

import org.junit.Assert;
import org.junit.Test;

public class LeftLeaningRedBlackTreeTests {
    @Test
    public void testInsert() {
        Tree<Integer,String> tree = new LeftLeaningRedBlackTree<>();
        Assert.assertNotNull(tree);

        tree.insert(7, "7");
        tree.insert(5, "5");
        tree.insert(9, "9");
        tree.insert(3, "3");
        tree.insert(6, "6");

        Assert.assertEquals(5, tree.size());
        Assert.assertNotNull(((LeftLeaningRedBlackTree<Integer, String>) tree).root.leftChild);
        Assert.assertNotNull(((LeftLeaningRedBlackTree<Integer, String>) tree).root.rightChild);
    }
}
