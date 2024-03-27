package com.jetbrains.milena.photoz.storage.service;


import com.jetbrains.milena.photoz.storage.model.Photo;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service //@Service - @Component is the same basically
public class PhotoService {
    private Map<String, Photo> db = new HashMap<>() {{
        put("1", new Photo());
    }};

    public Collection<Photo> values() {
        return db.values();
    }

    public Photo get(String id) {
        return db.get(id);
    }

    public Photo remove(String id) {
        return db.remove(id);
    }

    public Photo save(String fileName, String contentType, byte[] data) {
        Photo photo = new Photo(); //file in
        photo.setContentType(contentType);
        photo.setId(UUID.randomUUID().toString()); //file info
        photo.setFileName(fileName);
        photo.setPhotoData(data);
        db.put(photo.getId(), photo);
        return photo;
    }
}
