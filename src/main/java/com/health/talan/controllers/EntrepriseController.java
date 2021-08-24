package com.health.talan.controllers;

import com.health.talan.entities.Entreprise;
import com.health.talan.repositories.EntrepriseRepository;
import com.health.talan.repositories.UserRepository;
import com.health.talan.services.serviceInterfaces.EntrepriseServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

public class EntrepriseController {
    @Autowired
    private EntrepriseServices entrepriseService;
    @Autowired
    private EntrepriseRepository EntrepriseRepository;
    @Autowired
    UserRepository userRepository;


    @GetMapping("getAll")
    public List<Entreprise> getEntreprise() throws IOException {
        return entrepriseService.GetAllEntreprise();

    }

    @PostMapping("setEntreprise")
    public Entreprise setMessage(@RequestBody Entreprise entp)  throws IOException {
        entrepriseService.setEntreprise(entp);
        return entp;

    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteEntreprise(@PathVariable("id") Long id) throws IOException {

        String message = entrepriseService.deleteEntreprise(id);
        if (message.equals("Entreprise Deleted")) {
            return new ResponseEntity<>(message, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }
    }

	/*


	@PreAuthorize("hasAuthority('USER')")
	@PutMapping("save/{id}")
	public void saveEntreprise(@PathVariable("id") Long id, @RequestBody Entreprise Entreprise) {
		System.out.println(Entreprise);
		entrepriseService.saveEntreprise(id, Entreprise);
	}


	@PreAuthorize("hasAuthority('USER')")
	@PostMapping(value = "addEntreprise/{id}", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.MULTIPART_FORM_DATA_VALUE })
	public Entreprise addEntreprise(@PathVariable("id") Long id, @RequestParam("imageFile") MultipartFile file)
			throws Exception {
		return entrepriseService.addEntrepriseWithPiece(id,file);
	}


	@PreAuthorize("hasAuthority('USER')")
	@GetMapping("getAll")
	public List<Entreprise> displayAll() throws IOException {
		return entrepriseService.getAll();
	}

	@PreAuthorize("hasAuthority('USER')")
	@GetMapping("getMyEntreprise")
	public Entreprise displayByAdmin() throws IOException {
		return entrepriseService.getByAdmin();
	}


	*/
}
