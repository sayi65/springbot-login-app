package com.wordnet.community.dao.inter;

import org.springframework.stereotype.Service;

/**
 * @author 003418
 */
@Service
public class FileSystemStorageService  {

//    private final Path rootLocation;
//
//    @Autowired
//    public FileSystemStorageService(StorageProperties properties) {
//        this.rootLocation = Paths.get(properties.getLocation());
//    }
//
//    @Override
//    public void store(MultipartFile file) {
//        String filename = StringUtils.cleanPath(file.getOriginalFilename());
//        try {
//            if (file.isEmpty()) {
//                throw new StorageException("Failed to store empty file " + filename);
//            }
//            if (filename.contains("..")) {
//                // This is a security check
//                throw new StorageException(
//                        "Cannot store file with relative path outside current directory "
//                                + filename);
//            }
//            try (InputStream inputStream = file.getInputStream()) {
//                try {
//                    Files.copy(inputStream, this.rootLocation.resolve(filename),
//                            StandardCopyOption.REPLACE_EXISTING);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//        catch (IOException e) {
//            throw new StorageException("Failed to store file " + filename, e);
//        }
//    }
//
//    @Override
//    public Stream<Path> loadAll() {
//        try {
//            return Files.walk(this.rootLocation, 1)
//                    .filter(path -> !path.equals(this.rootLocation))
//                    .map(this.rootLocation::relativize);
//        }
//        catch (IOException e) {
//            throw new StorageException("Failed to read stored files", e);
//        }
//
//    }
//
//    @Override
//    public Path load(String filename) {
//        return rootLocation.resolve(filename);
//    }
//
//    @Override
//    public Resource loadAsResource(String filename) {
//        try {
//            Path file = load(filename);
//            Resource resource = new UrlResource(file.toUri());
//            if (resource.exists() || resource.isReadable()) {
//                return resource;
//            }
//            else {
//                throw new StorageFileNotFoundException(
//                        "Could not read file: " + filename);
//
//            }
//        }
//        catch (MalformedURLException e) {
//            throw new StorageFileNotFoundException("Could not read file: " + filename, e);
//        }
//    }
//
//    @Override
//    public void deleteAll() {
//        FileSystemUtils.deleteRecursively(rootLocation.toFile());
//    }
//
//    @Override
//    public void init() {
//        try {
//            Files.createDirectories(rootLocation);
//        }
//        catch (IOException e) {
//            throw new StorageException("Could not initialize storage", e);
//        }
//    }
}
