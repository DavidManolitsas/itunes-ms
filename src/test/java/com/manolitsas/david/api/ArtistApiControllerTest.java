package com.manolitsas.david.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.manolitsas.david.annotation.ControllerTest;
import com.manolitsas.david.exception.CustomApiException;
import com.manolitsas.david.model.Artist;
import com.manolitsas.david.module.ArtistModule;
import com.manolitsas.david.util.TestUtil;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

@ControllerTest
public class ArtistApiControllerTest {

  private static final String GET_ARTISTS_URI = "/itunes/artists/{term}";

  @Autowired private MockMvc mockMvc;
  @Autowired private ArtistModule artistModule;

  @Test
  void getArtist_whenValidRequest_thenReturn5Artists() throws Exception {

    List<Artist> artists =
        List.of(
            TestUtil.createNewArtist(
                183313439L,
                "Ed Sheeran",
                "Pop",
                "https://music.apple.com/us/artist/ed-sheeran/183313439?uo=4"),
            TestUtil.createNewArtist(
                146191L,
                "Collective Soul",
                "Rock",
                "https://music.apple.com/us/artist/collective-soul/146191?uo=4"),
            TestUtil.createNewArtist(
                267320L,
                "Eddie Vedder",
                "Rock",
                "https://music.apple.com/us/artist/eddie-vedder/267320?uo=4"),
            TestUtil.createNewArtist(
                207829L,
                "Ed Warren",
                "Fiction & Literature",
                "https://music.apple.com/us/author/ed-warren/id207829?uo=4"),
            TestUtil.createNewArtist(
                938939L,
                "Ed Young",
                "Christianity",
                "https://music.apple.com/us/author/ed-young/id938939?uo=4"));

    when(artistModule.getArtists("Ed")).thenReturn(artists);

    MvcResult result =
        mockMvc
            .perform(get(GET_ARTISTS_URI, "Ed").contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andReturn();

    String expectedResponse = new ObjectMapper().writeValueAsString(artists);

    assertEquals(expectedResponse, result.getResponse().getContentAsString());
  }

  @Test
  void getArtist_whenArtistsNotFound_thenReturn404Error() throws Exception {

    when(artistModule.getArtists("qwertyyuiop"))
        .thenThrow(
            CustomApiException.notFoundException("No artists found for search term 'qwertyyuiop'"));

    mockMvc
        .perform(get(GET_ARTISTS_URI, "qwertyyuiop").contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isNotFound())
        .andExpect(jsonPath("$.status").value(404))
        .andExpect(jsonPath("$.error").value("Not Found"))
        .andExpect(jsonPath("$.message").value("No artists found for search term 'qwertyyuiop'"));
  }
}
