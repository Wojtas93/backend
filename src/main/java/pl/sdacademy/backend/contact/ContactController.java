package pl.sdacademy.backend.contact;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.sdacademy.backend.Errors.ResponseMessage;
import pl.sdacademy.backend.user.UserController;
import pl.sdacademy.backend.user.UserRepository;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/contact")
@ResponseStatus(HttpStatus.OK)
@Valid
public class ContactController {

    private ContactRepository contactRepository;
    private UserRepository userRepository;

    public ContactController(ContactRepository contactRepository, UserRepository userRepository) {
        this.contactRepository = contactRepository;
        this.userRepository = userRepository;
    }

    @GetMapping
    public List<Contact> get() {
        return contactRepository.findAll();
    }

    @GetMapping("/{id}")
    public Contact get(@PathVariable Long id) {
        return contactRepository.getOne(id);
    }


    @PostMapping("/post")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseMessage create(@RequestBody Contact contact) {
        userRepository.findByEmail(contact.getEmail()).ifPresent(contact::setUser);
        contactRepository.save(contact);
        return new ResponseMessage("Contact created");
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseMessage update(@RequestBody Contact contactNew, @PathVariable long id) {
        ResponseMessage responseMessage = new ResponseMessage();
        contactRepository.findById(id).ifPresentOrElse(contact -> {
            contact.setEmail(contactNew.getEmail());
            if (contactNew.getUser() != null) {
                contact.setUser(contact.getUser());
            }
            contact.setContactMessage(contact.getContactMessage());
            responseMessage.setString("contact updated");
        }, () -> {
            contactRepository.save(contactNew);
            responseMessage.setString("couldn't find this contact, contact created");
        });
        return responseMessage;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseMessage delete(@PathVariable long id) throws NoSuchContactException {
        contactRepository.delete(contactRepository.findById(id).get());
        return new ResponseMessage("room deleted");

    }
}
