package co.istad.demomobilebanking.feature.media;

import co.istad.demomobilebanking.feature.media.dto.MediaResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/medias")
public class MediaController {
    private  final  MediaService mediaService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/uploadSingle")
    MediaResponse uploadSingle(@RequestPart MultipartFile file){
        return mediaService.uploadSingle(file,"img");
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/uploadMultiple")
    List<MediaResponse> uploadMultiple(@RequestPart List<MultipartFile> files){
        return mediaService.uploadMultiple(files,"img");
    }

    @GetMapping("/{mediaName}")
    MediaResponse findMediaByName(@PathVariable String mediaName){
        return mediaService.loadMediaByName(mediaName,"img");
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{mediaName}")
    MediaResponse deleteMediaByName(@PathVariable String mediaName){
        return mediaService.deleteMediaByName(mediaName,"img");
    }

    @GetMapping("/as")
    List<MediaResponse> getAllMedia(){
        return mediaService.loadAllMedia();
    }
}
