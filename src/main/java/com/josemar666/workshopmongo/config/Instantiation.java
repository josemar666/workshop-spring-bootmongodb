package com.josemar666.workshopmongo.config;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.josemar666.workshopmongo.domain.Post;
import com.josemar666.workshopmongo.domain.User;
import com.josemar666.workshopmongo.dto.AuthorDTO;
import com.josemar666.workshopmongo.dto.ComentDTO;
import com.josemar666.workshopmongo.repository.PostRepository;
import com.josemar666.workshopmongo.repository.UserRepository;


@Configuration
public class Instantiation implements CommandLineRunner {
	
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;
	

	@Override
	public void run(String... args) throws Exception {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		userRepository.deleteAll();
		postRepository.deleteAll();
		
		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");
		
		userRepository.saveAll(Arrays.asList(maria,alex,bob));
		
		Post post1 = new Post(null,sdf.parse("21/03/2018") , "partiu viagem", "vou viajar para São Paulo , Abraços !",new AuthorDTO( maria));
		Post post2 = new Post(null, sdf.parse("21/03/2018"), "Bom dia !", "Acordei Feliz Hoje !",new AuthorDTO( maria));
		
		ComentDTO c = new ComentDTO(" BOA VIAGEM FILHO DE GATO !", sdf.parse("21/03/2018"), new AuthorDTO(alex));
		ComentDTO c2 = new ComentDTO("APROVEITE BEM !", sdf.parse("22/02/2018"), new AuthorDTO(bob));
		ComentDTO c3 = new ComentDTO("TENHA UM ÓTIMO DIA !", sdf.parse("23/03/2018"), new AuthorDTO(alex));
		
		post1.getComents().addAll(Arrays.asList(c,c2));
		post2.getComents().addAll(Arrays.asList(c3));
		
		postRepository.saveAll(Arrays.asList(post1, post2));
		
		maria.getPost().addAll(Arrays.asList(post1,post2));
		userRepository.save(maria);
		
		
	}

}
