package org.fishbone.jpapractice.repositories;

import org.fishbone.jpapractice.models.Language;
import org.fishbone.jpapractice.models.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LanguageRepository extends JpaRepository<Language, Integer> {
}
