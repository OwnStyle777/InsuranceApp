package com.example.InsuranceApplication.client;

import jakarta.persistence.*;

import java.util.Arrays;

@Entity
@Table(name = "user_profile_picture")
public class ClientProfilePicture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "image_id")
    private Long imageId;

    @Override
    public String toString() {
        return "ClientProfilePicture{" +
                "id=" + id +
                ", imageId=" + imageId +
                ", imageData=" + Arrays.toString(imageData) +
                '}';
    }

    @Column(name = "image_data")
    private byte[] imageData;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getImageId() {
        return imageId;
    }

    public void setImageId(Long imageId) {
        this.imageId = imageId;
    }

    public byte[] getImageData() {
        return imageData;
    }

    public void setImageData(byte[] imageData) {
        this.imageData = imageData;
    }
}
