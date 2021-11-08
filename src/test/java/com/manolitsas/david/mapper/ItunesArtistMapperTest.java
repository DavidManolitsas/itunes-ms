package com.manolitsas.david.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import com.manolitsas.david.client.model.ItunesArtist;
import com.manolitsas.david.model.Artist;
import com.manolitsas.david.util.TestUtil;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class ItunesArtistMapperTest {

  private final ItunesArtistMapper mapper = new ItunesArtistMapperImpl();

  @ParameterizedTest
  @MethodSource("itunesArtistListToArtistList")
  void testItunesArtistListToArtistList(List<Artist> expected, List<ItunesArtist> source) {
    assertEquals(expected, mapper.itunesArtistListToArtistList(source));
  }

  @ParameterizedTest
  @MethodSource("toArtist")
  void testToArtist(Artist expected, ItunesArtist source) {
    assertEquals(expected, mapper.toArtist(source));
  }

  private static Stream<Arguments> toArtist() {
    ItunesArtist itunesArtist =
        ItunesArtist.builder()
            .wrapperType("artist")
            .artistName("Ed Sheeran")
            .artistLinkUrl("https://music.apple.com/us/artist/ed-sheeran/183313439?uo=4")
            .artistId(183313439)
            .amgArtistId("2342870")
            .primaryGenreName("Pop")
            .primaryGenreId(14)
            .build();

    Artist artist =
        TestUtil.createNewArtist(
            183313439L,
            "Ed Sheeran",
            "Pop",
            "https://music.apple.com/us/artist/ed-sheeran/183313439?uo=4");

    return Stream.of(arguments(artist, itunesArtist));
  }

  private static Stream<Arguments> itunesArtistListToArtistList() {
    List<ItunesArtist> itunesArtists =
        List.of(
            ItunesArtist.builder()
                .wrapperType("artist")
                .artistType("Artist")
                .artistName("Kasbo")
                .artistLinkUrl("https://music.apple.com/us/artist/kasbo/739932137?uo=4")
                .artistId(739932137)
                .amgArtistId("3408117")
                .primaryGenreName("Electronic")
                .primaryGenreId(7)
                .build(),
            ItunesArtist.builder()
                .wrapperType("artist")
                .artistType("Artist")
                .artistName("Kasbo")
                .artistLinkUrl("https://music.apple.com/us/artist/kasbo/1497002742?uo=4")
                .artistId(1497002742)
                .primaryGenreName("Electronic")
                .primaryGenreId(7)
                .build());

    List<Artist> artists =
        List.of(
            TestUtil.createNewArtist(
                739932137L,
                "Kasbo",
                "Electronic",
                "https://music.apple.com/us/artist/kasbo/739932137?uo=4"),
            TestUtil.createNewArtist(
                1497002742L,
                "Kasbo",
                "Electronic",
                "https://music.apple.com/us/artist/kasbo/1497002742?uo=4"));

    return Stream.of(arguments(artists, itunesArtists));
  }
}
