package guru.springframework.sfgpetclinic.services.map;

import guru.springframework.sfgpetclinic.model.Owner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;


class OwnerServiceMapTest {

    OwnerServiceMap ownerServiceMap;

    final Long ownerId = 1L;
    final String lastName = "Smith";

    @BeforeEach
    void setUp() {
        Owner owner = new Owner();
        owner.setId(ownerId);
        owner.setLastName(lastName);

        ownerServiceMap = new OwnerServiceMap(new PetTypeServiceMap(), new PetServiceMap());

        ownerServiceMap.save(owner);
    }

    @Test
    void findAll() {
        Set<Owner> owners = ownerServiceMap.findAll();

        assertEquals(1, owners.size());
    }

    @Test
    void deleteById() {
        ownerServiceMap.deleteById(ownerId);

        assertEquals(0, ownerServiceMap.findAll().size());
    }

    @Test
    void delete() {
        ownerServiceMap.delete(ownerServiceMap.findById(ownerId));

        assertEquals(0, ownerServiceMap.findAll().size());
    }

    @Test
    void saveExistingId() {
        Long id = 2L;
        Owner owner = new Owner();
        owner.setId(id);

        Owner savedOwner = ownerServiceMap.save(owner);

        assertEquals(id, savedOwner.getId());
    }

    @Test
    void saveNoId() {
        Owner owner = new Owner();

        Owner savedOwner = ownerServiceMap.save(owner);

        assertNotNull(savedOwner);
        assertNotNull(savedOwner.getId());
    }

    @Test
    void findById() {
        Owner owner = ownerServiceMap.findById(ownerId);

        assertEquals(ownerId, owner.getId());
    }

    @Test
    void findByLastName() {
        Owner owner = ownerServiceMap.findByLastName(lastName);

        assertNotNull(owner);
        assertEquals(lastName, owner.getLastName());
        assertEquals(ownerId, owner.getId());
    }

    @Test
    void findByLastNameNotFound() {
        Owner owner = ownerServiceMap.findByLastName("grishnak");

        assertNull(owner);
    }
}
