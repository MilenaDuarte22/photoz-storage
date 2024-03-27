package com.jetbrains.milena.photoz.storage.web;

import com.jetbrains.milena.photoz.storage.model.Photo;
import com.jetbrains.milena.photoz.storage.service.PhotoService;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

//this class is a controller to download
@RestController
public class DownloadController {

    private final PhotoService photoService;

    public DownloadController(PhotoService photoService) {
        this.photoService = photoService;
    }


    @GetMapping("/download/{id}") //we're going to send back raw bytes to the browser
    public ResponseEntity<byte[]> download(@PathVariable String id){

        Photo photo = photoService.get(id);
        if(photo == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        byte[] data = photo.getPhotoData();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.valueOf(photo.getContentType()));

        ContentDisposition build = ContentDisposition.builder("attachment").filename(photo.getFileName()).build();
        headers.setContentDisposition(build);

        return new ResponseEntity<>(data, headers, HttpStatus.OK);
    }
}
