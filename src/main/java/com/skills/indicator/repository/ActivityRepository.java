package com.skills.indicator.repository;


import com.skills.indicator.entity.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface ActivityRepository extends JpaRepository <Activity, Integer> {

    @Query("SELECT (a.sport + a.education + a.others + a.english + a.reading + a.writeOff) " +
            "FROM Activity a WHERE a.user.id = :userId")
    List<Integer> findAllActivityByUserId(@Param("userId") Integer userId);

}
