package com.driver.services;

import com.driver.models.*;
import com.driver.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageService {

    @Autowired
    BlogRepository blogRepository2;
    @Autowired
    ImageRepository imageRepository2;

    public Image addImage(Integer blogId, String description, String dimensions){
        //add an image to the blog
        Blog blog = blogRepository2.findById(blogId).get();
        Image image = new Image(blogId,description,dimensions);
        blog.getImageList().add(image);
        blogRepository2.save(blog);
        return image;
    }

    public void deleteImage(Integer id){
       imageRepository2.deleteById(id);
    }

    public int countImagesInScreen(Integer id, String screenDimensions) {
        //Find the number of images of given dimensions that can fit in a screen having `screenDimensions`
        String srcArray[] = screenDimensions.split("X");

        Image image = imageRepository2.findById(id).get();

        //find the dimension of the stirng
        String imageDimension = image.getDimensions();

        //create a string array for image dimensions
        String[] imgarray = imageDimension.split("X");

        int scl = Integer.parseInt(srcArray[0]);
        int scb = Integer.parseInt(srcArray[1]);

        int imgl = Integer.parseInt(imgarray[0]);
        int imgb = Integer.parseInt(imgarray[1]);

        return no_Images(scl,scb,imgl,imgb);
    }

    public int no_Images(int srcl,int srcb,int imgl,int imgb)
    {
       int lenC = srcl/imgl;
       int lenb = srcb/imgb;
       return lenC*lenb;
    }
}
