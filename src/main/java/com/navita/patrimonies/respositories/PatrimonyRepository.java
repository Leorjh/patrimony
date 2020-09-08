package com.navita.patrimonies.respositories;

import com.navita.patrimonies.entities.Patrimony;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatrimonyRepository extends JpaRepository<Patrimony, Long> {
}
