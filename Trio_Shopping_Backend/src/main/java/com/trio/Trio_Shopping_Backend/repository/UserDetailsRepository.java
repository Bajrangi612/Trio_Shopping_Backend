package com.trio.Trio_Shopping_Backend.repository;

import com.trio.Trio_Shopping_Backend.domain.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDetailsRepository extends JpaRepository<UserDetails,Long> {
    UserDetails findByUserMobileNumber(String userMobileNumber);

    UserDetails findByUserEmail(String data);
}
