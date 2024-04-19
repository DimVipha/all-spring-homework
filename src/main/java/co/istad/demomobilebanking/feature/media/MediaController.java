package co.istad.demomobilebanking.feature.media;

import co.istad.demomobilebanking.feature.media.dto.MediaResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/medias")
@RequiredArgsConstructor
public class MediaController {
    private final MediaService mediaService;
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/upload-single")
    MediaResponse uploadSingle(@RequestPart MultipartFile file){
        return mediaService.uploadSingle(file,"IMAGE");
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/upload-multiple")
    List<MediaResponse> uploadMultiple(@RequestPart List<MultipartFile> files){
        return mediaService.uploadMultiple(files,"IMAGE");
    }
    @GetMapping("/{mediaName}")
    MediaResponse loadMediaByName(@PathVariable String mediaName){
        return mediaService.loadMediaByName(mediaName,"IMAGE");
    }

    @DeleteMapping("/{mediaName}")
    MediaResponse deleteMediaByName(@PathVariable String mediaName){
        return mediaService.deleteMediaByName(mediaName,"IMAGE");
    }
    @GetMapping
    List<MediaResponse> loadAllMedias(){
        return mediaService.loadAllMedias("IMAGE");
    }

    @GetMapping("/download/{mediaName}")
    public ResponseEntity<?> downloadFile(@PathVariable String mediaName, HttpServletRequest request){
        return mediaService.serverFile(mediaName,"IMAGE", request);
    }

    //produce = accepts
    //consumes = content-types
    // produces = Accept
    // consumes = Content-Type
    @GetMapping(path = "/{mediaName}/download",
            produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    ResponseEntity<?> downloadMediaByName(@PathVariable String mediaName) {
        System.out.println("Start download");
        Resource resource = mediaService.downloadMediaByName(mediaName, "IMAGE");
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + mediaName);
        return ResponseEntity.ok()
                .headers(headers)
                .body(resource);
    }
    @DeleteMapping("/delete/{mediaName}")
    MediaResponse deleteByName(@PathVariable String mediaName) {
        return mediaService.deleteByName(mediaName, "IMAGE");
    }






//    @GetMapping("/download/{name}")
//    public MediaResponse downloadFile(@PathVariable("name") String name, HttpServletResponse response) {
//        return mediaService.downloadMediaByName(name,"IMAGE",response);
//    }

//    @GetMapping("/download/{name}")
//    public BaseReset<?> downloadFile(@PathVariable("name") String name, HttpServletResponse response) {
//        var resultFiles = mediaService.downloadMediaByName(name,"IMAGE",response);
//        return BaseReset.builder()
//                .status(true)
//                .code(HttpStatus.OK.value())
//                .message("Download successfully")
//                .timestamp(LocalDateTime.now())
//                .data(resultFiles)
//                .build();
    // }

// Assuming this is a controller or a service method where you want to retrieve all media files


}
