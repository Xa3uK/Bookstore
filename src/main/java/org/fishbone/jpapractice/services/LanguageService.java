package org.fishbone.jpapractice.services;

import java.util.List;
import org.fishbone.jpapractice.models.Cover;
import org.fishbone.jpapractice.models.Language;
import org.fishbone.jpapractice.repositories.CoverRepository;
import org.fishbone.jpapractice.repositories.LanguageRepository;
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

    public List<Language> findAll() {
        return languageRepository.findAll();
    }

    public int getIdByName(String name) {
        return languageRepository.findLanguageByName(name).getId();
    }
}
