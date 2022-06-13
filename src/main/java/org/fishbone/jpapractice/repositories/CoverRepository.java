package org.fishbone.jpapractice.repositories;

import org.fishbone.jpapractice.models.Cover;
import org.fishbone.jpapractice.models.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoverRepository extends JpaRepository<Cover, Integer> {
    Cover findCoverByName(String name);
}
