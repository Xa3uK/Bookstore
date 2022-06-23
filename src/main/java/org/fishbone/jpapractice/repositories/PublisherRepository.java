package org.fishbone.jpapractice.repositories;

import org.fishbone.jpapractice.models.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PublisherRepository extends JpaRepository<Publisher, Integer> {
    Publisher findPublisherByName(String name);
}
