package com.duyhelloworld.todoapp;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.duyhelloworld.todoapp.model.Todo;
import com.duyhelloworld.todoapp.repository.TodoRepository;

@SpringBootApplication
public class TodoappApplication implements CommandLineRunner {
	public static void main(String[] args) {
		SpringApplication.run(TodoappApplication.class, args);
	}

	@Autowired
	private TodoRepository repository;
	@Override
	public void run(String... args) throws Exception {
		LocalDateTime now = LocalDateTime.now();
		Todo todo1 = new Todo("Đi ăn", now.minusYears(2)); 
		todo1.setDescription("Ăn tại nhà hàng năm sao của Ý");
		Todo todo2 = new Todo("Đi casting", now.minusYears(1));
		todo2.setDescription("Được (chủ tịch công ty casting ở Ý) mời tham gia casting (vai quần chúng)");
		Todo todo3 = new Todo("Đi trả lời comment", now.minusYears(1));
		todo3.setDescription("Tìm trong tủ sách 1 đôi giày thật xịn và chụp nó với quyển sách fan hỏi");
		Todo todo4 = new Todo("Học tiếng Pháp", now.minusYears(3));
		todo4.setDescription("Flex fan sau câu hỏi rất pressing 'Anh biết tiếng Pháp không?'");
		repository.saveAll(List.of(todo1, todo2, todo3, todo4));
	}
}
