package fnb.oms_usermanagement.repository;

import fnb.oms_usermanagement.entity.UserCredentials;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserCredentialsRepository extends JpaRepository<UserCredentials, Long> {

    // Find credentials by user's customer ID
    Optional<UserCredentials> findByUserCustomerId(Long customerId);
}