package co.istad.demomobilebanking.feature.media;

import co.istad.demomobilebanking.feature.media.dto.MediaResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface MediaService {
    // handle client submit file
    MediaResponse uploadSingle(MultipartFile file,String folderName);
    List<MediaResponse> uploadMultiple(List<MultipartFile> files, String folderName);
    MediaResponse loadMediaByName(String mediaName, String folderName);

    MediaResponse deleteMediaByName(String mediaName,String folderName);

    // load all media
    List<MediaResponse> loadAllMedia();

    // download mediaByName
    MediaResponse downloadMediaByName(HttpServletRequest httpServletRequest, String folderName);

//    List<MediaResponse> getAllMedia(String folderName);

}
