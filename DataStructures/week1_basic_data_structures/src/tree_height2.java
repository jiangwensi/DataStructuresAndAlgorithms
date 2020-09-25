import java.util.*;
import java.io.*;

public class tree_height2 {
    private static TreeNode[] nodes;
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
            //node value equals to node index, construct the node first
            nodes = new TreeNode[n];
            for(int i =0; i < n; i++){
                nodes[i]=new TreeNode(i);
            }

            //construct tree
            int rootIndex=-1;
            for(int i =0; i<n;i++){
                int parentIndex = parent[i];
                if(parentIndex==-1){
                    rootIndex = i;
                } else{
                    nodes[parentIndex].children.add(nodes[i]);
                }
            }
            TreeNode root = nodes[rootIndex];

            return getChildrenHeight(root)+1;
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

    static public void main(String[] args) throws IOException {
        new Thread(null, new Runnable() {
            public void run() {
                try {
                    new tree_height2().run();
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
