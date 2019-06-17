package com.ozymern.spring.security.jwt.oauth2.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ozymern.spring.security.jwt.oauth2.models.entities.Pet;
import com.ozymern.spring.security.jwt.oauth2.models.repositories.PetRepository;

@Service
public class PetServiceImpl implements PetService {

	@Autowired
	private PetRepository petRepository;

	@Override
	public List<Pet> findAllPets() {

		return petRepository.findAll();
	}

	@Override
	public Pet findPetById(Long id) {

		return petRepository.findOne(id);
	}

	@Override
	public Pet updatePet(Pet pet, Long id) {

		return (findPetById(id) != null) ? petRepository.save(pet) : null;
	}

	@Override
	public void deletePet(Pet pet) {
		// validar si existe la mascota, lo eliminara
		if (this.findPetById(pet.getPetId()) != null) {
			petRepository.delete(pet);
		}

	}

	@Override
	public Pet createPetPet(Pet pet) {
		return petRepository.save(pet);
	}

}
