package com.project.mailscheduler.controller;


import com.project.mailscheduler.dto.ScheduledMailCreateRequestDto;
import com.project.mailscheduler.dto.ScheduledMailGetResponseDto;
import com.project.mailscheduler.dto.ScheduledMailUpdateRequestDto;
import com.project.mailscheduler.service.ScheduledMailService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/scheduled-mails")
@Tag(name = "Scheduler API's")
public class ScheduledJobController {

    @Autowired
    private ScheduledMailService scheduledMailService;


//    Logger log = (Logger) LoggerFactory.getLogger(ScheduledJobController.class);

    @GetMapping("/all")
    @Operation(summary = "get all scheduled mails")
    public ResponseEntity<List<ScheduledMailGetResponseDto>> getAllScheduledMails(){
        List<ScheduledMailGetResponseDto> allMails = scheduledMailService.getAllScheduledMails();
        return ResponseEntity.status(HttpStatus.OK).body(allMails);
    }

    @GetMapping("/{id}")
    @Operation(summary = "get a schedule mail with specific id")
    public ResponseEntity<ScheduledMailGetResponseDto>  getScheduledMail(@PathVariable(name = "id") Long scheduleId ){
        ScheduledMailGetResponseDto responseDto = scheduledMailService.getScheduledMailById(scheduleId);
        return ResponseEntity.ok(responseDto);

    }

    //create a new schedule controller
    //create new service, create a request dto, response dto already in place
    // write a basic exception if error in creation
    @PostMapping(value = "/newschedule")
    @Operation(summary = "create a new schedule")
    public ResponseEntity<ScheduledMailGetResponseDto> createSchedule(@Valid @RequestBody ScheduledMailCreateRequestDto newscheduledmail){
        return ResponseEntity.ok(scheduledMailService.createSchedule(newscheduledmail));
    }

    @DeleteMapping("/deleteschedule/{id}")
    @Operation(summary = "delete a schedule")
    public ResponseEntity<String> deleteSchedule(@PathVariable(name="id") Long scheduleId){
        return ResponseEntity.status(HttpStatus.OK).body(scheduledMailService.deleteScheduledMail(scheduleId));
    }

    @Operation(summary = "update a schedule")
    @PutMapping(value = "/updateschedule/{id}",consumes = {"application/json"})
    public ResponseEntity<ScheduledMailGetResponseDto> updateSchedule(@Valid @RequestBody ScheduledMailUpdateRequestDto updateScheduledMail,
                                                                      @PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(scheduledMailService.updateScheduledMail(id, updateScheduledMail));
    }
}
