package fr.limayrac.pfeback.controller;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.limayrac.pfeback.model.Animation;
import fr.limayrac.pfeback.service.IAnimationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AnimationControllerTest {
    @Autowired
    private WebApplicationContext context;
    @Autowired
    private ObjectMapper mapper;
    private MockMvc mockMvc;
    private JacksonTester<Animation> jacksonTester;
    private Animation animation;
    @Autowired
    private IAnimationService animationService;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build();

        animation = new Animation();
        animation.setDessin("dessin");
        animation.setPhoto("photo");
        animation.setSon("son");
        animation.setActive(true);
    }

    @Test
    public void create() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/api/animation")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(animation))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        JsonNode node = mapper.readTree(result.getResponse().getContentAsString());
        assertNotNull(node.get("id"));

        assertNotNull(node.get("dessin"));
        assertEquals(animation.getDessin(), node.get("dessin").asText());

        assertNotNull(node.get("photo"));
        assertEquals(animation.getPhoto(), node.get("photo").asText());

        assertNotNull(node.get("son"));
        assertEquals(animation.getSon(), node.get("son").asText());

        assertNotNull(node.get("active"));
        assertEquals(animation.getActive(), node.get("active").asBoolean());
    }
    @Test
    public void findById() throws Exception {
        animation = animationService.save(animation);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/api/animation/" + animation.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(animation))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        JsonNode node = mapper.readTree(result.getResponse().getContentAsString());
        assertNotNull(node.get("id"));
        assertEquals(animation.getId(), node.get("id").asLong());

        assertNotNull(node.get("dessin"));
        assertEquals(animation.getDessin(), node.get("dessin").asText());

        assertNotNull(node.get("photo"));
        assertEquals(animation.getPhoto(), node.get("photo").asText());

        assertNotNull(node.get("son"));
        assertEquals(animation.getSon(), node.get("son").asText());

        assertNotNull(node.get("active"));
        assertEquals(animation.getActive(), node.get("active").asBoolean());
    }
}
