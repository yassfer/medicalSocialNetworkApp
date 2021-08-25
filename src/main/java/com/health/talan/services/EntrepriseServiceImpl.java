package com.health.talan.services;

import com.health.talan.entities.Entreprise;
import com.health.talan.entities.User;
import com.health.talan.repositories.EntrepriseRepository;
import com.health.talan.repositories.UserRepository;
import com.health.talan.services.serviceInterfaces.EntrepriseServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

@Service
public class EntrepriseServiceImpl implements EntrepriseServices {

    @Autowired

    public EntrepriseRepository entrepriseRepository ;
    UserRepository userRepository;
    AuthService authService;

    @Autowired
    public EntrepriseServiceImpl(EntrepriseRepository entrepriseRepository, UserRepository userRepository,
                                 AuthService authService) {
        this.entrepriseRepository = entrepriseRepository;
        this.userRepository = userRepository;
        this.authService = authService;
    }

    @Override
    public List<Entreprise> GetAllEntreprise() throws IOException {

        return (List<Entreprise>)entrepriseRepository.findAll();
    }
    @Override
    public Entreprise setEntreprise (Entreprise ent) throws IOException {
        entrepriseRepository.save(ent);
        return ent;
    }

    @Override
    public String deleteEntreprise (long id) throws IOException {
        entrepriseRepository.deleteById(id);
        return "Entreprise Deleted";
    }


    // Add entreprise with only joint piece
    public Entreprise addEntrepriseWithPiece(Long idAdmin, MultipartFile file) throws IOException {
        Entreprise Entreprise = new Entreprise();
        User user = userRepository.findById(idAdmin).get();
        Entreprise.setImage(compressBytes(file.getBytes()));
        Entreprise.setUser(user);
        entrepriseRepository.save(Entreprise);
        return Entreprise;
    }


    // Display By Admin entreprise
    @Override
    public Entreprise getByAdmin() throws IOException {
        User user = authService.getCURRENTUSER();
        Entreprise Entreprise = (Entreprise) entrepriseRepository.findByAdminEntreprise(user);
        Entreprise.setImage(decompressBytes(Entreprise.getImage()));
        return Entreprise;
    }

    // Display all
    @Override
    public List<Entreprise> getAll() throws IOException {
        List<Entreprise> Entreprises = (List<Entreprise>) entrepriseRepository.findAll();
        for (Entreprise Entreprise : Entreprises) {
            Entreprise.setImage(Entreprise.getImage());
        }
        return Entreprises;
    }

    // Save Entreprise
    public void saveEntreprise(Long id, Entreprise Entreprise) {
        Entreprise EntrepriseN = entrepriseRepository.findById(id).get();
        EntrepriseN.setNom(Entreprise.getNom());
        EntrepriseN.setAddress(Entreprise.getAddress());
        EntrepriseN.setCode(Entreprise.getCode());
        EntrepriseN.setExpiration(Entreprise.getExpiration());
        EntrepriseN.setProduct(Entreprise.getProduct());
        EntrepriseN.setReduction(Entreprise.getReduction());
        EntrepriseN.setDatecreation(Entreprise.getDatecreation());
        entrepriseRepository.save(EntrepriseN);
    }


    // uplaodImage
    public void uplaodImage(String id, MultipartFile file) throws IOException {
        long idEntreprise = Long.parseLong(id);
        System.out.println("Original Image Byte Size - " + file.getBytes().length);
        Entreprise Entreprise = entrepriseRepository.findById(idEntreprise).get();
        Entreprise.setImage(compressBytes(file.getBytes()));
        entrepriseRepository.save(Entreprise);
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
