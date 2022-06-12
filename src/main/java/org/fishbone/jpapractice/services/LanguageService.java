package org.fishbone.jpapractice.services;

import org.fishbone.jpapractice.models.Language;
import org.fishbone.jpapractice.models.Publisher;
import org.fishbone.jpapractice.repositories.LanguageRepository;
import org.fishbone.jpapractice.repositories.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class LanguageService {

    LanguageRepository languageRepository;

    @Autowired
    public LanguageService(LanguageRepository languageRepository) {
        this.languageRepository = languageRepository;
    }

    public Language findById(int id) {
        return languageRepository.findById(id).orElse(null);
    }
}
