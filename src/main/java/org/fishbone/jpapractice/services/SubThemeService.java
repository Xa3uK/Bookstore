package org.fishbone.jpapractice.services;

import org.fishbone.jpapractice.models.Publisher;
import org.fishbone.jpapractice.models.SubTheme;
import org.fishbone.jpapractice.repositories.PublisherRepository;
import org.fishbone.jpapractice.repositories.SubThemeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class SubThemeService {
    SubThemeRepository subThemeRepository;

    @Autowired
    public SubThemeService(SubThemeRepository subThemeRepository) {
        this.subThemeRepository = subThemeRepository;
    }

    public SubTheme findById(int id){
        return subThemeRepository.findById(id).orElse(null);
    }
}
