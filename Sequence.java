package Huffman;

/**
 * Klasa Sequence reprezentuje sekwencję danych w kontekście algorytmu Huffmana.
 */
class Sequence {
    private String name;            // Nazwa sekwencji
    private int frequency;          // Częstość występowania sekwencji
    private String defaultBit;      // Domyślny ciąg bitów dla sekwencji
    private String newHuffmanBit;   // Ciąg bitów po skompresowaniu algorytmem Huffmana

    /**
     * Konstruktor klasy Sequence.
     * @param name Nazwa sekwencji
     * @param frequency Częstość występowania sekwencji
     * @param defaultBit Domyślny ciąg bitów dla sekwencji
     */
    public Sequence(String name, int frequency, String defaultBit) {
        this.name = name;
        this.frequency = frequency;
        this.defaultBit = defaultBit;
        this.newHuffmanBit = "";
    }

    // Gettery i settery dla pól klasy

    /**
     * Metoda ustawiająca nazwę sekwencji.
     * @param name Nazwa sekwencji
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Metoda ustawiająca częstość występowania sekwencji.
     * @param frequency Częstość występowania sekwencji
     */
    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    /**
     * Metoda ustawiająca domyślny ciąg bitów dla sekwencji.
     * @param defaultBit Domyślny ciąg bitów dla sekwencji
     */
    public void setDefaultBit(String defaultBit) {
        this.defaultBit = defaultBit;
    }

    /**
     * Metoda ustawiająca ciąg bitów po skompresowaniu algorytmem Huffmana.
     * @param newHuffmanBit Ciąg bitów po skompresowaniu algorytmem Huffmana
     */
    public void setNewHuffmanBit(String newHuffmanBit) {
        this.newHuffmanBit = newHuffmanBit;
    }

    /**
     * Metoda zwracająca nazwę sekwencji.
     * @return Nazwa sekwencji
     */
    public String getName() {
        return name;
    }

    /**
     * Metoda zwracająca częstość występowania sekwencji.
     * @return Częstość występowania sekwencji
     */
    public int getFrequency() {
        return frequency;
    }

    /**
     * Metoda zwracająca domyślny ciąg bitów dla sekwencji.
     * @return Domyślny ciąg bitów dla sekwencji
     */
    public String getDefaultBit() {
        return defaultBit;
    }

    /**
     * Metoda zwracająca ciąg bitów po skompresowaniu algorytmem Huffmana.
     * @return Ciąg bitów po skompresowaniu algorytmem Huffmana
     */
    public String getNewHuffmanBit() {
        return newHuffmanBit;
    }

    /**
     * Metoda zwracająca reprezentację tekstową obiektu klasy Sequence.
     * @return Tekstowa reprezentacja obiektu klasy Sequence
     */
    @Override
    public String toString() {
        return STR."Sequence{name='\{name}\{'\''}, frequency=\{frequency}, defaultBit='\{defaultBit}\{'\''}, newHuffmanBit='\{newHuffmanBit}\{'\''}\{'}'}";
    }
}
