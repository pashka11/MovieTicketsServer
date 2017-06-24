package nimrodpasha.cinema.WebApi;


import com.mongodb.util.JSON;
import nimrodpasha.cinema.utils.Parameters;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;

/**
 * Created by Yuval on 22-Mar-17.
 * This class handles the image related HTTP requests
 */
@Path("images")
public class ImageService {

    /*
     * The maximum allowed file size in megabytes.
     * Files larger than this size will not be uploadable or downloadable.
     */
    private static final int MAX_SIZE_IN_MB = 5;

    /*
     * The directory where the images will be stored.
     * Make sure this directory exists before you run the service.
     */
    private static final String RELATIVE_IMAGE_DIR = "/Images/";
    private static final java.nio.file.Path BASE_DIR = Paths.get("C:\\University\\AndroidProject\\ProjectGit\\MovieTicketsServer\\src\\main\\resources\\Images");
//    /*
//     * Download a list of all image file names.
//     */
//    @GET
//    @Produces(MediaType.APPLICATION_JSON)
//    public String getFileNames() throws IOException {
//
//        // Filters out all non JPEG or PNG files, as well as files larger than the maximum allowed size.
//        DirectoryStream.Filter<java.nio.file.Path> filter = entry -> {
//            boolean sizeOk = Files.size(entry) <= 1024 * 1024 * MAX_SIZE_IN_MB;
//            boolean extensionOk = entry.getFileName().toString().endsWith("jpg") || entry.getFileName().toString().endsWith("png");
//            return sizeOk && extensionOk;
//        };
//
//        // Browse the filtered directory and list all the files.
////        JsonArrayBuilder results = Json.createArrayBuilder();
//        ArrayList<String>strings = new ArrayList<>();
//        for (java.nio.file.Path entry : Files.newDirectoryStream(BASE_DIR, filter)) {
//            System.out.println(entry.getFileName().toString());
//            strings.add(entry.getFileName().toString());
//        }
//        return JSON.serialize(strings);
//    }

//    /*
//     * Upload a JPEG or PNG file.
//     */
//    @POST
//////    @Consumes({"image/jpeg", "image/png"})
//    @Consumes("*/*")
//    public Response uploadImage(InputStream in, @HeaderParam("Content-Type") String fileType, @HeaderParam("Content-Length") long fileSize) throws IOException {
//
//        // Make sure the file is not larger than the maximum allowed size.
//        if (fileSize > 1024 * 1024 * MAX_SIZE_IN_MB) {
//            throw new WebApplicationException(Response.status(Status.BAD_REQUEST).entity("Image is larger than " + MAX_SIZE_IN_MB + "MB").build());
//        }
//
//        // Generate a random file name based on the current time.
//        String fileName = "" + System.currentTimeMillis();
//
//        if (fileType.equals("image/jpeg")) {
//            fileName += ".jpg";
//        } else {
//            fileName += ".png";
//        }
//
//        // Copy the file to its location.
//        Files.copy(in, BASE_DIR.resolve(fileName), StandardCopyOption.REPLACE_EXISTING);
//
//        // Return a 201 Created response with the appropriate Location header.
//        return Response.status(Status.CREATED).entity(Parameters.IMAGE_FOLDER+fileName).build();
//    }

    /*
     * Download a JPEG file.
     */
    @GET
    @Path("{name}.jpg")
    @Produces("image/jpeg")
    public InputStream getJpegImage(@PathParam("name") String fileName) throws IOException {

        fileName += ".jpg";

        return GetImageResourceStream(fileName);
    }

    /*
     * Download a PNG file.
     */
    @GET
    @Path("{name}.png")
    @Produces("image/png")
    public InputStream getPngImage(@PathParam("name") String fileName) throws IOException {

        fileName += ".png";

        return GetImageResourceStream(fileName);
    }

    // Help function to get stream for image
    private InputStream GetImageResourceStream(String imageName)
    {
        InputStream stream = this.getClass().getResourceAsStream("/Images/"+imageName);

        if (stream == null)
            throw new WebApplicationException("File: '" + imageName + "' not found");

        return stream;
    }
}