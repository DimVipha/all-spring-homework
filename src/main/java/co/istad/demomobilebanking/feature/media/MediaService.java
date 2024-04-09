package co.istad.demomobilebanking.feature.media;

import co.istad.demomobilebanking.feature.media.dto.MediaResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface MediaService {
    MediaResponse uploadSingle(MultipartFile file, String folderName);
    List<MediaResponse> uploadMultiple(List<MultipartFile> files,String folderName);
    MediaResponse loadMediaByName(String mediaName,String folderName);

    MediaResponse deleteMediaByName(String mediaName,String folderName);
    MediaResponse downloadMediaByName(String mediaName, String folderName,HttpServletResponse response);
    List<MediaResponse> loadAllMedias(String folderName);
    ResponseEntity<Resource> serverFile(String fileName, String folderName, HttpServletRequest request);

    Resource downloadMediaByName(String mediaName,String folderName);
    MediaResponse deleteByName(String mediaName, String folderName);

}
