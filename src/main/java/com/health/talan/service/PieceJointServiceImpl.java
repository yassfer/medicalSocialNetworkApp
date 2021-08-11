package com.health.talan.service;

import com.health.talan.Repository.PieceJointRepo;
import com.health.talan.entities.PieceJoint;
import com.health.talan.entities.Publication;
import com.health.talan.service.serviceInterfaces.PieceJointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
public class PieceJointServiceImpl implements PieceJointService {

    private PieceJointRepo pieceJointRepo;
    private PublicationServiceImpl publicationServiceImpl;


    @Autowired
    public PieceJointServiceImpl(PieceJointRepo pieceJointRepo, PublicationServiceImpl publicationServiceImpl){
        this.pieceJointRepo = pieceJointRepo;
        this.publicationServiceImpl = publicationServiceImpl;
    }


    @Override
    public PieceJoint store(MultipartFile pieceJoint, Long publicationId) throws IOException {
        String PieceJointName = StringUtils.cleanPath(pieceJoint.getOriginalFilename());
        PieceJoint pieceJoint1 = new PieceJoint(PieceJointName, pieceJoint.getContentType(), pieceJoint.getBytes(), (int) pieceJoint.getSize());
        Optional<Publication> pub = publicationServiceImpl.getPublicationById(publicationId);
        pieceJoint1.setPublication(pub.get());

        return pieceJointRepo.save(pieceJoint1);
    }

    @Override
    public PieceJoint updatePieceJoint(PieceJoint pieceJoint){
        return pieceJointRepo.save(pieceJoint);
    }



    @Override
    public Optional<PieceJoint> getPieceJoint(Long id) {
        return Optional.ofNullable(pieceJointRepo.findById(id)).orElse(null);
    }


    @Override
    public Stream<PieceJoint> getAllPieceJoints() {
        return pieceJointRepo.findAll().stream();
    }

    @Override
    public Optional<List<PieceJoint>> getAllPieceJointsByPubId(Long publicationId){
        return pieceJointRepo.findByPublicationId(publicationId);
    }

}
