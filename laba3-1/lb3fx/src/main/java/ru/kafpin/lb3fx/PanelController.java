package ru.kafpin.lb3fx;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.nio.file.FileSystems;
import java.nio.file.Files;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;

public class PanelController {

    @FXML
    private ComboBox<String> box;

    @FXML
    private TableColumn<FileInfo, String> filedate;

    @FXML
    private TableColumn<FileInfo, String> filename;

    @FXML
    private TableColumn<FileInfo, Long> filesize;

    @FXML
    private TableColumn<FileInfo, String> filetype;

    @FXML
    private TableView<FileInfo> table;

    @FXML
    private TextField text;

    @FXML
    private Button up;



    @FXML
    void initialize(){
        box.getItems().clear();
        for (Path p : FileSystems.getDefault().getRootDirectories()){
            box.getItems().add(p.toString());
        }
        box.getSelectionModel().select(0);

        updateList(Paths.get("."));

        filetype.setCellValueFactory(param ->
                new SimpleStringProperty(param.getValue().getType().getName()));
        filename.setCellValueFactory(param ->
                new SimpleStringProperty(param.getValue().getFileName()));
        filesize.setCellValueFactory(param ->
                new SimpleObjectProperty<>(param.getValue().getSize()));
        DateTimeFormatter dtf = DateTimeFormatter
                .ofPattern("yyyy-MM-dd HH:mm:ss");
        filedate.setCellValueFactory(param ->
                new SimpleStringProperty(
                        param.getValue().getLastModified().format(dtf))
        );
        table.getSortOrder().add(filetype);

        filesize.setCellFactory(column -> new TableCell<>() {
            @Override
            protected void updateItem(Long aLong, boolean empty) {
                super.updateItem(aLong, empty);
                if (aLong == null || empty) {
                    setText(null);
                    setStyle("");
                } else {
                    String text = String.format("%,d байт", aLong);
                    if (aLong == -1) {
                        text = "[DIR]";
                    }
                    setText(text);
                }
            }
        });
    }

    public void updateList(Path path){
        table.getItems().clear();
        try {
            text.setText(
                    path.normalize().toAbsolutePath().toString()
            );
            table
                    .getItems()
                    .addAll(Files
                            .list(path)
                            .map(FileInfo::new)
                            . collect(Collectors.toList()));
            table.sort();
        } catch (IOException e) {
            Alert alert = new Alert(
                    Alert.AlertType.WARNING,
                    "Не удалось обновить список файлов",
                    ButtonType.OK);
            alert.showAndWait();
        }
    }

    @FXML
    void onUp(ActionEvent event) {
        Path upperPath =
                Paths.get(text.getText()).getParent();
        if (upperPath != null) {
            updateList(upperPath);
        }
    }

    @FXML
    void onTable(MouseEvent event) {
        if (event.getClickCount() == 2){
            Path path = Paths.get(
                            text
                                    .getText())
                    .resolve(table
                            .getSelectionModel()
                            .getSelectedItem()
                            .getFileName());
            if(Files.isDirectory(path)){
                updateList(path);
            }
        }
    }

    @FXML
    void onSelect(ActionEvent event) {
        updateList(Paths
                .get(box.getSelectionModel().getSelectedItem()));
    }

    public String getSelectedFilename() {
        if (table.isFocused()) {
            if(table.getSelectionModel() != null && table.getSelectionModel().getSelectedItem() != null){
                return table.getSelectionModel().getSelectedItem().getFileName();
            }
        }
        return null;
    }

    public String getCurrentPath() {
        return text.getText();
    }

}
