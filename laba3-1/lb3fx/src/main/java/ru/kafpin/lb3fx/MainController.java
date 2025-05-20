package ru.kafpin.lb3fx;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class MainController {
    @FXML
    private PanelController leftPanelController;
    @FXML
    private PanelController rightPanelController;

    @FXML
    public void onCopy(ActionEvent actionEvent) {
        checkSelect();

        PanelController src = null, dst = null;
        if(leftPanelController.getSelectedFilename() != null){
            src = leftPanelController;
            dst = rightPanelController;
        } else {
            src = rightPanelController;
            dst = leftPanelController;
        }

        Path srcPath = Paths.get(
                src.getCurrentPath(), src.getSelectedFilename()
        );

        Path dstPath = Paths.get(
                        dst.getCurrentPath())
                .resolve(srcPath.getFileName()
                );

        try {
            Files.copy(srcPath, dstPath,
                    StandardCopyOption.REPLACE_EXISTING);
            dst.updateList(Paths.get(dst.getCurrentPath()));
            src.updateList(Paths.get(src.getCurrentPath()));
        } catch (IOException e) {
            Alert alert = new Alert(
                    Alert.AlertType.ERROR,
                    "Ошибка копирования файла.",
                    ButtonType.OK
            );
            alert.showAndWait();
        }
    }

    @FXML
    public void onMove(ActionEvent actionEvent) {
        checkSelect();

        PanelController src = null, dst = null;
        if(leftPanelController.getSelectedFilename() != null){
            src = leftPanelController;
            dst = rightPanelController;
        } else {
            src = rightPanelController;
            dst = leftPanelController;
        }

        Path srcPath = Paths.get(
                src.getCurrentPath(), src.getSelectedFilename()
        );

        Path dstPath = Paths.get(
                        dst.getCurrentPath())
                .resolve(srcPath.getFileName()
                );

        try {
            Files.move(srcPath, dstPath,
                    StandardCopyOption.REPLACE_EXISTING);
            dst.updateList(Paths.get(dst.getCurrentPath()));
            src.updateList(Paths.get(src.getCurrentPath()));
        } catch (IOException e) {
            Alert alert = new Alert(
                    Alert.AlertType.ERROR,
                    "Ошибка перемещения файла.",
                    ButtonType.OK
            );
            alert.showAndWait();
        }
    }

    @FXML
    public void onDelete(ActionEvent actionEvent) {
        checkSelect();

        PanelController src = null;
        if(leftPanelController.getSelectedFilename() != null){
            src = leftPanelController;
        } else {
            src = rightPanelController;
        }

        Path srcPath = Paths.get(
                src.getCurrentPath(), src.getSelectedFilename()
        );

        try {
            Files.delete(srcPath);
            src.updateList(Paths.get(src.getCurrentPath()));
        } catch (IOException e) {
            Alert alert = new Alert(
                    Alert.AlertType.ERROR,
                    "Ошибка удаления файла.",
                    ButtonType.OK
            );
            alert.showAndWait();
        }
    }

    @FXML
    public void onExit(ActionEvent actionEvent) {
        Platform.exit();
    }

    public void checkSelect(){
        if(leftPanelController.getSelectedFilename() == null
                && rightPanelController.getSelectedFilename() == null) {
            Alert alert = new Alert(
                    Alert.AlertType.WARNING,
                    "Файл не выбран",
                    ButtonType.OK);
            alert.showAndWait();
            return;
        }
    }

    @FXML
    public void onAbout(ActionEvent actionEvent) {
        Alert alert = new Alert(
                Alert.AlertType.INFORMATION,
                "Лабораторная работа номер 3. Файловый менеджер.",
                ButtonType.OK
        );
        alert.showAndWait();
    }
}
