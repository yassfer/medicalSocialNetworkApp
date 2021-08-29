package com.health.talan.services;

import com.health.talan.entities.*;
import com.health.talan.repositories.*;
import com.health.talan.services.serviceInterfaces.PublicationCommunityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

@Service
public class PublicationCommunityServiceImpl implements PublicationCommunityService {

    private final PublicationCommunityRepo publicationCommunityRepo;
    private final UserServiceImpl userServiceImpl;
    private final LikingRepo likingRepo;
    private final CommentRepo commentRepo;
    private final PieceJointRepo pieceJointRepo;
    private final CommunityServiceImplémentation communityServiceImplémentation;

    @Autowired
    public PublicationCommunityServiceImpl(PublicationCommunityRepo publicationCommunityRepo, UserServiceImpl userServiceImpl,
                                  LikingRepo likingRepo, CommentRepo commentRepo, PieceJointRepo pieceJointRepo,
                                           CommunityServiceImplémentation communityServiceImplémentation){

        this.publicationCommunityRepo = publicationCommunityRepo;
        this.userServiceImpl = userServiceImpl;
        this.likingRepo = likingRepo;
        this.commentRepo = commentRepo;
        this.pieceJointRepo = pieceJointRepo;
        this.communityServiceImplémentation = communityServiceImplémentation;
    }

    @Override
    public List<PublicationCommunity> getAllPublications() {

        return publicationCommunityRepo.findAll();
    }


    @Override
    public Optional<PublicationCommunity> getPublicationById(Long id){

        return Optional.ofNullable(publicationCommunityRepo.findById(id)).orElse(null);
    }


    @Override
    public Optional<List<PublicationCommunity>> getPublicationByUserId(Long userId){

        return Optional.ofNullable(publicationCommunityRepo.findByUserId(userId)).orElse(null);
    }


    @Override
    public PublicationCommunity addPublicationCommunity(PublicationCommunity publicationCommunity, Long communityId, Long userId) throws IOException {

        Community community = communityServiceImplémentation.getCommunityById(communityId);
        Optional<User> user = userServiceImpl.getUserById(userId);

        publicationCommunity.setCommunity(community);
        publicationCommunity.setUser(user.get());

        PublicationCommunity newPublicationCommunity = publicationCommunityRepo.save(publicationCommunity);

        return newPublicationCommunity;

    }


    @Override
    public PublicationCommunity updatePublication(PublicationCommunity publication) {
        PublicationCommunity newPublication = publicationCommunityRepo.save(publication);

        return newPublication;
    }

    @Override
    public Optional<List<PublicationCommunity>> getPublicationCommunityByCommunityId(Long communityId){

        return Optional.ofNullable(publicationCommunityRepo.findByCommunityId(communityId)).orElse(null);
    }


    @Override
    public String deletePublication(Long id){
        Optional<PublicationCommunity> publication = publicationCommunityRepo.findById(id);
        if(publication.isPresent()){
            likingRepo.deleteByPublicationCommunityId(id);
            commentRepo.deleteByPublicationCommunityId(id);
            pieceJointRepo.deleteByPublicationCommunityId(id);
            publicationCommunityRepo.deletePublicationById(id);
            return "Publication Deleted";
        }
        else {
            return "Publication doesn't exit";
        }
    }

    public List<PublicationCommunity> getAllByDate(){

        return publicationCommunityRepo.getAllByDate();
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
