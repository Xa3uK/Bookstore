package org.fishbone.jpapractice.services;

import java.util.List;
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

    public List<Publisher> findAll() {
        return publisherRepository.findAll();
    }

    @Transactional
    public int getIdByName(String name) {
        if (publisherRepository.findPublisherByName(name) == null) {
            Publisher publisher = new Publisher();
            publisher.setName(name);
            publisherRepository.save(publisher);
        }
        return publisherRepository.findPublisherByName(name).getId();
    }
}
