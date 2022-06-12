package org.fishbone.jpapractice.repositories;

import org.fishbone.jpapractice.models.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PublisherRepository extends JpaRepository<Publisher, Integer> {
}
