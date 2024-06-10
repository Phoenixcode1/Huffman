package Huffman;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;


/**
 * Klasa HuffmanTree reprezentuje strukturę drzewa Huffmana oraz zawiera algorytm do jego budowy.
 */
class HuffmanTree {

    /**
     * Metoda algorytmHuffmana() wykonuje algorytm kompresji Huffmana na podanej liście sekwencji.
     * @param sequences Lista sekwencji, które mają zostać skompresowane
     */
    void algorytmHuffmana(List<Sequence> sequences) {
        // Tworzenie mapy częstości występowania znaków
        Map<String, Integer> map = new HashMap<>();
        for (Sequence seq : sequences) {
            map.put(seq.getName(), seq.getFrequency());
        }
        System.out.println(map);

        // Tworzenie kopca na podstawie mapy częstości
        Kopiec kop = new Kopiec(map);
        System.out.println(kop.getSeq());
        System.out.println(kop.getTopN(3));
    }

    /**
     * Metoda getMapMin() zwraca mapę zawierającą najmniejszą wartość i odpowiadający jej klucz z podanej mapy.
     * @param map Mapa do analizy
     * @return Mapa zawierająca najmniejszą wartość i odpowiadający jej klucz
     */


    Map<String, Integer> getMapMin(Map<String, Integer> map) {
        Map<String, Integer> retMap = new HashMap<>();
        String najmniejszyKlucz = null;
        int najmniejszaWartosc = Integer.MAX_VALUE;

        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (entry.getValue() < najmniejszaWartosc) {
                najmniejszaWartosc = entry.getValue();
                najmniejszyKlucz = entry.getKey();
            }
        }
        retMap.put(najmniejszyKlucz, najmniejszaWartosc);
        return retMap;
    }

    /**
     * Metoda buildHuffmanTree() buduje drzewo Huffmana na podstawie listy sekwencji.
     * @param sequences Lista sekwencji
     * @return Korzeń drzewa Huffmana
     */
    public Node buildHuffmanTree(List<Sequence> sequences) {
        PriorityQueue<Node> queue = new PriorityQueue<>();

        // Dodanie wszystkich sekwencji jako węzłów liści do kolejki priorytetowej
        for (Sequence seq : sequences) {
            queue.add(new Node(seq.getName(), seq.getFrequency()));
        }

        // Budowa drzewa Huffmana
        while (queue.size() > 1) {
            Node left = queue.poll();
            Node right = queue.poll();
            Node parent = new Node(left, right);
            queue.add(parent);
        }

        // Zwrócenie korzenia drzewa Huffmana
        return queue.poll();
    }

    /**
     * Klasa Node reprezentuje węzeł w drzewie Huffmana.
     */
    static class Node implements Comparable<Node> {
        String symbol;
        int frequency;
        Node left;
        Node right;

        // Konstruktor dla liścia
        public Node(String symbol, int frequency) {
            this.symbol = symbol;
            this.frequency = frequency;
        }

        // Konstruktor dla węzła wewnętrznego
        public Node(Node left, Node right) {
            this.left = left;
            this.right = right;
            this.frequency = left.frequency + right.frequency;
        }

        // Implementacja metody compareTo() z interfejsu Comparable
        @Override
        public int compareTo(Node other) {
            return this.frequency - other.frequency;
        }

        // Metoda toString() zwraca reprezentację tekstową węzła
        @Override
        public String toString() {
            return STR."Node{symbol='\{symbol}', frequency=\{frequency}, left=\{left}, right=\{right}}";
        }

        // Metoda modeToString() zwraca reprezentację tekstową drzewa
        public String modeToString() {
            return toStringRecursive(this);
        }

        // Metoda rekurencyjna toStringRecursive() dla reprezentacji tekstowej drzewa
        private String toStringRecursive(Node node) {
            if (node == null) {
                return "";
            }

            if (node.left == null && node.right == null) {
                return STR."(\{node.symbol}:\{node.frequency})";
            }

            String leftStr = toStringRecursive(node.left);
            String rightStr = toStringRecursive(node.right);

            return STR."(\{leftStr}:\{node.frequency}:\{rightStr})";
        }

        // Gettery dla pól węzła
        public Node getLeft() {
            return left;
        }

        public Node getRight() {
            return right;
        }

        public String getSymbol() {
            return symbol;
        }

        public int getFrequency() {
            return frequency;
        }
    }
}
