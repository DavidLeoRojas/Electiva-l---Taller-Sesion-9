package edu.uptc.swii.customerservice.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import edu.uptc.swii.customerservice.model.Customer;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;



@RestController
@CrossOrigin("*")
public class CustomerController {
    private static Map<Integer, Customer> customers = new HashMap<>(); 

    @PostMapping("/addcustomer") 
    public String addCustomer(@RequestBody Customer customer) {
        customers.put(customer.getId(), customer);
        return "";
    }  

    @GetMapping("/allcustomers") 
    public List<Customer> getCustomers() {
        return new ArrayList<Customer>(customers.values());
    } 

    @GetMapping("/findcustomer/{id}") 
    public String findCustomer(@PathVariable Integer id){
        return customers.get(id).getFirstName()+" "+customers.get(id).getLastName()+" "+customers.get(id).getAddress();
    }

    @GetMapping("/deletecustomer/{id}")
    public String deleteCustomer(@PathVariable Integer id) {
        String result = new String();
        if (customers.get(id)!=null){
            result="Customer "+customers.get(id).getFirstName()+" "+customers.get(id).getLastName()+" removed!";
            customers.remove(id);
        }else
            result="Customer empty!";
        return new String();
    }
    
}
