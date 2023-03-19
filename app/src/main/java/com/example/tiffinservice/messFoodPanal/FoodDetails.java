package com.example.tiffinservice.messFoodPanal;

public class FoodDetails {

    public String Dishes, Quantity,Price,Description,ImageURL,RandomUID,MessID;

    public FoodDetails(String dishes, String quantity, String price, String description, String imageURL, String randomUID, String messID) {
        Dishes = dishes;
        Quantity = quantity;
        Price = price;
        Description = description;
        ImageURL = imageURL;
        RandomUID = randomUID;
        MessID = MessID;
    }
}
