package Huffman;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Klasa główna odpowiedzialna za wykonywanie operacji związanych z algorytmem Huffmana.
 */
public class Main {
    private final String testKod = "aabbCcCdEffggggh";
    private String codeBitString = "";
    private final String Left = "1";
    private final String Right = "0";
    private List<Sequence> seqListObj = new ArrayList<>();

    public static void main(String[] args) {
        Main m = new Main();
        m.huffman();
    }

    private void huffman() {
        seqListObj = prepareString(testKod);
        HuffmanTree ht = new HuffmanTree();
        HuffmanTree.Node root = ht.buildHuffmanTree(seqListObj);

        chooseData(root);
    }

    private void chooseData(HuffmanTree.Node root) {
        Scanner scanner = new Scanner(System.in);
        boolean continueExecution;
        do {
            System.out.println("Wybierz dane, które chcesz zobaczyć:");
            System.out.println("1. Stworzyc Drzewo Huffmana");
            System.out.println("2. Lista sekwencji");
            System.out.println("3. Suma bitów");
            System.out.println("4. Zakodowany ciąg bitowy");
            System.out.println("5. Rozszyfrowany ciąg bitowy");
            System.out.println("6. Wizualizacja drzewa Huffmana");
            System.out.println("7. Zakończ program");

            if (scanner.hasNextInt()) {
                int choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        System.out.println("\nDrzewo Huffmana: stworzono pomyslnie");
                        showTree(root, "");
                        break;
                    case 2:
                        System.out.println("\nLista sekwencji:");
                        showList(seqListObj);
                        break;
                    case 3:
                        System.out.println("\nSuma bitów:");
                        countBit();
                        break;
                    case 4:
                        System.out.println("\nZakodowany ciąg bitowy:");
                        codeInHuffman();
                        break;
                    case 5:
                        System.out.println("\nRozszyfrowany ciąg bitowy:");
                        decodeFromHuffman();
                        break;
                    case 6:
                        System.out.println("\nWizualizacja drzewa Huffmana:");
                        HuffmanTreeVisualization.main(null);
                        break;
                    case 7:
                        System.out.println("Do widzenia!");
                        return;
                    default:
                        System.out.println("Nieprawidłowy wybór.");
                }
            } else {
                System.out.println("Nieprawidłowy wybór. Wprowadź liczbę odpowiadającą wybranej opcji.");
                scanner.next();
            }

            System.out.println("\nNaciśnij Enter, aby kontynuować...");
            scanner.nextLine(); // Czyści bufor wejściowy
            scanner.nextLine(); // Oczekuje na wciśnięcie Enter
            System.out.println(); // Pusty wiersz między wynikami a menu
        } while (true);
    }

    /**
     * Buduje drzewo Huffmana i zwraca jego korzeń.
     *
     * @return korzeń drzewa Huffmana
     */
    public HuffmanTree.Node buildHuffmanTree() {
        List<Sequence> seqListObj = prepareString(testKod);
        HuffmanTree ht = new HuffmanTree();
        return ht.buildHuffmanTree(seqListObj);
    }

    /**
     * Koduje ciąg znaków zgodnie z algorytmem Huffmana.
     */
    private void codeInHuffman() {
        Map<String, String> map = seqListObj.stream()
                .collect(Collectors.toMap(Sequence::getName, Sequence::getNewHuffmanBit));

        String[] code = testKod.split("");
        for (int i = 0; i < code.length; i++) {
            code[i] = map.get(code[i].toUpperCase());
            codeBitString = codeBitString.concat(code[i]);
        }
        System.out.println("String: " + testKod);
        System.out.println("String in Bit: " + codeBitString);
    }


    /**
     * Dekoduje ciąg bitowy zgodnie z algorytmem Huffmana.
     */
    private void decodeFromHuffman() {
        String[] codeBit = codeBitString.split("");
        String bit = "";
        String line = "";
        Map<String, String> map = seqListObj.stream()
                .collect(Collectors.toMap(Sequence::getName, Sequence::getNewHuffmanBit));

        for (String cb : codeBit) {
            bit += cb;
            if (map.containsValue(bit)) {
                line = line.concat(getKeyByValue(map, bit));
                bit = "";
            }
        }
        System.out.println("Oto rozszyfrowany kod bitowy: " + line);
    }

    /**
     * Znajduje klucz na podstawie wartości w mapie.
     *
     * @param map   mapa do przeszukania
     * @param value wartość, dla której szukamy klucza
     * @param <K>   typ klucza
     * @param <V>   typ wartości
     * @return klucz dla danej wartości lub null, jeśli wartość nie została znaleziona
     */
    public static <K, V> K getKeyByValue(Map<K, V> map, V value) {
        return map.entrySet()
                .stream()
                .filter(entry -> entry.getValue().equals(value))
                .map(Map.Entry::getKey)
                .findFirst()
                .orElse(null);
    }

    /**
     * Liczy liczbę bitów oszczędzonych w algorytmie Huffmana w porównaniu do standardowego kodowania.
     */
    private void countBit() {
        int sumaDef = seqListObj.stream()
                .mapToInt(seq -> seq.getDefaultBit().toCharArray().length * seq.getFrequency())
                .sum();
        int sumaHuff = seqListObj.stream()
                .mapToInt(seq -> seq.getNewHuffmanBit().toCharArray().length * seq.getFrequency())
                .sum();

        System.out.println("Default bit sum = " + sumaDef);
        System.out.println("Huffman bit sum = " + sumaHuff);
        System.out.println("You saved: " + (sumaDef - sumaHuff) + " bits");
    }

    /**
     * Wyświetla drzewo Huffmana w konsoli.
     *
     * @param root korzeń drzewa Huffmana
     * @param code kod Huffmana dla aktualnego węzła
     */
    public void showTree(HuffmanTree.Node root, String code) {
        if (root.getLeft() != null) {
            showTree(root.getLeft(), code.concat(Left));
        }
        if (root.getRight() != null) {
            showTree(root.getRight(), code.concat(Right));
        }
        if (root.getLeft() == null && root.getRight() == null) {
            seqListObj.stream()
                    .filter(seq -> Objects.equals(root.symbol, seq.getName()))
                    .findFirst()
                    .ifPresent(seq -> seq.setNewHuffmanBit(code));
        }
    }

    /**
     * Wyświetla listę sekwencji w konsoli.
     *
     * @param seqListObj lista sekwencji
     */
    private void showList(List<Sequence> seqListObj) {
        seqListObj.forEach(System.out::println);
    }

    /**
     * Przygotowuje ciąg znaków na podstawie którego zostanie zbudowane drzewo Huffmana.
     *
     * @param seq ciąg znaków
     * @return lista sekwencji na podstawie ciągu znaków
     */
    private List<Sequence> prepareString(String seq) {
        Map<String, Integer> frequencyMap = Arrays.stream(seq.toUpperCase().split(""))
                .collect(Collectors.toMap(s -> s, s -> 1, Integer::sum));

        List<String> bitMap = generateBinaryNumbers(calculateBitsNeeded(frequencyMap.size()));
        List<Sequence> lSeq = new ArrayList<>();
        int i = 0;

        for (Map.Entry<String, Integer> entry : frequencyMap.entrySet()) {
            lSeq.add(new Sequence(entry.getKey(), entry.getValue(), bitMap.get(i++)));
        }

        return lSeq;
    }

    /**
     * Generuje listę binarnych liczb na podstawie długości bitowej potrzebnej do zapisania liczby.
     * * @param bitLength długość bitowa
     * * @return lista binarnych liczb o podanej długości bitowej
     */

    public static List<String> generateBinaryNumbers(int bitLength) {
        List<String> binaryNumbers = new ArrayList<>();
        int maxNumber = (int) Math.pow(2, bitLength);
        for (int i = 0; i < maxNumber; i++) {
            String binaryString = String.format("%" + bitLength + "s", Integer.toBinaryString(i)).replace(' ', '0');
            binaryNumbers.add(binaryString);
        }

        return binaryNumbers;
    }

    /**
     * Oblicza ilość bitów potrzebną do zapisania danej liczby.
     *
     * @param numberToCalculate liczba, dla której obliczana jest ilość bitów
     * @return ilość bitów potrzebna do zapisania liczby
     */
    public static int calculateBitsNeeded(int numberToCalculate) {
        return numberToCalculate == 0 ? 1 : (int) (Math.floor(Math.log(numberToCalculate) / Math.log(2)) + 1);
    }
}

