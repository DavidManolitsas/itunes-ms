package com.manolitsas.david.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import com.manolitsas.david.client.model.ItunesAlbum;
import com.manolitsas.david.model.Album;
import com.manolitsas.david.model.Artist;
import com.manolitsas.david.util.TestUtil;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class ItunesAlbumMapperTest {

  private final ItunesAlbumMapper mapper = new ItunesAlbumMapperImpl();

  @ParameterizedTest
  @MethodSource("toAlbum")
  void testToAlbum(Album expected, ItunesAlbum source) {
    assertEquals(expected, mapper.toAlbum(source));
  }

  @ParameterizedTest
  @MethodSource("albumArtistDetailsToArtist")
  void testAlbumArtistDetailsToArtist(Artist expected, ItunesAlbum source) {
    assertEquals(expected, mapper.albumArtistDetailsToArtist(source));
  }

  @ParameterizedTest
  @MethodSource("itunesAlbumListToAlbumList")
  void testItunesAlbumListToAlbumList(List<Album> expected, List<ItunesAlbum> source) {
    assertEquals(expected, mapper.itunesAlbumListToAlbumList(source));
  }

  private static Stream<Arguments> toAlbum() {

    ItunesAlbum itunesAlbum =
        ItunesAlbum.builder()
            .wrapperType("collection")
            .collectionType("Album")
            .artistId(183313439)
            .collectionId(1193701079)
            .amgArtistId("2342870")
            .artistName("Ed Sheeran")
            .collectionName("÷ (Deluxe)")
            .collectionCensoredName("÷ (Deluxe)")
            .artistViewUrl("https://music.apple.com/us/artist/ed-sheeran/183313439?uo=4")
            .collectionViewUrl("https://music.apple.com/us/album/deluxe/1193701079?uo=4")
            .artworkUrl60(
                "https://is5-ssl.mzstatic.com/image/thumb/Music115/v4/30/bd/76/30bd76b9-ceb9-2f8a-6821-ee8ea016bbfd/source/60x60bb.jpg")
            .artworkUrl100(
                "https://is5-ssl.mzstatic.com/image/thumb/Music115/v4/30/bd/76/30bd76b9-ceb9-2f8a-6821-ee8ea016bbfd/source/100x100bb.jpg")
            .collectionPrice(12.99)
            .collectionExplicitness("notExplicit")
            .trackCount(16)
            .copyright(
                "℗ 2017 Asylum Records UK, a division of Atlantic Records UK, a Warner Music Group company.")
            .country("USA")
            .currency("USD")
            .releaseDate("2017-03-03T08:00:00Z")
            .primaryGenreName("Pop")
            .build();

    Album album =
        TestUtil.createNewAlbum(
            1193701079L, "÷ (Deluxe)", "https://music.apple.com/us/album/deluxe/1193701079?uo=4");

    return Stream.of(arguments(album, itunesAlbum));
  }

  private static Stream<Arguments> albumArtistDetailsToArtist() {
    ItunesAlbum artistDetails =
        ItunesAlbum.builder()
            .wrapperType("artist")
            .artistType("Artist")
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

    return Stream.of(arguments(artist, artistDetails));
  }

  private static Stream<Arguments> itunesAlbumListToAlbumList() {
    List<ItunesAlbum> itunesAlbums =
        List.of(
            ItunesAlbum.builder()
                .wrapperType("collection")
                .collectionType("Album")
                .artistId(739932137)
                .collectionId(1325128282)
                .amgArtistId("3408117")
                .artistName("Kasbo")
                .collectionName("Places We Don't Know")
                .collectionCensoredName("Places We Don't Know")
                .artistViewUrl("https://music.apple.com/us/artist/kasbo/739932137?uo=4")
                .collectionViewUrl(
                    "https://music.apple.com/us/album/places-we-dont-know/1325128282?uo=4")
                .artworkUrl60(
                    "https://is4-ssl.mzstatic.com/image/thumb/Music115/v4/f7/8a/cf/f78acf9b-889a-bca4-bb05-90c2cbd1a346/source/60x60bb.jpg")
                .artworkUrl100(
                    "https://is4-ssl.mzstatic.com/image/thumb/Music115/v4/f7/8a/cf/f78acf9b-889a-bca4-bb05-90c2cbd1a346/source/100x100bb.jpg")
                .collectionPrice(7.99)
                .collectionExplicitness("notExplicit")
                .trackCount(13)
                .copyright("℗ 2018 Foreign Family Collective/Counter Records")
                .country("USA")
                .currency("USD")
                .releaseDate("2018-03-23T07:00:00Z")
                .primaryGenreName("Electronic")
                .build(),
            ItunesAlbum.builder()
                .wrapperType("collection")
                .collectionType("Album")
                .artistId(739932137)
                .collectionId(1048203040)
                .amgArtistId("3408117")
                .artistName("Kasbo")
                .collectionName("Umbrella Club - EP")
                .collectionCensoredName("Umbrella Club - EP")
                .artistViewUrl("https://music.apple.com/us/artist/kasbo/739932137?uo=4")
                .collectionViewUrl(
                    "https://music.apple.com/us/album/umbrella-club-ep/1048203040?uo=4")
                .artworkUrl60(
                    "https://is5-ssl.mzstatic.com/image/thumb/Music125/v4/90/8d/04/908d0455-f230-b631-0568-f36231b756ac/source/60x60bb.jpg")
                .artworkUrl100(
                    "https://is5-ssl.mzstatic.com/image/thumb/Music125/v4/90/8d/04/908d0455-f230-b631-0568-f36231b756ac/source/100x100bb.jpg")
                .collectionPrice(4.95)
                .collectionExplicitness("explicit")
                .contentAdvisoryRating("Explicit")
                .trackCount(5)
                .copyright("℗ 2015 Kasbo")
                .country("USA")
                .currency("USD")
                .releaseDate("2015-10-09T07:00:00Z")
                .primaryGenreName("Electronic")
                .build(),
            ItunesAlbum.builder()
                .wrapperType("collection")
                .collectionType("Album")
                .artistId(739932137)
                .collectionId(1516632507)
                .amgArtistId("3408117")
                .artistName("Kasbo")
                .collectionName("The Making of a Paracosm")
                .collectionCensoredName("The Making of a Paracosm")
                .artistViewUrl("https://music.apple.com/us/artist/kasbo/739932137?uo=4")
                .collectionViewUrl(
                    "https://music.apple.com/us/album/the-making-of-a-paracosm/1516632507?uo=4")
                .artworkUrl60(
                    "https://is5-ssl.mzstatic.com/image/thumb/Music124/v4/62/36/6b/62366bc5-583e-bdad-ded3-ddf9c577b49d/source/60x60bb.jpg")
                .artworkUrl100(
                    "https://is5-ssl.mzstatic.com/image/thumb/Music124/v4/62/36/6b/62366bc5-583e-bdad-ded3-ddf9c577b49d/source/100x100bb.jpg")
                .collectionPrice(9.99)
                .collectionExplicitness("notExplicit")
                .trackCount(14)
                .copyright("℗ 2020 Foreign Family Collective/Counter Records")
                .country("USA")
                .currency("USD")
                .releaseDate("2020-10-23T07:00:00Z")
                .primaryGenreName("Electronic")
                .build());

    List<Album> albums =
        List.of(
            TestUtil.createNewAlbum(
                1325128282L,
                "Places We Don't Know",
                "https://music.apple.com/us/album/places-we-dont-know/1325128282?uo=4"),
            TestUtil.createNewAlbum(
                1048203040L,
                "Umbrella Club - EP",
                "https://music.apple.com/us/album/umbrella-club-ep/1048203040?uo=4"),
            TestUtil.createNewAlbum(
                1516632507L,
                "The Making of a Paracosm",
                "https://music.apple.com/us/album/the-making-of-a-paracosm/1516632507?uo=4"));

    return Stream.of(arguments(albums, itunesAlbums));
  }
}
