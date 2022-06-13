package org.fishbone.jpapractice.services;

import java.util.List;
import java.util.Optional;
import org.fishbone.jpapractice.models.Book;
import org.fishbone.jpapractice.models.Publisher;
import org.fishbone.jpapractice.repositories.BookRepository;
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

    public List<Publisher> findAll(){
        return publisherRepository.findAll();
    }


    public int getIdByName(String name){
            return publisherRepository.findPublisherByName(name).getId();
    }
}
