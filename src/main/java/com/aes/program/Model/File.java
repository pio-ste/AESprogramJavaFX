package com.aes.program.Model;

public class File {

    private byte[] data;
    private String fileName;
    private String filePath;
    private String fileType;

    public File(byte[] data, String fileName, String filePath, String fileType) {
        this.data = data;
        this.fileName = fileName;
        this.filePath = filePath;
        this.fileType = fileType;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }
}
