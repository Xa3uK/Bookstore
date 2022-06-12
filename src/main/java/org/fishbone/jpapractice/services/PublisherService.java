package org.fishbone.jpapractice.services;

import org.fishbone.jpapractice.models.Publisher;
import org.fishbone.jpapractice.repositories.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class PublisherService {
    PublisherRepository publisherRepository;

    @Autowired
    public PublisherService(PublisherRepository publisherRepository) {
        this.publisherRepository = publisherRepository;
    }

    public Publisher findById(int id){
        return publisherRepository.findById(id).orElse(null);
    }
}
