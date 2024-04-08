package co.istad.demomobilebanking.feature.media;

import co.istad.demomobilebanking.feature.media.dto.MediaResponse;
import co.istad.demomobilebanking.utile.MediaUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
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
                        .extension(MediaUtil.extension(mediaName))
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
    public List<MediaResponse> loadAllMedia() {
//        public List<MediaResponse> getAllMedia() {
            List<MediaResponse> mediaResponses = new ArrayList<>();

            Path folderPath = Paths.get(serverPath );
            try {
                Files.walk(folderPath)
                        .filter(Files::isRegularFile)
                        .forEach(path -> {
                            try {
                                Long size = Files.size(path);
                                Resource resource = new UrlResource(path.toUri());

                                mediaResponses.add(MediaResponse.builder()
                                        .name(resource.getFilename())
                                        .contentType(Files.probeContentType(path))
                                        .extension(MediaUtil.extension(resource.getFilename()))
                                        .size(size)
                                        .uri(String.format("%s/%s", baseUri, resource.getFilename()))
                                        .build());
                            } catch (IOException e) {
                                throw new ResponseStatusException(
                                        HttpStatus.INTERNAL_SERVER_ERROR,
                                        String.format("Error processing file: %s", e.getLocalizedMessage())
                                );
                            }
                        });
            } catch (IOException e) {
                throw new ResponseStatusException(
                        HttpStatus.INTERNAL_SERVER_ERROR,
                        String.format("Error accessing folder: %s", e.getLocalizedMessage())
                );
            }

            if (mediaResponses.isEmpty()) {
                throw new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "No files found in the specified folder"
                );
            }

            return mediaResponses;
//        return null;
    }

    @Override
    public MediaResponse downloadMediaByName(HttpServletRequest httpServletRequest, String folderName) {
        
        return null;
    }
   /* public String generateUrlDownload(HttpServletRequest httpServletRequest,String filename)
    {
        return String.format("%s://%s:%d/api/v1/file/download/%s"
                ,httpServletRequest.getScheme(),
                httpServletRequest.getServerName(),
                httpServletRequest.getServerPort(),
                filename
        );
    }*/

/*    @Override
    public List<MediaResponse> getAllMedia(String folderName) {
            List<MediaResponse> mediaResponses = new ArrayList<>();

            Path folderPath = Paths.get(serverPath + folderName);
            try {
                Files.walk(folderPath)
                        .filter(Files::isRegularFile)
                        .forEach(path -> {
                            try {
                                Long size = Files.size(path);
                                Resource resource = new UrlResource(path.toUri());

                                mediaResponses.add(MediaResponse.builder()
                                        .name(resource.getFilename())
                                        .contentType(Files.probeContentType(path))
                                        .extension(MediaUtil.extension(resource.getFilename()))
                                        .size(size)
                                        .uri(String.format("%s%s/%s", baseUri, folderName, resource.getFilename()))
                                        .build());
                            } catch (IOException e) {
                                throw new ResponseStatusException(
                                        HttpStatus.INTERNAL_SERVER_ERROR,
                                        String.format("Error processing file: %s", e.getLocalizedMessage())
                                );
                            }
                        });
            } catch (IOException e) {
                throw new ResponseStatusException(
                        HttpStatus.INTERNAL_SERVER_ERROR,
                        String.format("Error accessing folder: %s", e.getLocalizedMessage())
                );
            }

            if (mediaResponses.isEmpty()) {
                throw new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "No files found in the specified folder"
                );
            }

            return mediaResponses;
    }*/
}
