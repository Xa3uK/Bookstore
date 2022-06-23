package org.fishbone.jpapractice.repositories;

import org.fishbone.jpapractice.models.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LanguageRepository extends JpaRepository<Language, Integer> {
   Language findLanguageByName(String name);
}
