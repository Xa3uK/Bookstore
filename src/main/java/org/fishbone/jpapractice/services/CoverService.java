package org.fishbone.jpapractice.services;

import java.util.List;
import org.fishbone.jpapractice.models.Cover;
import org.fishbone.jpapractice.repositories.CoverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class CoverService {

    private final CoverRepository coverRepository;

    @Autowired
    public CoverService(CoverRepository coverRepository) {
        this.coverRepository = coverRepository;
    }

    public List<Cover> findAll() {
        return coverRepository.findAll();
    }

    @Transactional
    public Cover findByNameOrSave(String name){
        if (coverRepository.findCoverByName(name) == null){
            coverRepository.save(new Cover(name));
        }
        return coverRepository.findCoverByName(name);
    }
}
