package com.health.talan.services.serviceInterfaces;

import com.health.talan.entities.Entreprise;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface EntrepriseServices {
    List<Entreprise> GetAllEntreprise() throws IOException;
    Entreprise setEntreprise(Entreprise msg) throws IOException;
    String deleteEntreprise(long id) throws IOException;

    public void uplaodImage(String id, MultipartFile file) throws IOException;
    public Entreprise getByAdmin() throws IOException;
    public void saveEntreprise(Long id, Entreprise Entreprise);
    public Entreprise addEntrepriseWithPiece(Long idAdmin, MultipartFile file) throws IOException;

	/*
	public Long addEntreprise(Entreprise Entreprise);
	public void uplaodImage(String id, MultipartFile file) throws IOException;
	public Entreprise getByAdmin() throws IOException;
	public List<Entreprise> getAll() throws IOException;
	public void saveEntreprise(Long id, Entreprise Entreprise) ;
	public Entreprise addEntrepriseWithPiece(Long idAdmin, MultipartFile file) throws IOException ;*/


}