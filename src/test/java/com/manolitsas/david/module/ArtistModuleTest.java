package com.manolitsas.david.module;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import com.manolitsas.david.client.ItunesFeignClient;
import com.manolitsas.david.client.model.ItunesArtist;
import com.manolitsas.david.client.model.ItunesArtistsResponse;
import com.manolitsas.david.mapper.ItunesArtistMapper;
import com.manolitsas.david.model.Artist;
import com.manolitsas.david.util.TestUtil;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ArtistModuleTest {

  @InjectMocks ArtistModule module;
  @Mock ItunesFeignClient itunesClient;
  @Mock ItunesArtistMapper mapper;

  @Test
  void testGetArtists_whenValidRequest_thenReturnListOfArtists() {

    when(itunesClient.findAllArtistsByTerm(anyString(), anyString(), anyString(), anyString()))
        .thenReturn(
            ItunesArtistsResponse.builder()
                .resultCount(1)
                .results(
                    List.of(
                        ItunesArtist.builder()
                            .wrapperType("artist")
                            .artistName("Ed Sheeran")
                            .artistLinkUrl(
                                "https://music.apple.com/us/artist/ed-sheeran/183313439?uo=4")
                            .artistId(183313439)
                            .amgArtistId("2342870")
                            .primaryGenreName("Pop")
                            .primaryGenreId(14)
                            .build()))
                .build());

    List<Artist> artists =
        List.of(
            TestUtil.createNewArtist(
                183313439L,
                "Ed Sheeran",
                "Pop",
                "https://music.apple.com/us/artist/ed-sheeran/183313439?uo=4"));

    when(mapper.itunesArtistListToArtistList(any())).thenReturn(artists);

    List<Artist> actual = module.getArtists("Ed Sheeran");

    assertEquals(artists, actual);
  }
}
