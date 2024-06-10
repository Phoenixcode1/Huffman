package Huffman;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Klasa Kopiec reprezentuje strukturę danych kopca, która jest wykorzystywana w algorytmie Huffmana.
 */
class Kopiec {

    /**
     * Klasa wewnętrzna NodeK reprezentuje węzeł kopca, zawierający symbol i częstość występowania.
     */
    static class NodeK {
        String symbol;
        int frequency;

        /**
         * Konstruktor tworzy nowy węzeł kopca z danym symbolem i częstością.
         * @param symbol Symbol
         * @param frequency Częstość występowania
         */
        public NodeK(String symbol, int frequency) {
            this.symbol = symbol;
            this.frequency = frequency;
        }

        public String getSymbol() {
            return symbol;
        }

        public void setSymbol(String symbol) {
            this.symbol = symbol;
        }

        public int getFrequency() {
            return frequency;
        }

        public void setFrequency(int frequency) {
            this.frequency = frequency;
        }

        @Override
        public String toString() {
            return STR."Node{symbol='\{symbol}\{'\''}, frequency=\{frequency}\{'}'}";
        }
    }

    private final List<NodeK> seq = new ArrayList<>();

    /**
     * Metoda zwraca listę węzłów kopca.
     * @return Lista węzłów kopca
     */
    public List<NodeK> getSeq() {
        return seq;
    }

    @Override
    public String toString() {
        return STR."Kopiec{seq=\{seq}\{'}'}";
    }

    /**
     * Konstruktor tworzy kopiec na podstawie mapy symboli i częstości występowania.
     * @param frequencyMap Mapa symboli i ich częstości występowania
     */
    public Kopiec(Map<String, Integer> frequencyMap) {
        createNodeKListFromMap(frequencyMap);
    }

    void addKopiecEntry(List<Sequence> sequences) {
        for (Sequence seqq : sequences) {
            seq.add(new NodeK(seqq.getName(), seqq.getFrequency()));
        }
        sortSeq();
    }

    void addKopiecEntry(Sequence seqq) {
        seq.add(new NodeK(seqq.getName(), seqq.getFrequency()));
        sortSeq();
    }

    void createNodeKListFromMap(Map<String, Integer> frequencyMap) {
        for (Map.Entry<String, Integer> entry : frequencyMap.entrySet()) {
            String symbol = entry.getKey();
            int frequency = entry.getValue();
            seq.add(new NodeK(symbol, frequency));
        }
        sortSeq();
    }

    List<String> getNodeKSymbolsWithFrequency(int frequency) {
        return seq.stream()
                .filter(node -> node.getFrequency() == frequency)
                .map(NodeK::getSymbol)
                .collect(Collectors.toList());
    }

    void removeTopN(int n) {
        int size = Math.min(n, seq.size());
        seq.subList(0, size).clear();
    }

    List<NodeK> getTopN(int n) {
        sortSeq();
        int size = Math.min(n, seq.size());
        return new ArrayList<>(seq.subList(0, size));
    }

    void removeKopiecEntry(NodeK element) {
        seq.removeIf(node -> node.getSymbol().equals(element.getSymbol()) && node.getFrequency() == element.getFrequency());
        sortSeq();
    }

    private void sortSeq() {
        seq.sort(Comparator.comparingInt(NodeK::getFrequency).thenComparing(NodeK::getSymbol));
    }
}
