package co.istad.demomobilebanking.feature.media;

import co.istad.demomobilebanking.feature.media.dto.MediaResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface MediaService {
    // handle client submit file
    MediaResponse uploadSingle(MultipartFile file,String folderName);
    List<MediaResponse> uploadMultiple(List<MultipartFile> files, String folderName);
    MediaResponse loadMediaByName(String mediaName, String folderName);

    MediaResponse deleteMediaByName(String mediaName,String folderName);

    // load all media
//    List<MediaResponse> loadAllMedia();

    // download mediaByName
    List<MediaResponse> loadAllMedias(String folderName);
    ResponseEntity<Resource> downloadMediaByName(String fileName, String folderName, HttpServletRequest request);
//    List<MediaResponse> getAllMedia(String folderName);

}
