package com.securesidences.entry_exit_flow.Repository;

import com.securesidences.entry_exit_flow.Model.Resident;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResidentRepository extends JpaRepository <Resident, Long> {
}
