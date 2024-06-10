package com.example.demo.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.example.demo.models.RectangleRepository;
import com.example.demo.models.Rectangle;

import jakarta.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class RectangleController {
    @Autowired 
    private RectangleRepository rectRepo;

    @GetMapping("/rectangle/view")
    public String getAllRectangles(Model model){
        // get all rectangles from database
        List<Rectangle> rectangles = rectRepo.findAll();
        // end of database call
        model.addAttribute("rectList", rectangles);
        return "showAll";
    }

    @GetMapping("/rectangle/view/{uid}")
    public String getRectangle(Model model, @PathVariable Integer uid) {
        Rectangle rectangle = rectRepo.findByUid(uid);
        model.addAttribute("rectangle", rectangle);
        return "showRectangle";
    }

    @PostMapping("/rectangle/add")
    public String addRectangle(Model model, @RequestParam Map<String, String> newRectangle, HttpServletResponse response){
        String newName = newRectangle.get("name");
        String newColor = newRectangle.get("color");
        int newHeight = Integer.parseInt(newRectangle.get("height"));
        int newWidth = Integer.parseInt(newRectangle.get("width"));
        
        rectRepo.save(new Rectangle(newName, newWidth, newHeight, newColor));
        response.setStatus(201);

        String msg = "Rectangle " + newName + " successfully added!";
        model.addAttribute("message", msg);
        return "addedRectangle";
    }

    @PostMapping("/rectangle/update_name")
    public String updateName(@RequestParam Map<String, String> map) {

        StringBuilder result = new StringBuilder();
        
        for (Map.Entry<String, String> entry : map.entrySet()) {
            result.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
        }
        
        System.out.println(result.toString());

        String str_uid = map.get("uid");
        System.out.println("uid: " + str_uid);
        int uid = Integer.parseInt(str_uid);
        String name = map.get("new_name");
        System.out.println("name: " + name);
        Rectangle rect = rectRepo.findByUid(uid);
        rect.setName(name);
        rectRepo.save(rect);
        String redirect_str = "redirect:/rectangle/view/" + str_uid;
        return redirect_str;
    }

    @PostMapping("/rectangle/update_width")
    public String updateWidth(@RequestParam Map<String, String> map) {

        StringBuilder result = new StringBuilder();
        
        for (Map.Entry<String, String> entry : map.entrySet()) {
            result.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
        }
        
        System.out.println(result.toString());

        String str_uid = map.get("uid");
        System.out.println("uid: " + str_uid);
        int uid = Integer.parseInt(str_uid);
        String width = map.get("new_width");
        System.out.println("width: " + width);
        Rectangle rect = rectRepo.findByUid(uid);
        rect.setWidth(Integer.parseInt(width));
        rectRepo.save(rect);
        String redirect_str = "redirect:/rectangle/view/" + str_uid;
        return redirect_str;
    }

    @PostMapping("/rectangle/update_height")
    public String updateHeight(@RequestParam Map<String, String> map) {

        StringBuilder result = new StringBuilder();
        
        for (Map.Entry<String, String> entry : map.entrySet()) {
            result.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
        }
        
        System.out.println(result.toString());

        String str_uid = map.get("uid");
        System.out.println("uid: " + str_uid);
        int uid = Integer.parseInt(str_uid);
        String height = map.get("new_height");
        System.out.println("height: " + height);
        Rectangle rect = rectRepo.findByUid(uid);
        rect.setHeight(Integer.parseInt(height));
        rectRepo.save(rect);
        String redirect_str = "redirect:/rectangle/view/" + str_uid;
        return redirect_str;
    }

    @PostMapping("/rectangle/update_color")
    public String updateColor(@RequestParam Map<String, String> map) {

        StringBuilder result = new StringBuilder();
        
        for (Map.Entry<String, String> entry : map.entrySet()) {
            result.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
        }
        
        System.out.println(result.toString());

        String str_uid = map.get("uid");
        System.out.println("uid: " + str_uid);
        int uid = Integer.parseInt(str_uid);
        String color = map.get("new_color");
        System.out.println("color: " + color);
        Rectangle rect = rectRepo.findByUid(uid);
        rect.setColor(color);
        rectRepo.save(rect);
        String redirect_str = "redirect:/rectangle/view/" + str_uid;
        return redirect_str;
    }
    

    @PostMapping("rectangle/delete/{uid}")
    public String deleteRectangle(Model model, @PathVariable Integer uid) {
        
        rectRepo.deleteById(uid);
        model.addAttribute("message", "Item deleted successfully!");

        return "redirect:/?message=%2Item0deleted%20successfully!";
        //return "Rectangle with uid " + uid.toString() + " deleted successfully!";
        
    }
    
}
