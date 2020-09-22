
import java.util.*;
import java.io.*;

public class tree_height {
    class FastScanner {
        StringTokenizer tok = new StringTokenizer("");
        BufferedReader in;

        FastScanner() {
            in = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() throws IOException {
            while (!tok.hasMoreElements())
                tok = new StringTokenizer(in.readLine());
            return tok.nextToken();
        }

        int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }

    private static class TreeNode {
        int value;
        List<TreeNode> children = new ArrayList();

        public TreeNode(int value) {
            this.value = value;
        }
    }

    private static class ElementNode {
        int value;
        int parent;

        public ElementNode(int value, int parent) {
            this.value = value;
            this.parent = parent;
        }
    }

    private static List<ElementNode> toElementNodeList(int[] arr) {
        List<ElementNode> list = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            ElementNode e = new ElementNode(i, arr[i]);
            list.add(e);
        }
        return list;
    }

    private static TreeNode constructTree(List<ElementNode> elementNodes) {
        TreeNode root = null;
        ElementNode rootElement = null;
        for (ElementNode e : elementNodes) {
            if (e.parent == -1) {
                rootElement = e;
                root = new TreeNode(e.value);
                break;
            }
        }
        elementNodes.remove(rootElement);

        constructChildren(elementNodes, root);

        return root;
    }

    private static void constructChildren(List<ElementNode> elementNodes, TreeNode root) {
        if (elementNodes.size() == 0) {
            return;
        }

        List<ElementNode> childrenElements = new LinkedList<>();
        List<TreeNode> childrenNodes = new LinkedList<>();
        for (ElementNode e : elementNodes) {
            if (e.parent == root.value) {
                childrenNodes.add(new TreeNode(e.value));
                childrenElements.add(e);
            }
        }

        for(int i = 0 ;i< childrenElements.size();i++){
            root.children.add(childrenNodes.get(i));
            elementNodes.remove(childrenElements.get(i));
        }

        for(int i = 0 ;i< childrenElements.size();i++){
            constructChildren(elementNodes,childrenNodes.get(i));
        }
    }

    public static int getChildrenHeight(TreeNode treeNode) {

        int childrenHeight = 0;

        List<TreeNode> children = treeNode.children;

        for (TreeNode tn : children) {
            int h = getChildrenHeight(tn)+1;
            if (h > childrenHeight) {
                childrenHeight = h;
            }
        }

        return childrenHeight;
    }

    public class TreeHeight {
        int n;
        int parent[];

        void read() throws IOException {
            FastScanner in = new FastScanner();
            n = in.nextInt();
            parent = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = in.nextInt();
            }
        }

        int computeHeight() {

            List<ElementNode> elementList = toElementNodeList(parent);
            TreeNode root = constructTree(elementList);
            return getChildrenHeight(root) + 1;
        }


//		int computeHeight() {
//                        // Replace this code with a faster implementation
//			int maxHeight = 0;
//			for (int vertex = 0; vertex < n; vertex++) {
//				int height = 0;
//				for (int i = vertex; i != -1; i = parent[i])
//					height++;
//				maxHeight = Math.max(maxHeight, height);
//			}
//			return maxHeight;
//		}
    }

    static public void main(String[] args) throws IOException {
        new Thread(null, new Runnable() {
            public void run() {
                try {
                    new tree_height().run();
                } catch (IOException e) {
                }
            }
        }, "1", 1 << 26).start();
    }

    public void run() throws IOException {
        TreeHeight tree = new TreeHeight();
        tree.read();
        System.out.println(tree.computeHeight());
    }
}
