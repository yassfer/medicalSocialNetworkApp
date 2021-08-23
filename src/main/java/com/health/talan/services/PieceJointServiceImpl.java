package com.health.talan.services;

import com.health.talan.repositories.PieceJointRepo;
import com.health.talan.entities.PieceJoint;
import com.health.talan.entities.Publication;
import com.health.talan.services.serviceInterfaces.PieceJointService;
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
    private ChallengeService challengeService;

    @Autowired
    public PieceJointServiceImpl(PieceJointRepo pieceJointRepo, PublicationServiceImpl publicationServiceImpl,
                                 ChallengeService challengeService){
        this.pieceJointRepo = pieceJointRepo;
        this.publicationServiceImpl = publicationServiceImpl;
        this.challengeService= challengeService;
    }


    @Override
    public PieceJoint store(MultipartFile pieceJoint, Long publicationId) throws IOException {
        String PieceJointName = StringUtils.cleanPath(pieceJoint.getOriginalFilename());
        PieceJoint pieceJoint1 = new PieceJoint(PieceJointName, pieceJoint.getContentType(),
                challengeService.compressBytes(pieceJoint.getBytes()), (int) pieceJoint.getSize());
        Optional<Publication> pub = publicationServiceImpl.getPublicationById(publicationId);
        pieceJoint1.setPublication(pub.get());

        return pieceJointRepo.save(pieceJoint1);
    }

    @Override
    public PieceJoint store2(MultipartFile pieceJoint) throws IOException {
        String PieceJointName = StringUtils.cleanPath(pieceJoint.getOriginalFilename());
        PieceJoint pieceJoint1 = new PieceJoint(PieceJointName, pieceJoint.getContentType(),
                challengeService.compressBytes(pieceJoint.getBytes()), (int) pieceJoint.getSize());
        return pieceJointRepo.save(pieceJoint1);
    }

    @Override
    public PieceJoint updatePieceJoint(PieceJoint pieceJoint, Long pubId){
        Optional<Publication> pub = publicationServiceImpl.getPublicationById(pubId);
        pieceJoint.setPublication(pub.get());
        //pieceJoint.setData(challengeService.decompressBytes(pieceJoint.getData()));
        return pieceJointRepo.save(pieceJoint);
    }

    @Override
    public PieceJoint updatePieceJoint2(PieceJoint pieceJoint){
        //pieceJoint.setData(challengeService.decompressBytes(pieceJoint.getData()));
        return pieceJointRepo.save(pieceJoint);
    }



    @Override
    public Optional<PieceJoint> getPieceJoint(Long id) {
        Optional<PieceJoint> pieceJoint = Optional.ofNullable(pieceJointRepo.findById(id)).orElse(null);
        //pieceJoint.get().setData(challengeService.decompressBytes(pieceJoint.get().getData()));
        return pieceJoint;
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
