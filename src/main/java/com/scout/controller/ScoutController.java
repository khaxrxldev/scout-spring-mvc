package com.scout.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.ServletContext;

@Controller
public class ScoutController {

	@Autowired
	ServletContext context;
	
	@GetMapping("/")
	public String indexPage() {
		return "index";
	}
	
	@GetMapping("/account")
	public String studentAccountPage() {
		return "account";
	}
	
	@GetMapping( "/image/{imageName}")
    @ResponseBody
    public byte[] getImage(@PathVariable String imageName) throws IOException {
		BufferedImage bufferedImage = ImageIO.read(context.getResourceAsStream("/WEB-INF/resources/images/" + imageName));
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		ImageIO.write(bufferedImage, "png", byteArrayOutputStream);
		
        return byteArrayOutputStream.toByteArray();
    }
	
	@GetMapping( "/icon/{iconName}")
    @ResponseBody
    public byte[] getIcon(@PathVariable String iconName) throws IOException {
		BufferedImage bufferedIcon = ImageIO.read(context.getResourceAsStream("/WEB-INF/resources/icons/" + iconName));
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		ImageIO.write(bufferedIcon, "png", byteArrayOutputStream);
		
        return byteArrayOutputStream.toByteArray();
    }
}
