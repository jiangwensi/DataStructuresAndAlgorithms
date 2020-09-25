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
    private List<String>[] hashtable = new LinkedList[multiplier];

    public static void main(String[] args) throws IOException {
        new HashChains().processQueries();
    }

//    private int hashFunc2(String s) {
//        double hash = 0;
//        for (int i = s.length()-1; i >=0; i--) {
//            hash += s.charAt(i) * Math.pow(multiplier, i);
//        }
//        hash = ((hash % prime) + prime) % prime;
//        hash = ((hash % bucketCount) + bucketCount) % bucketCount;
//        return (int) hash;
//    }

//    private int hashFunc2(String s) {
//        long hash = 0;
//        for (int i = s.length() - 1; i >= 0; --i)
//            hash = ((hash * multiplier + s.charAt(i)) % prime + prime) % prime;
//        return (int) (hash % bucketCount + bucketCount) % bucketCount;
//    }

    private int hashFunc2(String s) {
        long hash = 0;
        for (int i = s.length() - 1; i >= 0; --i)
            hash = mod(hash * multiplier + s.charAt(i), prime);
        return (int) mod(hash, bucketCount);
    }

    private long mod(long a, long b) {
        return (a % b + b) % b;
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

    private void writeSearchResult(boolean wasFound) {
        out.println(wasFound ? "yes" : "no");
        // Uncomment the following if you want to play with the program interactively.
        // out.flush();
    }

    private void processQuery(Query query) {
        String s;
        List l;
        int hash;
        switch (query.type) {
            case "add":
                s = query.s;
                hash = hashFunc2(s);
                l = hashtable[hash];

                if (l == null) {
                    l = new LinkedList();
                    hashtable[hash] = l;
                } else {
                    for (int i = 0; i < l.size(); i++) {
                        if (l.get(i).equals(s)) {
                            return;
                        }
                    }
                }

                l.add(0, s);

                break;
            case "del":
                s = query.s;
                l = hashtable[hashFunc2(s)];

                if (l == null) {
                    return;
                } else {
                    for (int i = 0; i < l.size(); i++) {
                        if (l.get(i).equals(s)) {
                            l.remove(i);
                            if (l.size() == 0) {
                                l = null;
                            }
                            return;
                        }
                    }
                }

                break;
            case "find":
                s = query.s;
                l = hashtable[hashFunc2(s)];
                boolean found = false;

                if (l != null && l.size() != 0) {
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

    public void processQueries() throws IOException {
        elems = new ArrayList<>();
        in = new FastScanner();
        out = new PrintWriter(new BufferedOutputStream(System.out));
        bucketCount = in.nextInt();
        int queryCount = in.nextInt();
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
