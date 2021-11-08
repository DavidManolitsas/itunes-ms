package com.manolitsas.david.module;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

import com.manolitsas.david.client.ItunesFeignClient;
import com.manolitsas.david.client.model.ItunesAlbum;
import com.manolitsas.david.client.model.ItunesAlbumResponse;
import com.manolitsas.david.mapper.ItunesAlbumMapper;
import com.manolitsas.david.model.Album;
import com.manolitsas.david.model.Artist;
import com.manolitsas.david.model.ArtistsAlbumsResponse;
import com.manolitsas.david.util.TestUtil;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class AlbumModuleTest {

  @InjectMocks private AlbumModule module;
  @Mock private ItunesFeignClient itunesClient;
  @Mock private ItunesAlbumMapper mapper;

  @Test
  void testGetArtist_whenValidRequest_thenReturnArtistsAlbumResponse() {

    Artist artist =
        TestUtil.createNewArtist(
            500011271L, "MINHO", "K-Pop", "https://music.apple.com/us/artist/minho/500011271?uo=4");

    List<Album> albums =
        List.of(
            TestUtil.createNewAlbum(
                1457645687L,
                "I'm Home - Single",
                "https://music.apple.com/us/album/im-home-single/1457645687?uo=4"));

    List<ItunesAlbum> itunesAlbums = new ArrayList<>();
    itunesAlbums.add(
        ItunesAlbum.builder()
            .wrapperType("artist")
            .artistType("Artist")
            .artistName("MINHO")
            .artistLinkUrl("https://music.apple.com/us/artist/minho/500011271?uo=4")
            .artistId(500011271)
            .primaryGenreName("K-Pop")
            .primaryGenreId(51)
            .build());

    itunesAlbums.add(
        ItunesAlbum.builder()
            .wrapperType("collection")
            .collectionType("Album")
            .artistId(500011271)
            .collectionId(1457645687)
            .artistName("MINHO")
            .collectionName("I'm Home - Single")
            .collectionCensoredName("I'm Home - Single")
            .artistViewUrl("https://music.apple.com/us/artist/minho/500011271?uo=4")
            .collectionViewUrl("https://music.apple.com/us/album/im-home-single/1457645687?uo=4")
            .artworkUrl60(
                "https://is1-ssl.mzstatic.com/image/thumb/Music113/v4/84/78/2d/84782d09-2b74-aa4f-14b6-8b7a237e111f/source/60x60bb.jpg")
            .artworkUrl100(
                "https://is1-ssl.mzstatic.com/image/thumb/Music113/v4/84/78/2d/84782d09-2b74-aa4f-14b6-8b7a237e111f/source/100x100bb.jpg")
            .collectionPrice(1.99)
            .collectionExplicitness("notExplicit")
            .trackCount(2)
            .copyright("℗ 2019 SM Entertainment")
            .country("USA")
            .currency("USD")
            .releaseDate("2019-03-28T07:00:00Z")
            .primaryGenreName("K-Pop")
            .build());

    when(itunesClient.findAllAlbumsByArtistId(anyString(), anyString()))
        .thenReturn(ItunesAlbumResponse.builder().resultCount(2).results(itunesAlbums).build());

    when(mapper.albumArtistDetailsToArtist(any(ItunesAlbum.class))).thenReturn(artist);

    when(mapper.itunesAlbumListToAlbumList(any())).thenReturn(albums);

    ArtistsAlbumsResponse actual = module.getArtistAlbums("500011271");

    assertAll(
        () -> assertEquals(artist, actual.getArtist()),
        () -> assertEquals(albums, actual.getAlbums()));
  }
}
