package co.istad.demomobilebanking.feature.media;

import co.istad.demomobilebanking.feature.media.dto.MediaResponse;
import co.istad.demomobilebanking.utile.MediaUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.charset.MalformedInputException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;


import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

@RequiredArgsConstructor
@Service
@Slf4j
public class MediaServiceImpl implements  MediaService{

    @Value("${media.base-uri}")
    private String baseUri;

    @Value("${media.server-path}")
    private  String serverPath;

    @Override
    public MediaResponse uploadSingle(MultipartFile file,String folderName) {
        // generate new unique name for file
        String newName= UUID.randomUUID().toString();
        // extract extension from file upload
        // Assume profile.jpg

        String extension=MediaUtil.extension(newName);
       /* int lastDotIndex= file.getOriginalFilename()
                .lastIndexOf(".");
        String extension=file.getOriginalFilename()
                .substring(lastDotIndex+1);*/

        log.info("extension {}",extension);
        newName=newName+"."+extension;
        log.info(newName);

        // copy file to
        Path path= Paths.get(serverPath+folderName+"\\"+newName);
        try{
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
        }
        catch (Exception exception){
            throw  new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR
                    ,exception.getLocalizedMessage()
            );
        }

        return MediaResponse.builder()
                .name(newName)
                .contentType(file.getContentType())
                .extension(extension)
                .size(file.getSize())
                .uri(String.format("%s%s/%s",baseUri,folderName,newName)).build();
    }

    @Override
    public List<MediaResponse> uploadMultiple(List<MultipartFile> files, String folderName) {
        // create empty arrayList , wait for adding uploading file
        List<MediaResponse>  mediaResponses=new ArrayList<>();

        //use loop to upload each file
        files.forEach(file -> {
            MediaResponse mediaResponse=this.uploadSingle(file, folderName);
            mediaResponses.add(mediaResponse);
        });
//        files.stream().peek(file -> this.uploadSingle(file,folderName));
        return mediaResponses;
    }

    @Override
    public MediaResponse loadMediaByName(String mediaName,String folderName) {
        // create absolute path
        Path path=Paths.get(serverPath+folderName+"//"+mediaName);
        try{
            Resource resource=new UrlResource(path.toUri());
            log.info("load resource {}",resource.getFilename());
            if (!resource.exists()){
                throw  new ResponseStatusException(HttpStatus.NOT_FOUND,"file not found");
            }

            return MediaResponse.builder()
                    .name(resource.getFilename())
                    .contentType(Files.probeContentType(path))
                    .extension(MediaUtil.extension(mediaName))
                    .size(resource.contentLength())
                    .uri(String.format("%s%s/%s",baseUri,folderName,mediaName)).build();

        }catch (MalformedURLException e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,e.getLocalizedMessage());
        }catch (IOException e){
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,e.getLocalizedMessage()
            );
        }

    }

    @Override
    public MediaResponse deleteMediaByName(String mediaName, String folderName) {
        Path path=Paths.get(serverPath+folderName+"//"+mediaName);
        try {
            Long size=Files.size(path);
            Resource resource=new UrlResource(path.toUri());
            if (Files.deleteIfExists(path)){
                return MediaResponse.builder()
                        .name(resource.getFilename())
                        .contentType(Files.probeContentType(path))
                        .extension(MediaUtil.extension(folderName))
                        .size(size)
                        .uri(String.format("%s%s/%s",baseUri,folderName,mediaName)).build();
            }
            throw  new ResponseStatusException(
                    HttpStatus.NOT_FOUND,"file not found"
            );

        }catch (IOException e){
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    String.format("Media path %s can't not be deleted ",e.getLocalizedMessage())
            );
        }
//        return null;
    }



    @Override
    public List<MediaResponse> loadAllMedias(String folderName) {
        Path path = Paths.get(serverPath + folderName);

        try {
            Resource resource = new UrlResource(path.toUri());
            if (!resource.exists()) {
                throw new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Media not found"
                );
            }

            List<MediaResponse> mediaResponseList = new ArrayList<>();
            // Iterate over files in the directory
            try (Stream<Path> paths = Files.list(path)) {
                paths.forEach(filePath -> {

                    try {
                        String fileName = filePath.getFileName().toString();
                        // Create a MediaResponse object for each file
                        MediaResponse mediaResponse = MediaResponse.builder()
                                .name(fileName)
                                .uri(baseUri + folderName + "/" + fileName) // Ensure correct URI
                                .extension(MediaUtil.extractExtension(fileName))
                                .size(Files.size(filePath))
                                .build();
                        // Add MediaResponse to the list
                        mediaResponseList.add(mediaResponse);
                    } catch (IOException e) {
                        throw new ResponseStatusException(
                                HttpStatus.INTERNAL_SERVER_ERROR,
                                "Error occurred while processing media file: " + filePath.getFileName()
                        );
                    }
                });
            }
            return mediaResponseList;
        } catch (IOException e) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Error occurred while accessing media directory"
            );
        }
    }


    @Override
    public ResponseEntity<Resource> downloadMediaByName(String fileName, String folderName, HttpServletRequest request) {
        try {
            // Get path of the image
            Path imagePath = Paths.get(serverPath + folderName + "/" + fileName);
            Resource resource = new UrlResource(imagePath.toUri());

            if (resource.exists() && resource.isReadable()) {
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.parseMediaType(Files.probeContentType(imagePath)));
                // Set Content-Disposition to "attachment" to prompt download
                headers.setContentDispositionFormData("attachment", resource.getFilename());

                return ResponseEntity
                        .ok()
                        .headers(headers)
                        .body(resource);
            } else {
                // Return 404 Not Found if resource does not exist or is not readable
                return ResponseEntity.notFound().build();
            }
        } catch (IOException ex) {
            // Handle exception
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
