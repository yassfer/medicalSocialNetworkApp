package com.health.talan.service.serviceInterfaces;

import com.health.talan.entities.PieceJoint;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;
import java.util.stream.Stream;

public interface PieceJointService {

    public PieceJoint store(MultipartFile pieceJoint) throws IOException;

    public Optional<PieceJoint> getPieceJoint(Long id);

    public Stream<PieceJoint> getAllPieceJoints();


}
