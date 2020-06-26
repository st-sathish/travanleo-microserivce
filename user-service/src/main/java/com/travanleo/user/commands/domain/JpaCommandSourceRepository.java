package com.travanleo.user.commands.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaCommandSourceRepository extends JpaRepository<CommandSource, Long> {

}
