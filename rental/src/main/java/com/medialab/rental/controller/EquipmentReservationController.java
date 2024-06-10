package com.medialab.rental.controller;

import com.medialab.rental.Item;
import com.medialab.rental.User;
import com.medialab.rental.service.EquipmentReservationService;
import com.medialab.rental.service.ItemService;
import com.medialab.rental.service.SessionService;
import com.medialab.rental.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/reservation")
public class EquipmentReservationController {

}
