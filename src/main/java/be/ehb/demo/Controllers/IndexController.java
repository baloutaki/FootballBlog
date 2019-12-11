package be.ehb.demo.Controllers;

import be.ehb.demo.model.User;
import be.ehb.demo.model.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
public class IndexController {

    @Autowired
    UserDAO dao;

    @ModelAttribute(value = "alleUsers")
    public Iterable<User>getAllUsers(){
        return dao.findAll();
    }

    @ModelAttribute(value = "nUser")
    public User commentToSave(){
        return new User();
    }

    @RequestMapping(value = {"","/","/index"},method = RequestMethod.GET)
    public String showIndex(ModelMap map){
        return "index";
    }


    @RequestMapping(value ={"","/","/index"} ,method = RequestMethod.POST)
    public String saveComment(@ModelAttribute("nUser") @Valid User nComment, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return "index";
        dao.save(nComment);
        return "redirect:/index";
    }
    @RequestMapping(value = "/delete/{id}",method = RequestMethod.GET)
    public String deleteSnack(@PathVariable(value = "id") int id){
        dao.deleteById(id);
        return "redirect:/index";
    }
}