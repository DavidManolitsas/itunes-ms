package com.manolitsas.david.web;

import com.manolitsas.david.services.AlbumService;
import com.manolitsas.david.web.model.ArtistsAlbumsResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/albums")
public class AlbumController {

    private final AlbumService service;

    public AlbumController(AlbumService service) {
        this.service = service;
    }


    @GetMapping("/{artistId}")
    public ArtistsAlbumsResponse getArtistAlbums(@PathVariable String artistId) {
        return service.getArtistAlbums(artistId);
    }

}
