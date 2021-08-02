package com.leandro.mazzuchello.ibmtest.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.leandro.mazzuchello.ibmtest.entities.People;
import com.leandro.mazzuchello.ibmtest.repositories.PeopleRepository;
import com.leandro.mazzuchello.ibmtest.repositories.SimulationRepository;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.standaloneSetup;

@WebMvcTest
public class ResourcesTest {

    private ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private Resources resources;

    @MockBean
    private PeopleRepository peopleRepository;

    @MockBean
    private SimulationRepository simulationRepository;

    @BeforeEach
    public void setup() {
        standaloneSetup(this.resources);
    }

    private People createPeople(String cpf, Boolean hasRestriction) {
        return new People("TESTE", cpf, hasRestriction);
    }

    // CRIAR OS TESTES A PARTIR DAQUI

    @Test
    public void shouldCreatePeoples_whenICreatepeople() throws JsonProcessingException {
        People people = createPeople("678.819.549-81", false);
        String requestBody = mapper.writeValueAsString(people);
        given()
                .accept(ContentType.JSON)
                .when()
                .post("/api/create-people")
                .then()
                .statusCode(200);

    }
   /* @Test
  public void shouldListAllPeople_whenIListAllPeople(Matcher<? super Integer> HttpStatus) {

		 given()
		  	.accept(ContentType.JSON)
	     .when()
	        .get("/api/people")
	      .then()
	          .statusCode(200);

}    @Test
    public void shouldDeletePeopleID_whenIDeleteAllPeople(Matcher<? super Integer> HttpStatus) {

		 given()
		  	.accept(ContentType.JSON)
	     .when()
	        .get("/api/delete-people/{peopleId}")
	      .then()
	          .statusCode(204);

    }
      @Test
	public void shouldCreateOneSimulation_whenICreateSimulation(Matcher<? super Integer> HttpStatus) {

	 given()
	  	.accept(ContentType.JSON)
    .when()
       .get("/api/create-simulation")
     .then()
         .statusCode(200);
	}
      @Test
      public void shouldListAllSimulation_whenIListAllSimulation(Matcher<? super Integer> HttpStatus) {

		 given()
		  	.accept(ContentType.JSON)
	    .when()
	       .get("/api/simulations")
	     .then()
	         .statusCode(200);
		}

      @Test
      public void shouldIConsultoneRestrictionForCpf_whenIListRestrictionForCpf(Matcher<? super Integer> HttpStatus) {

		 given()
		  	.accept(ContentType.JSON)
	    .when()
	       .get("/api/get-restrictions/{cpf}")
	     .then()
	         .statusCode(200);
		}

      @Test
      public void shouldIConsultSimulationbyCpf_whenIListSimulationbyCpf(Matcher<? super Integer> HttpStatus) {

 		 given()
 		  	.accept(ContentType.JSON)
 	    .when()
 	       .get("/api/simulation-by-cpf/{cpf}\"")
 	     .then()
 	         .statusCode(200);
 		}
      @Test
      public void shouldIRemoveSimulationById_whenIRemoveSimulationById(Matcher<? super Integer> HttpStatus) {

 		 given()
 		  	.accept(ContentType.JSON)
 	    .when()
 	       .get("/api/delete-simulation/{id}")
 	     .then()
 	         .statusCode(204);

 		}
      @Test
      public void shouldIupdateSimulationById_whenIUpdateSimulationById(Matcher<? super Integer> HttpStatus) {

 		 given()
 		  	.accept(ContentType.JSON)
 	    .when()
 	       .get("/api/update-simulation/{id}")
 	     .then()
 	         .statusCode(204);
 		}
 		*/

}
