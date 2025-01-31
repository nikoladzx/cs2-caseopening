package com.blabla.projekat.controllers;

import jakarta.annotation.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
public class ImageController {
    @GetMapping("/images/{filename}")    
    public ResponseEntity<UrlResource> getImage(@PathVariable String filename) {
        Path imagePath = Paths.get("F:/csgocase/slotgame1/" + filename);
        try {
            UrlResource resource = new UrlResource(imagePath.toUri());
            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_JPEG)
                    .body(resource);
        } catch (IOException e) {
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping("/images/upload")
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file) {
        try {
            byte[] bytes = file.getBytes();
            Path path = Paths.get("F:/csgocase/slotgame1/" + file.getOriginalFilename());
            Files.write(path, bytes);

            return ResponseEntity.ok(file.getOriginalFilename());
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Image upload failed: " + e.getMessage());

        }}}