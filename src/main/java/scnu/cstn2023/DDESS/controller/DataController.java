package scnu.cstn2023.DDESS.controller;

import scnu.cstn2023.DDESS.model.Data;
import scnu.cstn2023.DDESS.model.User;
import scnu.cstn2023.DDESS.service.DataService;
import scnu.cstn2023.DDESS.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/data")
public class DataController {
    @Autowired
    private DataService dataService;

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public Data createData(@RequestBody String dataContent, @AuthenticationPrincipal UserDetails userDetails) {
        User user = userService.getUserByUsername(userDetails.getUsername());
        return dataService.createData(dataContent, user);
    }

    @GetMapping
    public List<Data> getUserData(@AuthenticationPrincipal UserDetails userDetails) {
        User user = userService.getUserByUsername(userDetails.getUsername());
        return dataService.getUserData(user);
    }

    @GetMapping("/{id}")
    public Data getDataById(@PathVariable Long id, @AuthenticationPrincipal UserDetails userDetails) {
        User user = userService.getUserByUsername(userDetails.getUsername());
        return dataService.getDataByIdAndUser(id, user).orElseThrow(() -> new RuntimeException("Data not found"));
    }

    @PutMapping("/update/{id}")
    public Data updateData(@PathVariable Long id, @RequestBody String newDataContent,
            @AuthenticationPrincipal UserDetails userDetails) {
        User user = userService.getUserByUsername(userDetails.getUsername());
        return dataService.updateData(id, newDataContent, user);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteData(@PathVariable Long id, @AuthenticationPrincipal UserDetails userDetails) {
        User user = userService.getUserByUsername(userDetails.getUsername());
        dataService.deleteData(id, user);
        return "Data deleted successfully";
    }
}