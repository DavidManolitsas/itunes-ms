package com.manolitsas.david.mapper;

import com.manolitsas.david.client.model.ItunesArtist;
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
public interface ItunesArtistMapper {

  @Mapping(target = "artistId", source = "itunesArtist.artistId")
  @Mapping(target = "name", source = "itunesArtist.artistName")
  @Mapping(target = "primaryGenreName", source = "itunesArtist.primaryGenreName")
  @Mapping(target = "artistLinkUrl", source = "itunesArtist.artistLinkUrl")
  Artist toArtist(ItunesArtist itunesArtist);

  List<Artist> itunesArtistListToArtistList(List<ItunesArtist> itunesArtists);
}
