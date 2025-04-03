package com.fileStoringInDB.StoreFileInDB.model;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name="files")
public class FileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String name;

    private String type;

    private byte[] data;

    public FileEntity() {
    }

    public FileEntity(String name, String type, byte[] data) {
        this.name = name;
        this.type = type;
        this.data = data;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getData() {
        return data;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
}
