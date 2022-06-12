package org.fishbone.jpapractice.repositories;

import org.fishbone.jpapractice.models.Publisher;
import org.fishbone.jpapractice.models.SubTheme;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubThemeRepository extends JpaRepository<SubTheme, Integer> {
}
