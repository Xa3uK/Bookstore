package org.fishbone.jpapractice.services;

import java.util.List;
import org.fishbone.jpapractice.models.Cover;
import org.fishbone.jpapractice.models.Publisher;
import org.fishbone.jpapractice.repositories.CoverRepository;
import org.fishbone.jpapractice.repositories.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class CoverService {

  CoverRepository coverRepository;

  @Autowired
    public CoverService(CoverRepository coverRepository) {
        this.coverRepository = coverRepository;
    }

    public List<Cover> findAll(){
        return coverRepository.findAll();
    }


    public int getIdByName(String name){
            return coverRepository.findCoverByName(name).getId();
    }
}
