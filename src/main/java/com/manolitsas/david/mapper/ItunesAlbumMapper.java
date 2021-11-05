package com.manolitsas.david.mapper;

import com.manolitsas.david.client.model.ItunesAlbum;
import com.manolitsas.david.model.Album;
import com.manolitsas.david.model.Artist;
import java.util.List;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(
    componentModel = "spring",
    injectionStrategy = InjectionStrategy.CONSTRUCTOR,
    unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ItunesAlbumMapper {

  @Mapping(target = "albumId", source = "itunesAlbum.collectionId")
  @Mapping(target = "albumName", source = "itunesAlbum.collectionName")
  @Mapping(target = "albumUrl", source = "itunesAlbum.collectionViewUrl")
  Album toAlbum(ItunesAlbum itunesAlbum);

  List<Album> itunesAlbumListToAlbumList(List<ItunesAlbum> itunesAlbums);

  @Mapping(target = "artistId", source = "itunesAlbum.artistId")
  @Mapping(target = "name", source = "itunesAlbum.artistName")
  @Mapping(target = "primaryGenreName", source = "itunesAlbum.primaryGenreName")
  @Mapping(target = "artistLinkUrl", source = "itunesAlbum.artistLinkUrl")
  Artist albumArtistDetailsToArtist(ItunesAlbum itunesAlbum);
}
