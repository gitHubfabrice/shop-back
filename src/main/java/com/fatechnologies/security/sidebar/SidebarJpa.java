package com.fatechnologies.security.sidebar;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SidebarJpa extends JpaRepository<Sidebar, Long> {

    @Query("SELECT DISTINCT child FROM Sidebar s JOIN s.childs child WHERE child.id = :childId")
    List<Sidebar> checkIfChild(@Param("childId") Long childId);


    @Query("SELECT DISTINCT m FROM Authority a JOIN a.sidebars m WHERE a.id = :authorityId")
    List<Sidebar> findAllByAuthotityId(@Param("authorityId") Long authorityId);


}
