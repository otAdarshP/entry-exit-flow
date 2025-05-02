package com.securesidences.entry_exit_flow.Repository;

import com.securesidences.entry_exit_flow.Model.Resident;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ResidentRepository extends JpaRepository <Resident, Long> {

    Optional<Resident> findByResidentId (Long Id);
    Optional<Resident> findByResidentName (String name);

}
