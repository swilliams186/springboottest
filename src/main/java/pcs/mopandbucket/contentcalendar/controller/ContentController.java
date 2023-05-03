package pcs.mopandbucket.contentcalendar.controller;

import java.util.List;
import java.util.Optional;

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
import org.springframework.web.server.ResponseStatusException;

import jakarta.validation.Valid;
import pcs.mopandbucket.contentcalendar.content.Content;
import pcs.mopandbucket.contentcalendar.repository.ContentCollectionRepository;

@RestController
@RequestMapping("/api/content")
public class ContentController {
	
	//We are dependent on this class
	//Spring will "INJECT" the dependencies. Dependency Injection
	//Because there is only ever 1 instance, we can use FINAL
	private final ContentCollectionRepository repository;
	
	public ContentController(ContentCollectionRepository repository) {
		//repository = new ContentCollectionRepository(); //This would actually create a second instance!
		//Any time you see "new" while making using spring, pay attention
		//Spring Application Context should be doing this , not us!
		//Because ContentCollectionRepository is marked as a component (@repository)
		//Spring has automatically created an instance of this already and it will
		//be in the application context 
		//This is called "Inversion of Control"
		
		this.repository = repository;
				
	}
	
	@GetMapping("") // Because the class mapping is /api/content, this is the same if left blank
	public List<Content> findAll(){
		return repository.findAll();
	}
	
	@GetMapping("/{id}")
	public Content findById(@PathVariable Integer id){
		return repository.findById(id)
				.orElseThrow(() ->
				new ResponseStatusException(HttpStatus.NOT_FOUND,
											"Content not found!"));
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("")
	public void create(@Valid @RequestBody Content content) {
		repository.save(content);
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PutMapping("/{id}")
	public void update(@RequestBody Content content, @PathVariable Integer id) {
		//Param id and content id can be different 
		if(repository.existsById(id)) {
			repository.save(content);
		}else {
			throw new ResponseStatusException(HttpStatus.NOT_MODIFIED, "That ID wasn't found!");
		}
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Integer id) {
		repository.deleteById(id);
	}
	
}
