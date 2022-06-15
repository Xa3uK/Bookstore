package org.fishbone.jpapractice.repositories;

import org.fishbone.jpapractice.models.SubTheme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubThemeRepository extends JpaRepository<SubTheme, Integer> {
    SubTheme findSubThemeByName(String name);
}
