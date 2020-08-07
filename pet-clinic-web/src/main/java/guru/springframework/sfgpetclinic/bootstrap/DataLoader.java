package guru.springframework.sfgpetclinic.bootstrap;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.model.Pet;
import guru.springframework.sfgpetclinic.model.PetType;
import guru.springframework.sfgpetclinic.model.Vet;
import guru.springframework.sfgpetclinic.services.OwnerService;
import guru.springframework.sfgpetclinic.services.PetTypeService;
import guru.springframework.sfgpetclinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;


    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
    }

    @Override
    public void run(String... args) throws Exception {

        PetType dog = new PetType();
        dog.setName("Dog");
        PetType savedDogPetType = petTypeService.save(dog);

        PetType cat = new PetType();
        cat.setName("Cat");
        PetType savedCatPetType = petTypeService.save(cat);

        Owner owner1 = new Owner();
        owner1.setFirstName("Michael");
        owner1.setLastName("Weston");
        owner1.setAddres("Main Street");
        owner1.setCity("Miami");
        owner1.setTelephone("768488994");

        Pet michaelsPet = new Pet();
        michaelsPet.setPetType(savedDogPetType);
        michaelsPet.setOwner(owner1);
        michaelsPet.setBirthDate(LocalDate.now());
        michaelsPet.setName("Rosco");
        owner1.getPets().add(michaelsPet);

        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setFirstName("Fionna");
        owner2.setLastName("Gegaleane");
        owner2.setAddres("Main Street");
        owner2.setCity("New York");
        owner2.setTelephone("768488994");

        Pet fionnasCat = new Pet();
        fionnasCat.setPetType(savedCatPetType);
        fionnasCat.setOwner(owner2);
        fionnasCat.setBirthDate(LocalDate.now());
        fionnasCat.setName("Catarsis");
        owner2.getPets().add(fionnasCat);

        ownerService.save(owner2);

        System.out.println("Loaded owners...");

        Vet vet1 = new Vet();
        vet1.setFirstName("Sam");
        vet1.setLastName("Axe");

        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("John");
        vet2.setLastName("Doe");

        vetService.save(vet2);
        System.out.println("Loaded vets...");
    }
}
