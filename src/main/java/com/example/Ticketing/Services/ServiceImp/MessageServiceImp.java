package com.example.Ticketing.Services.ServiceImp;

import com.example.Ticketing.Config.MailSender.EmailService;
import com.example.Ticketing.Config.SequenceGeneratorService;
import com.example.Ticketing.Exceptions.EntityNotFoundException;
import com.example.Ticketing.Exceptions.ErrorCodes;
import com.example.Ticketing.Exceptions.InvalidEntityException;
import com.example.Ticketing.Models.Message;
import com.example.Ticketing.Repository.MessageRepository;
import com.example.Ticketing.Services.MessageService;
import com.example.Ticketing.Validators.MessageValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class MessageServiceImp implements MessageService {

    @Autowired
    MessageRepository messageRepository;

    @Autowired
    SequenceGeneratorService service;

    @Autowired
    EmailService emailService;

    @Override
    public Message save(Message message) {

        List<String> errors= MessageValidator.validator(message);

        if(!errors.isEmpty()){
            throw new InvalidEntityException("Please fill all required fields",
                    (Throwable)errors,
                    ErrorCodes.MESSAGE_NOT_VALID);
        }

        message.setCostumeid(service.generateSequence(message.SEQUENCE_NAME));

        //TODO : Check if the email sender actually works

        /* Project project= message.getTicket().getProject();
        if (message.getUser().getUsername() == project.getCse().getUsername()){
            emailService.sendEmail(project.getClient().getEmail(),
                    message.getDescription(),
                    message.getTicket().getTitle(),
                    project.getCse().getEmail());
        }
        */

        return messageRepository.save(message);
    }

    @Override
    public void delete(Long id) {
        if(!messageRepository.existsByCostumeid(id)){
            throw new EntityNotFoundException("This id doesn't belong to any message", ErrorCodes.MESSAGE_NOT_FOUND);
        }
        messageRepository.deleteByCostumeid(id);
    }

    @Override
    public Optional<Message> findById(Long id) {
        Optional<Message> message = messageRepository.findByCostumeid(id);
        return Optional.of(message).orElseThrow(()-> new EntityNotFoundException
                ("No Message Found with the ID"+ id,ErrorCodes.MESSAGE_NOT_FOUND));

    }

    @Override
    public List<Message> findAll() {
        return messageRepository.findAll();
    }

}
