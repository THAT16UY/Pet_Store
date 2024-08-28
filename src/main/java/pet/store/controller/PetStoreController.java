package pet.store.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import pet.store.controller.model.PetStoreData;
import pet.store.controller.model.PetStoreData.PetStoreEmployee;
import pet.store.entity.Employee;
import pet.store.service.PetStoreService;

@RestController
@RequestMapping("/pet_store") 
@Slf4j
public class PetStoreController {
	@Autowired
	private PetStoreService petStoreService;
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	
	public PetStoreData savePetStore(
			@RequestBody PetStoreData petStoreData) {
		log.info("Creating petstore {}", petStoreData);
		return petStoreService.savePetStore(petStoreData);
	}
	
	@PutMapping("/petStore/{petStoreId}")
	
	public PetStoreData updatePetStoreData(@RequestBody PetStoreData petStoreData,@PathVariable Long petStoreId) {
		log.info("Updating petstore with ID = {}", petStoreId);
		petStoreData.setPetStoreId(petStoreId);
		return petStoreService.savePetStore(petStoreData);
	}
	
	@PostMapping("/pet_store/{petStoreId}/employee")
	@ResponseStatus(code = HttpStatus.CREATED)
	public PetStoreEmployee addEmployee(@RequestBody PetStoreEmployee petStoreEmployee,@PathVariable Long petStoreId) {
		return petStoreService.saveEmployee(petStoreEmployee, petStoreId);
	}
	
	@GetMapping
	public List<PetStoreData>retrieveAllPetStores(){
		return petStoreService.retrieveAllPetStores();
	}
	
	@GetMapping("/{petStoreId}")
	public PetStoreData retrievePetStoreById(@PathVariable Long petStoreId) {
		return petStoreService.retrievePetStoreById(petStoreId);
	}
	
	@DeleteMapping("/{petStoreId}")
	public Map<String,String> deletePetStoreById(@PathVariable Long petStoreId){
		petStoreService.deletPetStoreById(petStoreId);
		log.info("Deleting PetStore with this ID = {}", petStoreId );
		return Map.of("message", "PetStore with this ID = " + petStoreId + " was deleted");
	}
}
