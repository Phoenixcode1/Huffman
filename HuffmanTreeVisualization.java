package Huffman;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Klasa służy do wizualizacji drzewa Huffmana.
 */
public class HuffmanTreeVisualization extends Application {

    /**
     * Metoda start() inicjuje aplikację JavaFX i rysuje drzewo Huffmana.
     * @param primaryStage Główny etap aplikacji JavaFX
     */
    @Override
    public void start(Stage primaryStage) {
        Main m = new Main();
        HuffmanTree.Node root = m.buildHuffmanTree();

        Pane pane = new Pane();
        drawTree(pane, root, 400, 50, 200);

        Scene scene = new Scene(pane, 800, 600);
        primaryStage.setTitle("Wizualizacja drzewa Huffmana");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Metoda rysuje drzewo Huffmana rekurencyjnie.
     * @param pane Obszar, na którym rysowane jest drzewo
     * @param root Korzeń drzewa do narysowania
     * @param x Współrzędna x aktualnego węzła
     * @param y Współrzędna y aktualnego węzła
     * @param hGap Odległość między węzłami na poziomie
     */
    private void drawTree(Pane pane, HuffmanTree.Node root, double x, double y, double hGap) {
        if (root == null) {
            return;
        }

        if (root.getLeft() != null) {
            Line leftLine = new Line(x, y, x - hGap, y + 50);
            pane.getChildren().add(leftLine);
            drawTree(pane, root.getLeft(), x - hGap, y + 50, hGap / 2);
        }

        if (root.getRight() != null) {
            Line rightLine = new Line(x, y, x + hGap, y + 50);
            pane.getChildren().add(rightLine);
            drawTree(pane, root.getRight(), x + hGap, y + 50, hGap / 2);
        }

        String symbol = root.getSymbol() != null ? root.getSymbol() : " ";
        Text text = new Text(x - 10, y, symbol + ":" + root.getFrequency());
        text.setFill(Color.RED);
        pane.getChildren().add(text);
    }

    /**
     * Metoda główna, uruchamiająca aplikację JavaFX.
     * @param args Argumenty wiersza poleceń
     */
    public static void main(String[] args) {
        launch(args);
    }
}
