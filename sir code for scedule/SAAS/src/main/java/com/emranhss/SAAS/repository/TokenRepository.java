package com.emranhss.SAAS.repository;

import com.emranhss.SAAS.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<Token, Long> {
    List<Token> findAllByToken(String token);

//    @Query("""
//    SELECT t FROM Token t
//    WHERE t.user.id = :userId
//    AND t.revoked = false
//""")
//    List<Token> findAllValidTokensByUser(@Param("userId") Long userId);

    void deleteAllByCreatedAtBefore(Instant cutoff);





    @Query("""
    SELECT t FROM Token t
    WHERE t.user.id = :userId
    AND t.revoked = false
    AND t.expired = false
""")
    List<Token> findAllValidTokensByUser(Long userId);


}