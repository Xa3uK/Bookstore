package org.fishbone.jpapractice.services;

import java.util.List;
import org.fishbone.jpapractice.models.Language;
import org.fishbone.jpapractice.repositories.LanguageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class LanguageService {

    private final LanguageRepository languageRepository;

    @Autowired
    public LanguageService(LanguageRepository languageRepository) {
        this.languageRepository = languageRepository;
    }

    public List<Language> findAll() {
        return languageRepository.findAll();
    }

    @Transactional
    public Language findByNameOrSave(String name){
        if(languageRepository.findLanguageByName(name) == null){
            languageRepository.save(new Language(name));
        }
        return languageRepository.findLanguageByName(name);
    }
}
