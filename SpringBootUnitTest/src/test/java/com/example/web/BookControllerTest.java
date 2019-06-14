package com.example.web;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.example.BookApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = BookApplication.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BookControllerTest {

	private MockMvc mockMvc;
	
	@Autowired
    private WebApplicationContext wac;

	@Before
	public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();

	}

	@Test
	public void verifyAllBookList() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/book").accept(MediaType.APPLICATION_JSON))
			.andExpect(jsonPath("$", hasSize(4))).andDo(print());
	}
	
	@Test
	public void verifyToDoById() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/book/1").accept(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.bookId").exists())
		.andExpect(jsonPath("$.title").exists())
		.andExpect(jsonPath("$.price").exists())
		.andExpect(jsonPath("$.bookId").value(1))
		.andExpect(jsonPath("$.title").value("AI"))
		.andExpect(jsonPath("$.price").value(200))
		.andDo(print());
	}
	
	@Test
	public void verifyInvalidBookArgument() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/book/f").accept(MediaType.APPLICATION_JSON))
			.andExpect(jsonPath("$.errorCode").value(400))
			.andExpect(jsonPath("$.message").value("The request could not be understood by the server due to malformed syntax."))
			.andDo(print());
	}
	
	@Test
	public void verifyInvalidBookId() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/book/0").accept(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.errorCode").value(404))
		.andExpect(jsonPath("$.message").value("Book doesn´t exist"))
		.andDo(print());
	}
	
	@Test
	public void verifyNullBook() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/book/6").accept(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.errorCode").value(404))
		.andExpect(jsonPath("$.message").value("Book doesn´t exist"))
		.andDo(print());
	}
	
	@Test
	public void verifyDeleteBook() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.delete("/book/3").accept(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.status").value(200))
		.andExpect(jsonPath("$.message").value("Book has been deleted"))
		.andDo(print());
	}
	
	@Test
	public void verifyInvalidBookIdToDelete() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.delete("/book/9").accept(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.errorCode").value(404))
		.andExpect(jsonPath("$.message").value("Book to delete doesn´t exist"))
		.andDo(print());
	}
	
	
	@Test
	public void verifySaveBook() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/book/")
        .contentType(MediaType.APPLICATION_JSON)
        .content("{\"title\" : \"New Book Sample\", \"price\" : \"0\" }")
		.accept(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.bookId").exists())
		.andExpect(jsonPath("$.title").exists())
		.andExpect(jsonPath("$.price").exists())
		.andExpect(jsonPath("$.title").value("New Book Sample"))
		.andExpect(jsonPath("$.price").value(0))
		.andDo(print());
	}
	
	@Test
	public void verifyMalformedSaveBook() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/book/")
        .contentType(MediaType.APPLICATION_JSON)
        .content("{ \"bookId\": \"1\", \"title\" : \"ITIM\", \"price\" : \"200\", \"volumn\" : \"2\" }")
		.accept(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.errorCode").value(404))
		.andExpect(jsonPath("$.message").value("Payload malformed, id must not be defined"))
		.andDo(print());
	}
	
	@Test
	public void verifyUpdateBook() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.patch("/book/")
        .contentType(MediaType.APPLICATION_JSON)
        .content("{ \"bookId\": \"1\", \"title\" : \"Lambda\", \"price\" : \"200\", \"volumn\" : \"2\" }")
        .accept(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.bookId").exists())
		.andExpect(jsonPath("$.title").exists())
		.andExpect(jsonPath("$.price").exists())
		.andExpect(jsonPath("$.bookId").value(1))
		.andExpect(jsonPath("$.title").value("Lambda"))
		.andExpect(jsonPath("$.price").value(200))
		.andDo(print());
	}
	
	@Test
	public void verifyInvalidToDoUpdate() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.patch("/book/")
        .contentType(MediaType.APPLICATION_JSON)
        .content("{ \"idd\": \"1\", \"title\" : \"SolutionArt\", \"price\" : \"200\", \"volumn\" : \"2\" }")
		.accept(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.errorCode").value(404))
		.andExpect(jsonPath("$.message").value("Book to update doesn´t exist"))
		.andDo(print());
	}

}
