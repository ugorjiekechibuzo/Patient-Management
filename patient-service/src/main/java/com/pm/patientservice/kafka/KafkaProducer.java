package com.pm.patientservice.kafka;

import com.pm.patientservice.model.Patient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import patient.events.PatientEvent;

@Service
public class KafkaProducer {

    private static final Logger log = LoggerFactory.getLogger(KafkaProducer.class);
    //How we define our messages, and we use the kafkatemplate to send the messages
    private final KafkaTemplate<String, byte[]> kafkaTemplate;

    //Here sping is going to autowire the kafka template implementation and assign it to our instance variable
    public KafkaProducer(KafkaTemplate<String, byte[]> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendEvent(Patient patient){
        PatientEvent event = PatientEvent.newBuilder().setPatientId(patient.getId().toString())
                .setName(patient.getName()).setEmail(patient.getEmail())
                .setEventType("PATIENT_CREATED").build();

        try{

            kafkaTemplate.send("patient", event.toByteArray());

        }catch(Exception e){
            log.error("Error sending PatientCreated event: {}", event);
        }

    }
}
