package com.health.talan.Repository;

import com.health.talan.entities.PieceJoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PieceJointRepo extends JpaRepository<PieceJoint, Long> {
}
