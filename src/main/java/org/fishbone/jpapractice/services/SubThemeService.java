package org.fishbone.jpapractice.services;

import java.util.List;
import org.fishbone.jpapractice.models.SubTheme;
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

    public List<SubTheme> findAll() {
        return subThemeRepository.findAll();
    }

    @Transactional
    public int getIdByName(String name) {
        if (subThemeRepository.findSubThemeByName(name) == null) {
            SubTheme subTheme = new SubTheme();
            subTheme.setName(name);
            subThemeRepository.save(subTheme);
        }
        return subThemeRepository.findSubThemeByName(name).getId();
    }
}
