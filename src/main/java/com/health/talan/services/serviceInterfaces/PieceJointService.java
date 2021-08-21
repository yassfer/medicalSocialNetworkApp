package com.health.talan.services.serviceInterfaces;

import com.health.talan.entities.PieceJoint;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public interface PieceJointService {

    public PieceJoint store(MultipartFile pieceJoint, Long publicationId) throws IOException;

    public Optional<PieceJoint> getPieceJoint(Long id);

    public Stream<PieceJoint> getAllPieceJoints();

    public Optional<List<PieceJoint>> getAllPieceJointsByPubId(Long publicationId);

    public PieceJoint updatePieceJoint(PieceJoint pieceJoint, Long pubId);

    public PieceJoint store2(MultipartFile pieceJoint) throws IOException;

    public PieceJoint updatePieceJoint2(PieceJoint pieceJoint);


}
