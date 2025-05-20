package com.skills.indicator.repository;


import com.skills.indicator.entity.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface ActivityRepository extends JpaRepository <Activity, Integer> {


}
