package me.niallmurray.niall_assignment13.repository;

import me.niallmurray.niall_assignment13.domain.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

}
