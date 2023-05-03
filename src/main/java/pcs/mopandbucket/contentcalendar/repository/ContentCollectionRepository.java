package pcs.mopandbucket.contentcalendar.repository;

import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import jakarta.annotation.PostConstruct;

import java.util.List;
import java.util.Optional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import pcs.mopandbucket.contentcalendar.content.*;



@Repository
public class ContentCollectionRepository {
	private final List<Content> contentList = new ArrayList<>();
	
	public ContentCollectionRepository() {}
	
	public List<Content> findAll(){
		return contentList;
	}
	
	public Optional<Content> findById(Integer id){
		
//This is functionally like 
//		for (Content c : content) {
//		    if (c.id().equals(id)) {
//		        return Optional.of(c);
//		    }
//		}
//		return Optional.empty();
		return contentList.stream().filter(c -> c.id().equals(id)).findFirst();
	//Streams are a cleaner, more concise and more flexible way to process collections of data
		//in java
		
	}
	
	@PostConstruct
	public void init() {
		Content content = new Content(1,
				"First post!",
				"Little tester post for ya",
				Status.IDEA,
				Type.ARTICLE,
				LocalDateTime.now(),
				null,
				"");
		contentList.add(content);
	}

	public void save(@RequestBody Content content) {
		contentList.removeIf(c -> c.id().equals(content.id()));
		contentList.add(content);
	}

	public boolean existsById(Integer id) {
		return contentList.stream()
				.filter(c -> c.id().equals(id)).count() == 1;
	}

	public void deleteById(Integer id) {
		contentList.removeIf(c -> c.id().equals(id));
		
	}

	
	
	
}
