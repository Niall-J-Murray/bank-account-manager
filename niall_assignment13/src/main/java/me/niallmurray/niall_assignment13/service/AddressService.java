package me.niallmurray.niall_assignment13.service;

import me.niallmurray.niall_assignment13.domain.Address;
import me.niallmurray.niall_assignment13.domain.User;
import me.niallmurray.niall_assignment13.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepo;

    public Address updateAddress(User user) {
        Address address = user.getAddress();
        address.setUser(user);
        address.setUserId(user.getUserId());
        return addressRepo.save(address);
    }

}
