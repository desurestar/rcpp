package ru.kafpin.lb3fx;

import javafx.scene.control.Alert;

import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import javafx.scene.control.ButtonType;

public class FileInfo {
    private String fileName;
    private FileType type;
    private long size;
    private LocalDateTime lastModified;

    public enum FileType {
        FILE("F"), DIRECTORY("D");
        private String name;
        FileType(String name) {
            this.name = name;
        }
        public String getName() {
            return name;
        }
    }

    public FileInfo(Path path) {
        try {
            this.fileName = path.getFileName().toString();
            this.size = Files.size(path);
            this.type = Files.isDirectory(path) ?
                    FileType.DIRECTORY : FileType.FILE;
            if (this.type == FileType.DIRECTORY) {
                this.size = -1L;
            }
            this.lastModified = LocalDateTime.ofInstant(
                    Files.getLastModifiedTime(path).toInstant(),
                    ZoneOffset.ofHours(3)
            );
        }
        catch (Exception ex){
            Alert alert = new Alert(
                    Alert.AlertType.ERROR,
                    "Ошибка обращения к файлу.",
                    ButtonType.OK
            );
            alert.showAndWait();
        }
    }


    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public FileType getType() {
        return type;
    }

    public void setType(FileType type) {
        this.type = type;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public LocalDateTime getLastModified() {
        return lastModified;
    }

    public void setLastModified(LocalDateTime lastModified) {
        this.lastModified = lastModified;
    }
}
