package com.aes.program.Model;

import java.util.Collection;
import java.util.List;

public class Form {

    private byte[] key;
    private byte[] password;
    private List<File> files;
    private boolean multithread;

    public enum algorithm {
        AES,
        DES,
    }
    public enum cipherMode {
        ECB,
        CBC,
    }
    private int keyLength;

    public Form(byte[] key, byte[] password, List<File> files, boolean multithread, int keyLength) {
        this.key = key;
        this.password = password;
        this.files = files;
        this.multithread = multithread;
        this.keyLength = keyLength;
    }

    public Form() {

    }

    public byte[] getKey() {
        return key;
    }

    public void setKey(byte[] key) {
        this.key = key;
    }

    public byte[] getPassword() {
        return password;
    }

    public void setPassword(byte[] password) {
        this.password = password;
    }

    public List<File> getFiles() {
        return files;
    }

    public void setFiles(List<File> files) {
        this.files = files;
    }

    public boolean isMultithread() {
        return multithread;
    }

    public void setMultithread(boolean multithread) {
        this.multithread = multithread;
    }

    public int getKeyLength() {
        return keyLength;
    }

    public void setKeyLength(int keyLength) {
        this.keyLength = keyLength;
    }
}
