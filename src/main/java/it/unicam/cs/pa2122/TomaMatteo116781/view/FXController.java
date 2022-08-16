package it.unicam.cs.pa2122.TomaMatteo116781.view;

import it.unicam.cs.pa2122.TomaMatteo116781.controller.Controller;
import it.unicam.cs.pa2122.TomaMatteo116781.controller.DefaultController;
import it.unicam.cs.pa2122.TomaMatteo116781.model.RGBColor;
import it.unicam.cs.pa2122.TomaMatteo116781.model.interfaces.*;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Scale;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * View per rappresentare tutti i contenuti del package model e controllarli.
 */
public class FXController implements PlaneUpdateListener<Point<Double>> {

    private final Controller<Point<Double>> controller = new DefaultController();
    @FXML
    public MenuItem loadFile;
    @FXML
    public Pane textAreaPane;
    @FXML
    public TextArea instructionArea;
    @FXML
    public Pane bottomPane;
    @FXML
    public Pane planePane;
    @FXML
    public Button autoButton;
    @FXML
    public Button saveButton;
    @FXML
    public Button previousButton;
    @FXML
    public Button nextButton;


    private List<Integer> executeTextFieldInstructions; // Istruzioni eseguite nella textfield, da
    // rimuovere dalla lista di tutte le istruzioni
    private int i; // Contatore istruzione attuale

    @FXML
    public void initialize() {
        i = 0;
        setCartesianCoordinates();
        createPlanePane();
        createRectangle();
        createCursor();
        bottomPane.setVisible(false);
        previousButton.setDisable(true);
        instructionArea.setEditable(false);
        instructionArea.setStyle("-fx-font-weight: bold");
        instructionArea.positionCaret(instructionArea.getLength());
        controller.getPlane().addPlaneUpdateListener(this);
        executeTextFieldInstructions = new ArrayList<>();
    }

    private void createPlanePane() {
        controller.newPlane(1000, 500);
        planePane.setMinHeight(500);
        planePane.setMaxHeight(500);
        planePane.setMinWidth(1000);
        planePane.setMaxWidth(1000);
        this.planePane.setStyle("-fx-border-color: black");
    }

    private void createRectangle() {
        Rectangle rectangle = new Rectangle(planePane.getMaxWidth(), planePane.getMaxHeight());
        rectangle.setFill(Color.rgb(controller.getPlane().getBackgroundColor().getRed(), controller.getPlane().getBackgroundColor().getGreen(), controller.getPlane().getBackgroundColor().getBlue()));
        planePane.getChildren().add(0, rectangle);
    }

    private void createCursor() {
        final URL url = getClass().getResource("/turtleCursor.png");
        assert url != null;
        final Image imageCursor = new Image(url.toString());
        ImagePattern imagePattern = new ImagePattern(imageCursor);
        Circle cursor = new Circle(controller.getPlane().getHome().getX(), controller.getPlane().getHome().getY(), 20);
        cursor.setFill(imagePattern);
        planePane.getChildren().add(1, cursor);
    }

    private void setCartesianCoordinates() {
        Scale scale = new Scale();
        scale.setX(1);
        scale.setY(-1);
        scale.pivotYProperty().bind(Bindings.createDoubleBinding(() ->
                        planePane.getBoundsInLocal().getMinY() + planePane.getBoundsInLocal().getHeight() / 2,
                planePane.boundsInLocalProperty()));
        planePane.getTransforms().add(scale);
    }

    /**
     * Metodo per caricare un file di testo contenente le istruzioni Logo.
     *
     * @param actionEvent Un evento che indica che si &egrave; verificata un'azione definita dal componente.
     *                    Questo evento viene generato da un componente (come un pulsante)
     *                    quando si verifica l'azione specifica del componente (come la pressione)
     * @throws IOException lancia un'eccezione di tipo Input/Output se non riesce a caricare il file.
     */
    public void caricaFile(ActionEvent actionEvent) throws IOException {
        this.reset(actionEvent);
        controller.getAllInstructions().clear();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Scegli il file da caricare");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Text Files", "*.txt"));
        File selectedFile = fileChooser.showOpenDialog(new Stage());
        if (selectedFile == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResource("/AppIcon.png")).toString()));
            alert.setTitle("");
            alert.setHeaderText("");
            alert.setContentText("Non \u00E8 stato scelto nessun file");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                alert.close();
            } else alert.close();
        }
        if (selectedFile != null) controller.loadInstructions(selectedFile.getAbsolutePath());

        bottomPane.setVisible(true);
    }

    public void startExecution(ActionEvent actionEvent) {
        StringBuilder text = new StringBuilder();
        while (i < this.controller.getAllInstructions().size()) {
            controller.execute(controller.getAllInstructions().get(i));
            text.append(controller.getAllInstructions().get(i)).append("\n");
            i++;
        }
        instructionArea.appendText(text.toString());
        autoButton.setDisable(true);
        saveButton.setDisable(false);
        previousButton.setDisable(false);
        if (i == controller.getAllInstructions().size())
            nextButton.setDisable(true);
    }

    @Override
    public void MovedCursor(Point<Double> point) {
        Circle circle = (Circle) planePane.getChildren().get(1);
        circle.setCenterX(point.getX());
        circle.setCenterY(point.getY());
    }

    @Override
    public void GeneratedLine(Line<Point<Double>> line) {
        javafx.scene.shape.Line lineFX = new javafx.scene.shape.Line(line.getStartingPoint().getX(), line.getStartingPoint().getY(), line.getEndPoint().getX(), line.getEndPoint().getY());
        lineFX.setStroke(Color.rgb(line.getColor().getRed(), line.getColor().getGreen(), line.getColor().getBlue()));
        lineFX.setStrokeWidth(line.getSize());
        planePane.getChildren().add(lineFX);
        MovedCursor(line.getEndPoint());
    }

    @Override
    public void GeneratedArea(ClosedArea<Line<Point<Double>>> area) {
        int p = 0;
        double[] points = new double[area.getArea().size() * 4];
        for (Line<Point<Double>> l : area.getArea()) {
            points[p] = l.getStartingPoint().getX();
            p++;
            points[p] = l.getStartingPoint().getY();
            p++;
            points[p] = l.getEndPoint().getX();
            p++;
            points[p] = l.getEndPoint().getY();
            p++;
        }
        Polygon polygon = new Polygon(points);
        polygon.setFill(Color.rgb(area.getColor().getRed(), area.getColor().getGreen(), area.getColor().getBlue()));
        planePane.getChildren().add(polygon);
    }

    @Override
    public void ScreenColor(RGBColor color) {
        Rectangle rectangle = new Rectangle(planePane.getWidth(), planePane.getHeight());
        rectangle.setFill(Color.rgb(color.getRed(), color.getGreen(), color.getBlue()));
        planePane.getChildren().set(0, rectangle);
    }

    @Override
    public void ScreenCleaned() {
        planePane.getChildren().remove(2, planePane.getChildren().size());
        controller.clearNext();
    }

    public void previousPlane(ActionEvent actionEvent) {
        if (controller.hasPrevious()) {
            i--;
            nextButton.setDisable(false);
            autoButton.setDisable(false);
            configurationPlane(controller.previous());
            appendConfigurationInstructions();
            if (!controller.hasPrevious())
                previousButton.setDisable(true);
        }
    }

    public void nextPlane(ActionEvent actionEvent) {
        previousButton.setDisable(false);
        if (controller.hasNext())
            configurationPlane(controller.next());
        else if (i < this.controller.getAllInstructions().size())
            controller.execute(controller.getAllInstructions().get(i));
        i++;
        appendConfigurationInstructions();
        if (i == controller.getAllInstructions().size()) {
            nextButton.setDisable(true);
            autoButton.setDisable(true);
            saveButton.setDisable(false);
        }
    }

    private void appendConfigurationInstructions() {
        StringBuilder text = new StringBuilder();
        if (!controller.getConfigurationInstructions().isEmpty())
            for (String s : controller.getConfigurationInstructions())
                text.append(s).append("\n");
        instructionArea.setText(text.toString());
        instructionArea.positionCaret(instructionArea.getLength());
    }

    private void configurationPlane(Plane<Point<Double>> plane) {
        planePane.getChildren().remove(2, planePane.getChildren().size());
        MovedCursor(plane.getCursorPosition());
        ScreenColor(plane.getBackgroundColor());
        for (Line<Point<Double>> l : plane.getLines())
            GeneratedLine(l);
        for (ClosedArea<Line<Point<Double>>> a : plane.getClosedAreas())
            GeneratedArea(a);
    }

    public void reset(ActionEvent actionEvent) {
        i = 0;
        planePane.getChildren().remove(2, planePane.getChildren().size());
        controller.getPlane().removePlaneUpdateListener(this);
        controller.clear();
        MovedCursor(controller.getPlane().getHome());
        ScreenColor(controller.getPlane().getBackgroundColor());
        instructionArea.setText("");
        for (Integer executeTextFieldInstruction : executeTextFieldInstructions)
            this.controller.getAllInstructions().set(executeTextFieldInstruction, "");
        this.executeTextFieldInstructions.clear();
        autoButton.setDisable(false);
        previousButton.setDisable(true);
        saveButton.setDisable(true);
        nextButton.setDisable(false);
        controller.getPlane().addPlaneUpdateListener(this);
    }

    public void exit(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResource("/AppIcon.png")).toString()));
        alert.setTitle("Conferma chiusura");
        alert.setHeaderText("");
        alert.setContentText("Sei sicuro di voler chiudere il programma?");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.orElse(null) == ButtonType.OK)
            Platform.exit();
    }


    public void aboutAction(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.setHeight(500);
        stage.setWidth(500);
        stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResource("/AppIcon.png")).toString()));
        alert.setTitle("Istruzioni");
        alert.setHeaderText("");
        alert.setContentText("""
                Per prima cosa apri un file in formato txt che contenga le istruzioni logo. Per farlo premi File -> Carica un file.
                Dopodich\u00E8 , se vuoi che le istruzioni vengano lette ed eseguite in automatico, premi AUTO. Altrimenti,\040
                premi avanti per eseguirle una alla volta.\s
                Premi indietro per guardare la configurazione precedente.
                Premi azzera per resettare il piano e tutte le istruzioni mostrate.\040
                Sar\u00E0 necessario caricare un nuovo file di cui leggere le istruzioni.
                Infine, premi SALVA dopo aver completato la lettura delle istruzioni per salvare il contenuto del disegno come file.""");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            alert.close();
        } else alert.close();

    }

    public void saveConfiguration(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Specifica il nome del file da salvare");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Text Files", "*.txt"));
        File selectedFile = fileChooser.showSaveDialog(new Stage());
        bottomPane.setVisible(true);
        try {
            if (selectedFile != null) {
                if (controller.createLOGOFile(selectedFile.getAbsolutePath())) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                    stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResource("/AppIcon.png")).toString()));
                    alert.setTitle("Salvataggio riuscito");
                    alert.setHeaderText("");
                    alert.setContentText("File creato con successo.");
                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.isPresent() && result.get() == ButtonType.OK) {
                        alert.close();
                    } else alert.close();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
