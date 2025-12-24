package com.petapp.client.service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.petapp.client.model.Pet;
import okhttp3.*;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class PetApiService {
    private static final String BASE_URL = "http://localhost:8080/api/pets";
    private static final MediaType JSON = MediaType.get("application/json; charset=utf-8");
    
    private final OkHttpClient client;
    private final Gson gson;

    public PetApiService() {
        this.client = new OkHttpClient();
        this.gson = new Gson();
    }

    // Получить всех питомцев
    public List<Pet> getAllPets() throws IOException {
        Request request = new Request.Builder()
                .url(BASE_URL)
                .get()
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }
            
            String responseBody = response.body().string();
            Type listType = new TypeToken<ArrayList<Pet>>(){}.getType();
            return gson.fromJson(responseBody, listType);
        }
    }

    // Создать питомца
    public Pet createPet(Pet pet) throws IOException {
        String json = gson.toJson(pet);
        RequestBody body = RequestBody.create(json, JSON);
        
        Request request = new Request.Builder()
                .url(BASE_URL)
                .post(body)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }
            
            String responseBody = response.body().string();
            return gson.fromJson(responseBody, Pet.class);
        }
    }

    // Обновить питомца
    public Pet updatePet(Long id, Pet pet) throws IOException {
        String json = gson.toJson(pet);
        RequestBody body = RequestBody.create(json, JSON);
        
        Request request = new Request.Builder()
                .url(BASE_URL + "/" + id)
                .put(body)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }
            
            String responseBody = response.body().string();
            return gson.fromJson(responseBody, Pet.class);
        }
    }

    // Удалить питомца
    public void deletePet(Long id) throws IOException {
        Request request = new Request.Builder()
                .url(BASE_URL + "/" + id)
                .delete()
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }
        }
    }
}