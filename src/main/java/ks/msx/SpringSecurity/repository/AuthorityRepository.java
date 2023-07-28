package ks.msx.SpringSecurity.repository;


import ks.msx.SpringSecurity.entity.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, Long> {
    Authority findNameById(Long id);
}
