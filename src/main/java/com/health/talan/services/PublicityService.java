package com.health.talan.services;

import java.io.ByteArrayOutputStream;

import java.io.IOException;
import java.util.List;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.health.talan.entities.Publicity;
import com.health.talan.entities.User;
import com.health.talan.repositories.PublicityRepository;
import com.health.talan.repositories.UserRepository;
@Service
public class PublicityService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PublicityRepository publicityRepository;


    public Long addPublicity(Long id, Publicity publicity) {
        User user = userRepository.findById(id).get();
        publicity.setUser(user);
        publicityRepository.save(publicity);
        return publicity.getId();
    }

    public void deletePublicity(Long id) {
        publicityRepository.deleteById(id);
    }

    public void updatePublicity(Long id, Publicity publicity) {
        Publicity oldPublicity = publicityRepository.findById(id).get();
        oldPublicity.setDescription(publicity.getDescription());
        oldPublicity.setImage(publicity.getImage());
        publicityRepository.save(oldPublicity);
    }

    public Publicity displayById(Long id) {
        return publicityRepository.findById(id).get();
    }

    // uplaodImage
    public void uplaodImage(String id, MultipartFile file) throws IOException {
        long idPublicity = Long.parseLong(id);
        System.out.println("Original Image Byte Size - " + file.getBytes().length);
        Publicity publicity = publicityRepository.findById(idPublicity).get();
        publicity.setImage(compressBytes(file.getBytes()));
        publicityRepository.save(publicity);
    }

    // Display all
    public List<Publicity> getAll() throws IOException {
        List<Publicity> publicities = (List<Publicity>) publicityRepository.findAll();
        for (Publicity publicity : publicities) {
            publicity.setImage(decompressBytes(publicity.getImage()));
        }
        return publicities;
    }

    // compress the image bytes before storing it in the database
    public static byte[] compressBytes(byte[] data) {
        Deflater deflater = new Deflater();
        deflater.setInput(data);
        deflater.finish();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        while (!deflater.finished()) {
            int count = deflater.deflate(buffer);
            outputStream.write(buffer, 0, count);
        }
        try {
            outputStream.close();
        } catch (IOException e) {
        }
        System.out.println("Compressed Image Byte Size - " + outputStream.toByteArray().length);

        return outputStream.toByteArray();
    }

    // uncompress the image bytes before returning it to the angular application
    public static byte[] decompressBytes(byte[] data) {
        Inflater inflater = new Inflater();
        inflater.setInput(data);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        try {
            while (!inflater.finished()) {
                int count = inflater.inflate(buffer);
                outputStream.write(buffer, 0, count);
            }
            outputStream.close();
        } catch (IOException ioe) {
        } catch (DataFormatException e) {
        }
        return outputStream.toByteArray();
    }
}
