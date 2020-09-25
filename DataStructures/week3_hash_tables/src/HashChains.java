import java.io.*;
import java.util.*;

public class HashChains {

    private FastScanner in;
    private PrintWriter out;
    // store all strings in one list
    private List<String> elems;
    // for hash function
    private int bucketCount;
    private int prime = 1000000007;
    private int multiplier = 263;
    private List<String>[] hashtable ;

    public static void main(String[] args) throws IOException {
        new HashChains().processQueries();
    }

    private int hashFunc(String s) {
        long hash = 0;
        for (int i = s.length() - 1; i >= 0; --i)
            hash = (hash * multiplier + s.charAt(i)) % prime;
        return (int) hash % bucketCount;
    }

    private Query readQuery() throws IOException {
        String type = in.next();

        if (!type.equals("check")) {
            String s = in.next();
            return new Query(type, s);
        } else {
            int ind = in.nextInt();
            return new Query(type, ind);
        }
    }

    private void printHashTable() {
        if (hashtable == null) {
            System.out.println("hashtable is null");
            return;
        }
        if (hashtable.length == 0) {
            System.out.println("hashtable length is 0");
            return;
        }
        for (int i = 0; i < hashtable.length; i++) {
            if (hashtable[i] == null) {
//                System.out.println("hashtable[" + i + "] is null");
            } else {
                System.out.println("hashtable[" + i + "]:");
                for (int j = 0; j < hashtable[i].size(); j++) {
                    System.out.print(hashtable[i].get(j)+" ");
                }
                System.out.println();
            }
        }
    }

    private void processQuery(Query query) {
//        printHashTable();
//        printQuery(query);
        String s = query.s;
        List l;
        int hash = -1;
        if (s != null) {
            hash = hashFunc(s);
        }

        switch (query.type) {
            case "add":
                s = query.s;
                l = hashtable[hash];

                if (l != null) {
                    for (int i = 0; i < l.size(); i++) {
                        if (l.get(i).equals(s)) {
                            return;
                        }
                    }
                } else {
                    l = new LinkedList();
                    hashtable[hash] = l;
                }

                l.add(0, s);

                break;
            case "del":
                s = query.s;
                l = hashtable[hash];

                if (l != null) {
                    for (int i = 0; i < l.size(); i++) {
                        if (l.get(i).equals(s)) {
                            l.remove(i);
                            if (l.size() == 0) {
                                hashtable[hash] = null;
                            }
                            return;
                        }
                    }
                }

                break;
            case "find":
                s = query.s;
                l = hashtable[hash];
                boolean found = false;

                if (l != null) {
                    for (int i = 0; i < l.size(); i++) {
                        if (l.get(i).equals(s)) {
                            System.out.println("yes");
                            found = true;
                            break;
                        }
                    }
                }

                if (!found) {
                    System.out.println("no");
                }
                break;

            case "check":
                l = hashtable[query.ind];

                if (l == null || l.size() == 0) {
                    System.out.println();
                    return;
                } else {
                    for (int i = 0; i < l.size(); i++) {
                        System.out.print(l.get(i) + " ");
                    }
                    System.out.println();
                }
                break;
            default:
                throw new RuntimeException("Unknown query: " + query.type);
        }
    }

    private void printQuery(Query query) {
        System.out.println("\n**type:"+query.type + ",s:"+query.s +",ind:"+ query.ind);
    }

    public void processQueries() throws IOException {
        elems = new ArrayList<>();
        in = new FastScanner();
        out = new PrintWriter(new BufferedOutputStream(System.out));
        bucketCount = in.nextInt();
        int queryCount = in.nextInt();
        hashtable= new LinkedList[bucketCount];
        for (int i = 0; i < queryCount; ++i) {
            processQuery(readQuery());
        }
        out.close();
    }

    static class Query {
        String type;
        String s;
        int ind;

        public Query(String type, String s) {
            this.type = type;
            this.s = s;
        }

        public Query(String type, int ind) {
            this.type = type;
            this.ind = ind;
        }
    }

    static class FastScanner {
        private BufferedReader reader;
        private StringTokenizer tokenizer;

        public FastScanner() {
            reader = new BufferedReader(new InputStreamReader(System.in));
            tokenizer = null;
        }

        public String next() throws IOException {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                tokenizer = new StringTokenizer(reader.readLine());
            }
            return tokenizer.nextToken();
        }

        public int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }
}
