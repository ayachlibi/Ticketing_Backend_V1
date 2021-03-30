package com.example.Ticketing.Service.ServiceImp;

import com.example.Ticketing.Config.SequenceGeneratorService;
import com.example.Ticketing.Exceptions.EntityNotFoundException;
import com.example.Ticketing.Exceptions.ErrorCodes;
import com.example.Ticketing.Exceptions.InvalidEntityException;
import com.example.Ticketing.Models.Message;
import com.example.Ticketing.Repository.MessageRepository;
import com.example.Ticketing.Repository.TicketRepository;
import com.example.Ticketing.Repository.UserRepository;
import com.example.Ticketing.Service.MessageService;
import com.example.Ticketing.Validators.MessageValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service

@Slf4j

public class MessageServiceImp implements MessageService {

    private MessageRepository messageRepository;

    private UserRepository userRepository;

    private TicketRepository ticketRepository;

    private SequenceGeneratorService sequenceGeneratorService;
    @Autowired
    public MessageServiceImp(MessageRepository messageRepository,
                             UserRepository userRepository,
                             TicketRepository ticketRepository,
                             SequenceGeneratorService sequenceGeneratorService){
        this.messageRepository=messageRepository;
        this.ticketRepository=ticketRepository;
        this.userRepository=userRepository;
        this.sequenceGeneratorService=sequenceGeneratorService;
    }

    @Override
    public Message addMessage(Message message) {
        List<String> errors= MessageValidator.validator(message);
        if (!errors.isEmpty()){
            log.error("Message is not Valid{}",message);
            throw new InvalidEntityException("This Message is Invalid", ErrorCodes.MESSAGE_NOT_VALID);
        }
        if (!userRepository.existsById(message.getUser().getId())){
            throw new EntityNotFoundException("This User does not Exist",ErrorCodes.USER_NOT_FOUND);
        }
        if (!ticketRepository.existsById(message.getTicket().getId())){
            throw new EntityNotFoundException("This Ticket does not Exist",ErrorCodes.TICKET_NOT_FOUND);
        }

        message.setId(sequenceGeneratorService.generateSequence(message.SEQUENCE_NAME));
        return messageRepository.save(message);
    }

    @Override
    public Message modifyMessage(Message message) {
        if (!messageRepository.existsById(message.getId())){
            throw new EntityNotFoundException("This Message does not Exist",ErrorCodes.MESSAGE_NOT_FOUND);
        }
        return messageRepository.save(message);
    }

    @Override
    public void deleteMessage(Long id) {
        if (id==null){
            log.error("Id is null");

        }
        if (!messageRepository.existsById(id)){
            throw new EntityNotFoundException("This Message does Not Exist",ErrorCodes.MESSAGE_NOT_FOUND);
        }
        messageRepository.deleteById(id);
    }

    @Override
    public List<Message> findAll() {
        return messageRepository.findAll().stream()
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Message> findById(Long id) {
        if (id == null){
            log.error("Message ID is null");
            return null;
        }
        Optional<Message> message= messageRepository.findById(id);
        return Optional.of(message).orElseThrow(()-> new EntityNotFoundException
                ("No Message Found with the ID"+ id,ErrorCodes.MESSAGE_NOT_FOUND));

    }
}
