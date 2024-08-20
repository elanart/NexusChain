package com.nxc.controllers;

import com.nxc.components.JwtService;
import com.nxc.dto.account.request.AccountRequestDTO;
import com.nxc.dto.account.response.JwtResponse;
import com.nxc.dto.user.request.UserRequestDTO;
import com.nxc.dto.user.response.UserResponseDTO;
import com.nxc.pojo.Account;
import com.nxc.pojo.User;
import com.nxc.service.AccountService;
import com.nxc.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/user")
@CrossOrigin
@RequiredArgsConstructor
public class ApiUserController {
    private final UserService userService;
    private final AccountService accountService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@ModelAttribute @Valid UserRequestDTO userRequestDTO,
                                          BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<String> errors = bindingResult.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.toList());

            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }

        UserResponseDTO responseDTO = this.userService.registerUser(userRequestDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    @PatchMapping("/update")
    public ResponseEntity<String> updateUser(@ModelAttribute @Valid UserRequestDTO userRequestDTO,
                                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>("Co loi xay ra!!!", HttpStatus.BAD_REQUEST);
        }
        this.userService.updateUser(userRequestDTO);
        return new ResponseEntity<>("Cap nhat thanh cong!", HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUserDetails(@PathVariable Long id) {
        UserResponseDTO responseDTO = this.userService.getUserDetails(id);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @GetMapping("/current-user")
    public ResponseEntity<UserResponseDTO> getCurrentUserDetails(Principal principal) {
        String username = principal.getName();
        Account account = this.accountService.findByUsername(username);

        UserResponseDTO responseDTO = this.userService.getUserDetails(account.getId());
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @DeleteMapping("/request-delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void requestDeleteUser(@PathVariable Long id) {
        this.userService.requestDeleteUser(id);
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody AccountRequestDTO accountRequestDTO) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(accountRequestDTO.getUsername(), accountRequestDTO.getPassword())
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);
            
            Account account = this.accountService.findByUsername(accountRequestDTO.getUsername());
            User user = account.getUser();
            
            if(!user.getIsConfirm()) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Tai khoan chua duoc xac nhan!");
            }
            
            String jwt = jwtService.generateTokenLogin(accountRequestDTO.getUsername());
            return ResponseEntity.ok(new JwtResponse(jwt));
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Tai khoan hoac mat khau khong đung!");
        }
    }
}
