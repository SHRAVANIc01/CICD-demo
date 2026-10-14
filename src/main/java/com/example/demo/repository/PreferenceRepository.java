package com.example.demo.repository;



import com.example.demo.entity.Preference;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.entity.Member;
@Repository
public interface PreferenceRepository extends JpaRepository<Preference, Long> {

    // Find preference by Member entity
    Optional<Preference> findByMember(Member member);

    // OR find by member's id directly
    Preference findByMember_Id(Long memberId);
}
