package com.jetbrains.milena.photoz.storage.web;

import com.jetbrains.milena.photoz.storage.model.Photo;
import com.jetbrains.milena.photoz.storage.service.PhotoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.*;

@RestController
public class PhotosController {

    private final PhotoService photoService;

    public PhotosController(PhotoService photoService) {
        this.photoService = photoService;
    }

    @GetMapping("/")
    public  String Hello(){
        return "Hello";
    }

    @GetMapping("/photos") //returning all my photos
    public Collection<Photo> get(){
        return photoService.values();
    }

    //getting a specific photo
    @GetMapping("/photos/{id}")
    public Photo get(@PathVariable String id){ //passing the id as a parameter with pathvar, of course only works if i have a photo available
        Photo photo = photoService.get(id);

        if(photo == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        return photo;
    }

    //deleting photos
    @DeleteMapping("/photos/{id}")
    public void delete(@PathVariable String id){
        Photo photo = photoService.remove(id);
        if(photo == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);

    }

    //post new photos
    @PostMapping("/photos")
    public Photo create(@RequestPart("data") MultipartFile file) throws IOException {
        return  photoService.save(file.getOriginalFilename(), file.getContentType(), file.getBytes());

    }
}
