package com.health.talan.service;

import com.health.talan.Repository.PieceJointRepo;
import com.health.talan.entities.PieceJoint;
import com.health.talan.service.serviceInterfaces.PieceJointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;
import java.util.stream.Stream;

@Service
public class PieceJointServiceImpl implements PieceJointService {

    private PieceJointRepo pieceJointRepo;


    @Autowired
    public PieceJointServiceImpl(PieceJointRepo pieceJointRepo){
        this.pieceJointRepo = pieceJointRepo;
    }


    @Override
    public PieceJoint store(MultipartFile pieceJoint) throws IOException {
        String PieceJointName = StringUtils.cleanPath(pieceJoint.getOriginalFilename());
        PieceJoint pieceJoint1 = new PieceJoint(PieceJointName, pieceJoint.getContentType(), pieceJoint.getBytes(), pieceJoint.getSize());

        return pieceJointRepo.save(pieceJoint1);
    }


    @Override
    public Optional<PieceJoint> getPieceJoint(Long id) {
        return Optional.ofNullable(pieceJointRepo.findById(id)).orElse(null);
    }


    @Override
    public Stream<PieceJoint> getAllPieceJoints() {
        return pieceJointRepo.findAll().stream();
    }
}
