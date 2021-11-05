package com.manolitsas.david.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.manolitsas.david.annotation.ControllerTest;
import com.manolitsas.david.exception.CustomApiException;
import com.manolitsas.david.model.ArtistsAlbumsResponse;
import com.manolitsas.david.module.AlbumModule;
import com.manolitsas.david.util.TestUtil;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

@ControllerTest
public class AlbumApiControllerTest {

  private static final String GET_ALBUMS_URI = "/itunes/albums/{artistId}";

  @Autowired private MockMvc mockMvc;
  @Autowired private AlbumModule albumModule;

  @Test
  void getAlbums_whenValidRequest_thenReturnArtistsAlbums() throws Exception {
    ArtistsAlbumsResponse response = new ArtistsAlbumsResponse();
    response.setArtist(
        TestUtil.createNewArtist(
            500011271L,
            "MINHO",
            "K-Pop",
            "https://music.apple.com/us/artist/minho/500011271?uo=4"));
    response.setAlbums(
        List.of(
            TestUtil.createNewAlbum(
                1457645687L,
                "I'm Home - Single",
                "https://music.apple.com/us/album/im-home-single/1457645687?uo=4")));

    when(albumModule.getArtistAlbums("500011271")).thenReturn(response);

    MvcResult result =
        mockMvc
            .perform(get(GET_ALBUMS_URI, 500011271).contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andReturn();

    String expectedResponse = new ObjectMapper().writeValueAsString(response);
    assertEquals(expectedResponse, result.getResponse().getContentAsString());
  }

  @Test
  void getArtist_whenArtistsNotFound_thenReturn404Error() throws Exception {

    when(albumModule.getArtistAlbums("1234"))
        .thenThrow(CustomApiException.notFoundException("No albums found for artist id: 1234"));

    mockMvc
        .perform(get(GET_ALBUMS_URI, "1234").contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isNotFound())
        .andExpect(jsonPath("$.status").value(404))
        .andExpect(jsonPath("$.error").value("Not Found"))
        .andExpect(jsonPath("$.message").value("No albums found for artist id: 1234"));
  }
}
